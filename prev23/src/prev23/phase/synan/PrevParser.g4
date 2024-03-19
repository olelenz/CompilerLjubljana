parser grammar PrevParser;

@header {

	package prev23.phase.synan;
	
	import java.util.*;
	
	import prev23.common.report.*;
	import prev23.phase.lexan.*;
	import prev23.phase.abstr.*;
	import prev23.data.ast.tree.*;
	import prev23.data.ast.attribute.*;
	import prev23.data.ast.tree.expr.*;
	import prev23.data.ast.tree.type.*;
	import prev23.data.ast.tree.decl.*;
	import prev23.data.ast.tree.stmt.*;
	
}

options{
    tokenVocab=PrevLexer;
}

source returns [AstTrees ast]
  : source_ext EOF {$ast = $source_ext.ast;}
  ;

source_ext returns [AstTrees ast]
  : {Vector<AstTrees> declarations = new Vector<>();}
  (type_declarations {declarations.add($type_declarations.ast);}
  | function_declarations {declarations.add($function_declarations.ast);}
  | variable_declarations {declarations.add($variable_declarations.ast);})+
  {$ast = new AstTrees("Declarations", declarations);}
  ;

type_declarations returns [AstTrees ast]
  : {Vector<AstTypDecl> type_declarations = new Vector<>();}

  (KEYWORD_TYP IDENTIFIER SYMBOL_EQUAL type

    {
        type_declarations.add(new AstTypDecl(new Location($IDENTIFIER.line, $IDENTIFIER.getCharPositionInLine(), $type.endLine, $type.endColumn), $IDENTIFIER.text, (AstType)$type.ast));
    }

  (
  SYMBOL_COMMA IDENTIFIER SYMBOL_EQUAL type
    {
        type_declarations.add(new AstTypDecl(new Location($IDENTIFIER.line,$IDENTIFIER.getCharPositionInLine(), $type.endLine, $type.endColumn), $IDENTIFIER.text, (AstType)$type.ast));
    }
  )*?
  SYMBOL_SEMICOLON)


  {
        $ast = new AstTrees(new Location($KEYWORD_TYP.line, $KEYWORD_TYP.getCharPositionInLine(), $SYMBOL_SEMICOLON.line, $SYMBOL_SEMICOLON.getCharPositionInLine()+$SYMBOL_SEMICOLON.getText().length()), "Typ-Declaration", type_declarations);
  }
  ;

function_declarations returns [AstTrees ast]
  :
  {Vector<AstFunDecl> fun_declarations = new Vector<>();
  AstTrees<AstParDecl> parameters = null;
  int lastLine = 0;
  int lastColumn = 0;}
  (
      KEYWORD_FUN funid_out = IDENTIFIER SYMBOL_ROUND_BRACKET_OPEN
      {Vector<AstParDecl> parameters_vector = new Vector<>();}
          (
            IDENTIFIER SYMBOL_COLON type
            {AstParDecl param = new AstParDecl(new Location($IDENTIFIER.line,$IDENTIFIER.getCharPositionInLine(), $type.endLine, $type.endColumn), $IDENTIFIER.text, (AstType)$type.ast); parameters_vector.add(param);}
            (
                SYMBOL_COMMA IDENTIFIER SYMBOL_COLON type
                {AstParDecl param_add = new AstParDecl(new Location($SYMBOL_COMMA.line,$SYMBOL_COMMA.getCharPositionInLine(), $type.endLine, $type.endColumn), $IDENTIFIER.text, (AstType)$type.ast); parameters_vector.add(param_add);}
            )*?
            {parameters = new AstTrees("Par-Declarations", parameters_vector);}
          )?

      SYMBOL_ROUND_BRACKET_CLOSE SYMBOL_COLON type
      {AstType fun_type = (AstType)$type.ast; AstStmt fun_stmt = null; lastLine = $type.endLine; lastColumn = $type.endColumn;}

        (
            SYMBOL_EQUAL statement
            {
                fun_stmt = $statement.ast;
                lastLine = getCurrentToken().getLine(); lastColumn = getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length();
            }
        )?
       {AstFunDecl func_decl = new AstFunDecl(new Location($KEYWORD_FUN.line,$KEYWORD_FUN.getCharPositionInLine(), lastLine, lastColumn), $funid_out.text, parameters, fun_type, fun_stmt); fun_declarations.add(func_decl);}

      (
        SYMBOL_COMMA funid_in = IDENTIFIER SYMBOL_ROUND_BRACKET_OPEN
        {AstTrees<AstParDecl> parameters_more = null;
        Vector<AstParDecl> parameters_vector_more = new Vector<>();
        int lastLine_more = 0;
        int lastColumn_more = 0;}
          (
            IDENTIFIER SYMBOL_COLON type
            {AstParDecl param_more = new AstParDecl(new Location($IDENTIFIER.line,$IDENTIFIER.getCharPositionInLine(), $type.endLine, $type.endColumn), $IDENTIFIER.text, (AstType)$type.ast); parameters_vector_more.add(param_more);}
            (
                SYMBOL_COMMA IDENTIFIER SYMBOL_COLON type
                {AstParDecl param_add_more = new AstParDecl(new Location($SYMBOL_COMMA.line,$SYMBOL_COMMA.getCharPositionInLine(), $type.endLine, $type.endColumn), $IDENTIFIER.text, (AstType)$type.ast); parameters_vector_more.add(param_add_more);}
            )*?
            {parameters_more = new AstTrees("Par-Declarations", parameters_vector_more);}
          )?
        SYMBOL_ROUND_BRACKET_CLOSE SYMBOL_COLON type
        {AstType fun_type_more = (AstType)$type.ast; AstStmt fun_stmt_more = null; lastLine_more = $type.endLine; lastColumn_more = $type.endColumn;}
        (
          SYMBOL_EQUAL statement
          {
              fun_stmt_more = $statement.ast;
              lastLine_more = getCurrentToken().getLine(); lastColumn_more = getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length();
          }
        )?
        {AstFunDecl func_decl_more = new AstFunDecl(new Location($SYMBOL_COMMA.line,$SYMBOL_COMMA.getCharPositionInLine(), lastLine_more, lastColumn_more), $funid_in.text, parameters_more, fun_type_more, fun_stmt_more); fun_declarations.add(func_decl_more);}
      )*?
)
      SYMBOL_SEMICOLON
  {$ast = new AstTrees(new Location($KEYWORD_FUN.line,$KEYWORD_FUN.getCharPositionInLine(), $SYMBOL_SEMICOLON.line,$SYMBOL_SEMICOLON.getCharPositionInLine()+$SYMBOL_SEMICOLON.getText().length()),"Fun-Declaration", fun_declarations);}
  ;

