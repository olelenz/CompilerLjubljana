package prev23.phase.imclin;

import java.util.*;

import prev23.data.ast.tree.AstTree;
import prev23.data.ast.tree.AstTrees;
import prev23.data.ast.tree.decl.*;
import prev23.data.ast.tree.expr.*;
import prev23.data.ast.tree.stmt.*;
import prev23.data.ast.visitor.*;
import prev23.data.imc.code.expr.ImcCONST;
import prev23.data.imc.code.expr.ImcExpr;
import prev23.data.imc.code.expr.ImcTEMP;
import prev23.data.mem.*;
import prev23.data.imc.code.stmt.*;
import prev23.data.lin.*;

import static prev23.phase.imcgen.ImcGen.stmtImc;
import static prev23.phase.imclin.ImcLin.*;
import static prev23.phase.memory.Memory.*;

public class ChunkGenerator extends AstFullVisitor<Object, Object> {
    @Override
    public Object visit(AstAtomExpr atomExpr, Object o) {  
        if (atomExpr.type == AstAtomExpr.Type.STR) {
            System.out.println("tadaaa");
            MemAbsAccess memAbsAccess = strings.get(atomExpr);
            ImcLin.addDataChunk(new LinDataChunk(memAbsAccess));
        }
        return null;
    }

    public void checkNewFun(AstTree treeInput) {
        if (treeInput instanceof AstDeclStmt decl) {
            for (AstTrees tree : decl.decls) {
                if (tree.get(0) instanceof AstFunDecl) {
                    System.out.println("found another function");
                    tree.get(0).accept(this, null);
                }
            }
        }
    }

    public void iterateStmts(AstTrees<AstStmt> stmts){
        for(AstStmt stmt : stmts){
            if(stmt instanceof AstFunDecl funDecl){
                funDecl.accept(this, null);
            }else if(stmt instanceof AstIfStmt ifStmt){
                if(ifStmt.thenStmt instanceof AstStmts){
                    iterateStmts(((AstStmts) ifStmt.thenStmt).stmts);
                }
                if(ifStmt.elseStmt instanceof AstStmts){
                    iterateStmts(((AstStmts) ifStmt.elseStmt).stmts);
                }
            }else if(stmt instanceof AstWhileStmt whileStmt){
                if(whileStmt.bodyStmt instanceof AstStmts){
                    iterateStmts(((AstStmts) whileStmt.bodyStmt).stmts);
                }
            }else if(stmt instanceof AstDeclStmt declStmt){
                iterateDecls(declStmt.decls);

                if(declStmt.stmt instanceof AstStmts){
                    iterateStmts(((AstStmts) declStmt.stmt).stmts);
                }
            }
        }
    }

    public Object iterateDecls(AstTrees<AstTrees<AstDecl>> decls){
        for (AstTrees<AstDecl> decl:decls) {
            for (AstDecl declIn:decl) {
                if (declIn instanceof AstFunDecl){
                    declIn.accept(this, null);
                }
            }
        }

        return null;
    }

    @Override
    public Object visit(AstFunDecl funDecl, Object o) {  
        if (funDecl == null) return null;
        Vector<ImcStmt> stmtVector = new Vector<>();
        MemFrame frame = frames.get(funDecl);
        MemLabel entryLabel = new MemLabel();
        MemLabel exitLabel = new MemLabel();

        stmtVector.add(new ImcLABEL(entryLabel));  // at start of function

        if (funDecl.stmt != null) {
            if (funDecl.stmt instanceof AstDeclStmt declStmt){
                iterateDecls(declStmt.decls);
            }
            ImcStmt imcStmt = stmtImc.get(funDecl.stmt);
            if (imcStmt instanceof ImcSTMTS imcStmts) {
                if (funDecl.stmt instanceof AstDeclStmt declStmt){
                    iterateDecls(declStmt.decls);

                    if (declStmt.stmt instanceof AstStmts){
                        iterateStmts(((AstStmts) declStmt.stmt).stmts);
                    }
                } else {
                    iterateStmts(((AstStmts) funDecl.stmt).stmts);
                }

                Vector<ImcStmt> stmts = imcStmts.stmts;

                ImcStmt lastStmt = imcStmts;

                while (lastStmt instanceof ImcSTMTS) {
                    stmts = ((ImcSTMTS) lastStmt).stmts;
                    for (int i = 0; i < stmts.size() - 1; i++) {
                        stmtVector.addAll(stmts.get(i).accept(new StmtCanonizer(), stmtVector));
                    }
                    lastStmt = stmts.get(stmts.size() - 1);
                }  // when going out, last statement is no longer of type stmts

                lastStmt.accept(new StmtCanonizer(), stmtVector);


                if (lastStmt instanceof ImcMOVE lastStmtExpr) {  
                    ImcExpr mem = lastStmtExpr.src.accept(new ExprCanonizer(), stmtVector);
                    stmtVector.add(new ImcMOVE(new ImcTEMP(frame.RV), mem));
                }

            } else if (imcStmt instanceof ImcESTMT imcStmtExpr) {

                stmtVector.add(new ImcMOVE(new ImcTEMP(frame.RV), imcStmtExpr.expr.accept(new ExprCanonizer(), stmtVector)));
            } else if (imcStmt instanceof ImcMOVE imcStmtMove) {
                stmtVector.add(new ImcMOVE(new ImcTEMP(frame.RV), imcStmtMove.src.accept(new ExprCanonizer(), stmtVector)));
            } 
        } else {  // no function body 
            stmtVector.add(new ImcMOVE(new ImcTEMP(frame.RV), new ImcCONST(0)));
        }

        stmtVector.add(new ImcJUMP(exitLabel));  // at end of function

        // building chunk
        LinCodeChunk linCodeChunk = new LinCodeChunk(frame, removeCJump(stmtVector), entryLabel, exitLabel);
        addCodeChunk(linCodeChunk);
        return null;
    }

    @Override
    public Object visit(AstVarDecl varDecl, Object o) {  
        MemAccess access = accesses.get(varDecl);
        if (access instanceof MemAbsAccess absAccess) {
            ImcLin.addDataChunk(new LinDataChunk(absAccess));
        }
        return null;
    }

    // function to remove negative condition in cjump from statements in function
    public Vector<ImcStmt> removeCJump(Vector<ImcStmt> statements) {
        Vector<ImcStmt> outStmts = new Vector<>();
        for (int i = 0; i < statements.size(); i++) {
            if (statements.get(i) instanceof ImcCJUMP cjumpStmt) {
                MemLabel newNegLabel = new MemLabel();
                ImcCJUMP newCJump = new ImcCJUMP(cjumpStmt.cond, cjumpStmt.posLabel, newNegLabel);
                outStmts.add(newCJump);
                outStmts.add(new ImcLABEL(newNegLabel));
                outStmts.add(new ImcJUMP(cjumpStmt.negLabel));
            } else {
                outStmts.add(statements.get(i));
            }
        }
        return outStmts;
    }
}
