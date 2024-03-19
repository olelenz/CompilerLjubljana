package prev23.phase.all;

import prev23.Compiler;
import prev23.data.asm.AsmInstr;
import prev23.data.asm.Code;
import prev23.data.mem.MemTemp;
import prev23.phase.asmgen.ExprGenerator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CompleteFunction {
    private List<String> prologue = new LinkedList<>();
    private List<String> epilogue = new LinkedList<>();
    private Code body;
    private HashMap<MemTemp, Integer> registers;

    public CompleteFunction(Code body, HashMap<MemTemp, Integer> register){
        this.body = body;
        this.registers = register;
        prologue();  // add prologue
        epilogue();  // add epilogue
    }

    private void prologue() {
        // pseudo instructions
        // label
        prologue.add(body.frame.label.name+"\tSWYM");

        // move FP down
        long spOffset = body.frame.locsSize + 8;

        // load spOffset into $0
            // memtemp for storing i.e. FP
        MemTemp store = new MemTemp();
            // add this temp to register 0
        this.registers.put(store, 0);
            // actually load it
        List<AsmInstr> constInstr = ExprGenerator.getConst(store, spOffset);
        for(AsmInstr instr : constInstr){
            prologue.add("\t\t"+instr.toString(this.registers).replaceFirst(" ", "\t"));
        }

        //prologue.add("\t\tSUB\t$254,$254,"+spOffset);  // TODO: add later to deal with values > 255
        prologue.add("\t\tSUB\t$254,$254,$0");
        prologue.add("\t\tSTO\t$253,$254,0");
        prologue.add("\t\tSUB\t$254,$254,8");
        prologue.add("\t\tGET\t$253,rJ");
        prologue.add("\t\tSTO\t$253,$254,0");
        prologue.add("\t\tADD\t$254,$254,8");
        //prologue.add("\t\tADD\t$254,$254,"+spOffset);
        prologue.add("\t\tADD\t$254,$254,$0");
        prologue.add("\t\tOR\t$253,$254,0");

        // load sizeFrame into $0
        long sizeFrame = body.tempSize + body.frame.size;  // TODO: add later to deal with values > 255
        List<AsmInstr> constInstrTwo = ExprGenerator.getConst(store, sizeFrame);
        for(AsmInstr instr : constInstrTwo){
            prologue.add("\t\t"+instr.toString(this.registers).replaceFirst(" ", "\t"));
        }

        //prologue.add("\t\tSUB\t$254,$254,"+sizeFrame);
        prologue.add("\t\tSUB\t$254,$254,$0");

        // goto entry label
        prologue.add("\t\t"+"JMP\t\t"+body.entryLabel.name);

    }

    private void epilogue() {
        // exit label of function
        epilogue.add(body.exitLabel.name+"\tSWYM");

        // store the RV
        int retValue = registers.get(body.frame.RV);
        epilogue.add("\t\tSTO\t\t$"+retValue+",$253,0");

        // deallocate the Stackframe
        // restore FP, SP, RA
        // Set SP to FP
        epilogue.add("\t\tSET\t$254,$253");

        // Set old FP
        long oldFP = body.frame.locsSize + 8;
        // load oldFP into $0
            // memtemp for storing i.e. FP
        /*MemTemp store = new MemTemp();
        // add this temp to register 0
        this.registers.put(store, 1);
        // actually load it
        List<AsmInstr> constInstr = ExprGenerator.getConst(store, oldFP);
        for(AsmInstr instr : constInstr){
            prologue.add("\t\t"+instr.toString(this.registers).replaceFirst(" ", "\t"));
        }*/

        epilogue.add("\t\tSUB\t$0,$254,"+0);
        generateInstructionsWithBitNumber("SUB", "$0", oldFP, epilogue);

        //epilogue.add("\t\tSUB\t$0,$254,$1");
        epilogue.add("\t\tLDO\t$253,$0,0");

        // Load return address to rJ
        epilogue.add("\t\tSUB\t$0,$0,8");
        epilogue.add("\t\tLDO\t$0,$0,0");
        epilogue.add("\t\tPUT\trJ,$0");

        // pop out
        epilogue.add("\t\t"+"POP\t\t0,0");
    }

    public static void generateInstructionsWithBitNumber(String instruction, String reg, Long num, List<String> toAddTo){
        long sizeToSub = 0;
        boolean negative = false;
        if(num < 0){
            num = -num;
            negative = true;
        }
        while(sizeToSub < num){
            long difference = num - sizeToSub;
            if(difference > 255) difference = 255;
            toAddTo.add("\t\t"+instruction+"\t"+reg+","+reg+","+difference);
            sizeToSub += 255;
        }
        if(negative){
            toAddTo.add("\t\tNEG\t"+reg+","+reg+"");
        }
    }

    public List<String> getPrologue() {
        return prologue;
    }

    public void setPrologue(List<String> prologue) {
        this.prologue = prologue;
    }

    public List<String> getEpilogue() {
        return epilogue;
    }

    public void setEpilogue(List<String> epilogue) {
        this.epilogue = epilogue;
    }

    public Code getBody() {
        return body;
    }

    public void setBody(Code body) {
        this.body = body;
    }
}
