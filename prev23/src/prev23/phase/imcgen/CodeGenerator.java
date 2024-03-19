package prev23.phase.imcgen;

import prev23.common.report.Report;
import prev23.data.ast.tree.AstTree;
import prev23.data.ast.tree.AstTrees;
import prev23.data.ast.tree.decl.*;
import prev23.data.ast.tree.expr.*;
import prev23.data.ast.tree.stmt.*;
import prev23.data.ast.tree.type.*;
import prev23.data.ast.visitor.AstFullVisitor;
import prev23.data.imc.code.expr.*;
import prev23.data.imc.code.stmt.*;
import prev23.data.mem.*;
import prev23.data.typ.*;
import prev23.phase.imclin.StmtCanonizer;

import java.util.Stack;
import java.util.Vector;

import static prev23.phase.imcgen.ImcGen.exprImc;
import static prev23.phase.imcgen.ImcGen.stmtImc;
import static prev23.phase.memory.Memory.*;
import static prev23.phase.seman.SemAn.*;

public class CodeGenerator extends AstFullVisitor<Object, Object> {
    public CodeGenerator() {

    }

    Stack<MemFrame> currentFrames = new Stack<>();

    boolean declaring = false;
    boolean getRV = false;
    boolean getRvNoSTMTS = false;
    boolean isVoidFun = false;

    @Override
    public Object visit(AstTrees<? extends AstTree> trees, Object arg) {
        if (trees == null) return null;  // empty parameters
        if (trees.title.equals("Declarations")) {
            for (AstTree t : trees) {
                if (t == null) return null;
                this.visit((AstTrees<? extends AstTree>) t, null);
            }
            return null;
        }
        if (trees.title.equals("Var-Declaration")) {
            for (AstTree t : trees) {
                if (t == null) return null;
                this.visit((AstVarDecl) t, null);
            }
            return null;
        }
        if (trees.title.equals("Typ-Declaration")) {
            declaring = true;
            for (AstTree t : trees) {
                if (t == null) return null;
                this.visit((AstTypDecl) t, null);
            }
            return null;
        }
        if (trees.title.equals("Fun-Declaration")) {
            for (AstTree t : trees) {
                if (t == null) return null;
                this.visit((AstFunDecl) t, null);
            }
            return null;
        }
        if (trees.title.equals("Par-Declarations")) {
            for (AstTree t : trees) {
                if (t == null) return null;
                this.visit((AstParDecl) t, null);
            }
            return null;
        }
        if (trees.title.equals("Par-Declarations-Call")) {
            for (AstTree t : trees) {
                if (t != null) {
                    this.visit((AstExpr) t, null);
                }
            }
            return null;
        }
        if (trees.title.equals("statements")) {
            for (AstTree t : trees) {
                if (t == null) return null;
                this.visit((AstStmt) t, null);
            }
            return null;
        }
        return null;
    }

    // DECLARATIONS

    @Override
    public Object visit(AstCmpDecl acceptor, Object arg) {
        if (acceptor.type != null) visit(acceptor.type, arg);
        return null;
    }

    @Override
    public Object visit(AstFunDecl acceptor, Object arg) {
        currentFrames.push(frames.get(acceptor));

        boolean isVoidFunReset = isVoidFun;
        if (acceptor.type instanceof AstAtomType && ((AstAtomType) acceptor.type).type == AstAtomType.Type.VOID) {
            isVoidFun = true;  // function of return type void and first stmts
        }

        if (acceptor.type != null) visit(acceptor.type, null);
        if (acceptor.pars != null) visit(acceptor.pars, null);
        boolean retrieveRV = getRV;  // was added
        getRV = true;
        if(!(acceptor.stmt instanceof AstStmts)){
            getRvNoSTMTS = true;
        }
        if (acceptor.stmt != null) visit(acceptor.stmt, null);
        getRV = retrieveRV;
        getRvNoSTMTS = false;
        isVoidFun = isVoidFunReset;

        currentFrames.pop();
        return null;
    }

    @Override
    public Object visit(AstParDecl acceptor, Object arg) {
        if (acceptor.type != null) {
            visit(acceptor.type, arg);
        }
        return null;
    }

