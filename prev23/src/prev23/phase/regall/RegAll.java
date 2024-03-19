package prev23.phase.regall;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import prev23.data.mem.*;
import prev23.data.asm.*;
import prev23.phase.*;
import prev23.phase.asmgen.*;
import prev23.phase.livean.LiveAn;

import static prev23.phase.all.CompleteFunction.generateInstructionsWithBitNumber;

/**
 * Register allocation.
 */
public class RegAll extends Phase {

    private int regs = 4;
    boolean notDone = false;

	/** Mapping of temporary variables to registers. */
	public HashMap<MemTemp, Integer> tempToReg = new HashMap<MemTemp, Integer>();
    private Stack<MemTemp> stack = new Stack<>();
    private HashSet<MemTemp> potentialSpills = new HashSet<>();
    private HashSet<MemTemp> actualSpills = new HashSet<>();

	public RegAll(int num_regs) {
		super("regall");
        if (num_regs < 2){  // TODO: change back to four
            throw new RuntimeException("Number of registers must be at least three!");
        }
        this.regs = num_regs;
	}

	public void allocate() {
        HashMap<MemTemp, Integer> tempToRegAll = new HashMap<>();
        for(Code function : AsmGen.codes){
            HashMap<MemTemp, Integer> retTemps = allocateFunction(function);
            tempToRegAll.putAll(retTemps);
        }
        tempToReg = tempToRegAll;
	}

    public HashMap<MemTemp, Integer> allocateFunction(Code function){
        notDone = false;
        HashMap<MemTemp, LinkedHashSet<MemTemp>> connections = new HashMap<MemTemp, LinkedHashSet<MemTemp>>();
        // create graph
        Graph graph = new Graph(function);
        for(MemTemp temp : graph.getTemps().keySet()){  // make copy
            connections.put(temp, new LinkedHashSet<>(graph.getTemps().get(temp)));
        }
        do {
            notDone = colour(graph);
        } while(notDone);
        // map to registers
        this.select(connections, function);
        return tempToReg;
    }

    public void select(HashMap<MemTemp, LinkedHashSet<MemTemp>> connections, Code function){

        HashSet<Integer> colours = new HashSet<>();
        for(int i = 0; i < this.regs; i++) {
            colours.add(i);
        }
        HashSet<Integer> coloursStartState = new HashSet<>(colours);
        while(!stack.isEmpty()){
            MemTemp temp = stack.pop();

            // handle FP
            if(temp == function.frame.FP){
                tempToReg.put(temp, 253);
                continue;
            }

            colours = new HashSet<>(coloursStartState);
            int newColour = 0;
            for(MemTemp neighbor : connections.get(temp)){
                if (tempToReg.get(neighbor) != null){
                    colours.remove(tempToReg.get(neighbor));
                }
            }
            if(colours.size() == 0){
                actualSpills.add(temp);
            } else {
                newColour = colours.iterator().next();
                tempToReg.put(temp, newColour);
            }
        }
        if (actualSpills.size() != 0){
            // start over, move spilled temp into stackframe by modifying the assembly code
            MemTemp tempToMoveToSF = actualSpills.iterator().next();
            changeInstructions(tempToMoveToSF, function);
            redoLiveAnAndRegAll(function);
        }
    }

    public boolean colour(Graph graph){
        this.simplify(graph);
        if(graph.getSize() > 0){ 
            this.spill(graph);
            return true;
        }
        return false;
    }

    private void spill(Graph graph){
        MemTemp spilledTemp = graph.spillTemp();
        potentialSpills.add(spilledTemp);
        stack.push(spilledTemp);
        colour(graph);
    }

    private void simplify(Graph graph){
        while(graph.getSize() > 0){
            MemTemp tempToRemove = graph.getNextTempSmallerDegree(this.regs);
            if(tempToRemove != null){
                this.stack.push(tempToRemove);
            } else {  // no more possible temp to remove
                return;  // resulting in size of graph > 0
            }
        }
    }

