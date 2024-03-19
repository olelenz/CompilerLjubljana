package prev23.phase.asmgen;

import java.util.*;

import prev23.data.imc.code.ImcInstr;
import prev23.data.imc.code.expr.*;
import prev23.data.imc.code.stmt.*;
import prev23.data.imc.visitor.*;
import prev23.data.lin.LinCodeChunk;
import prev23.data.lin.LinDataChunk;
import prev23.data.mem.*;
import prev23.data.asm.*;
import prev23.common.report.*;

/**
 * Machine code generator for statements.
 */
public class StmtGenerator implements ImcVisitor<Vector<AsmInstr>, Object> {

    @Override
    public Vector<AsmInstr> visit(ImcLABEL label, Object visArg) {
        Vector<AsmInstr> out = new Vector<>();
        out.add(new AsmLABEL(label.label));
        return out;
    }

    @Override
    public Vector<AsmInstr> visit(ImcMOVE move, Object visArg) {
        Vector<AsmInstr> out = new Vector<>();
        Vector<MemTemp> uses = new Vector<>();
        Vector<MemTemp> defs = new Vector<>();

        MemTemp srcTemp = move.src.accept(new ExprGenerator(), out);
        uses.add(srcTemp);
        if(move.dst instanceof ImcTEMP dstTmp){
            MemTemp dstTemp = dstTmp.accept(new ExprGenerator(), out);
            defs.add(dstTemp);
            out.add(new AsmMOVE("SET `d0, `s0", uses, defs));
        } else if(move.dst instanceof ImcMEM dstMem){
            MemTemp dstTemp = dstMem.addr.accept(new ExprGenerator(), out);
            uses.add(dstTemp);
            out.add(new AsmOPER("STO `s0,`s1,0", uses, null, null));
        } else{
            throw new RuntimeException("That should not happen - Move is on left side not followed with Mem or Temp.");
        }
        return out;
    }

    @Override
    public Vector<AsmInstr> visit(ImcESTMT eStmt, Object visArg) {
        //System.out.println("ESTMT");
        Vector<AsmInstr> out = new Vector<>();
        eStmt.expr.accept(new ExprGenerator(), out);
        return out;
    }

    @Override
    public Vector<AsmInstr> visit(ImcJUMP jump, Object visArg) {
        Vector<AsmInstr> out = new Vector<>();
        Vector<MemTemp> uses = new Vector<>();
        Vector<MemTemp> defs = new Vector<>();
        Vector<MemLabel> jumps = new Vector<>();
        jumps.add(jump.label);

        // cover case for returning from function
        if(visArg instanceof LinCodeChunk && ((LinCodeChunk)visArg).exitLabel.equals(jump.label)){
            uses.add(((LinCodeChunk)visArg).frame.RV); // add RV to jump
        }

        out.add(new AsmOPER("JMP "+jump.label.name, uses, defs, jumps));
        return out;
    }

    @Override
    public Vector<AsmInstr> visit(ImcCJUMP cjump, Object visArg) {
        Vector<AsmInstr> out = new Vector<>();
        MemTemp cTemp = cjump.cond.accept(new ExprGenerator(), out);
        Vector<MemTemp> uses = new Vector<>();
        uses.add(cTemp);

        Vector<MemTemp> defs = new Vector<>();
        Vector<MemLabel> jumps = new Vector<>();
        jumps.add(cjump.posLabel);
        jumps.add(cjump.negLabel);

        out.add(new AsmOPER("BNZ `s0,"+cjump.posLabel.name, uses, defs, jumps));
        return out;
    }
}
