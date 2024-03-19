package prev23.phase.memory;

import prev23.common.logger.*;
import prev23.data.ast.tree.AstTree;
import prev23.data.ast.tree.AstTrees;
import prev23.data.ast.tree.decl.*;
import prev23.data.ast.tree.expr.AstAtomExpr;
import prev23.data.ast.tree.expr.AstExpr;
import prev23.data.ast.tree.stmt.AstStmt;
import prev23.data.ast.visitor.*;
import prev23.data.mem.*;
import prev23.data.typ.*;

import prev23.common.report.Report;
import prev23.data.ast.tree.*;
import prev23.data.ast.tree.decl.*;
import prev23.data.ast.tree.expr.*;
import prev23.data.ast.tree.stmt.*;
import prev23.data.ast.tree.type.*;
import prev23.data.ast.visitor.*;
import prev23.phase.seman.SemAn;
import prev23.phase.seman.SymbTable;

import java.util.HashMap;

import static prev23.phase.memory.Memory.accesses;
import static prev23.phase.memory.Memory.strings;
import static prev23.phase.seman.SemAn.isType;
import static prev23.phase.seman.SemAn.ofType;

public class MemEvaluator extends AstNullVisitor<Object, MemEvaluator.Frame> {

    public MemEvaluator() {}

    abstract class Frame{}

    HashMap<String, Integer> varDeclsCounter = new HashMap<>();

    private class StackFrame extends Frame{
        int depth = 0;
        long locsSize = 0;
        long argsSize = 0;
        long paramsSize = new SemPtr(new SemVoid()).size();  // initial size for space for static link
    }

    private class RecordFrame extends Frame{
        long cmpSize;
    }

    @Override
    public Object visit(AstTrees<? extends AstTree> trees, Frame arg) {
        if (trees == null) return null;  // empty parameters
        if (trees.title.equals("Declarations")) {
            for (AstTree t : trees) {
                if (t == null) continue;
                this.visit((AstTrees<? extends AstTree>) t, arg);
            }
            return arg;
        }
        if (trees.title.equals("Var-Declaration")) {
            for (AstTree t : trees) {
                if (t == null) continue;
                this.visit((AstVarDecl) t, arg);
            }
            return arg;
        }
        if (trees.title.equals("Typ-Declaration")) {
            for (AstTree t : trees) {
                if (t == null) continue;
                this.visit((AstTypDecl) t, arg);
            }
            return arg;
        }
        if (trees.title.equals("Fun-Declaration")) {
            for (AstTree t : trees) {
                if (t == null) continue;
                this.visit((AstFunDecl) t, arg);
            }
            return arg;
        }
        if (trees.title.equals("Par-Declarations")) {
            for (AstTree t : trees) {
                if (t == null) continue;
                this.visit((AstParDecl) t, arg);
            }
            return arg;
        }
        if (trees.title.equals("Par-Declarations-Call")) {
            for (AstTree t : trees) {
                if (t == null) continue;
                this.visit((AstExpr) t, arg);
            }
            return arg;
        }
        if (trees.title.equals("Rec-Declaration")) {
            RecordFrame recordFrame = new RecordFrame();
            for (AstTree t : trees) {
                if (t == null) continue;
                this.visit((AstCmpDecl) t, recordFrame);
            }
            return arg;
        }
        if (trees.title.equals("statements")) {
            for (AstTree t : trees) {
                if (t == null) return null;
                this.visit((AstStmt) t, arg);
            }
            return arg;
        }
        return arg;
    }

    // DECLARATIONS

    @Override
    public Object visit(AstCmpDecl cmpDecl, Frame arg) {
        if (cmpDecl.type instanceof AstRecType){
            ((AstRecType) cmpDecl.type).comps.accept(this, arg);
        }
        RecordFrame recordFrame = (RecordFrame) arg;
        long size = isType.get(cmpDecl.type).actualType().size();
        long offset = recordFrame.cmpSize;
        int depth = 0;
        MemRelAccess memRelAccess = new MemRelAccess(size, offset, depth);
        accesses.put(cmpDecl, memRelAccess);
        recordFrame.cmpSize += size;
        return null;
    }

    @Override
    public Object visit(AstFunDecl funDecl, Frame oldFrame) {
        StackFrame newFrame = new StackFrame();
        MemLabel memLabel;

        if (oldFrame != null){
            newFrame.depth = ((StackFrame)oldFrame).depth + 1;
            memLabel = new MemLabel();
        } else {
            memLabel = new MemLabel(funDecl.name);
        }

        if (funDecl.pars != null) visit(funDecl.pars, newFrame);
        if (funDecl.type != null) visit(funDecl.type, newFrame);
        if (funDecl.stmt != null) visit(funDecl.stmt, newFrame);

        MemFrame frame = new MemFrame(memLabel, newFrame.depth, newFrame.locsSize, newFrame.argsSize);
        Memory.frames.put(funDecl, frame);
        return null;
    }