    private void changeInstructions(MemTemp tempToMoveToSF, Code function){
        function.tempSize += 8;
        MemTemp framePointer = function.frame.FP;
        long offset = -function.frame.locsSize - 16 - function.tempSize;  
        // move offset to new Memtemp and load the right value using the function from ExprGenerator
            // memtemp for storing i.e. FP
        MemTemp store = new MemTemp();
        // load it
        List<AsmInstr> constInstr = ExprGenerator.getConst(store, offset);  // this should be added into the code when the offset is needed
        int constInstrSize = constInstr.size();

        for(int j = 0; j < function.instrs.size(); j++){
            AsmOPER currentOper = (AsmOPER) function.instrs.get(j);
            boolean tempUsed = currentOper.uses().contains(tempToMoveToSF);
            boolean tempDefined = currentOper.defs().contains(tempToMoveToSF);

            if(tempDefined) {
                MemTemp replaceTempOne = new MemTemp();

                // replace temp in defs
                for (int i = 0; i < currentOper.defs().size(); i++) {
                    if (currentOper.defs().get(i) == tempToMoveToSF) {
                        Vector<MemTemp> defsNew = currentOper.defs();
                        defsNew.set(i, replaceTempOne);
                        function.instrs.set(j, new AsmOPER(currentOper.instr(), currentOper.uses(), defsNew, currentOper.jumps()));

                        // add new instructions, set and store
                        // set
                        MemTemp replaceTempTwo = new MemTemp();
                        Vector<MemTemp> defs = new Vector<>();
                        defs.add(replaceTempTwo);
                        Vector<MemTemp> usesConst = new Vector<>();
                        usesConst.add(store);
                        for(int o = 1; o < constInstrSize + 1; o++){
                            function.instrs.insertElementAt(constInstr.get(o-1), j + o);
                        }
                        AsmOPER setOper = new AsmOPER("SET `d0,`s0", usesConst, defs, null);

                        // store
                        Vector<MemTemp> uses = new Vector<>();
                        uses.add(replaceTempOne);
                        uses.add(framePointer);
                        uses.add(replaceTempTwo);
                        AsmOPER storeOper = new AsmOPER("STO `s0,`s1,`s2", uses, null, null);
                        function.instrs.insertElementAt(setOper, j + 1 + constInstrSize);
                        function.instrs.insertElementAt(storeOper, j + 2 + constInstrSize);
                        j = j + 2 + constInstrSize;
                    }
                }
            }

            if(tempUsed){
                MemTemp replaceTempOne = new MemTemp();
                Vector<MemTemp> usesNew = currentOper.uses();

                // replace temp in defs
                for (int i = 0; i < currentOper.uses().size(); i++) {
                    if (currentOper.uses().get(i) == tempToMoveToSF){
                        usesNew.set(i, replaceTempOne);
                        // add new instructions, load and replace old
                        // load
                        Vector<MemTemp> defs = new Vector<>();
                        defs.add(replaceTempOne);  // before: with loadTemp
                        Vector<MemTemp> uses = new Vector<>();
                        uses.add(framePointer);

                        if(offset < 0){
                            offset = -offset;
                            long mod = offset % 255;
                            long num = mod;
                            Vector<MemTemp> defsLoad = new Vector<>();
                            defsLoad.add(replaceTempOne);
                            AsmOPER loadConst = new AsmOPER("SETL `d0,"+mod, null, defsLoad, null);
                            function.instrs.insertElementAt(loadConst, j);
                            j++;

                            while(num < offset){
                                long difference = offset - num;
                                if(difference > 255) difference = 255;
                                Vector<MemTemp> defsAdd = new Vector<>();
                                defsAdd.add(replaceTempOne);
                                AsmOPER addToConst = new AsmOPER("ADD `d0,`d0,"+difference, null, defsAdd, null);
                                function.instrs.insertElementAt(addToConst, j);
                                j++;
                                num += 255;
                            }

                            Vector<MemTemp> usesNeg = new Vector<>();
                            usesNeg.add(replaceTempOne);
                            Vector<MemTemp> defsNeg= new Vector<>();
                            defsNeg.add(replaceTempOne);
                            AsmOPER negConst = new AsmOPER("NEG `d0,`s0", usesNeg, defsNeg, null);
                            function.instrs.insertElementAt(negConst, j);
                            j++;

                            uses.add(replaceTempOne);
                            AsmOPER setOper = new AsmOPER("LDO `d0,`s0,`s1", uses, defs, null);
                            function.instrs.insertElementAt(setOper, j);

                        } else{
                            AsmOPER setOper = new AsmOPER("LDO `d0,`s0,"+offset, uses, defs, null);
                            function.instrs.insertElementAt(setOper, j);
                        }
                    }
                }
                function.instrs.set(j+1, new AsmOPER(currentOper.instr(), usesNew, currentOper.defs(), currentOper.jumps()));
            }
        }

    }

    private void redoLiveAnAndRegAll(Code function){
        try (LiveAn livean = new LiveAn()) {
            livean.analyseCodeblock(function);
            livean.log();
        }
        try (RegAll regall = new RegAll(this.regs)) {
            this.tempToReg = regall.allocateFunction(function);
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
				logger.addAttribute("code", instr.toString(tempToReg));
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