    @Override
    public Object visit(AstTypDecl acceptor, Object arg) { // (d1)
        if (acceptor.type != null) visit(acceptor.type, arg);
        return null;
    }

    @Override
    public Object visit(AstVarDecl acceptor, Object arg) {
        if (acceptor.type != null) visit(acceptor.type, acceptor.name);
        return null;
    }

    // EXPRESSIONS

    public Object visit(AstExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        Object returnValue = null;
        if (acceptor instanceof AstArrExpr) {
            returnValue = visit((AstArrExpr) acceptor, arg);
        } else if (acceptor instanceof AstAtomExpr) {
            returnValue = visit((AstAtomExpr) acceptor, arg);
        } else if (acceptor instanceof AstBinExpr) {
            returnValue = visit((AstBinExpr) acceptor, arg);
        } else if (acceptor instanceof AstCallExpr) {
            returnValue = visit((AstCallExpr) acceptor, arg);
        } else if (acceptor instanceof AstCastExpr) {
            returnValue = visit((AstCastExpr) acceptor, arg);
        } else if (acceptor instanceof AstDelExpr) {
            returnValue = visit((AstDelExpr) acceptor, arg);
        } else if (acceptor instanceof AstNameExpr) {
            returnValue = visit((AstNameExpr) acceptor, arg);
        } else if (acceptor instanceof AstNewExpr) {
            returnValue = visit((AstNewExpr) acceptor, arg);
        } else if (acceptor instanceof AstPfxExpr) {
            returnValue = visit((AstPfxExpr) acceptor, arg);
        } else if (acceptor instanceof AstRecExpr) {
            returnValue = visit((AstRecExpr) acceptor, arg);
            returnValue = acceptor;
        } else if (acceptor instanceof AstSfxExpr) {
            returnValue = visit((AstSfxExpr) acceptor, arg);
        }
        return returnValue;
    }

    @Override
    public Object visit(AstArrExpr arrExpr, Object arg) {
        visit(arrExpr.arr, arg);
        visit(arrExpr.idx, arg);

        SemType arrType = ofType.get(arrExpr);
        ImcMEM imcArrExpr = (ImcMEM) exprImc.get(arrExpr.arr);
        ImcExpr imcIdxExpr = exprImc.get(arrExpr.idx);

        ImcMEM mem = new ImcMEM(
                new ImcBINOP(ImcBINOP.Oper.ADD,
                        imcArrExpr.addr,
                        new ImcBINOP(ImcBINOP.Oper.MUL,
                                imcIdxExpr,
                                new ImcCONST(arrType.size())))
        );

        exprImc.put(arrExpr, mem);
        return null;
    }

    @Override
    public Object visit(AstAtomExpr atomExpr, Object arg) {  
        ImcExpr imcExpr = null;
        switch (atomExpr.type){
            case PTR :
            case VOID : {
                imcExpr = new ImcCONST(0);
            }break;
            case INT: {
                long value = Long.parseLong(atomExpr.value);
                imcExpr = new ImcCONST(value);
            }break;
            case BOOL:{
                if(atomExpr.value.equals("false")){
                    imcExpr = new ImcCONST(0);
                } else {
                    imcExpr = new ImcCONST(1);
                }
            }break;
            case CHAR:{
                imcExpr = new ImcCONST(atomExpr.value.charAt(1));
            }break;
            case STR:{
                MemAbsAccess access = strings.get(atomExpr);
                imcExpr = new ImcNAME(access.label);
            }break;
        }
        exprImc.put(atomExpr, imcExpr);
        return null;
    }

