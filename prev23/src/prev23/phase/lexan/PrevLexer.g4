lexer grammar PrevLexer;

@header {
	package prev23.phase.lexan;
	import prev23.common.report.*;
	import prev23.data.sym.*;
}

@members {
    @Override
	public Token nextToken() {
		return (Token) super.nextToken();
	}
}

CONSTANT_VOID : 'none' ;
CONSTANT_TRUE : 'true' ;
CONSTANT_FALSE : 'false' ;
CONSTANT_INTEGER : [0] | ([1-9] [0-9]*) ;
CONSTANT_CHAR : '\'' ([\u0020-\u0026] | '\\\'' | [\u0028-\u007E])  '\'' ;
CONSTANT_STRING : '"' ([\u0020-\u0021] | '\\"' | [\u0023-\u007E])*  '"' ;
CONSTANT_POINTER : 'nil' ;

SYMBOL_ROUND_BRACKET_OPEN : '(' ;
SYMBOL_ROUND_BRACKET_CLOSE : ')' ;
SYMBOL_CURLY_BRACKET_OPEN : '{' ;
SYMBOL_CURLY_BRACKET_CLOSE : '}' ;
SYMBOL_SQUARE_BRACKET_OPEN : '[' ;
SYMBOL_SQUARE_BRACKET_CLOSE : ']' ;
SYMBOL_DOT : '.' ;
SYMBOL_COMMA : ',' ;
SYMBOL_COLON : ':' ;
SYMBOL_SEMICOLON : ';' ;
SYMBOL_AND : '&' ;
SYMBOL_VERTICAL_BAR : '|' ;
SYMBOL_EXCLAMATION_MARK : '!' ;
SYMBOL_DOUBLE_EQUAL : '==' ;
SYMBOL_NOT_EQUAL : '!=' ;
SYMBOL_SMALLER_THAN : '<' ;
SYMBOL_GREATER_THAN : '>' ;
SYMBOL_SMALLER_OR_EQUALS : '<=' ;
SYMBOL_GREATER_OR_EQUALS : '>=' ;
SYMBOL_STAR : '*' ;
SYMBOL_SLASH : '/' ;
SYMBOL_PERCENT : '%' ;
SYMBOL_PLUS : '+' ;
SYMBOL_MINUS : '-' ;
SYMBOL_CIRCUMFLEX : '^' ;
SYMBOL_EQUAL : '=' ;

KEYWORD_BOOL : 'bool' ;
KEYWORD_CHAR : 'char' ;
KEYWORD_DEL : 'del' ;
KEYWORD_DO : 'do' ;
KEYWORD_ELSE : 'else' ;
KEYWORD_FUN : 'fun' ;
KEYWORD_IF : 'if' ;
KEYWORD_IN : 'in' ;
KEYWORD_INT : 'int' ;
KEYWORD_LET : 'let' ;
KEYWORD_NEW : 'new' ;
KEYWORD_THEN : 'then' ;
KEYWORD_TYP : 'typ' ;
KEYWORD_VAR : 'var' ;
KEYWORD_VOID : 'void' ;
KEYWORD_WHILE : 'while' ;

IDENTIFIER : ([A-Z] | [a-z] | [_]) ([A-Z] | [a-z] | [_] | [0-9])* ;

COMMENT : '#' ~('\n'|'\r')* -> skip ;

WHITE_SPACE : (' ' | '\n' | '\r') -> skip ;  
WHITE_TAB : ('\t') {
    setCharPositionInLine(getCharPositionInLine() + 8 - getCharPositionInLine()%8);
} -> skip ;


ERROR : {
    if (1==1) {
        String allChars = getInputStream().toString();
        char lastChar = allChars.charAt(getCharIndex());
        throw new Report.Error(new Location(getLine(), getCharPositionInLine()), "Illegal character or expression at "+lastChar);
    }
};  // getCharPositionInLine() + 1 if we start with one and not zero

/**
BB : [ \n] ;  // use meaningfull names -> KEYW_WHILE
D : [0-9] ;
P : ';' ;
AA : 'a' 'a'* ;
E : { if (1==1) throw new Report.Error(new Location(5, 100), "ERROR"); };  // get the right numbers -> check documentation!
**/