variable_declarations returns [AstTrees ast]
  : {Vector<AstVarDecl> var_declarations = new Vector<>();}

  (KEYWORD_VAR IDENTIFIER SYMBOL_COLON type
    {var_declarations.add(new AstVarDecl(new Location($IDENTIFIER.line,$IDENTIFIER.getCharPositionInLine(), $type.endLine, $type.endColumn), $IDENTIFIER.text, (AstType)$type.ast));}
  (SYMBOL_COMMA IDENTIFIER SYMBOL_COLON type {var_declarations.add(new AstVarDecl(new Location($IDENTIFIER.line,$IDENTIFIER.getCharPositionInLine(), $type.endLine, $type.endColumn), $IDENTIFIER.text, (AstType)$type.ast));} )*?
  SYMBOL_SEMICOLON)

  {
        $ast = new AstTrees(new Location($KEYWORD_VAR.line,$KEYWORD_VAR.getCharPositionInLine(), $SYMBOL_SEMICOLON.line,$SYMBOL_SEMICOLON.getCharPositionInLine()+$SYMBOL_SEMICOLON.getText().length()), "Var-Declaration", var_declarations);
  }
  ;

type returns [AstTree ast, int begLine, int begColumn, int endLine, int endColumn]
  : KEYWORD_VOID {
         AstAtomType atom = new AstAtomType(new Location($KEYWORD_VOID.line, $KEYWORD_VOID.getCharPositionInLine(), $KEYWORD_VOID.line, $KEYWORD_VOID.getCharPositionInLine()+$KEYWORD_VOID.getText().length()), AstAtomType.Type.VOID);
         $ast = atom;
         $begLine = $KEYWORD_VOID.line;
         $begColumn = $KEYWORD_VOID.getCharPositionInLine();
         $endLine = $KEYWORD_VOID.line;
         $endColumn = $KEYWORD_VOID.getCharPositionInLine()+$KEYWORD_VOID.getText().length();
  }
  | KEYWORD_CHAR {
         AstAtomType atom = new AstAtomType(new Location($KEYWORD_CHAR.line, $KEYWORD_CHAR.getCharPositionInLine(), $KEYWORD_CHAR.line, $KEYWORD_CHAR.getCharPositionInLine()+$KEYWORD_CHAR.getText().length()), AstAtomType.Type.CHAR);
         $ast = atom;
         $begLine = $KEYWORD_CHAR.line;
         $begColumn = $KEYWORD_CHAR.getCharPositionInLine();
         $endLine = $KEYWORD_CHAR.line;
         $endColumn = $KEYWORD_CHAR.getCharPositionInLine()+$KEYWORD_CHAR.getText().length();
  }
  | KEYWORD_INT {
         AstAtomType atom = new AstAtomType(new Location($KEYWORD_INT.line, $KEYWORD_INT.getCharPositionInLine(), $KEYWORD_INT.line, $KEYWORD_INT.getCharPositionInLine()+$KEYWORD_INT.getText().length()), AstAtomType.Type.INT);
         $ast = atom;
         $begLine = $KEYWORD_INT.line;
         $begColumn = $KEYWORD_INT.getCharPositionInLine();
         $endLine = $KEYWORD_INT.line;
         $endColumn = $KEYWORD_INT.getCharPositionInLine()+$KEYWORD_INT.getText().length();
  }
  | KEYWORD_BOOL {
         AstAtomType atom = new AstAtomType(new Location($KEYWORD_BOOL.line, $KEYWORD_BOOL.getCharPositionInLine(), $KEYWORD_BOOL.line, $KEYWORD_BOOL.getCharPositionInLine()+$KEYWORD_BOOL.getText().length()), AstAtomType.Type.BOOL);
         $ast = atom;
         $begLine = $KEYWORD_BOOL.line;
         $begColumn = $KEYWORD_BOOL.getCharPositionInLine();
         $endLine = $KEYWORD_BOOL.line;
         $endColumn = $KEYWORD_BOOL.getCharPositionInLine()+$KEYWORD_BOOL.getText().length();
  }
  | IDENTIFIER {
        AstNameType name = new AstNameType(new Location($IDENTIFIER.line, $IDENTIFIER.getCharPositionInLine(), $IDENTIFIER.line, $IDENTIFIER.getCharPositionInLine()+$IDENTIFIER.getText().length()), $IDENTIFIER.text);
        $ast = name;
        $begLine = $IDENTIFIER.line;
        $begColumn = $IDENTIFIER.getCharPositionInLine();
        $endLine = $IDENTIFIER.line;
        $endColumn = $IDENTIFIER.getCharPositionInLine()+$IDENTIFIER.getText().length();
    }
  | SYMBOL_SQUARE_BRACKET_OPEN expression SYMBOL_SQUARE_BRACKET_CLOSE type {
        AstArrType arr = new AstArrType(new Location($SYMBOL_SQUARE_BRACKET_OPEN.line, $SYMBOL_SQUARE_BRACKET_OPEN.getCharPositionInLine(), $type.endLine, $type.endColumn), (AstType)$type.ast, $expression.ast);
        $ast = arr;
        $begLine = $SYMBOL_SQUARE_BRACKET_OPEN.line;
        $begColumn = $SYMBOL_SQUARE_BRACKET_OPEN.getCharPositionInLine();
        $endLine = $type.endLine;
        $endColumn = $type.endColumn;
    }
  | SYMBOL_CIRCUMFLEX type {
        AstPtrType pointer = new AstPtrType(new Location($SYMBOL_CIRCUMFLEX.line, $SYMBOL_CIRCUMFLEX.getCharPositionInLine(), $type.endLine, $type.endColumn), (AstType)$type.ast);
        $ast = pointer;
        $begLine = $SYMBOL_CIRCUMFLEX.line;
        $begColumn = $SYMBOL_CIRCUMFLEX.getCharPositionInLine();
        $endLine = $type.endLine;
        $endColumn = $type.endColumn;
    }
  |
    {Vector<AstTree> rec_declarations = new Vector<>();}
  (SYMBOL_CURLY_BRACKET_OPEN IDENTIFIER SYMBOL_COLON type
    {
        rec_declarations.add(new AstCmpDecl(new Location($SYMBOL_CURLY_BRACKET_OPEN.line, $SYMBOL_CURLY_BRACKET_OPEN.getCharPositionInLine(), $type.endLine, $type.endColumn), $IDENTIFIER.text, (AstType)$type.ast));
    }
      (
        SYMBOL_COMMA IDENTIFIER SYMBOL_COLON type
        {
            rec_declarations.add(new AstCmpDecl(new Location($SYMBOL_COMMA.line, $SYMBOL_COMMA.getCharPositionInLine(), $type.endLine, $type.endColumn), $IDENTIFIER.text, (AstType)$type.ast));
        }
      )*?
  SYMBOL_CURLY_BRACKET_CLOSE)
    {
        AstTrees recAst = new AstTrees("Rec-Declaration", rec_declarations);
        AstRecType rec = new AstRecType(new Location($SYMBOL_CURLY_BRACKET_OPEN.line, $SYMBOL_CURLY_BRACKET_OPEN.getCharPositionInLine(), $SYMBOL_CURLY_BRACKET_CLOSE.line, $SYMBOL_CURLY_BRACKET_CLOSE.getCharPositionInLine()+$SYMBOL_CURLY_BRACKET_CLOSE.getText().length()), recAst);
        $ast = rec;
        $begLine = $SYMBOL_CURLY_BRACKET_OPEN.line;
        $begColumn = $SYMBOL_CURLY_BRACKET_OPEN.getCharPositionInLine();
        $endLine = $SYMBOL_CURLY_BRACKET_CLOSE.line;
        $endColumn = $SYMBOL_CURLY_BRACKET_CLOSE.getCharPositionInLine()+$SYMBOL_CURLY_BRACKET_CLOSE.getText().length();
    }
  | SYMBOL_ROUND_BRACKET_OPEN type SYMBOL_ROUND_BRACKET_CLOSE {
        $ast = $type.ast;
        $begLine = $SYMBOL_ROUND_BRACKET_OPEN.line;
        $begColumn = $SYMBOL_ROUND_BRACKET_OPEN.getCharPositionInLine();
        $endLine = $SYMBOL_ROUND_BRACKET_CLOSE.line;
        $endColumn = $SYMBOL_ROUND_BRACKET_CLOSE.getCharPositionInLine()+$SYMBOL_ROUND_BRACKET_CLOSE.getText().length();
    }
  ;