    @Override
    public Object visit(AstBinExpr binExpr, Object arg) {
        visit(binExpr.fstExpr, arg);
        visit(binExpr.sndExpr, arg);

        ImcExpr imcExpr = null;
        ImcExpr fstExprInside = exprImc.get(binExpr.fstExpr);
        ImcExpr sndExprInside = exprImc.get(binExpr.sndExpr);
        ImcBINOP.Oper oper = null;

        switch (binExpr.oper){
            case ADD -> oper = ImcBINOP.Oper.ADD;
            case OR -> oper = ImcBINOP.Oper.OR;
            case AND -> oper = ImcBINOP.Oper.AND;
            case DIV -> oper = ImcBINOP.Oper.DIV;
            case EQU -> oper = ImcBINOP.Oper.EQU;
            case GEQ -> oper = ImcBINOP.Oper.GEQ;
            case GTH -> oper = ImcBINOP.Oper.GTH;
            case LEQ -> oper = ImcBINOP.Oper.LEQ;
            case LTH -> oper = ImcBINOP.Oper.LTH;
            case MOD -> oper = ImcBINOP.Oper.MOD;
            case MUL -> oper = ImcBINOP.Oper.MUL;
            case NEQ -> oper = ImcBINOP.Oper.NEQ;
            case SUB -> oper = ImcBINOP.Oper.SUB;
        }

        imcExpr = new ImcBINOP(oper, fstExprInside, sndExprInside);
        exprImc.put(binExpr, imcExpr);
        return null;
    }

    @Override
    public Object visit(AstCallExpr callExpr, Object arg) {  
        visit(callExpr.args, arg);


        MemLabel label = null;
        AstNameDecl astDecl = declaredAt.get(callExpr);
        if (astDecl instanceof AstFunDecl) {
            MemFrame memFrame = frames.get((AstFunDecl) astDecl);
            label = memFrame.label;

            Vector<Long> offsets = new Vector<>();
            Vector<ImcExpr> args = new Vector<>();

            offsets.add(0L);


            if (memFrame == currentFrames.peek()) {  // same function call, get address of pointer
                args.add(new ImcMEM(new ImcTEMP(memFrame.FP)));
            } else {
                ImcExpr link = new ImcTEMP(currentFrames.peek().FP);
                for (int i = 0; i < currentFrames.peek().depth - memFrame.depth+1; i++) {
                    link = new ImcMEM(link);
                }
                args.add(link);
            }


            for (AstExpr argExpr : callExpr.args) {
                SemType type = ofType.get(argExpr);
                offsets.add(offsets.lastElement()+type.size());
                args.add(ImcGen.exprImc.get(argExpr));
            }

            ImcCALL call = new ImcCALL(label, offsets, args);
            exprImc.put(callExpr, call);
        } else {
            return null;
        }
        return null;
    }

    @Override
    public Object visit(AstCastExpr castExpr, Object arg) {
        visit(castExpr.expr, arg);
        if (castExpr.type != null){
            visit(castExpr.type, arg);
            if(isType.get(castExpr.type) instanceof SemChar){
                ImcBINOP binop = new ImcBINOP(ImcBINOP.Oper.MOD, exprImc.get(castExpr.expr), new ImcCONST(256));
                exprImc.put(castExpr, binop);
            } else {
                exprImc.put(castExpr, exprImc.get(castExpr.expr));
            }
        } else {
            exprImc.put(castExpr, exprImc.get(castExpr.expr));
            return null;
        }
        ImcExpr code = exprImc.get(castExpr);
        return null;
    }

    @Override
    public Object visit(AstDelExpr delExpr, Object arg) {  
        visit(delExpr.expr, arg);

        MemLabel label = new MemLabel("del");
        ImcExpr imcExpr = exprImc.get(delExpr.expr);
        Vector<Long> offs = new Vector<>();
        Vector<ImcExpr> args = new Vector<>();
        offs.add(0L);
        args.add(imcExpr);

        ImcCALL call = new ImcCALL(label, offs, args);

        ImcGen.exprImc.put(delExpr, call);

        return null;
    }

    @Override
    public Object visit(AstNameExpr nameExpr, Object arg) {
        AstNameDecl nameDecl = declaredAt.get(nameExpr);
        MemAccess memAccess = null;
        ImcExpr imcExpr = null;

        if(nameDecl instanceof AstParDecl || nameDecl instanceof AstVarDecl){
            memAccess = accesses.get((AstMemDecl) nameDecl);
        }

        if (memAccess instanceof MemAbsAccess){
            ImcNAME imcNAME = new ImcNAME(((MemAbsAccess) memAccess).label);
            imcExpr = new ImcMEM(imcNAME);
        }

        if (memAccess instanceof MemRelAccess){
            MemFrame currentFrame = currentFrames.peek();
            ImcExpr fp = new ImcTEMP(currentFrame.FP);
            for(int ite = 0; ite < (currentFrames.size() - ((MemRelAccess) memAccess).depth - 1); ite++){
                fp = new ImcMEM(fp);
            }

            ImcCONST offset = new ImcCONST(((MemRelAccess) memAccess).offset);
            ImcBINOP imcBINOP = new ImcBINOP(ImcBINOP.Oper.ADD, fp, offset);
            imcExpr = new ImcMEM(imcBINOP);
        }

        exprImc.put(nameExpr, imcExpr);
        return null;
    }