    @Override
    public Object visit(AstParDecl parDecl, Frame arg) {
        if (parDecl.type != null) visit(parDecl.type, arg);
        assert parDecl.type != null;
        long size = isType.get(parDecl.type).actualType().size();
        MemAccess memAccess;
        StackFrame stackFrame = (StackFrame) arg;
        memAccess = new MemRelAccess(size, stackFrame.paramsSize, stackFrame.depth);

        stackFrame.paramsSize += size;
        accesses.put(parDecl, memAccess);
        return null;
    }

    @Override
    public Object visit(AstTypDecl acceptor, Frame arg) {
        if (acceptor!=null && acceptor.type != null)visit(acceptor.type, arg);
        return null;
    }

    @Override
    public Object visit(AstVarDecl varDecl, Frame arg) {
        if (varDecl == null) return null;
        if (varDecl.type != null) visit(varDecl.type, arg);

        boolean varUseBefore = false;

        if (varDeclsCounter.get(varDecl.name) == null){
           varDeclsCounter.put(varDecl.name, 1);
        } else if (varDeclsCounter.get(varDecl.name) > 0){
            varUseBefore = true;
        }

        assert varDecl.type != null;
        SemType acceptorType = isType.get(varDecl.type);
        long size = acceptorType.size();
        MemAccess access;
        if(arg == null && !varUseBefore) {  // not in function - top level
            access = new MemAbsAccess(size, new MemLabel(varDecl.name));
            accesses.put(varDecl, access);
        } else if (varUseBefore && arg == null){
            //System.out.println("var declared again outside function");
            access = new MemRelAccess(size, 0, 0);  
            accesses.put(varDecl, access);
        } else{
            //System.out.println("relative access");
            StackFrame stackFrame = (StackFrame) arg;
            access = new MemRelAccess(size, -stackFrame.locsSize-size, stackFrame.depth);
            accesses.put(varDecl, access);
            stackFrame.locsSize += size;
        }
        return null;
    }

    // EXPRESSIONS

    // EXPRESSIONS

    public Object visit(AstExpr acceptor, Frame arg) {
        if (acceptor == null) return null;
        if (acceptor instanceof AstArrExpr) {
            visit((AstArrExpr) acceptor, arg);
        } else if (acceptor instanceof AstAtomExpr) {
            visit((AstAtomExpr) acceptor, arg);
        } else if (acceptor instanceof AstBinExpr) {
            visit((AstBinExpr) acceptor, arg);
        } else if (acceptor instanceof AstCallExpr) {
            visit((AstCallExpr) acceptor, arg);
        } else if (acceptor instanceof AstCastExpr) {
            visit((AstCastExpr) acceptor, arg);
        } else if (acceptor instanceof AstDelExpr) {
            visit((AstDelExpr) acceptor, arg);
        } else if (acceptor instanceof AstNameExpr) {
            visit((AstNameExpr) acceptor, arg);
        } else if (acceptor instanceof AstNewExpr) {
            visit((AstNewExpr) acceptor, arg);
        } else if (acceptor instanceof AstPfxExpr) {
            visit((AstPfxExpr) acceptor, arg);
        } else if (acceptor instanceof AstRecExpr) {
            visit((AstRecExpr) acceptor, arg);
        } else if (acceptor instanceof AstSfxExpr) {
            visit((AstSfxExpr) acceptor, arg);
        }
        return null;
    }

    @Override
    public Object visit(AstArrExpr acceptor, Frame arg) {
        if (acceptor == null) return null;
        if (acceptor.arr != null) visit(acceptor.arr, arg);
        if (acceptor.idx != null) visit(acceptor.idx, arg);
        return null;
    }

    @Override
    public Object visit(AstAtomExpr atomExpr, Frame arg) {
        switch (atomExpr.type) {
            case STR: {
                String initialValue = atomExpr.value.substring(1, atomExpr.value.length()-1);
                long size = (new SemChar()).size() * (initialValue.length()+1);
                MemAbsAccess memAbsAccess = new MemAbsAccess(size, new MemLabel(), initialValue);
                strings.put(atomExpr, memAbsAccess);
            }break;
            default:
                break;
        }
        return null;
    }

    @Override
    public Object visit(AstBinExpr acceptor, Frame arg) {
        if (acceptor == null) return null;
        if (acceptor.fstExpr != null) visit(acceptor.fstExpr, arg);
        if (acceptor.sndExpr != null) visit(acceptor.sndExpr, arg);
        return null;
    }

    @Override
    public Object visit(AstCallExpr acceptor, Frame arg) {
        if (acceptor == null) return null;
        visit(acceptor.args, arg);

        StackFrame stackFrame = ((StackFrame)arg);
        long size = new SemPtr(new SemVoid()).size();  //SL
        for (AstExpr t : acceptor.args) {
            if (t == null) continue;
            size += ofType.get(t).size();
        }
        stackFrame.argsSize = Math.max(size, stackFrame.argsSize);
        return null;
    }

    @Override
    public Object visit(AstCastExpr acceptor, Frame arg) {
        if (acceptor == null) return null;
        if (acceptor.expr != null) visit(acceptor.expr, arg);
        if (acceptor.type != null) visit(acceptor.type, arg);
        return null;
    }

