package prev23.phase.imclin;

import java.util.*;

import prev23.common.report.*;
import prev23.data.mem.*;
import prev23.data.imc.code.expr.*;
import prev23.data.imc.code.stmt.*;
import prev23.data.imc.visitor.*;

import static prev23.phase.memory.Memory.frames;

/**
 * Statement canonizer.
 */
public class StmtCanonizer implements ImcVisitor<Vector<ImcStmt>, Object> {


    @Override
    public Vector<ImcStmt> visit(ImcSTMTS stmts, Object setRV) {
        Vector<ImcStmt> vec = new Vector<>();
        for (ImcStmt stmt:stmts.stmts) {
            vec.addAll(stmt.accept(this, null));
        }
        return vec;
    }

    @Override
    public Vector<ImcStmt> visit(ImcCJUMP cjump, Object visArg) {
        Vector<ImcStmt> vec = new Vector<>();
        ImcExpr cond = cjump.cond.accept(new ExprCanonizer(), vec);
        vec.add(new ImcCJUMP(cond, cjump.posLabel, cjump.negLabel));
        return vec;
    }

    @Override
    public Vector<ImcStmt> visit(ImcESTMT eStmt, Object visArg) {
        if (eStmt.expr instanceof ImcCALL) {
            Vector<ImcStmt> result = new Vector<>();
            ImcCALL call = (ImcCALL) eStmt.expr;
            Vector<ImcExpr> args = new Vector<>();
            for (ImcExpr arg : call.args) {
                ImcExpr canonizedArg = arg.accept(new ExprCanonizer(), result);
                args.add(canonizedArg);
            }
            result.add(new ImcESTMT(new ImcCALL(call.label, call.offs, args)));
            return result;
        }

        Vector<ImcStmt> vec = new Vector<>();
        ImcExpr expr = eStmt.expr.accept(new ExprCanonizer(), vec);
        vec.add(new ImcESTMT(expr));
        return vec;
    }

    @Override
    public Vector<ImcStmt> visit(ImcJUMP jump, Object visArg) {
        Vector<ImcStmt> vec = new Vector<>();
        vec.add(new ImcJUMP(jump.label));
        return vec;
    }

    @Override
    public Vector<ImcStmt> visit(ImcLABEL label, Object visArg) {
        Vector<ImcStmt> vec = new Vector<>();
        vec.add(new ImcLABEL(label.label));
        return vec;
    }

    @Override
    public Vector<ImcStmt> visit(ImcMOVE move, Object visArg) {  
        Vector<ImcStmt> vec = new Vector<>();
        if (move.dst instanceof ImcMEM moveDstMem){
            ImcExpr dstExpr = moveDstMem.addr.accept(new ExprCanonizer(), vec);
            ImcExpr srcExpr = move.src.accept(new ExprCanonizer(), vec);

            MemTemp dstMem = new MemTemp();
            MemTemp srcMem = new MemTemp();

            vec.add(new ImcMOVE(new ImcTEMP(dstMem), dstExpr));
            vec.add(new ImcMOVE(new ImcTEMP(srcMem), srcExpr));

            ImcExpr dst = new ImcMEM(new ImcTEMP(dstMem));
            ImcExpr src = new ImcTEMP(srcMem);
            vec.add(new ImcMOVE(dst, src));
            return vec;
        }
        if (move.dst instanceof ImcTEMP){
            MemTemp srcTemp = new MemTemp();
            ImcExpr srcExpr = move.src.accept(new ExprCanonizer(), vec);
            MemTemp dstTemp = new MemTemp();
            dstTemp = ((ImcTEMP) move.dst).temp;

            vec.add(new ImcMOVE(new ImcTEMP(srcTemp), srcExpr));
            vec.add(new ImcMOVE(new ImcTEMP(dstTemp), new ImcTEMP(srcTemp)));


            return vec;
        }
        return null;
    }
}
