package prev23.phase.seman;
import prev23.common.report.Report;
import prev23.data.ast.tree.*;
import prev23.data.ast.tree.decl.*;
import prev23.data.ast.tree.expr.*;
import prev23.data.ast.tree.stmt.*;
import prev23.data.ast.tree.type.*;
import prev23.data.ast.visitor.*;
import prev23.data.typ.*;

import javax.net.ssl.ManagerFactoryParameters;
import java.util.*;

import static prev23.phase.seman.SemAn.*;


/**
 * Type resolver.
 */


public class TypeResolver extends AstFullVisitor<Object, Object> {

    private final HashMap<SemRec, SymbTable> symTables = new HashMap<>();
    public TypeResolver() {

    }

    // GENERAL PURPOSE
    boolean declaring = false;
    ArrayList<String> typNames = new ArrayList<>();
    ArrayList<String> typNamesUsed = new ArrayList<>();

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
            typNames.clear();
            typNamesUsed.clear();
            for (AstTree t : trees) {
                if (t == null) return null;
                this.visit((AstTypDecl) t, null);
            }
            declaring = false;
            for (AstTree t : trees) {
                if (t == null) return null;
                this.visit((AstTypDecl) t, null);
            }
            if(typNamesUsed.size() == typNames.size()){
                throw new Report.Error("ERROR: Recursive type not allowed: "+trees.location());
            }
            return null;
        }
        if (trees.title.equals("Fun-Declaration")) {
            declaring = true;
            for (AstTree t : trees) {
                if (t == null) return null;
                this.visit((AstFunDecl) t, null);
            }
            declaring = false;
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
            int ite = 0;
            AstTrees<AstParDecl> params = (AstTrees<AstParDecl>)arg;
            int paramsSize = 0;
            if (params != null) {
                paramsSize = params.size();
            }
            if (paramsSize != trees.size()){
                throw new Report.Error("ERROR: Not the same number of arguments: "+trees.location());
            }
            for (AstTree t : trees) {
                AstExpr returnValue = null;
                if (t != null) {
                    returnValue = (AstExpr) this.visit((AstExpr) t, null);
                }
                SemType paramCall = ofType.get(returnValue);

                SemType paramDef = isType.get(params.get(ite).type);

                if (!paramCall.getClass().equals(paramDef.getClass())){
                    throw new Report.Error("ERROR: Type of argument does not match definition: "+returnValue.location());
                }
                if (paramCall instanceof SemPtr){
                    if(!(comparePtr((SemPtr) paramCall, (SemPtr) paramDef))){
                        throw new Report.Error("ERROR: Expressions must be of the same type: " + returnValue.location() +" and "+ params.get(ite).location());
                    }
                }
                ite ++;
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
        if (declaring){
            if (acceptor.type != null) visit(acceptor.type, null);
            if (acceptor.pars != null) visit(acceptor.pars, null);
        } else {


            if (acceptor.stmt != null) visit(acceptor.stmt, null);

            assert acceptor.type != null;
            SemType returnType = isType.get(acceptor.type);
            if (acceptor.stmt != null){
                SemType stmtType = ofType.get(acceptor.stmt);
                if (!(returnType.getClass().equals(stmtType.getClass()))) {
                    throw new Report.Error("ERROR: Returned type does not match signature: " + acceptor.type.location());  // preconditions 1 & 2 failed
                }
                if (returnType instanceof SemPtr){
                    if(!(comparePtr((SemPtr) returnType, (SemPtr) stmtType))){
                        throw new Report.Error("ERROR: Returned type does not match signature: " + acceptor.type.location());
                    }
                }
            }
        }



        return null;
    }

    @Override
    public Object visit(AstParDecl acceptor, Object arg) {
        if (acceptor.type != null) {
            visit(acceptor.type, arg);
        }
        assert acceptor.type != null;
        if (isType.get(acceptor.type) instanceof SemVoid){
            throw new Report.Error("ERROR: Parameter must not be of type void: "+acceptor.type.location());
        }
        return null;
    }

    @Override
    public Object visit(AstTypDecl acceptor, Object arg) { 
        try {
            assert acceptor.type != null;

            if(declaring){
                SemName name = new SemName(acceptor.name);
                declaresType.put(acceptor, name);
                typNames.add(name.name);
            }else {
                visit(acceptor.type, acceptor.name);
                SemName name = declaresType.get(acceptor);
                SemType definedType = isType.get(acceptor.type);
                name.define(definedType);
                if((definedType instanceof SemName)){
                    typNamesUsed.add(((AstNameType)acceptor.type).name);
                    if (((SemName) definedType).name.equals(acceptor.name)) {
                        throw new Report.Error("ERROR: Recursive type not allowed: "+acceptor.type.location());
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new Report.Error("ERROR: Could not retrieve datatype of following type: "+acceptor.location());
        }
        return null;
    }

    @Override
    public Object visit(AstVarDecl acceptor, Object arg) {
        if (acceptor.type != null) visit(acceptor.type, acceptor.name);
        try {
            assert acceptor.type != null;
            SemType acceptorType = isType.get(acceptor.type);
            if (acceptorType instanceof SemVoid){
                throw new Report.Error("ERROR: Type must not be of type void: "+acceptor.type.location());
            }
        } catch (Exception e){
            throw new Report.Error("ERROR: Could not retrieve datatype of following type: "+acceptor.location());
        }
        return null;
    }

    // EXPRESSIONS

    public Object visit(AstExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        Object returnValue = null;
        if (acceptor instanceof AstArrExpr) {
            returnValue = visit((AstArrExpr) acceptor, arg);
            Boolean addrArr = isAddr.get(((AstArrExpr) acceptor).arr);
            if (addrArr != null && addrArr){
                isAddr.put(acceptor, true);
            } else{
                isAddr.put(acceptor, false);
            }
        } else if (acceptor instanceof AstAtomExpr) {
            returnValue = visit((AstAtomExpr) acceptor, arg);
            isAddr.put(acceptor, false);
        } else if (acceptor instanceof AstBinExpr) {
            returnValue = visit((AstBinExpr) acceptor, arg);
            isAddr.put(acceptor, false);
        } else if (acceptor instanceof AstCallExpr) {
            returnValue = visit((AstCallExpr) acceptor, arg);
            isAddr.put(acceptor, false);
        } else if (acceptor instanceof AstCastExpr) {
            returnValue = visit((AstCastExpr) acceptor, arg);
            isAddr.put(acceptor, false);
        } else if (acceptor instanceof AstDelExpr) {
            returnValue = visit((AstDelExpr) acceptor, arg);
            isAddr.put(acceptor, false);
        } else if (acceptor instanceof AstNameExpr) {
            returnValue = visit((AstNameExpr) acceptor, arg);

            if ((Boolean)returnValue){
                isAddr.put(acceptor, true);
            } else{
                isAddr.put(acceptor, false);
            }
            returnValue = acceptor;
        } else if (acceptor instanceof AstNewExpr) {
            returnValue = visit((AstNewExpr) acceptor, arg);
            isAddr.put(acceptor, false);
        } else if (acceptor instanceof AstPfxExpr) {
            returnValue = visit((AstPfxExpr) acceptor, arg);
            isAddr.put(acceptor, false);
        } else if (acceptor instanceof AstRecExpr) {
            returnValue = visit((AstRecExpr) acceptor, arg);

            Boolean addrArr = isAddr.get(((AstRecExpr) acceptor).rec);
            if (addrArr != null && addrArr){
                isAddr.put(acceptor, true);
            } else{
                isAddr.put(acceptor, false);
            }
            returnValue = acceptor;
        } else if (acceptor instanceof AstSfxExpr) {
            returnValue = visit((AstSfxExpr) acceptor, arg);

            if(isAddr.get(((AstSfxExpr) acceptor).expr)){
                isAddr.put(acceptor, true);
            }
        }
        return returnValue;
    }

    @Override
    public Object visit(AstArrExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.arr != null) visit(acceptor.arr, arg);
        if (acceptor.idx != null) visit(acceptor.idx, arg);

        // preconditions:
        SemType semElemType;
        SemType semElemTypeIdx;
        SemType arrType;
        try {
            assert acceptor.arr != null;
            semElemType = ofType.get(acceptor.arr);
            if(!(semElemType instanceof SemArr)){
                throw new Report.Error("ERROR: Expression must be of type array: "+acceptor.arr.location());
            }
            arrType = ((SemArr)semElemType).elemType;
            assert acceptor.idx != null;
            semElemTypeIdx = ofType.get(acceptor.idx);

        } catch (Exception e) {
            throw new Report.Error("ERROR: Could not retrieve datatype of following type: "+acceptor.location());
        }
        ofType.put(acceptor, ((SemArr) semElemType).elemType);
        return acceptor;
    }

    @Override
    public Object visit(AstAtomExpr acceptor, Object arg) {
        switch (acceptor.type) {
            case VOID -> ofType.put(acceptor, new SemVoid());
            case PTR -> ofType.put(acceptor, new SemPtr(new SemVoid()));
            case STR -> ofType.put(acceptor, new SemPtr(new SemChar()));
            case BOOL -> ofType.put(acceptor, new SemBool());
            case CHAR -> ofType.put(acceptor, new SemChar());
            case INT -> ofType.put(acceptor, new SemInt());
        }
        return acceptor;
    }

    @Override
    public Object visit(AstBinExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.fstExpr == null) return null;
        visit(acceptor.fstExpr, arg);
        if (acceptor.sndExpr == null) return null;
        visit(acceptor.sndExpr, arg);

        switch (acceptor.oper){
            case OR:  //fallthrough
            case AND: {
                // preconditions:
                try {
                    // precondition 1
                    SemType semElemTypeFst = ofType.get(acceptor.fstExpr);
                    if (!(semElemTypeFst instanceof SemBool)) {
                        throw new Report.Error("ERROR: Expression must be of type bool: " + acceptor.fstExpr.location());  // precondition 1 failed
                    }

                    // precondition 2
                    SemType semElemTypeSnd = ofType.get(acceptor.sndExpr);
                    if (!(semElemTypeSnd instanceof SemBool)) {
                        throw new Report.Error("ERROR: Expression must be of type bool: " + acceptor.sndExpr.location());  // precondition 2 failed
                    }
                } catch (Exception e) {
                    throw new Report.Error("ERROR: Could not retrieve datatype of following type: " + acceptor.location());
                }
                ofType.put(acceptor, new SemBool());
            } break;
            case ADD:  //fallthrough
            case SUB:  //fallthrough
            case MUL:  //fallthrough
            case DIV:  //fallthrough
            case MOD: {
                // preconditions:
                try {
                    // precondition 1
                    SemType semElemTypeFst = ofType.get(acceptor.fstExpr);
                    if (!(semElemTypeFst instanceof SemInt)) {
                        throw new Report.Error("ERROR: Expression must be of type int: " + acceptor.fstExpr.location());  // precondition 1 failed
                    }

                    // precondition 2
                    SemType semElemTypeSnd = ofType.get(acceptor.sndExpr);
                    if (!(semElemTypeSnd instanceof SemInt)) {
                        throw new Report.Error("ERROR: Expression must be of type int: " + acceptor.sndExpr.location());  // precondition 2 failed
                    }
                } catch (Exception e) {
                    throw new Report.Error("ERROR: Could not retrieve datatype of following type: " + acceptor.location());
                }
                ofType.put(acceptor, new SemInt());
            } break;
            case EQU:  //fallthrough
            case NEQ: {
                // preconditions:
                try {
                    // precondition 1
                    SemType semElemType = ofType.get(acceptor.fstExpr);

                    // precondition 2
                    SemType semElemTypeSnd = ofType.get(acceptor.sndExpr);
                    if (!(semElemType.getClass().equals(semElemTypeSnd.getClass()))) {
                        throw new Report.Error("ERROR: Expressions must be of the same type: " + acceptor.fstExpr.location() +" and "+ acceptor.sndExpr.location());  // preconditions 1 & 2 failed
                    }
                    if (semElemType instanceof SemPtr){
                        if(!(comparePtr((SemPtr) semElemType, (SemPtr) semElemTypeSnd))){
                            throw new Report.Error("ERROR: Expressions must be of the same type: " + acceptor.fstExpr.location() +" and "+ acceptor.sndExpr.location());
                        }
                    }else if (!(semElemType instanceof SemBool || semElemType instanceof SemChar || semElemType instanceof SemInt)){
                        throw new Report.Error("ERROR: Expressions must be of type bool, char, int, or be a pointer: " + acceptor.fstExpr.location() +" and "+ acceptor.sndExpr.location());  // precondition 3 failed
                    }
                } catch (Exception e) {
                    throw new Report.Error("ERROR: Could not retrieve datatype of following type: " + acceptor.location());
                }
                ofType.put(acceptor, new SemBool());
            } break;
            case LEQ:  //fallthrough
            case GEQ:  //fallthrough
            case LTH:  //fallthrough
            case GTH: {
                // preconditions:
                try {
                    // precondition 1
                    SemType semElemType = ofType.get(acceptor.fstExpr);

                    // precondition 2
                    SemType semElemTypeSnd = ofType.get(acceptor.sndExpr);
                    if (!(semElemType.getClass().equals(semElemTypeSnd.getClass()))) {
                        throw new Report.Error("ERROR: Expressions must be of the same type: " + acceptor.fstExpr.location() +" and "+ acceptor.sndExpr.location());  // preconditions 1 & 2 failed
                    }if (semElemType instanceof SemPtr){
                        if(!(comparePtr((SemPtr) semElemType, (SemPtr) semElemTypeSnd))){
                            throw new Report.Error("ERROR: Expressions must be of the same type: " + acceptor.fstExpr.location() +" and "+ acceptor.sndExpr.location());
                        }
                    }else if (!(semElemType instanceof SemChar || semElemType instanceof SemInt)){
                        throw new Report.Error("ERROR: Expressions must be of type char, int, or be a pointer: " + acceptor.fstExpr.location() +" and "+ acceptor.sndExpr.location());  // precondition 3 failed
                    }
                } catch (Exception e) {
                    throw new Report.Error("ERROR: Could not retrieve datatype of following type: " + acceptor.location());
                }
                ofType.put(acceptor, new SemBool());
            }
        }
        return acceptor;
    }


    public boolean comparePtr(SemPtr ptr1, SemPtr ptr2){
        if (!(ptr1.baseType instanceof SemPtr) || !(ptr2.baseType instanceof SemPtr)){
            if (ptr1.baseType.actualType().equals(ptr2.baseType.actualType())){
                return true;
            }
            return ptr1.baseType.getClass().equals(ptr2.baseType.getClass());
        }
        try{
            return comparePtr((SemPtr) ptr1.baseType, (SemPtr) ptr2.baseType);
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Object visit(AstCallExpr acceptor, Object arg) {
        AstNameDecl acceptorType = declaredAt.get(acceptor);

        if(!(acceptorType instanceof AstFunDecl)){
            throw new Report.Error("ERROR: This identifier does not represent a function: "+acceptor.location());
        }
        SemType name = isType.get(((AstFunDecl)acceptorType).type);
        ofType.put(acceptor, name);

        AstTrees<AstParDecl> params = ((AstFunDecl)acceptorType).pars;
        if (acceptor.args != null) visit(acceptor.args, params);
        return acceptor;
    }

    @Override
    public Object visit(AstCastExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.expr != null) visit(acceptor.expr, arg);
        if (acceptor.type != null) visit(acceptor.type, arg);

        assert acceptor.expr != null;
        SemType exprType = ofType.get(acceptor.expr);
        if (acceptor.type == null){
            ofType.put(acceptor, exprType);
        } else {
            SemType typeType = isType.get(acceptor.type);
            if (!(exprType instanceof SemInt || exprType instanceof SemChar || exprType instanceof SemPtr) || !(typeType instanceof SemInt || typeType instanceof SemChar || typeType instanceof SemPtr)) {
                throw new Report.Error("ERROR: Cast-elements must be of type int, char or pointer: " + acceptor.location());
            }
            ofType.put(acceptor, typeType);
        }
        return acceptor;
    }

    @Override
    public Object visit(AstDelExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.expr != null) visit(acceptor.expr, arg);

        // preconditions:
        SemType semElemType;
        try {
            assert acceptor.expr != null;
            semElemType = ofType.get(acceptor.expr);
            if (!(semElemType instanceof SemPtr)){
                throw new Report.Error("ERROR: Expression must be of type pointer: "+acceptor.expr.location());
            }
        } catch (Exception e) {
            throw new Report.Error("ERROR: Could not retrieve datatype of following type: "+acceptor.location());
        }
        ofType.put(acceptor, new SemVoid());
        return acceptor;
    }

    @Override
    public Object visit(AstNameExpr acceptor, Object arg) {
        AstNameDecl acceptorType = declaredAt.get(acceptor);  // must be ofType
        if (acceptorType instanceof AstVarDecl) {
            SemType name = isType.get(((AstVarDecl) acceptorType).type);
            ofType.put(acceptor, name);
            return Boolean.TRUE;
        } else if (acceptorType instanceof AstParDecl) {
            SemType name = isType.get(((AstParDecl) acceptorType).type);
            ofType.put(acceptor, name);
            return Boolean.TRUE;
        }else if (acceptorType instanceof AstFunDecl) {
            throw new Report.Error("ERROR: This is an identifier to a function missing its call: "+acceptor.location());
        } else {
            if (acceptorType instanceof AstTypDecl){
                throw new Report.Error("ERROR: This is not an expression: "+acceptor.location());
            }
            SemName name = declaresType.get((AstTypDecl) acceptorType);
            ofType.put(acceptor, name.type());
            return Boolean.FALSE;
        }
    }

    @Override
    public Object visit(AstNewExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.type != null) visit(acceptor.type, arg);

        // preconditions:
        SemType semElemType;
        try {
            assert acceptor.type != null;
            semElemType = isType.get(acceptor.type);
        } catch (Exception e) {
            throw new Report.Error("ERROR: Could not retrieve datatype of following type: "+acceptor.location());
        }
        ofType.put(acceptor, new SemPtr(semElemType));

        return acceptor;
    }

    @Override
    public Object visit(AstPfxExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.expr == null) return null;
        visit(acceptor.expr, arg);
        switch (acceptor.oper) {
            //(v3)
            case NOT: {
                // preconditions:
                try {
                    SemType semElemType = ofType.get(acceptor.expr);
                    if (!(semElemType instanceof SemBool)){  // precondition 1
                        throw new Report.Error("ERROR: Expression must be of type boolean: "+acceptor.location());  // precondition 1 failed
                    }
                } catch (Exception e) {
                    throw new Report.Error("ERROR: Could not retrieve datatype of following type: "+acceptor.location());
                }
                ofType.put(acceptor, new SemBool());

            }break;
            case ADD:  //fallthrough
            case SUB: {
                // preconditions:
                try {
                    SemType semElemType = ofType.get(acceptor.expr);
                    if (!(semElemType instanceof SemInt)) {  // precondition 1
                        throw new Report.Error("ERROR: Expression must be of type int: " + acceptor.location());  // precondition 1 failed
                    }
                } catch (Exception e) {
                    throw new Report.Error("ERROR: Could not retrieve datatype of following type: " + acceptor.location());
                }
                ofType.put(acceptor, new SemInt());
            }break;
            //(v8.1)
            case PTR: {
                // preconditions:
                SemType semElemType = null;
                try {
                    semElemType = ofType.get(acceptor.expr);
                } catch (Exception e) {
                    throw new Report.Error("ERROR: Could not retrieve datatype of following type: " + acceptor.location());
                }
                ofType.put(acceptor, new SemPtr(semElemType));
            }break;
        }
        return acceptor;
    }

    @Override
    public Object visit(AstRecExpr acceptor, Object arg) {
        assert acceptor.rec != null;
        assert acceptor.comp != null;
        visit(acceptor.rec, arg);

        SemType typeRec = SemAn.ofType.get(acceptor.rec);

        if (typeRec instanceof SemName){
            typeRec = typeRec.actualType();
        }
        if (!(typeRec instanceof SemRec)) {
            throw new Report.Error("ERROR: Record not of type rec: "+acceptor.rec.location());
        }
        try {
            AstCmpDecl compDecl = (AstCmpDecl) symTables.get(typeRec).fnd(acceptor.comp.name);
            ofType.put(acceptor, isType.get(compDecl.type).actualType());
            declaredAt.put(acceptor.comp, compDecl);
            isAddr.put(acceptor, isAddr.get(acceptor.rec));
        } catch (SymbTable.CannotFndNameException __) {
            throw new Report.Error("ERROR: Component was not found!");
        }
        return acceptor;
    }

    @Override
    public Object visit(AstSfxExpr acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.expr != null) visit(acceptor.expr, arg);
        SemType semElemType;
        // preconditions:
        try {
            assert acceptor.expr != null;
            semElemType = ofType.get(acceptor.expr);
            if (!(semElemType instanceof SemPtr)){
                throw new Report.Error("ERROR: You can only dereference a pointer: "+acceptor.expr.location());
            }
        } catch (Exception e) {
            throw new Report.Error("ERROR: Could not retrieve datatype of following type: "+acceptor.location());
        }
        ofType.put(acceptor, ((SemPtr)semElemType).baseType);
        return acceptor;
    }

    // STATEMENTS

    public Object visit(AstStmt acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor instanceof AstAssignStmt) {
            visit((AstAssignStmt) acceptor, arg);
        } else if (acceptor instanceof AstDeclStmt) {
            visit((AstDeclStmt) acceptor, arg);
        } else if (acceptor instanceof AstExprStmt) {
            visit((AstExprStmt) acceptor, arg);
        } else if (acceptor instanceof AstIfStmt) {
            visit((AstIfStmt) acceptor, arg);
        } else if (acceptor instanceof AstStmts) {
            visit((AstStmts) acceptor, arg);
        } else if (acceptor instanceof AstWhileStmt) {
            visit((AstWhileStmt) acceptor, arg);
        }
        return null;
    }

    @Override
    public Object visit(AstAssignStmt acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.dst != null) visit(acceptor.dst, arg);
        if (acceptor.src != null) visit(acceptor.src, arg);

        try{
            assert acceptor.dst != null;
            if(!isAddr.get(acceptor.dst)){
                throw new Report.Error("ERROR: Assignment to left expression at: " + acceptor.dst.location()+ " not possible!");
            }
            SemType dstType = ofType.get(acceptor.dst);
            assert acceptor.src != null;
            SemType srcType = ofType.get(acceptor.src);

            if (!(dstType.getClass().equals(srcType.getClass()))) {
                throw new Report.Error("ERROR: Expressions must be of the same type: " + acceptor.dst.location() +" and "+ acceptor.src.location());  // preconditions 1 & 2 failed
            }
            if (srcType instanceof SemPtr){
                if(!(comparePtr((SemPtr) srcType, (SemPtr) dstType))){
                    throw new Report.Error("ERROR: Expressions must be of the same type: " + acceptor.dst.location() +" and "+ acceptor.src.location());
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        ofType.put(acceptor, new SemVoid());
        return null;
    }

    @Override
    public Object visit(AstDeclStmt acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.decls != null) visit(acceptor.decls, arg);
        if (acceptor.stmt != null) visit(acceptor.stmt, arg);

        assert acceptor.stmt != null;
        SemType stmtType = ofType.get(acceptor.stmt);
        ofType.put(acceptor, stmtType);

        return null;
    }

    @Override
    public Object visit(AstExprStmt acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.expr != null) visit(acceptor.expr, arg);

        assert acceptor.expr != null;
        SemType exprType = ofType.get(acceptor.expr);
        ofType.put(acceptor, exprType);
        return null;
    }

    @Override
    public Object visit(AstIfStmt acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.cond != null) visit(acceptor.cond, arg);
        if (acceptor.thenStmt != null) visit(acceptor.thenStmt, arg);
        if (acceptor.elseStmt != null) visit(acceptor.elseStmt, arg);

        if (!(ofType.get(acceptor.cond) instanceof SemBool)){
            throw new Report.Error("ERROR: Condition must be of type bool: "+acceptor.cond.location());
        }

        ofType.put(acceptor, new SemVoid());
        return null;
    }

    @Override
    public Object visit(AstStmts acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.stmts == null) return null;
        visit(acceptor.stmts, arg);

        SemType lastType = ofType.get(acceptor.stmts.get(acceptor.stmts.size()-1));
        ofType.put(acceptor, lastType);
        return null;
    }

    @Override
    public Object visit(AstWhileStmt acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.cond == null) return null;
        visit(acceptor.cond, arg);
        if (acceptor.bodyStmt == null) return null;
        visit(acceptor.bodyStmt, arg);

        if (!(ofType.get(acceptor.cond) instanceof SemBool)){
            throw new Report.Error("ERROR: Condition must be of type bool: "+acceptor.cond.location());
        }

        ofType.put(acceptor, new SemVoid());
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
        Object returnValue = visit(acceptor.numElems, arg);

        // preconditions:
        SemType semElemType;
        long numElements = -1;
        try {
            semElemType = isType.get(acceptor.elemType);  // precondition 1
            if (semElemType == null) throw new Exception();
            if(acceptor.numElems instanceof AstPfxExpr){
                if (((AstPfxExpr)acceptor.numElems).oper == AstPfxExpr.Oper.SUB){
                    throw new Report.Error("ERROR: Number of elements must be greater than zero: "+acceptor.numElems.location());
                } else {
                    if (!(((AstPfxExpr)acceptor.numElems).expr instanceof AstAtomExpr)){
                        throw new Report.Error("ERROR: Must be an int: "+((AstPfxExpr)acceptor.numElems).expr.location());
                    }
                    returnValue = ((AstAtomExpr)((AstPfxExpr)acceptor.numElems).expr).value;
                }
            } else {
                if (!(returnValue instanceof AstAtomExpr)) {  //check return type
                    throw new Report.Error("ERROR: Must be an int: "+acceptor.numElems.location());
                }
                returnValue = ((AstAtomExpr) returnValue).value;
            }

        } catch (Exception e){  // precondition 1 failed
            throw new Report.Error("ERROR: Could not retrieve datatype of following type: "+acceptor.location());
        }

        if (semElemType instanceof SemVoid) {  // precondition 4
        // precondition 4 failed
            throw new Report.Error("ERROR: Following datatype not allowed: "+acceptor.elemType.location());
        }
        try{
            numElements = Long.parseLong((String)returnValue);  // precondition 2
        } catch (Exception e){  // precondition 2 or 3 failed
            throw new Report.Error("ERROR: Not a number or number to small/to big: "+acceptor.numElems.location());
        }
        if(!(numElements > 0 && numElements <= (Math.pow(2, 63)-1))){  // precondition 3 might be redundant here
            // precondition 3 failed
            throw new Report.Error("ERROR: Number to small or to big: "+acceptor.numElems.location());
        }
        isType.put(acceptor, new SemArr(semElemType, numElements));
        return null;
    }

    @Override
    public Object visit(AstAtomType acceptor, Object arg) {
        switch (acceptor.type) {
            case VOID -> isType.put(acceptor, new SemVoid());
            case CHAR -> isType.put(acceptor, new SemChar());
            case INT -> isType.put(acceptor, new SemInt());
            case BOOL -> isType.put(acceptor, new SemBool());
        }
        return null;
    }

    @Override
    public Object visit(AstNameType acceptor, Object arg) {
        AstNameDecl acceptorType = declaredAt.get(acceptor);
        if(acceptorType instanceof AstTypDecl) {
            SemName name = declaresType.get((AstTypDecl) acceptorType);
            if(name == null){
                throw new Report.Error("ERROR: Typ not declared yet: "+acceptorType.location());
            }
            if (name.type() == null){
                isType.put(acceptor, name);
            } else {
                isType.put(acceptor, name.type());
            }
        } else {
            throw new Report.Error("ERROR: Not a type declaration: "+acceptor.location());

        }
        return null;
    }

    @Override
    public Object visit(AstPtrType acceptor, Object arg) {
        if (acceptor == null) return null;
        if (acceptor.baseType == null) {
            return null;
        }
        visit(acceptor.baseType, arg);
        SemType semElemType;
        try {
            semElemType = isType.get(acceptor.baseType);  // precondition 1
        } catch (Exception e){
            throw new Report.Error("ERROR: Could not retrieve datatype of following type: "+acceptor.location());
        }
        isType.put(acceptor, new SemPtr(semElemType));
        return null;
    }

    @Override
    public Object visit(AstRecType acceptor, Object arg) {
        Vector<SemType> typesCmp = new Vector<>();
        SymbTable crrntRecord = new SymbTable();


        for (AstCmpDecl cmp : acceptor.comps) {
            visit(cmp, arg);
        }
        for (AstCmpDecl cmp : acceptor.comps) {
            try {
                SemType typeCmp = isType.get(cmp.type);


                if (typeCmp instanceof SemVoid) {
                    throw new Report.Error( "ERROR: Component must not be of type void.");
                } else {
                    crrntRecord.ins(cmp.name, cmp);
                    typesCmp.add(typeCmp);
                }
            } catch (SymbTable.CannotInsNameException e) {
                throw new Report.Error("ERROR: Component can not be defined again!");

            }
        }
        SemRec semRec = new SemRec(typesCmp);
        symTables.put(semRec, crrntRecord);
        isType.put(acceptor, semRec);
        return null;
    }
}