expression returns [AstExpr ast]
  : expression_two expression_p [$expression_two.ast] {
        $ast = $expression_p.ast;
  }
  ;

expression_p [AstExpr left] returns [AstExpr ast]
  : SYMBOL_VERTICAL_BAR expression_two expression_p[new AstBinExpr(new Location($SYMBOL_VERTICAL_BAR.line,$SYMBOL_VERTICAL_BAR.getCharPositionInLine(), getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length()), AstBinExpr.Oper.OR, $left, $expression_two.ast)] {
         $ast = $expression_p.ast;
  }
  | {$ast = $left;}
  ;

expression_two returns [AstExpr ast]
  : expression_three expression_two_p [$expression_three.ast] {
        $ast = $expression_two_p.ast;
  }
  ;

expression_two_p [AstExpr left] returns [AstExpr ast]
  : SYMBOL_AND expression_three expression_two_p[new AstBinExpr(new Location($SYMBOL_AND.line,$SYMBOL_AND.getCharPositionInLine(), getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length()), AstBinExpr.Oper.AND, $left, $expression_three.ast)] {
        $ast = $expression_two_p.ast;
  }
  | {$ast = $left;}
  ;

expression_three returns [AstExpr ast]
  : expression_four expression_three_p [$expression_four.ast] {
        $ast = $expression_three_p.ast;
 }
  ;

