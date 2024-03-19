package prev23.phase.regall;

import prev23.data.asm.AsmInstr;
import prev23.data.asm.Code;
import prev23.data.mem.MemTemp;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class Graph {
    private final Code function;
    private HashMap<MemTemp, LinkedHashSet<MemTemp>> temps = new HashMap<>();
    private Object[] keys;

    private int counter = 0;

    public Graph(Graph graph){
        this.function = graph.function;
        this.temps = new HashMap<MemTemp, LinkedHashSet<MemTemp>>(graph.temps);
        this.keys = graph.keys.clone();
        this.counter = graph.counter;
    }

    public Graph(Code function){
        this.function = function;
        this.initSets();
        this.keys = temps.keySet().toArray();
    }

    public Integer getLowestDegree(){
        int degree = 1000;
        for(MemTemp temp : temps.keySet()){
            int newDegree = getDegree(temp);
            if(newDegree < degree){
                degree = newDegree;
            }
        }
        return degree;
    }

    public HashMap<MemTemp, LinkedHashSet<MemTemp>> getTemps(){
        return temps;
    }

    public MemTemp spillTemp(){
        this.counter = 0;  // reset counter
        MemTemp toSpill = (MemTemp) keys[counter];
        deleteTemporary(toSpill);
        return toSpill;
    }

    public MemTemp getNextTempSmallerDegree(int degree){
        MemTemp newTryTemp = (MemTemp) keys[counter];
        while(getDegree(newTryTemp) >= degree){
            this.counter++;
            // check for size of keys, happens if there are no more possible temps to remove which are of less degree than degree
            if (this.counter >= keys.length){
                return null;
            }
            newTryTemp = (MemTemp) keys[counter];
        }
        this.counter++;
        deleteTemporary(newTryTemp);
        return newTryTemp;
    }

    public int getSize(){
        return temps.size();
    }

    public int getDegree(MemTemp temp){
        return temps.get(temp) == null ? 0 : temps.get(temp).size();
    }


    private void initSets(){
        for(AsmInstr instr : function.instrs){  // init temps to temps map
            for(MemTemp var : instr.defs()){  // add all defs
                if(!(temps.containsKey(var))){  // add to set if not in
                    temps.put(var, new LinkedHashSet<MemTemp>());
                }
                LinkedHashSet<MemTemp> connections = temps.get(var);
                connections.addAll(instr.out());  // update set with out-vars, since we are at a variable which is defined
                temps.put(var, connections);
            }
            for(MemTemp var : instr.uses()){  // add all uses
                if(!(temps.containsKey(var))){  // add to set if not in
                    temps.put(var, new LinkedHashSet<MemTemp>());
                }
                LinkedHashSet<MemTemp> connections = temps.get(var);
                connections.addAll(instr.in());  // update set with in-vars, since we are at a variable which is used
                temps.put(var, connections);
            }
        }

        // fully connect neighbors
        for(MemTemp temp : temps.keySet()) {
            for(MemTemp neighbor : temps.get(temp)){
                LinkedHashSet<MemTemp> connectionsOfNeighbor = temps.get(neighbor);
                connectionsOfNeighbor.add(temp);
            }
        }

        for(MemTemp temp : temps.keySet()){  // remove self from connection-set
            LinkedHashSet<MemTemp> tempSet = temps.get(temp);
            tempSet.remove(temp);
            temps.put(temp, tempSet);
        }
    }

    private void deleteTemporary(MemTemp toDeleteTemp){
        LinkedHashSet<MemTemp> neighbors = temps.get(toDeleteTemp);
        if(neighbors != null){
            temps.remove(toDeleteTemp);
            for(MemTemp neighbor : neighbors){
                LinkedHashSet<MemTemp> newSet = temps.get(neighbor);
                if(!(newSet == null)){  // remove self from neighbors
                    newSet.remove(toDeleteTemp);
                    temps.put(neighbor, newSet);
                }
            }
        }
        this.counter = 0;  // reset counter
        this.keys = temps.keySet().toArray();  // new set of keys
    }
}
