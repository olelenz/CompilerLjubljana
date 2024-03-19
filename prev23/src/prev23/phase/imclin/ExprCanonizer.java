package prev23.phase.imclin;

import java.util.*;

import prev23.data.ast.tree.expr.AstAtomExpr;
import prev23.data.lin.LinDataChunk;
import prev23.data.mem.*;
import prev23.data.imc.code.expr.*;
import prev23.data.imc.code.stmt.*;
import prev23.data.imc.visitor.*;

import static prev23.phase.memory.Memory.strings;

/**
 * Expression canonizer.
 */
public class ExprCanonizer implements ImcVisitor<ImcExpr, Vector<ImcStmt>> {
    @Override
    public ImcExpr visit(ImcBINOP binOp, Vector<ImcStmt> visArg) {
        ImcExpr fstExpr = binOp.fstExpr.accept(this, visArg);
        MemTemp temp1 = new MemTemp();
        visArg.add(new ImcMOVE(new ImcTEMP(temp1), fstExpr));
        ImcExpr sndEprx = binOp.sndExpr.accept(this, visArg);
        MemTemp temp2 = new MemTemp();
        visArg.add(new ImcMOVE(new ImcTEMP(temp2), sndEprx));
        MemTemp res = new MemTemp();
        visArg.add(new ImcMOVE(new ImcTEMP(res), new ImcBINOP(binOp.oper, new ImcTEMP(temp1), new ImcTEMP(temp2))));
        return new ImcTEMP(res);
    }

    @Override
    public ImcExpr visit(ImcCALL call, Vector<ImcStmt> visArg) {
        Vector<ImcExpr> callArgs = new Vector<>();
        for (ImcExpr arg : call.args) {
            ImcExpr callArg = arg.accept(this, visArg);
            MemTemp temp = new MemTemp();
            visArg.add(new ImcMOVE(new ImcTEMP(temp), callArg));  // add to vector in function
            callArgs.add(new ImcTEMP(temp));
        }
        MemTemp temp = new MemTemp();
        visArg.add(new ImcMOVE(new ImcTEMP(temp), new ImcCALL(call.label, call.offs, callArgs)));  // add to vector in function
        return new ImcTEMP(temp);
    }

    @Override
    public ImcExpr visit(ImcCONST constant, Vector<ImcStmt> visArg) {
        return new ImcCONST(constant.value);
    }

    @Override
    public ImcExpr visit(ImcMEM mem, Vector<ImcStmt> visArg) {  
        ImcExpr addr = mem.addr.accept(this, visArg);
        MemTemp addrT = new MemTemp();
        visArg.add(new ImcMOVE(new ImcTEMP(addrT), new ImcMEM(addr)));
        return new ImcTEMP(addrT);
    }

    @Override
    public ImcExpr visit(ImcNAME name, Vector<ImcStmt> visArg) {
        return new ImcNAME(name.label);
    }

    @Override
    public ImcExpr visit(ImcTEMP temp, Vector<ImcStmt> visArg) {
        ImcTEMP newTemp = new ImcTEMP(temp.temp);
        return newTemp;
    }

    @Override
    public ImcExpr visit(ImcUNOP unOp, Vector<ImcStmt> visArg) {
        ImcExpr expr = unOp.subExpr.accept(this, visArg);
        return new ImcUNOP(unOp.oper, expr);
    }
}