expression_three_p [AstExpr left] returns [AstExpr ast]
  : relational_operator expression_four expression_four_p[new AstBinExpr(new Location($relational_operator.begLine, $relational_operator.begColumn, $relational_operator.endLine, $relational_operator.endColumn), $relational_operator.op, $left, $expression_four.ast)] {
        $ast = $expression_four_p.ast;
}
  | {$ast = $left;}
  ;

expression_four returns [AstExpr ast]
  : expression_five expression_four_p [$expression_five.ast] {
        $ast = $expression_four_p.ast;
  }
  ;

expression_four_p [AstExpr left] returns [AstExpr ast]
  : additive_operator expression_five expression_four_p[new AstBinExpr(new Location($additive_operator.begLine, $additive_operator.begColumn, $additive_operator.endLine, $additive_operator.endColumn), $additive_operator.op, $left, $expression_five.ast)] {
        $ast = $expression_four_p.ast;
  }
  | {$ast = $left;}
  ;

expression_five returns [AstExpr ast]
  : expression_six expression_five_p [$expression_six.ast]{
        $ast = $expression_five_p.ast;
  }
  ;

expression_five_p[AstExpr left] returns [AstExpr ast]
  : multiplicative_operator expression_six expression_five_p[new AstBinExpr(new Location($multiplicative_operator.begLine, $multiplicative_operator.begColumn, $multiplicative_operator.endLine, $multiplicative_operator.endColumn), $multiplicative_operator.op, $left, $expression_six.ast)] {
        $ast = $expression_five_p.ast;
  }
  | {$ast = $left;}
  ;

expression_six returns [AstExpr ast]
  : prefix_operator expression_six {
        $ast = new AstPfxExpr(new Location($prefix_operator.begLine, $prefix_operator.begColumn, $prefix_operator.endLine, $prefix_operator.endColumn), $prefix_operator.op, $expression_six.ast);
  }
  | expression_seven {$ast = $expression_seven.ast;}
  ;



expression_seven returns [AstExpr ast]
    : expr=expression_seven SYMBOL_CIRCUMFLEX {
                              $ast = new AstSfxExpr(new Location($SYMBOL_CIRCUMFLEX.line, $SYMBOL_CIRCUMFLEX.getCharPositionInLine(), getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length()), AstSfxExpr.Oper.PTR, $expr.ast);
                         }
    | expr=expression_seven SYMBOL_SQUARE_BRACKET_OPEN expression SYMBOL_SQUARE_BRACKET_CLOSE{
                              $ast = new AstArrExpr(new Location($SYMBOL_SQUARE_BRACKET_OPEN.line, $SYMBOL_SQUARE_BRACKET_OPEN.getCharPositionInLine(), getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length()), $expr.ast, $expression.ast);
                         }
    | expr=expression_seven SYMBOL_DOT IDENTIFIER{
                              AstNameExpr name_expr = new AstNameExpr(new Location($IDENTIFIER.line, $IDENTIFIER.getCharPositionInLine(), getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length()), $IDENTIFIER.text);
                              AstRecExpr rec_expr = new AstRecExpr(new Location($SYMBOL_DOT.line, $SYMBOL_DOT.getCharPositionInLine(), getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length()), $expr.ast, name_expr);
                              $ast = rec_expr;
                         }
    | expression_eight {$ast = $expression_eight.ast;}
    ;

