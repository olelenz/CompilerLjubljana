package prev23.phase.seman;

import prev23.common.report.Report;
import prev23.data.ast.tree.*;
import prev23.data.ast.tree.decl.*;
import prev23.data.ast.tree.expr.*;
import prev23.data.ast.tree.stmt.*;
import prev23.data.ast.tree.type.*;
import prev23.data.ast.visitor.*;
import prev23.data.typ.SemName;

/**
 * Name resolver.
 * 
 * Name resolver connects each node of a abstract syntax tree where a name is
 * used with the node where it is declared. The only exceptions are a record
 * field names which are connected with its declarations by type resolver. The
 * results of the name resolver are stored in
 * {@link prev23.phase.seman.SemAn#declaredAt}.
 */
public class NameResolver extends AstFullVisitor<Object, Object> {

    //class variable for the hashtable
    private final SymbTable symbTable = new SymbTable();
    boolean declaring = true;

    public NameResolver() {

    }

    // GENERAL PURPOSE

    @Override
    public Object visit(AstTrees<? extends AstTree> trees, Object arg) {
        if (trees == null) return null;  // empty parameters
        if (trees.title.equals("Declarations")) {
            for (AstTree t : trees) {
                if (t == null) continue;
                this.visit((AstTrees<? extends AstTree>) t, null);
            }
            return null;
        }
        if (trees.title.equals("Var-Declaration")) {
            symbTable.newScope();
            declaring = true;
            for (AstTree t : trees) {
                if (t == null) continue;
                this.visit((AstVarDecl) t, null);
            }
            declaring = false;
            for (AstTree t : trees) {
                if (t == null) continue;
                this.visit((AstVarDecl) t, null);
            }
            return null;
        }
        if (trees.title.equals("Typ-Declaration")) {
            symbTable.newScope();
            declaring = true;
            for (AstTree t : trees) {
                if (t == null) continue;
                this.visit((AstTypDecl) t, null);
            }
            declaring = false;
            for (AstTree t : trees) {
                if (t == null) continue;
                this.visit((AstTypDecl) t, null);
            }
            return null;
        }
        if (trees.title.equals("Fun-Declaration")) {
            declaring = true;
            for (AstTree t : trees) {
                if (t == null) continue;
                this.visit((AstFunDecl) t, null);
            }
            declaring = false;
            for (AstTree t : trees) {
                if (t == null) continue;
                int current_depth = symbTable.currDepth();
                symbTable.newScope();
                this.visit((AstFunDecl) t, null);
                while (symbTable.currDepth() > current_depth){
                    symbTable.oldScope();
                }
            }
            return null;
        }
        if (trees.title.equals("Par-Declarations")) {
            declaring = true;
            for (AstTree t : trees) {
                if (t == null) continue;
                this.visit((AstParDecl) t, null);
            }
            declaring = false;
            for (AstTree t : trees) {
                if (t == null) continue;
                this.visit((AstParDecl) t, null);
            }
            return null;
        }
        if (trees.title.equals("Par-Declarations-Call")) {
            for (AstTree t : trees) {
                if (t == null) continue;
                this.visit((AstExpr) t, null);
            }
            return null;
        }
        if (trees.title.equals("statements")) {
            for (AstTree t : trees) {
                if (t == null) continue;
                this.visit((AstStmt) t, null);
            }
            return null;
        } if (trees.title.equals("Rec-Declaration")) {
            for (AstTree t : trees) {
                if (t == null) continue;
                this.visit((AstCmpDecl) t, null);
            }
            return null;
        }
        return null;
    }

    // DECLARATIONS

    @Override
    public Object visit(AstCmpDecl acceptor, Object arg) {
        visit(acceptor.type, arg);
        return null;
    }

