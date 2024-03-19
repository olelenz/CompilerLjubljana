package prev23.phase.asmgen;

import java.util.*;

import prev23.common.report.Report;
import prev23.data.imc.code.ImcInstr;
import prev23.data.imc.code.stmt.ImcCJUMP;
import prev23.data.imc.code.stmt.ImcJUMP;
import prev23.data.imc.code.stmt.ImcLABEL;
import prev23.data.mem.*;
import prev23.data.imc.code.expr.*;
import prev23.data.imc.visitor.*;
import prev23.data.asm.*;

/**
 * Machine code generator for expressions.
 */
public class ExprGenerator implements ImcVisitor<MemTemp, Vector<AsmInstr>> {
    @Override
    public MemTemp visit(ImcCONST constant, Vector<AsmInstr> visArg) {
        MemTemp res = new MemTemp();
        visArg.addAll(getConst(res, constant.value));
        return res;
    }

    public static Vector<AsmInstr> getConst(MemTemp temp, long val){
        Vector<AsmInstr> out = new Vector<>();
        Vector<MemTemp> uses = new Vector<>();
        uses.add(temp);
        Vector<MemTemp> defs = new Vector<>();
        defs.add(temp);
        boolean neg = val < 0;
        val = Math.abs(val);


        //out.add(new AsmOPER("SET `d0,"+posVal, uses, defs, null));  // only possible up to eight bit


        // the following is needed because SETL only supports up to 16bit
        if (val >= 0){
            out.add(new AsmOPER("SETL `d0,"+(val & 0xFFFF), null, defs, null));  // bit 0-15, & 0xFFFF needed to set the length
            val = val >> 16;
        }
        if (val > 0){  // from here increment is used
            out.add(new AsmOPER("INCML `d0,"+(val & 0xFFFF), uses, defs, null));  // bit 16-31
            val = val >> 16;
        }
        if (val > 0){
            out.add(new AsmOPER("INCMH `d0,"+(val & 0xFFFF), uses, defs, null));  // bit 32-47
            val = val >> 16;
        }
        if (val > 0){
            out.add(new AsmOPER("INCH `d0,"+(val & 0xFFFF), uses, defs, null));  // bit 48-63
        }
        if (neg){
            out.add(new AsmOPER("NEG `d0,`s0", uses, defs, null));
        }
        return out;
    }

    @Override
    public MemTemp visit(ImcNAME name, Vector<AsmInstr> visArg) {
        MemTemp res = new MemTemp();
        Vector<MemTemp> defs = new Vector<>();
        defs.add(res);
        visArg.add(new AsmOPER("LDA `d0," + name.label.name, null, defs, null));
        return res;
    }