    @Override
    public Object visit(AstDelExpr acceptor, Frame arg) {
        if (acceptor == null) return null;
        if (acceptor.expr != null) visit(acceptor.expr, arg);
        return null;
    }

    @Override
    public Object visit(AstNameExpr acceptor, Frame arg) {
        return null;
    }

    @Override
    public Object visit(AstNewExpr acceptor, Frame arg) {
        if (acceptor == null) return null;
        if (acceptor.type != null) visit(acceptor.type, arg);
        return null;
    }

    @Override
    public Object visit(AstPfxExpr acceptor, Frame arg) {
        if (acceptor == null) return null;
        if (acceptor.expr != null) visit(acceptor.expr, arg);
        return null;
    }

    @Override
    public Object visit(AstRecExpr acceptor, Frame arg) {
        if (acceptor == null) return null;
        if (acceptor.rec != null) visit(acceptor.rec, arg);
        return null;
    }

    @Override
    public Object visit(AstSfxExpr acceptor, Frame arg) {
        if (acceptor == null) return null;
        if (acceptor.expr != null) visit(acceptor.expr, arg);
        return null;
    }

    // STATEMENTS

    public Object visit(AstStmt acceptor, Frame arg) {
        if (acceptor == null) return null;
        if (acceptor instanceof AstAssignStmt) {
            visit((AstAssignStmt) acceptor, arg);
        } else if (acceptor instanceof AstDeclStmt) {
            visit((AstDeclStmt) acceptor, arg);
        } else if (acceptor instanceof AstExprStmt) {
            visit((AstExprStmt) acceptor, arg);
        } else if (acceptor instanceof AstIfStmt) {
            visit((AstIfStmt) acceptor, arg);
        } else if (acceptor instanceof AstStmts) {
            visit((AstStmts) acceptor, arg);
        } else if (acceptor instanceof AstWhileStmt) {
            visit((AstWhileStmt) acceptor, arg);
        }
        return null;
    }

    @Override
    public Object visit(AstAssignStmt acceptor, Frame arg) {
        if (acceptor == null) return null;
        if (acceptor.dst != null) visit(acceptor.dst, arg);
        if (acceptor.src != null) visit(acceptor.src, arg);
        return null;
    }

    @Override
    public Object visit(AstDeclStmt acceptor, Frame arg) {
        if (acceptor == null) return null;
        if (acceptor.decls != null) visit(acceptor.decls, arg);
        if (acceptor.stmt != null) visit(acceptor.stmt, arg);
        return null;
    }

    @Override
    public Object visit(AstExprStmt acceptor, Frame arg) {
        if (acceptor == null) return null;
        if (acceptor.expr != null) visit(acceptor.expr, arg);
        return null;
    }

    @Override
    public Object visit(AstIfStmt acceptor, Frame arg) {
        if (acceptor == null) return null;
        if (acceptor.cond != null) visit(acceptor.cond, arg);
        if (acceptor.thenStmt != null) visit(acceptor.thenStmt, arg);
        if (acceptor.elseStmt != null) visit(acceptor.elseStmt, arg);
        return null;
    }

    @Override
    public Object visit(AstStmts acceptor, Frame arg) {
        if (acceptor == null) return null;
        if (acceptor.stmts != null) visit(acceptor.stmts, arg);
        return null;
    }

    @Override
    public Object visit(AstWhileStmt acceptor, Frame arg) {
        if (acceptor == null) return null;
        if (acceptor.cond != null) visit(acceptor.cond, arg);
        if (acceptor.bodyStmt != null)  visit(acceptor.bodyStmt, arg);
        return null;
    }




    // TYPES

    public Object visit(AstType acceptor, Frame arg) {
        if (acceptor == null) return null;
        if (acceptor instanceof AstArrType) {
            visit((AstArrType) acceptor, arg);
        } else if (acceptor instanceof AstAtomType) {
            visit((AstAtomType) acceptor, arg);
        } else if (acceptor instanceof AstNameType) {
            visit((AstNameType) acceptor, arg);
        } else if (acceptor instanceof AstPtrType) {
            visit((AstPtrType) acceptor, arg);
        } else if (acceptor instanceof AstRecType) {
            visit((AstRecType) acceptor, arg);
        }
        return null;
    }

    @Override
    public Object visit(AstArrType acceptor, Frame arg) {
        if (acceptor == null) return null;
        if (acceptor.elemType != null) visit(acceptor.elemType, arg);
        if (acceptor.numElems != null) visit(acceptor.numElems, arg);
        return null;
    }

    @Override
    public Object visit(AstAtomType acceptor, Frame arg) {  
        return null;
    }

    @Override
    public Object visit(AstNameType acceptor, Frame arg) {
        return null;
    }

    @Override
    public Object visit(AstPtrType acceptor, Frame arg) {
        if (acceptor == null) return null;
        if (acceptor.baseType != null) visit(acceptor.baseType, arg);
        return null;
    }

    @Override
    public Object visit(AstRecType acceptor, Frame arg) {
        visit(acceptor.comps, arg);
        return null;
    }

}