expression_eight returns [AstExpr ast, int begLine, int begColumn, int endLine, int endColumn]
  : constant {
        $ast = $constant.const;
        $begLine = $constant.begLine;
        $begColumn = $constant.begColumn;
        $endLine = $constant.endLine;
        $endColumn = $constant.endColumn;
  }
  |
  {Vector<AstExpr> expr_vec = new Vector<>(); boolean isFunctionCall = false;}
  (IDENTIFIER (SYMBOL_ROUND_BRACKET_OPEN {isFunctionCall = true;}
  (
  expression {expr_vec.add($expression.ast);}
  (SYMBOL_COMMA expression {expr_vec.add($expression.ast);})*?
  )?
  SYMBOL_ROUND_BRACKET_CLOSE)?)
  {if (!isFunctionCall){
        $ast = new AstNameExpr(new Location($IDENTIFIER.line, $IDENTIFIER.getCharPositionInLine(), $IDENTIFIER.line, $IDENTIFIER.getCharPositionInLine()+$IDENTIFIER.getText().length()), $IDENTIFIER.text);
        $begLine = $IDENTIFIER.line;
        $begColumn = $IDENTIFIER.getCharPositionInLine();
        $endLine = $IDENTIFIER.line;
        $endColumn = $IDENTIFIER.getCharPositionInLine()+$IDENTIFIER.getText().length();
  }else{
        AstTrees trees = new AstTrees("Par-Declarations-Call", expr_vec);
        $ast = new AstCallExpr(new Location($IDENTIFIER.line, $IDENTIFIER.getCharPositionInLine(), getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length()), $IDENTIFIER.text, trees);}
        $begLine = $IDENTIFIER.line;
        $begColumn = $IDENTIFIER.getCharPositionInLine();
        $endLine = getCurrentToken().getLine();
        $endColumn = getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length();
  }
  | {AstType type_type = null;}
  (SYMBOL_ROUND_BRACKET_OPEN expression
  (SYMBOL_COLON type {type_type = (AstType)$type.ast;})?
  SYMBOL_ROUND_BRACKET_CLOSE)
  {
        $ast = new AstCastExpr(new Location($SYMBOL_ROUND_BRACKET_OPEN.line, $SYMBOL_ROUND_BRACKET_OPEN.getCharPositionInLine(), $SYMBOL_ROUND_BRACKET_CLOSE.line, $SYMBOL_ROUND_BRACKET_CLOSE.getCharPositionInLine()+$SYMBOL_ROUND_BRACKET_CLOSE.getText().length()),$expression.ast,type_type);
        $begLine = $SYMBOL_ROUND_BRACKET_OPEN.line;
        $begColumn = $SYMBOL_ROUND_BRACKET_OPEN.getCharPositionInLine();
        $endLine = $SYMBOL_ROUND_BRACKET_CLOSE.line;
        $endColumn = $SYMBOL_ROUND_BRACKET_CLOSE.getCharPositionInLine()+$SYMBOL_ROUND_BRACKET_CLOSE.getText().length();
  }

  | KEYWORD_NEW SYMBOL_ROUND_BRACKET_OPEN type SYMBOL_ROUND_BRACKET_CLOSE
  {
        $ast = new AstNewExpr(new Location($KEYWORD_NEW.line, $KEYWORD_NEW.getCharPositionInLine(), $SYMBOL_ROUND_BRACKET_CLOSE.line, $SYMBOL_ROUND_BRACKET_CLOSE.getCharPositionInLine()+$SYMBOL_ROUND_BRACKET_CLOSE.getText().length()),(AstType)$type.ast);
        $begLine = $KEYWORD_NEW.line;
        $begColumn = $KEYWORD_NEW.getCharPositionInLine();
        $endLine = $SYMBOL_ROUND_BRACKET_CLOSE.line;
        $endColumn = $SYMBOL_ROUND_BRACKET_CLOSE.getCharPositionInLine()+$SYMBOL_ROUND_BRACKET_CLOSE.getText().length();
  }
  | KEYWORD_DEL SYMBOL_ROUND_BRACKET_OPEN expression SYMBOL_ROUND_BRACKET_CLOSE
  {
        $ast = new AstDelExpr(new Location($KEYWORD_DEL.line, $KEYWORD_DEL.getCharPositionInLine(), $SYMBOL_ROUND_BRACKET_CLOSE.line, $SYMBOL_ROUND_BRACKET_CLOSE.getCharPositionInLine()+$SYMBOL_ROUND_BRACKET_CLOSE.getText().length()), $expression.ast);
        $begLine = $KEYWORD_DEL.line;
        $begColumn = $KEYWORD_DEL.getCharPositionInLine();
        $endLine = $SYMBOL_ROUND_BRACKET_CLOSE.line;
        $endColumn = $SYMBOL_ROUND_BRACKET_CLOSE.getCharPositionInLine()+$SYMBOL_ROUND_BRACKET_CLOSE.getText().length();
  }
  ;