    @Override
    public Object visit(AstNewExpr newExpr, Object arg) {  
        visit(newExpr.type, arg);

        MemLabel label = new MemLabel("new");
        Vector<Long> offs = new Vector<>();
        Vector<ImcExpr> args = new Vector<>();
        offs.add(8L);
        int val = 1;
        if(newExpr.type instanceof AstArrType arrType){
            val = Integer.parseInt(((AstAtomExpr)arrType.numElems).value);
        }

        args.add(new ImcCONST(val));

        ImcCALL call = new ImcCALL(label, offs, args);
        ImcGen.exprImc.put(newExpr, call);
        return null;
    }

    @Override
    public Object visit(AstPfxExpr pfxExpr, Object arg) {
        visit(pfxExpr.expr, arg);
        ImcExpr exprInside = exprImc.get(pfxExpr.expr);

        ImcExpr imcExpr = null;
        switch (pfxExpr.oper){
            case ADD:{
                imcExpr = exprInside;
            }break;
            case SUB:{
                imcExpr = new ImcUNOP(ImcUNOP.Oper.NEG, exprInside);
            }break;
            case NOT:{
                imcExpr = new ImcUNOP(ImcUNOP.Oper.NOT, exprInside);
            }break;
            case PTR:{  
                if (exprInside instanceof ImcMEM){
                    imcExpr = ((ImcMEM) exprInside).addr;
                } else {
                    throw new Report.Error("ERROR: Must have an address: " + pfxExpr.expr.location());
                }

            }break;
        }
        exprImc.put(pfxExpr, imcExpr);
        return null;
    }

    @Override
    public Object visit(AstRecExpr recExpr, Object arg) {
        visit(recExpr.rec, arg);
        visit(recExpr.comp, arg);

        ImcMEM recordMem = (ImcMEM) exprImc.get(recExpr.rec);
        AstCmpDecl cmpDecl = (AstCmpDecl) declaredAt.get(recExpr.comp);
        MemRelAccess relAccess = (MemRelAccess) accesses.get(cmpDecl);

        ImcBINOP imcBINOP = new ImcBINOP(ImcBINOP.Oper.ADD, recordMem.addr, new ImcCONST(relAccess.offset));
        ImcMEM mem = new ImcMEM(imcBINOP);
        ImcGen.exprImc.put(recExpr, mem);
        return null;
    }

    @Override
    public Object visit(AstSfxExpr sfxExpr, Object arg) {  
        visit(sfxExpr.expr, arg);
        if (sfxExpr.oper == null) return null;

        ImcExpr exprInside = exprImc.get(sfxExpr.expr);
        ImcExpr imcExpr = new ImcMEM(exprInside);

        exprImc.put(sfxExpr, imcExpr);
        return null;
    }

    // STATEMENTS