    @Override
    public MemTemp visit(ImcBINOP binOp, Vector<AsmInstr> visArg) {
        Vector<MemTemp> uses = new Vector<>();
        uses.add(binOp.fstExpr.accept(this, visArg));
        uses.add(binOp.sndExpr.accept(this, visArg));  // FIXME: may still cause errors

        Vector<MemTemp> defs = new Vector<>();
        MemTemp res = new MemTemp();
        defs.add(res);

        switch (binOp.oper){
            case ADD:
                visArg.add(new AsmOPER("ADD `d0,`s0,`s1", uses, defs, null));break;
            case SUB:
                visArg.add(new AsmOPER("SUB `d0,`s0,`s1", uses, defs, null));break;
            case DIV:
                visArg.add(new AsmOPER("DIV `d0,`s0,`s1", uses, defs, null));break;
            case MUL:
                visArg.add(new AsmOPER("MUL `d0,`s0,`s1", uses, defs, null));break;
            case OR:
                visArg.add(new AsmOPER("OR `d0,`s0,`s1", uses, defs, null));break;
            case AND:
                visArg.add(new AsmOPER("AND `d0,`s0,`s1", uses, defs, null));break;
            case MOD: {
                visArg.add(new AsmOPER("DIV `d0,`s0,`s1", uses, defs, null));
                visArg.add(new AsmOPER("GET `d0,rR", null, defs, null));  // remainder save in special register rR
            }break;
            case EQU: {// CMP T3,T1,T2
                // CMP T4,T3,-1  -> absolute value
                // CMP T5,T3,1
                // OR T6,T4,T5
                // BNZ T,L1 -> into res to return

                // or

                // CMP T3,T1,T2 -> T3 = -1 or 0 or +1
                // XOR T4,T4,T4 -> T4 = 0
                // CSZ T4,T3,1 -> T4 = 1 if T3 is 0, 0 otherwise
                // BNZ T4,L1

                // or

                // CMP T3,T1,T2 -> T3 = -1 or 0 or +1
                // ZSZ T4,T3,1 -> T4 = 1 if T3 is 0, 0 otherwise
                // BNZ T4,L1
                MemTemp tempTemp = new MemTemp();
                Vector<MemTemp> cmpDefs = new Vector<>();
                cmpDefs.add(tempTemp);
                Vector<MemTemp> zszUses = new Vector<>();
                zszUses.add(tempTemp);
                visArg.add(new AsmOPER("CMP `d0,`s0,`s1", uses, cmpDefs, null));
                visArg.add(new AsmOPER("ZSZ `d0,`s0,1", zszUses, defs, null));
            }break;

            case GEQ: {
                MemTemp tempTemp = new MemTemp();
                Vector<MemTemp> cmpDefs = new Vector<>();
                cmpDefs.add(tempTemp);
                Vector<MemTemp> zsnnUses = new Vector<>();
                zsnnUses.add(tempTemp);
                visArg.add(new AsmOPER("CMP `d0,`s0,`s1", uses, cmpDefs, null));
                visArg.add(new AsmOPER("ZSNN `d0,`s0,1", zsnnUses, defs, null));

                // CMP T3,T1,T2 -> T3 = -1 or 0 or +1
                // ZSNN T4,T3,1 -> T4 = 1 if T3 >= 0, 0 otherwise
            }break;
            case GTH: {
                MemTemp tempTemp = new MemTemp();
                Vector<MemTemp> cmpDefs = new Vector<>();
                cmpDefs.add(tempTemp);
                Vector<MemTemp> zspUses = new Vector<>();
                zspUses.add(tempTemp);
                visArg.add(new AsmOPER("CMP `d0,`s0,`s1", uses, cmpDefs, null));
                visArg.add(new AsmOPER("ZSP `d0,`s0,1", zspUses, defs, null));

                // CMP T3,T1,T2 -> T3 = -1 or 0 or +1
                // ZSP T4,T3,1 -> T4 = 1 if T3 > 0, 0 otherwise
            }break;
            case LEQ: {
                MemTemp tempTemp = new MemTemp();
                Vector<MemTemp> cmpDefs = new Vector<>();
                cmpDefs.add(tempTemp);
                Vector<MemTemp> zsnpUses = new Vector<>();
                zsnpUses.add(tempTemp);
                visArg.add(new AsmOPER("CMP `d0,`s0,`s1", uses, cmpDefs, null));
                visArg.add(new AsmOPER("ZSNP `d0,`s0,1", zsnpUses, defs, null));

                // CMP T3,T1,T2 -> T3 = -1 or 0 or +1
                // ZSNP T4,T3,1 -> T4 = 1 if T3 <= 0, 0 otherwise
            }break;
            case LTH: {
                MemTemp tempTemp = new MemTemp();
                Vector<MemTemp> cmpDefs = new Vector<>();
                cmpDefs.add(tempTemp);
                Vector<MemTemp> zsnUses = new Vector<>();
                zsnUses.add(tempTemp);
                visArg.add(new AsmOPER("CMP `d0,`s0,`s1", uses, cmpDefs, null));
                visArg.add(new AsmOPER("ZSN `d0,`s0,1", zsnUses, defs, null));

                // CMP T3,T1,T2 -> T3 = -1 or 0 or +1
                // ZSN T4,T3,1 -> T4 = 1 if T3 < 0, 0 otherwise
            }break;
            case NEQ: {
                MemTemp tempTemp = new MemTemp();
                Vector<MemTemp> cmpDefs = new Vector<>();
                cmpDefs.add(tempTemp);
                Vector<MemTemp> andUses = new Vector<>();
                andUses.add(tempTemp);
                visArg.add(new AsmOPER("CMP `d0,`s0,`s1", uses, cmpDefs, null));
                visArg.add(new AsmOPER("AND `d0,`s0,1", andUses, defs, null));
                // CMP T3,T1,T2 -> T3 = -1 or 0 or +1
                // AND T4,T3,1 -> T4 = 1 if T3 is 1 or -1, 0 otherwise
            }break;
        }
        return res;
    }

    @Override
    public MemTemp visit(ImcTEMP temp, Vector<AsmInstr> visArg) {
        return temp.temp;
    }

    @Override
    public MemTemp visit(ImcMEM mem, Vector<AsmInstr> visArg) {
        MemTemp res = new MemTemp();

        Vector<MemTemp> defs = new Vector<>();
        defs.add(res);

        Vector<MemTemp> uses = new Vector<>();
        MemTemp addr = mem.addr.accept(this, visArg);
        uses.add(addr);

        visArg.add(new AsmMOVE("LDO `d0,`s0,0", uses, defs));
        return res;
    }

    @Override
    public MemTemp visit(ImcUNOP unOp, Vector<AsmInstr> visArg) {
        MemTemp res = new MemTemp();
        Vector<MemTemp> defs = new Vector<>();
        defs.add(res);

        Vector<MemTemp> uses = new Vector<>();
        uses.add(unOp.subExpr.accept(this, visArg));

        if (unOp.oper.equals(ImcUNOP.Oper.NOT)){
            // false -> 0
            // true -> 1
            // XOR false, 1 -> 1, meaning false(0) becomes 1
            // XOR true, 1 -> 0, meaning true(1) becomes 0
            visArg.add(new AsmOPER("XOR `d0,`s0,1", uses, defs, null));
        } else if (unOp.oper.equals(ImcUNOP.Oper.NEG)) {
            visArg.add(new AsmOPER("NEG `d0,0,`s0", uses, defs, null));
        } else {
            throw new InternalError("unary operator not covered (yet)!");
        }
        return res;
    }

    @Override
    public MemTemp visit(ImcCALL call, Vector<AsmInstr> visArg) {
        for (int i = 0; i < call.args.size(); i++) {  // put arguments into frame
            ImcExpr argument = call.args.get(i);
            Long offset = call.offs.get(i);
            MemTemp tempOfArg = argument.accept(this, visArg);
            Vector<MemTemp> uses = new Vector<>();
            uses.add(tempOfArg);

            visArg.add(new AsmOPER("STO `s0,$254," + offset, uses, null, null));  
        }


        visArg.add(new AsmOPER("PUSHJ $nOle" + "," + call.label.name, null, null, null));
        MemTemp ret = new MemTemp();
        Vector<MemTemp> defs = new Vector<>();
        defs.add(ret);
        visArg.add(new AsmOPER("LDO `d0,$254,0", null, defs, null));  // load result, same FIXME here as above
        return ret;
    }
}