constant returns [AstAtomExpr const, int begLine, int begColumn, int endLine, int endColumn]
  : CONSTANT_VOID {
      $const = new AstAtomExpr(new Location($CONSTANT_VOID.line,$CONSTANT_VOID.getCharPositionInLine(),$CONSTANT_VOID.line,$CONSTANT_VOID.getCharPositionInLine()+$CONSTANT_VOID.getText().length()), AstAtomExpr.Type.VOID, $CONSTANT_VOID.text);
      $begLine = $CONSTANT_VOID.line;
      $begColumn = $CONSTANT_VOID.getCharPositionInLine();
      $endLine = $CONSTANT_VOID.line;
      $endColumn = $CONSTANT_VOID.getCharPositionInLine()+$CONSTANT_VOID.getText().length();
  }
  | CONSTANT_TRUE {
      $const = new AstAtomExpr(new Location($CONSTANT_TRUE.line,$CONSTANT_TRUE.getCharPositionInLine(),$CONSTANT_TRUE.line,$CONSTANT_TRUE.getCharPositionInLine()+$CONSTANT_TRUE.getText().length()), AstAtomExpr.Type.BOOL, $CONSTANT_TRUE.text);
      $begLine = $CONSTANT_TRUE.line;
      $begColumn = $CONSTANT_TRUE.getCharPositionInLine();
      $endLine = $CONSTANT_TRUE.line;
      $endColumn = $CONSTANT_TRUE.getCharPositionInLine()+$CONSTANT_TRUE.getText().length();
  }
  | CONSTANT_FALSE {
      $const = new AstAtomExpr(new Location($CONSTANT_FALSE.line,$CONSTANT_FALSE.getCharPositionInLine(),$CONSTANT_FALSE.line,$CONSTANT_FALSE.getCharPositionInLine()+$CONSTANT_FALSE.getText().length()), AstAtomExpr.Type.BOOL, $CONSTANT_FALSE.text);
      $begLine = $CONSTANT_FALSE.line;
      $begColumn = $CONSTANT_FALSE.getCharPositionInLine();
      $endLine = $CONSTANT_FALSE.line;
      $endColumn = $CONSTANT_FALSE.getCharPositionInLine()+$CONSTANT_FALSE.getText().length();
  }
  | CONSTANT_INTEGER {
      $const = new AstAtomExpr(new Location($CONSTANT_INTEGER.line,$CONSTANT_INTEGER.getCharPositionInLine(),$CONSTANT_INTEGER.line,$CONSTANT_INTEGER.getCharPositionInLine()+$CONSTANT_INTEGER.getText().length()), AstAtomExpr.Type.INT, $CONSTANT_INTEGER.text);
      $begLine = $CONSTANT_INTEGER.line;
      $begColumn = $CONSTANT_INTEGER.getCharPositionInLine();
      $endLine = $CONSTANT_INTEGER.line;
      $endColumn = $CONSTANT_INTEGER.getCharPositionInLine()+$CONSTANT_INTEGER.getText().length();
  }
  | CONSTANT_CHAR {
      $const = new AstAtomExpr(new Location($CONSTANT_CHAR.line,$CONSTANT_CHAR.getCharPositionInLine(),$CONSTANT_CHAR.line,$CONSTANT_CHAR.getCharPositionInLine()+$CONSTANT_CHAR.getText().length()), AstAtomExpr.Type.CHAR, $CONSTANT_CHAR.text);
      $begLine = $CONSTANT_CHAR.line;
      $begColumn = $CONSTANT_CHAR.getCharPositionInLine();
      $endLine = $CONSTANT_CHAR.line;
      $endColumn = $CONSTANT_CHAR.getCharPositionInLine()+$CONSTANT_CHAR.getText().length();
  }
  | CONSTANT_STRING {
      $const = new AstAtomExpr(new Location($CONSTANT_STRING.line,$CONSTANT_STRING.getCharPositionInLine(),$CONSTANT_STRING.line,$CONSTANT_STRING.getCharPositionInLine()+$CONSTANT_STRING.getText().length()), AstAtomExpr.Type.STR, $CONSTANT_STRING.text);
      $begLine = $CONSTANT_STRING.line;
      $begColumn = $CONSTANT_STRING.getCharPositionInLine();
      $endLine = $CONSTANT_STRING.line;
      $endColumn = $CONSTANT_STRING.getCharPositionInLine()+$CONSTANT_STRING.getText().length();
  }
  | CONSTANT_POINTER {
      $const = new AstAtomExpr(new Location($CONSTANT_POINTER.line,$CONSTANT_POINTER.getCharPositionInLine(),$CONSTANT_POINTER.line,$CONSTANT_POINTER.getCharPositionInLine()+$CONSTANT_POINTER.getText().length()), AstAtomExpr.Type.PTR, $CONSTANT_POINTER.text);
      $begLine = $CONSTANT_POINTER.line;
      $begColumn = $CONSTANT_POINTER.getCharPositionInLine();
      $endLine = $CONSTANT_POINTER.line;
      $endColumn = $CONSTANT_POINTER.getCharPositionInLine()+$CONSTANT_POINTER.getText().length();
  }
  ;