    public Object visit(AstStmt acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor instanceof AstAssignStmt) {  // no move to rv needed
            visit((AstAssignStmt) acceptor, arg);
        } else if (acceptor instanceof AstDeclStmt) {  // move to rv needed  -> covered in other visitors below the ast
            visit((AstDeclStmt) acceptor, arg);
        } else if (acceptor instanceof AstExprStmt) {  // move to rv needed
            visit((AstExprStmt) acceptor, arg);
        } else if (acceptor instanceof AstIfStmt) {  // no move to rv needed
            visit((AstIfStmt) acceptor, arg);
        } else if (acceptor instanceof AstStmts) {  // move to rv handled in visit function
            visit((AstStmts) acceptor, arg);
        } else if (acceptor instanceof AstWhileStmt) {  // no move to rv needed
            visit((AstWhileStmt) acceptor, arg);
        }
        return null;
    }

    @Override
    public Object visit(AstAssignStmt assignStmt, Object arg) {
        visit(assignStmt.src, arg);
        visit(assignStmt.dst, arg);

        ImcExpr imcExprFst = exprImc.get(assignStmt.dst);
        ImcExpr imcExprSnd = exprImc.get(assignStmt.src);
        ImcMOVE move = new ImcMOVE(imcExprFst, imcExprSnd);
        stmtImc.put(assignStmt, move);
        return null;
    }

    @Override
    public Object visit(AstDeclStmt declStmt, Object arg) {
        visit(declStmt.decls, arg);
        visit(declStmt.stmt, arg);

        ImcStmt stmt = stmtImc.get(declStmt.stmt);
        if (stmt instanceof ImcSTMTS stmts){
            if (stmts.stmts.get(stmts.stmts.size()-1) instanceof ImcLABEL){
                MemTemp rvTemp = currentFrames.peek().RV;
                ImcMOVE move = new ImcMOVE(new ImcMEM(new ImcTEMP(rvTemp)), new ImcCONST(0));
                if (stmts.stmts.get(stmts.stmts.size()-2) instanceof ImcSTMTS imcSTMTS){
                    Vector<ImcStmt> stmtsInside = imcSTMTS.stmts;
                    stmtsInside.remove(stmtsInside.size()-1);
                    stmts.stmts.set(stmts.stmts.size()-2, new ImcSTMTS(stmtsInside));
                }
                stmts.stmts.add(move);
            }
        }

        stmtImc.put(declStmt, stmt);
        ImcStmt code = ImcGen.stmtImc.get(declStmt);
        return null;
    }

    @Override
    public Object visit(AstExprStmt exprStmt, Object arg) {
        visit(exprStmt.expr, arg);
        ImcExpr imcExpr = exprImc.get(exprStmt.expr);
        ImcStmt imcStmt = new ImcESTMT(imcExpr);
        stmtImc.put(exprStmt, imcStmt);
        return null;
    }

    @Override
    public Object visit(AstIfStmt ifStmt, Object arg) {
        visit(ifStmt.cond, arg);
        visit(ifStmt.thenStmt, arg);
        if(ifStmt.elseStmt != null) {
            visit(ifStmt.elseStmt, arg);
        }
        ImcExpr exprCond = exprImc.get(ifStmt.cond);
        ImcStmt stmtThen = stmtImc.get(ifStmt.thenStmt);
        ImcStmt stmtElse = null;
        MemLabel labelL2 = new MemLabel();
        MemLabel labelL3 = new MemLabel();
        MemLabel labelL4 = new MemLabel();
        if (ifStmt.elseStmt != null) {
            stmtElse = stmtImc.get(ifStmt.elseStmt);
        }

        ImcCJUMP cjump = new ImcCJUMP(exprCond, labelL2, labelL3);

        Vector<ImcStmt> vecStmts = new Vector<>();

        if (ifStmt.elseStmt != null) {
            vecStmts.add(cjump);
            vecStmts.add(new ImcLABEL(labelL2));
            vecStmts.add(stmtThen);
            vecStmts.add(new ImcJUMP(labelL4));
            vecStmts.add(new ImcLABEL(labelL3));
            vecStmts.add(stmtElse);
            vecStmts.add(new ImcLABEL(labelL4));
        } else {
            vecStmts.add(cjump);
            vecStmts.add(new ImcLABEL(labelL2));
            vecStmts.add(stmtThen);
            vecStmts.add(new ImcLABEL(labelL3));
        }

        ImcSTMTS stmts = new ImcSTMTS(vecStmts);
        stmtImc.put(ifStmt, stmts);
        return null;
    }

    @Override
    public Object visit(AstStmts stmts, Object arg) {
        boolean isTopLevel = false;
        if(isVoidFun){
            isTopLevel = true;
            isVoidFun = false;
        }
        Vector<ImcStmt> vecStmts = new Vector<>();

        getRvNoSTMTS = false;  // if there is an aststmts, no need to put rv of exprs in
        boolean restoreRVFlag = getRV;
        getRV = false;


        for (int i = 0; i < stmts.stmts.size()-1; i++) {
            if (stmts.stmts.get(i) != null) {
                this.visit(stmts.stmts.get(i), arg);
                ImcStmt stmt = stmtImc.get(stmts.stmts.get(i));
                vecStmts.add(stmt);
            }
        }

        int lastIndex = stmts.stmts.size()-1;
        AstStmt lastStmtAst = stmts.stmts.get(lastIndex);
        if (lastStmtAst != null) {
            if (lastStmtAst instanceof AstDeclStmt lastStmtAstDecl){
                getRV = restoreRVFlag;
                this.visit(lastStmtAst, arg);  //TODO: maybe back in?
                this.visit(lastStmtAstDecl.decls, arg);
                this.visit(lastStmtAstDecl.stmt, arg);
                ImcStmt stmt = stmtImc.get(lastStmtAst);
                vecStmts.add(stmt);
                getRV = false;
            } else {
                this.visit(lastStmtAst, arg);
                ImcStmt stmt = stmtImc.get(lastStmtAst);
                vecStmts.add(stmt);
            }
        }



        getRV = restoreRVFlag;

        MemTemp rvTemp = currentFrames.peek().RV;
        ImcStmt lastStmt = stmtImc.get(stmts.stmts.get(vecStmts.size()-1));
        SemType lastType = ofType.get(stmts.stmts.get(vecStmts.size()-1));
        if(getRV && lastStmt instanceof ImcESTMT){
            vecStmts.remove(vecStmts.size() - 1);
            ImcMOVE move = new ImcMOVE(new ImcMEM(new ImcTEMP(rvTemp)), ((ImcESTMT) lastStmt).expr);
            vecStmts.add(move);
        }
         else if (getRV && lastType instanceof SemVoid && isTopLevel){
            ImcMOVE move = new ImcMOVE(new ImcMEM(new ImcTEMP(rvTemp)), new ImcCONST(0));
            vecStmts.add(move);
        }

        stmtImc.put(stmts, new ImcSTMTS(vecStmts));
        return null;
    }

    @Override
    public Object visit(AstWhileStmt whileStmt, Object arg) {
        visit(whileStmt.cond, arg);
        visit(whileStmt.bodyStmt, arg);

        ImcExpr exprCond = exprImc.get(whileStmt.cond);
        ImcStmt stmtBody = stmtImc.get(whileStmt.bodyStmt);
        MemLabel labelBefore = new MemLabel();
        MemLabel labelBody = new MemLabel();
        MemLabel labelOver = new MemLabel();

        ImcCJUMP cjump = new ImcCJUMP(exprCond, labelBody, labelOver);

        Vector<ImcStmt> vecStmts = new Vector<>();
        vecStmts.add(new ImcLABEL(labelBefore));
        vecStmts.add(cjump);
        vecStmts.add(new ImcLABEL(labelBody));
        vecStmts.add(stmtBody);
        vecStmts.add(new ImcJUMP(labelBefore));
        vecStmts.add(new ImcLABEL(labelOver));

        ImcSTMTS stmts = new ImcSTMTS(vecStmts);
        stmtImc.put(whileStmt, stmts);
        return null;
    }

    // TYPES

    public Object visit(AstType acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor instanceof AstArrType) {
            visit((AstArrType) acceptor, arg);
        } else if (acceptor instanceof AstAtomType) {
            visit((AstAtomType) acceptor, arg);
        } else if (acceptor instanceof AstNameType) {
            visit((AstNameType) acceptor, arg);
        } else if (acceptor instanceof AstPtrType) {
            visit((AstPtrType) acceptor, arg);
        } else if (acceptor instanceof AstRecType) {
            visit((AstRecType) acceptor, arg);
        }
        return null;
    }

    @Override
    public Object visit(AstArrType acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.elemType == null) return null;
        visit(acceptor.elemType, arg);
        if (acceptor.numElems == null) return null;
        visit(acceptor.numElems, arg);

        return null;
    }

    @Override
    public Object visit(AstAtomType acceptor, Object arg) {
        return null;
    }

    @Override
    public Object visit(AstNameType acceptor, Object arg) {
        return null;
    }

    @Override
    public Object visit(AstPtrType acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.baseType == null) {
            return null;
        }
        visit(acceptor.baseType, arg);
        return null;
    }

    @Override
    public Object visit(AstRecType acceptor, Object arg) {
        for (AstCmpDecl cmp : acceptor.comps) {
            visit(cmp, arg);
        }
        return null;
    }

}