    @Override
    public Object visit(AstFunDecl acceptor, Object arg) {
        if (acceptor == null) return null;
        if (declaring) {
            try {
                if (acceptor.name == null) return null;
                symbTable.ins(acceptor.name, acceptor);
            } catch (SymbTable.CannotInsNameException e) {
                String location_defined = "";
                try {
                    location_defined = symbTable.fnd(acceptor.name).location().toString();
                } catch (SymbTable.CannotFndNameException e2){
                    e2.printStackTrace();
                }
                throw new Report.Error("ERROR: Name \""+ acceptor.name +"\" in current scope already defined at "+location_defined+"!");
            }
        }else {
            if (acceptor.pars != null) {
                visit(acceptor.pars, null);
            }
            if (acceptor.type != null) visit(acceptor.type, null);
            if (acceptor.stmt != null) visit(acceptor.stmt, null);
        }
        return null;
    }

    @Override
    public Object visit(AstParDecl acceptor, Object arg) {
        if (acceptor == null) return null;
        if (declaring) {
            try {
                if (acceptor.name == null) return null;
                symbTable.ins(acceptor.name, acceptor);
            } catch (SymbTable.CannotInsNameException e) {
                String location_defined = "";
                try {
                    location_defined = symbTable.fnd(acceptor.name).location().toString();
                } catch (SymbTable.CannotFndNameException e2) {
                    e2.printStackTrace();
                }
                throw new Report.Error("ERROR: Name \""+ acceptor.name +"\" in current scope already defined at "+location_defined+"!");
            }
        } else {
            if (acceptor.type != null) visit(acceptor.type, arg);
        }
        return null;
    }

    @Override
    public Object visit(AstTypDecl acceptor, Object arg) {
        if (acceptor == null) return null;
        if (declaring) {
            try {
                if (acceptor.name == null) return null;
                symbTable.ins(acceptor.name, acceptor);
            } catch (SymbTable.CannotInsNameException e) {
                String location_defined = "";
                try {
                    location_defined = symbTable.fnd(acceptor.name).location().toString();
                } catch (SymbTable.CannotFndNameException e2){
                    e2.printStackTrace();
                }
                throw new Report.Error("ERROR: Name \""+ acceptor.name +"\" in current scope already defined at "+location_defined+"!");

            }
        }else {
            if (acceptor.type != null) visit(acceptor.type, null);
        }
        return null;
    }

    @Override
    public Object visit(AstVarDecl acceptor, Object arg) {
        if (acceptor == null) return null;
        if (declaring) {
            try {
                if (acceptor.name == null) return null;
                symbTable.ins(acceptor.name, acceptor);
            } catch (SymbTable.CannotInsNameException e) {
                String location_defined = "";
                try {
                    location_defined = symbTable.fnd(acceptor.name).location().toString();
                } catch (SymbTable.CannotFndNameException e2) {
                    e2.printStackTrace();
                }
                throw new Report.Error("ERROR: Name \""+ acceptor.name +"\" in current scope already defined at "+location_defined+"!");
            }
        } else {
            if (acceptor.type != null) visit(acceptor.type, arg);
        }
        return null;
    }

    // EXPRESSIONS

    public Object visit(AstExpr acceptor, Object arg) {
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
    public Object visit(AstArrExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.arr != null) visit(acceptor.arr, arg);
        if (acceptor.idx != null) visit(acceptor.idx, arg);
        return null;
    }

    @Override
    public Object visit(AstAtomExpr acceptor, Object arg) {  
        return null;
    }