prefix_operator returns [AstPfxExpr.Oper op, int begLine, int begColumn, int endLine, int endColumn]
  : SYMBOL_EXCLAMATION_MARK
  {
        $op = AstPfxExpr.Oper.NOT;
        $begLine = $SYMBOL_EXCLAMATION_MARK.line;
        $begColumn = $SYMBOL_EXCLAMATION_MARK.getCharPositionInLine();
        $endLine = $SYMBOL_EXCLAMATION_MARK.line;
        $endColumn = $SYMBOL_EXCLAMATION_MARK.getCharPositionInLine()+$SYMBOL_EXCLAMATION_MARK.getText().length();
  }
  | SYMBOL_PLUS
  {
        $op = AstPfxExpr.Oper.ADD;
        $begLine = $SYMBOL_PLUS.line;
        $begColumn = $SYMBOL_PLUS.getCharPositionInLine();
        $endLine = $SYMBOL_PLUS.line;
        $endColumn = $SYMBOL_PLUS.getCharPositionInLine()+$SYMBOL_PLUS.getText().length();
  }
  | SYMBOL_MINUS
  {
        $op = AstPfxExpr.Oper.SUB;
        $begLine = $SYMBOL_MINUS.line;
        $begColumn = $SYMBOL_MINUS.getCharPositionInLine();
        $endLine = $SYMBOL_MINUS.line;
        $endColumn = $SYMBOL_MINUS.getCharPositionInLine()+$SYMBOL_MINUS.getText().length();
  }
  | SYMBOL_CIRCUMFLEX
  {
        $op = AstPfxExpr.Oper.PTR;
        $begLine = $SYMBOL_CIRCUMFLEX.line;
        $begColumn = $SYMBOL_CIRCUMFLEX.getCharPositionInLine();
        $endLine = $SYMBOL_CIRCUMFLEX.line;
        $endColumn = $SYMBOL_CIRCUMFLEX.getCharPositionInLine()+$SYMBOL_CIRCUMFLEX.getText().length();
  }
  ;

multiplicative_operator returns [AstBinExpr.Oper op, int begLine, int begColumn, int endLine, int endColumn]
  : SYMBOL_STAR
  {
        $op = AstBinExpr.Oper.MUL;
        $begLine = $SYMBOL_STAR.line;
        $begColumn = $SYMBOL_STAR.getCharPositionInLine();
        $endLine = $SYMBOL_STAR.line;
        $endColumn = $SYMBOL_STAR.getCharPositionInLine()+$SYMBOL_STAR.getText().length();
  }
  | SYMBOL_SLASH
  {
        $op = AstBinExpr.Oper.DIV;
        $begLine = $SYMBOL_SLASH.line;
        $begColumn = $SYMBOL_SLASH.getCharPositionInLine();
        $endLine = $SYMBOL_SLASH.line;
        $endColumn = $SYMBOL_SLASH.getCharPositionInLine()+$SYMBOL_SLASH.getText().length();
  }
  | SYMBOL_PERCENT
  {
        $op = AstBinExpr.Oper.MOD;
        $begLine = $SYMBOL_PERCENT.line;
        $begColumn = $SYMBOL_PERCENT.getCharPositionInLine();
        $endLine = $SYMBOL_PERCENT.line;
        $endColumn = $SYMBOL_PERCENT.getCharPositionInLine()+$SYMBOL_PERCENT.getText().length();
  }
  ;

additive_operator returns [AstBinExpr.Oper op, int begLine, int begColumn, int endLine, int endColumn]
  : SYMBOL_PLUS
  {
        $op = AstBinExpr.Oper.ADD;
        $begLine = $SYMBOL_PLUS.line;
        $begColumn = $SYMBOL_PLUS.getCharPositionInLine();
        $endLine = $SYMBOL_PLUS.line;
        $endColumn = $SYMBOL_PLUS.getCharPositionInLine()+$SYMBOL_PLUS.getText().length();
  }
  | SYMBOL_MINUS
  {
        $op = AstBinExpr.Oper.SUB;
        $begLine = $SYMBOL_MINUS.line;
        $begColumn = $SYMBOL_MINUS.getCharPositionInLine();
        $endLine = $SYMBOL_MINUS.line;
        $endColumn = $SYMBOL_MINUS.getCharPositionInLine()+$SYMBOL_MINUS.getText().length();
  }
  ;

