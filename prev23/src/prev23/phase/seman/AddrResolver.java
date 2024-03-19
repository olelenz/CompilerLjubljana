package prev23.phase.seman;
import prev23.common.report.Report;
import prev23.data.ast.tree.*;
import prev23.data.ast.tree.decl.*;
import prev23.data.ast.tree.expr.*;
import prev23.data.ast.tree.stmt.*;
import prev23.data.ast.tree.type.*;
import prev23.data.ast.visitor.*;

/**
 * Address resolver. - lvalues
 */


public class AddrResolver extends AstFullVisitor<Object, Object> {

    public AddrResolver() {

    }

    // GENERAL PURPOSE

    @Override
    public Object visit(AstTrees<? extends AstTree> trees, Object arg) {
        if (trees == null) return null;  // empty parameters
        if (trees.title.equals("Declarations")) {
            //symbTable.newScope();
            for (AstTree t : trees) {
                if (t == null) return null;
                this.visit((AstTrees<? extends AstTree>) t, null);
            }
            return null;
        }
        if (trees.title.equals("Var-Declaration")) {
            for (AstTree t : trees) {
                if (t == null) return null;
                this.visit((AstVarDecl) t, null);
            }
            for (AstTree t : trees) {
                if (t == null) return null;
                this.visit((AstVarDecl) t, null);
            }
            return null;
        }
        if (trees.title.equals("Typ-Declaration")) {
            for (AstTree t : trees) {
                if (t == null) return null;
                this.visit((AstTypDecl) t, null);
            }
            for (AstTree t : trees) {
                if (t == null) return null;
                this.visit((AstTypDecl) t, null);
            }
            return null;
        }
        if (trees.title.equals("Fun-Declaration")) {
            for (AstTree t : trees) {
                if (t == null) return null;
                this.visit((AstFunDecl) t, null);
            }
            for (AstTree t : trees) {
                if (t == null) return null;
                this.visit((AstFunDecl) t, null);
            }
            return null;
        }
        if (trees.title.equals("Par-Declarations")) {
            for (AstTree t : trees) {
                if (t == null) return null;
                this.visit((AstParDecl) t, null);
            }
            for (AstTree t : trees) {
                if (t == null) return null;
                this.visit((AstParDecl) t, null);
            }
            return null;
        }
        if (trees.title.equals("Par-Declarations-Call")) {
            for (AstTree t : trees) {
                if (t == null) return null;
                this.visit((AstExpr) t, null);
            }
            return null;
        }
        if (trees.title.equals("statements")) {
            for (AstTree t : trees) {
                if (t == null) return null;
                this.visit((AstStmt) t, null);
            }
            return null;
        }
        // TODO: component declaration NEXT WEEK
        return null;
    }

    // DECLARATIONS

    @Override
    public Object visit(AstCmpDecl acceptor, Object arg) {  
        return null;
    }

    @Override
    public Object visit(AstFunDecl acceptor, Object arg) {
        return null;
    }

    @Override
    public Object visit(AstParDecl acceptor, Object arg) {
        return null;
    }

    @Override
    public Object visit(AstTypDecl acceptor, Object arg) {
        return null;
    }

    @Override
    public Object visit(AstVarDecl acceptor, Object arg) {
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
        if (acceptor.arr == null) return null;
        visit(acceptor.arr, arg);
        if (acceptor.idx == null) return null;
        visit(acceptor.idx, arg);
        return null;
    }

    @Override
    public Object visit(AstAtomExpr acceptor, Object arg) {  
        return null;
    }

    @Override
    public Object visit(AstBinExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.fstExpr == null) return null;
        visit(acceptor.fstExpr, arg);
        if (acceptor.sndExpr == null) return null;
        visit(acceptor.sndExpr, arg);
        return null;
    }

    @Override
    public Object visit(AstCallExpr acceptor, Object arg) {
        return null;
    }

    @Override
    public Object visit(AstCastExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.expr == null) return null;
        visit(acceptor.expr, arg);
        if (acceptor.type == null) return null;
        visit(acceptor.type, arg);
        return null;
    }

    @Override
    public Object visit(AstDelExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.expr == null) return null;
        visit(acceptor.expr, arg);
        return null;
    }

    @Override
    public Object visit(AstNameExpr acceptor, Object arg) {
        return null;
    }

    @Override
    public Object visit(AstNewExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.type == null) return null;
        visit(acceptor.type, arg);
        return null;
    }

    @Override
    public Object visit(AstPfxExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.expr == null) return null;
        visit(acceptor.expr, arg);
        return null;
    }

    @Override
    public Object visit(AstRecExpr acceptor, Object arg) {  
        if (acceptor == null) return null;
        if (acceptor.rec == null) return null;
        visit(acceptor.rec, arg);
        return null;
    }

    @Override
    public Object visit(AstSfxExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.expr == null) return null;
        visit(acceptor.expr, arg);
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
        if (acceptor.dst == null) return null;
        visit(acceptor.dst, arg);
        if (acceptor.src == null) return null;
        visit(acceptor.src, arg);
        return null;
    }

    @Override
    public Object visit(AstDeclStmt acceptor, Object arg) {
        return null;
    }

    @Override
    public Object visit(AstExprStmt acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.expr == null) return null;
        visit(acceptor.expr, arg);
        return null;
    }

    @Override
    public Object visit(AstIfStmt acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.cond == null) return null;
        visit(acceptor.cond, arg);
        if (acceptor.thenStmt == null) return null;
        visit(acceptor.thenStmt, arg);
        if (acceptor.elseStmt == null) return null;
        visit(acceptor.elseStmt, arg);
        return null;
    }

    @Override
    public Object visit(AstStmts acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.stmts == null) return null;
        visit(acceptor.stmts, arg);
        return null;
    }

    @Override
    public Object visit(AstWhileStmt acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.cond == null) return null;
        visit(acceptor.cond, arg);
        if (acceptor.bodyStmt == null) return null;
        visit(acceptor.bodyStmt, arg);
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
        if (acceptor.elemType == null) return null;
        visit(acceptor.elemType, arg);
        if (acceptor.numElems == null) return null;
        visit(acceptor.numElems, arg);
        return null;
    }

    @Override
    public Object visit(AstAtomType acceptor, Object arg) {  
        return null;
    }

    @Override
    public Object visit(AstNameType acceptor, Object arg) {
        return null;
    }

    @Override
    public Object visit(AstPtrType acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.baseType == null) return null;
        visit(acceptor.baseType, arg);
        return null;
    }

    @Override
    public Object visit(AstRecType acceptor, Object arg) {  
        return null;
    }


}