    @Override
    public Object visit(AstBinExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.fstExpr != null) visit(acceptor.fstExpr, arg);
        if (acceptor.sndExpr != null) visit(acceptor.sndExpr, arg);
        return null;
    }

    @Override
    public Object visit(AstCallExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        AstNameDecl declaredAt;
        try {
            if (acceptor.name == null) return null;
            declaredAt = symbTable.fnd(acceptor.name);
        } catch (SymbTable.CannotFndNameException e) {
            throw new Report.Error("ERROR: Name \""+ acceptor.name +"\" at "+acceptor.location().toString()+" not defined yet!");
        }
        SemAn.declaredAt.put(acceptor, declaredAt);

        if (acceptor.args != null) visit(acceptor.args, arg);
        return null;
    }

    @Override
    public Object visit(AstCastExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.expr != null) visit(acceptor.expr, arg);
        if (acceptor.type != null) visit(acceptor.type, arg);
        return null;
    }

    @Override
    public Object visit(AstDelExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.expr != null) visit(acceptor.expr, arg);
        return null;
    }

    @Override
    public Object visit(AstNameExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        AstNameDecl declaredAt;
        try {
            if (acceptor.name == null) return null;
            declaredAt = symbTable.fnd(acceptor.name);
        } catch (SymbTable.CannotFndNameException e) {
            throw new Report.Error("ERROR: Name \""+ acceptor.name +"\" at "+acceptor.location().toString()+" not defined yet!");
        }
        SemAn.declaredAt.put(acceptor, declaredAt);
        return null;
    }

    @Override
    public Object visit(AstNewExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.type != null) visit(acceptor.type, arg);
        return null;
    }

    @Override
    public Object visit(AstPfxExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.expr != null) visit(acceptor.expr, arg);
        return null;
    }

    @Override
    public Object visit(AstRecExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.rec != null) visit(acceptor.rec, arg);
        return null;
    }

    @Override
    public Object visit(AstSfxExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.expr != null) visit(acceptor.expr, arg);
        return null;
    }

    // STATEMENTS

    public Object visit(AstStmt acceptor, Object arg) {
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
    public Object visit(AstAssignStmt acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.dst != null) visit(acceptor.dst, arg);
        if (acceptor.src != null) visit(acceptor.src, arg);
        return null;
    }

    @Override
    public Object visit(AstDeclStmt acceptor, Object arg) {
        if (acceptor == null) return null;
        int current_depth = symbTable.currDepth();
        symbTable.newScope();
        if (acceptor.decls != null) visit(acceptor.decls, arg);
        if (acceptor.stmt != null) visit(acceptor.stmt, arg);
        while (symbTable.currDepth() > current_depth){
            symbTable.oldScope();
        }
        return null;
    }

    @Override
    public Object visit(AstExprStmt acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.expr != null) visit(acceptor.expr, arg);
        return null;
    }

    @Override
    public Object visit(AstIfStmt acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.cond != null) visit(acceptor.cond, arg);
        if (acceptor.thenStmt != null) visit(acceptor.thenStmt, arg);
        if (acceptor.elseStmt != null) visit(acceptor.elseStmt, arg);
        return null;
    }

    @Override
    public Object visit(AstStmts acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.stmts != null) visit(acceptor.stmts, arg);
        return null;
    }

    @Override
    public Object visit(AstWhileStmt acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.cond != null) visit(acceptor.cond, arg);
        if (acceptor.bodyStmt != null)  visit(acceptor.bodyStmt, arg);
        return null;
    }




    // TYPES

    public Object visit(AstType acceptor, Object arg) {
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
    public Object visit(AstArrType acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.elemType != null) visit(acceptor.elemType, arg);
        if (acceptor.numElems != null) visit(acceptor.numElems, arg);
        return null;
    }

    @Override
    public Object visit(AstAtomType acceptor, Object arg) {  
        return null;
    }

    @Override
    public Object visit(AstNameType acceptor, Object arg) {
        if (acceptor == null) return null;
        AstNameDecl declaredAt;
        try {
            if (acceptor.name == null) return null;
            declaredAt = symbTable.fnd(acceptor.name);
        } catch (SymbTable.CannotFndNameException e) {
            throw new Report.Error("ERROR: Name \""+ acceptor.name +"\" at "+acceptor.location().toString()+" not defined yet!");
        }
        SemAn.declaredAt.put(acceptor, declaredAt);
        return null;
    }

    @Override
    public Object visit(AstPtrType acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.baseType != null) visit(acceptor.baseType, arg);
        return null;
    }

    @Override
    public Object visit(AstRecType acceptor, Object arg) {
        visit(acceptor.comps, arg);
        return null;
    }


}