relational_operator returns [AstBinExpr.Oper op, int begLine, int begColumn, int endLine, int endColumn]
  : SYMBOL_DOUBLE_EQUAL
  {
        $op = AstBinExpr.Oper.EQU;
        $begLine = $SYMBOL_DOUBLE_EQUAL.line;
        $begColumn = $SYMBOL_DOUBLE_EQUAL.getCharPositionInLine();
        $endLine = $SYMBOL_DOUBLE_EQUAL.line;
        $endColumn = $SYMBOL_DOUBLE_EQUAL.getCharPositionInLine()+$SYMBOL_DOUBLE_EQUAL.getText().length();
  }
  | SYMBOL_NOT_EQUAL
  {
        $op = AstBinExpr.Oper.NEQ;
        $begLine = $SYMBOL_NOT_EQUAL.line;
        $begColumn = $SYMBOL_NOT_EQUAL.getCharPositionInLine();
        $endLine = $SYMBOL_NOT_EQUAL.line;
        $endColumn = $SYMBOL_NOT_EQUAL.getCharPositionInLine()+$SYMBOL_NOT_EQUAL.getText().length();
  }
  | SYMBOL_SMALLER_THAN
  {
        $op = AstBinExpr.Oper.LTH;
        $begLine = $SYMBOL_SMALLER_THAN.line;
        $begColumn = $SYMBOL_SMALLER_THAN.getCharPositionInLine();
        $endLine = $SYMBOL_SMALLER_THAN.line;
        $endColumn = $SYMBOL_SMALLER_THAN.getCharPositionInLine()+$SYMBOL_SMALLER_THAN.getText().length();
  }
  | SYMBOL_GREATER_THAN
  {
        $op = AstBinExpr.Oper.GTH;
        $begLine = $SYMBOL_GREATER_THAN.line;
        $begColumn = $SYMBOL_GREATER_THAN.getCharPositionInLine();
        $endLine = $SYMBOL_GREATER_THAN.line;
        $endColumn = $SYMBOL_GREATER_THAN.getCharPositionInLine()+$SYMBOL_GREATER_THAN.getText().length();
  }
  | SYMBOL_SMALLER_OR_EQUALS
  {
        $op = AstBinExpr.Oper.LEQ;
        $begLine = $SYMBOL_SMALLER_OR_EQUALS.line;
        $begColumn = $SYMBOL_SMALLER_OR_EQUALS.getCharPositionInLine();
        $endLine = $SYMBOL_SMALLER_OR_EQUALS.line;
        $endColumn = $SYMBOL_SMALLER_OR_EQUALS.getCharPositionInLine()+$SYMBOL_SMALLER_OR_EQUALS.getText().length();
  }
  | SYMBOL_GREATER_OR_EQUALS
  {
        $op = AstBinExpr.Oper.GEQ;
        $begLine = $SYMBOL_GREATER_OR_EQUALS.line;
        $begColumn = $SYMBOL_GREATER_OR_EQUALS.getCharPositionInLine();
        $endLine = $SYMBOL_GREATER_OR_EQUALS.line;
        $endColumn = $SYMBOL_GREATER_OR_EQUALS.getCharPositionInLine()+$SYMBOL_GREATER_OR_EQUALS.getText().length();
  }
  ;

statement returns [AstStmt ast]
  : {AstExpr expr_source = null;AstExpr expr_destination = null;}

  (expr_1=expression {expr_destination = $expr_1.ast;} (SYMBOL_EQUAL expr_2=expression {expr_source = $expr_2.ast;})?)
  {if (expr_source == null) {
    $ast = new AstExprStmt(new Location(0,0),expr_destination);

  } else{
    $ast = new AstAssignStmt(new Location($SYMBOL_EQUAL.line,$SYMBOL_EQUAL.getCharPositionInLine(), getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length()),expr_destination, expr_source);
  }
  }

  |{AstExpr cond = null; AstStmt thenStmt = null; AstStmt elseStmt = null;int lastLine = 0; int lastColumn = 0;}
   (KEYWORD_IF expression {cond = $expression.ast;} KEYWORD_THEN stat1=statement {thenStmt = $stat1.ast;lastLine=getCurrentToken().getLine();lastColumn=getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length();} (KEYWORD_ELSE stat2=statement {elseStmt = $stat2.ast;lastLine=getCurrentToken().getLine();lastColumn=getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length();})?)
   {$ast = new AstIfStmt(new Location($KEYWORD_IF.line,$KEYWORD_IF.getCharPositionInLine(), lastLine, lastColumn), cond, thenStmt, elseStmt);}
  | KEYWORD_WHILE expression KEYWORD_DO statement
  {$ast = new AstWhileStmt(new Location($KEYWORD_WHILE.line,$KEYWORD_WHILE.getCharPositionInLine(), getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length()), $expression.ast, $statement.ast);}
  | KEYWORD_LET source_ext KEYWORD_IN statement
  {$ast = new AstDeclStmt(new Location($KEYWORD_LET.line,$KEYWORD_LET.getCharPositionInLine(), getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length()), $source_ext.ast, $statement.ast);}
  |
  {Vector<AstStmt> stat_vec = new Vector<>();}
  (SYMBOL_CURLY_BRACKET_OPEN stat1=statement {stat_vec.add($stat1.ast);} (SYMBOL_SEMICOLON stat2=statement {stat_vec.add($stat2.ast);})*? SYMBOL_CURLY_BRACKET_CLOSE)
  {$ast = new AstStmts(new Location($SYMBOL_CURLY_BRACKET_OPEN.line,$SYMBOL_CURLY_BRACKET_OPEN.getCharPositionInLine(), getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length()), new AstTrees("statements", stat_vec));}
  ;

