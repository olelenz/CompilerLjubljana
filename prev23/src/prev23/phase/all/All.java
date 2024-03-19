package prev23.phase.all;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import prev23.data.asm.*;
import prev23.data.lin.LinDataChunk;
import prev23.phase.asmgen.AsmGen;
import prev23.phase.imclin.ImcLin;
import prev23.phase.regall.RegAll;
import prev23.Compiler;

public class All {
    public static List<CompleteFunction> functions = new LinkedList<>();
    public List<String> initInstr = new LinkedList<>();
    public List<String> library = new LinkedList<>();
    public List<String> staticVars = new LinkedList<>();
    private long sizeData = 0;

    public void completeFunctions(RegAll regall){
        for(Code code : AsmGen.codes){
            functions.add(new CompleteFunction(code, regall.tempToReg));
        }
    }

    public void buildFile(String filename, RegAll regall){
        int numRegs = Integer.decode(Compiler.cmdLineArgs.get("--num-regs"));
        // init static data
        initStaticVariables();

        // init program
        initProgram();

        // add standard library
        initStandardLibrary();

        // add functions

        try (PrintWriter writer = new PrintWriter(filename+".mms", StandardCharsets.UTF_8)) {
            for(String instr : staticVars){
                writer.println(instr);  // indention dealt with in creation
            }

            for(String instr : initInstr){
                writer.println(instr);  // indention dealt with in creation
            }
            for(CompleteFunction function : functions){
                Set<String> libraryFun = new HashSet<>();
                libraryFun.add("_putChar");
                libraryFun.add("_getChar");
                libraryFun.add("_new");
                libraryFun.add("_del");
                if(libraryFun.contains(function.getBody().frame.label.name)){  // part of standard library, TODO: add three others
                    continue;
                }

                for(String instr : function.getPrologue()){
                    writer.println(instr);
                }
                for(AsmInstr line : function.getBody().instrs){
                    String lineToInsert = line.toString(regall.tempToReg);
                    if(lineToInsert.startsWith("SET ")){
                        if(lineToInsert.endsWith(lineToInsert.substring(4,6))){
                            continue;  // remove unnecessary set calls -> for queens from 1541 to 1189 with two regs
                        }
                    }
                    String instr = lineToInsert.split(" ")[0];
                    int lenInstr = instr.length();
                    if(line instanceof AsmLABEL){
                        lineToInsert = lineToInsert.replaceFirst(" ", "\t\t")+"\tSWYM";
                    } else if (lenInstr == 3) {
                        lineToInsert = "\t\t" + lineToInsert.replaceFirst(" ", "\t\t");
                    } else {
                        lineToInsert = "\t\t" + lineToInsert.replaceFirst(" ", "\t").replace("nOle",""+numRegs);  // replace nOle with num of regs when PUSHJ
                    }

                    lineToInsert = lineToInsert.replace(" ","");
                    writer.println(lineToInsert);
                }
                for(String instr : function.getEpilogue()){
                    writer.println(instr);
                }
            }

            for(String instr : library){
                writer.println(instr);
            }

        } catch (IOException e) {
            throw new RuntimeException("Writing not possible!");
        }

    }

    private void initProgram(){
        // MAIN label for MMIX
        initInstr.add("Main\tSET\t\t$0,252");  // load heap pointer (252) into reg 0


        initInstr.add("\t\tORH\t$254,#5000");
        initInstr.add("\t\tOR\t$253,$254,#f0");
        //initInstr.add("\t\tPUT\t\trG,$0");  // set the limit of usable registers to 251
        // create stack
        // create heap
        // add starting point, i.e. call main function
        int numRegs = Integer.decode(Compiler.cmdLineArgs.get("--num-regs"));
        initInstr.add("\t\tPUSHJ\t$"+numRegs+",_main");
        initInstr.add("\t\tLDO\t\t$255,$254,0");
        initInstr.add("\t\tTRAP\t0,Halt,0");
    }

    private void initStaticVariables(){
        staticVars.add("\t\tLOC\t\tData_Segment");
        staticVars.add("\t\tGREG\t0");
        staticVars.add("\t\tGREG\t0");
        staticVars.add("\t\tGREG\t@");
        staticVars.add("\t\tGREG\t@");

        staticVars.add("Buffer\tBYTE\t0,0");
        staticVars.add("\tLOC\tBuffer+80");
        staticVars.add("Arg\tOCTA\tBuffer,2");
        staticVars.add("\tOCTA\t80");

        staticVars.add("bufferOut\t\tBYTE\t0,0,0,0,0,0,0,0");

        for(LinDataChunk data : ImcLin.dataChunks()){
            if(data.size == 8){  // I guess you can call them primitive?
                staticVars.add(data.label.name + "\t\tOCTA\t1");
            } else {
                if(data.size % 8 != 0){
                    throw new RuntimeException("Data size not divisible by eight");
                }
                String nums = "";
                for(int i = 0; i < data.size; i+=8){
                    nums += "1,";
                }
                nums = nums.substring(0, nums.length()-1);
                staticVars.add(data.label.name + "\t\tOCTA\t"+nums);

            }
        }

        staticVars.add("\t\tLOC\t\t#100");  // set address
    }

    private void initStandardLibrary(){
        // _getChar

        library.add("_getChar\tLDA\t$255,Arg");
        library.add("\t\tTRAP\t0,Fgets,StdIn");
        library.add("\t\tLDA\t$0,Buffer");
        library.add("\tSET\t$1,0");
        library.add("\t\tLDB\t$1,$0,0");
        library.add("\t\tSTO\t$1,$254,0");
        library.add("\t\tPOP\t0,0");


        // _putChar
        library.add("_putChar\tLDO\t\t$0,$254,8"); // load argument into buffer
        library.add("\t\tLDA\t\t$255,bufferOut");  // load buffer
        library.add("\t\tSTB\t\t$0,$255,0");  // store value
        library.add("\t\tXOR\t\t$0,$0,$0");
        library.add("\t\tSTB\t\t$0,$255,1");
        library.add("\t\tTRAP\t0,Fputs,StdOut");  // trap to print
        library.add("\t\tPOP\t\t0,0");  // return

        // _new
        library.add("_new\t\tSWYM");
        library.add("\t\tSTO\t$252,$254,0");  // set RV to new address
        library.add("\t\tLDO\t$0,$254,8");  // get first argument, num of bytes
        library.add("\t\tMUL\t$0,$0,8");  // calc the amount of memory to allocate
        library.add("\t\tADD\t$252,$252,$0");  // increase hp
        library.add("\t\tPOP\t0,0");

        // _del
        library.add("_del\t\tPOP\t0,0");  // pop right away

    }
}
