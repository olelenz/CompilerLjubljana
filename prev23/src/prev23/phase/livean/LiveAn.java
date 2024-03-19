package prev23.phase.livean;

import prev23.data.lin.LinCodeChunk;
import prev23.data.mem.*;
import prev23.data.asm.*;
import prev23.phase.*;
import prev23.phase.asmgen.*;
import prev23.phase.imclin.ImcLin;
import java.util.*;

/**
 * Liveness analysis.
 */
public class LiveAn extends Phase {

	public LiveAn() {
		super("livean");
	}

	public void analysis() {
        for (Code currentCode: AsmGen.codes) {
            analyseCodeblock(currentCode);
        }
	}

    public void analyseCodeblock(Code codeblock){
        HashMap<String, Integer> labels = getAllLabels(codeblock.instrs);
        resetAll(codeblock.instrs);
        boolean changes = true;
        int counter = 0;
        while (changes) {
            if (counter > 1_000) break;  
            counter++;
            changes = false;
            for(int i = 0; i < codeblock.instrs.size() - 1; i++){  // last not needed, since always jump out
                AsmInstr currentInstr = codeblock.instrs.get(i);
                if (!(currentInstr instanceof AsmOPER operInstr)) continue;
                boolean inChanges = calculateIn(operInstr);
                boolean outChanges = calculateOut(codeblock, operInstr, i, labels);


                changes = changes || inChanges || outChanges;

            }
        }
    }

    public boolean calculateIn(AsmOPER instr){
        HashSet<MemTemp> oldIn = instr.in();
        HashSet<MemTemp> inNew = new HashSet<MemTemp>(instr.out());
        instr.defs().forEach(inNew::remove);
        //inNew.removeAll(instr.defs());
        inNew.addAll(instr.uses());
        instr.addInTemps(inNew);
        return !oldIn.equals(inNew);
    }

    public boolean calculateOut(Code code, AsmOPER instr, int index, HashMap<String, Integer> labels){
        HashSet<MemTemp> oldOut = instr.out();
        HashSet<MemTemp> outNew = new HashSet<MemTemp>();
        if (instr.jumps().size() < 1){  // only one immediate successor
            if (index + 1 < code.instrs.size()) {
                outNew.addAll(code.instrs.get(index + 1).in());
            }
        } else {  // jumping here
            for(MemLabel label : instr.jumps()){
                Integer jumpingIndex = labels.get(label.name);
                if (jumpingIndex != null && jumpingIndex < code.instrs.size()){
                    outNew.addAll(code.instrs.get(jumpingIndex).in());
                }
            }
        }
        instr.addOutTemp(outNew);
        return !oldOut.equals(outNew);
    }

    public HashMap<String, Integer> getAllLabels(Vector<AsmInstr> instructions){
        HashMap<String, Integer> out = new HashMap<>();
        for (int i = 0; i < instructions.size(); i++){
            if (instructions.get(i) instanceof AsmLABEL label){
                out.put(label.toString(), i);
            }
        }
        return out;
    }

    public void resetAll(Vector<AsmInstr> instructions){
        for (AsmInstr instr : instructions){
            if (instr instanceof AsmOPER oper){
                oper.removeAllFromIn();
                oper.removeAllFromOut();
            }
        }
    }

	public void log() {
		if (logger == null)
			return;
		for (Code code : AsmGen.codes) {
			logger.begElement("code");
			logger.addAttribute("entrylabel", code.entryLabel.name);
			logger.addAttribute("exitlabel", code.exitLabel.name);
			logger.addAttribute("tempsize", Long.toString(code.tempSize));
			code.frame.log(logger);
			logger.begElement("instructions");
			for (AsmInstr instr : code.instrs) {
				logger.begElement("instruction");
				logger.addAttribute("code", instr.toString());
				logger.begElement("temps");
				logger.addAttribute("name", "use");
				for (MemTemp temp : instr.uses()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "def");
				for (MemTemp temp : instr.defs()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "in");
				for (MemTemp temp : instr.in()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "out");
				for (MemTemp temp : instr.out()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.endElement();
			}
			logger.endElement();
			logger.endElement();
		}
	}

}
