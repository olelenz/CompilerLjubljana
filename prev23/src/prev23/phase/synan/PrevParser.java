// Generated from PrevParser.g4 by ANTLR 4.13.1


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
	

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class PrevParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CONSTANT_VOID=1, CONSTANT_TRUE=2, CONSTANT_FALSE=3, CONSTANT_INTEGER=4, 
		CONSTANT_CHAR=5, CONSTANT_STRING=6, CONSTANT_POINTER=7, SYMBOL_ROUND_BRACKET_OPEN=8, 
		SYMBOL_ROUND_BRACKET_CLOSE=9, SYMBOL_CURLY_BRACKET_OPEN=10, SYMBOL_CURLY_BRACKET_CLOSE=11, 
		SYMBOL_SQUARE_BRACKET_OPEN=12, SYMBOL_SQUARE_BRACKET_CLOSE=13, SYMBOL_DOT=14, 
		SYMBOL_COMMA=15, SYMBOL_COLON=16, SYMBOL_SEMICOLON=17, SYMBOL_AND=18, 
		SYMBOL_VERTICAL_BAR=19, SYMBOL_EXCLAMATION_MARK=20, SYMBOL_DOUBLE_EQUAL=21, 
		SYMBOL_NOT_EQUAL=22, SYMBOL_SMALLER_THAN=23, SYMBOL_GREATER_THAN=24, SYMBOL_SMALLER_OR_EQUALS=25, 
		SYMBOL_GREATER_OR_EQUALS=26, SYMBOL_STAR=27, SYMBOL_SLASH=28, SYMBOL_PERCENT=29, 
		SYMBOL_PLUS=30, SYMBOL_MINUS=31, SYMBOL_CIRCUMFLEX=32, SYMBOL_EQUAL=33, 
		KEYWORD_BOOL=34, KEYWORD_CHAR=35, KEYWORD_DEL=36, KEYWORD_DO=37, KEYWORD_ELSE=38, 
		KEYWORD_FUN=39, KEYWORD_IF=40, KEYWORD_IN=41, KEYWORD_INT=42, KEYWORD_LET=43, 
		KEYWORD_NEW=44, KEYWORD_THEN=45, KEYWORD_TYP=46, KEYWORD_VAR=47, KEYWORD_VOID=48, 
		KEYWORD_WHILE=49, IDENTIFIER=50, COMMENT=51, WHITE_SPACE=52, WHITE_TAB=53, 
		ERROR=54;
	public static final int
		RULE_source = 0, RULE_source_ext = 1, RULE_type_declarations = 2, RULE_function_declarations = 3, 
		RULE_variable_declarations = 4, RULE_type = 5, RULE_expression = 6, RULE_expression_p = 7, 
		RULE_expression_two = 8, RULE_expression_two_p = 9, RULE_expression_three = 10, 
		RULE_expression_three_p = 11, RULE_expression_four = 12, RULE_expression_four_p = 13, 
		RULE_expression_five = 14, RULE_expression_five_p = 15, RULE_expression_six = 16, 
		RULE_expression_seven = 17, RULE_expression_eight = 18, RULE_constant = 19, 
		RULE_prefix_operator = 20, RULE_multiplicative_operator = 21, RULE_additive_operator = 22, 
		RULE_relational_operator = 23, RULE_statement = 24;
	private static String[] makeRuleNames() {
		return new String[] {
			"source", "source_ext", "type_declarations", "function_declarations", 
			"variable_declarations", "type", "expression", "expression_p", "expression_two", 
			"expression_two_p", "expression_three", "expression_three_p", "expression_four", 
			"expression_four_p", "expression_five", "expression_five_p", "expression_six", 
			"expression_seven", "expression_eight", "constant", "prefix_operator", 
			"multiplicative_operator", "additive_operator", "relational_operator", 
			"statement"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'none'", "'true'", "'false'", null, null, null, "'nil'", "'('", 
			"')'", "'{'", "'}'", "'['", "']'", "'.'", "','", "':'", "';'", "'&'", 
			"'|'", "'!'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'*'", "'/'", 
			"'%'", "'+'", "'-'", "'^'", "'='", "'bool'", "'char'", "'del'", "'do'", 
			"'else'", "'fun'", "'if'", "'in'", "'int'", "'let'", "'new'", "'then'", 
			"'typ'", "'var'", "'void'", "'while'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "CONSTANT_VOID", "CONSTANT_TRUE", "CONSTANT_FALSE", "CONSTANT_INTEGER", 
			"CONSTANT_CHAR", "CONSTANT_STRING", "CONSTANT_POINTER", "SYMBOL_ROUND_BRACKET_OPEN", 
			"SYMBOL_ROUND_BRACKET_CLOSE", "SYMBOL_CURLY_BRACKET_OPEN", "SYMBOL_CURLY_BRACKET_CLOSE", 
			"SYMBOL_SQUARE_BRACKET_OPEN", "SYMBOL_SQUARE_BRACKET_CLOSE", "SYMBOL_DOT", 
			"SYMBOL_COMMA", "SYMBOL_COLON", "SYMBOL_SEMICOLON", "SYMBOL_AND", "SYMBOL_VERTICAL_BAR", 
			"SYMBOL_EXCLAMATION_MARK", "SYMBOL_DOUBLE_EQUAL", "SYMBOL_NOT_EQUAL", 
			"SYMBOL_SMALLER_THAN", "SYMBOL_GREATER_THAN", "SYMBOL_SMALLER_OR_EQUALS", 
			"SYMBOL_GREATER_OR_EQUALS", "SYMBOL_STAR", "SYMBOL_SLASH", "SYMBOL_PERCENT", 
			"SYMBOL_PLUS", "SYMBOL_MINUS", "SYMBOL_CIRCUMFLEX", "SYMBOL_EQUAL", "KEYWORD_BOOL", 
			"KEYWORD_CHAR", "KEYWORD_DEL", "KEYWORD_DO", "KEYWORD_ELSE", "KEYWORD_FUN", 
			"KEYWORD_IF", "KEYWORD_IN", "KEYWORD_INT", "KEYWORD_LET", "KEYWORD_NEW", 
			"KEYWORD_THEN", "KEYWORD_TYP", "KEYWORD_VAR", "KEYWORD_VOID", "KEYWORD_WHILE", 
			"IDENTIFIER", "COMMENT", "WHITE_SPACE", "WHITE_TAB", "ERROR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "PrevParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PrevParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SourceContext extends ParserRuleContext {
		public AstTrees ast;
		public Source_extContext source_ext;
		public Source_extContext source_ext() {
			return getRuleContext(Source_extContext.class,0);
		}
		public TerminalNode EOF() { return getToken(PrevParser.EOF, 0); }
		public SourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_source; }
	}

	public final SourceContext source() throws RecognitionException {
		SourceContext _localctx = new SourceContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_source);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			((SourceContext)_localctx).source_ext = source_ext();
			setState(51);
			match(EOF);
			((SourceContext)_localctx).ast =  ((SourceContext)_localctx).source_ext.ast;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Source_extContext extends ParserRuleContext {
		public AstTrees ast;
		public Type_declarationsContext type_declarations;
		public Function_declarationsContext function_declarations;
		public Variable_declarationsContext variable_declarations;
		public List<Type_declarationsContext> type_declarations() {
			return getRuleContexts(Type_declarationsContext.class);
		}
		public Type_declarationsContext type_declarations(int i) {
			return getRuleContext(Type_declarationsContext.class,i);
		}
		public List<Function_declarationsContext> function_declarations() {
			return getRuleContexts(Function_declarationsContext.class);
		}
		public Function_declarationsContext function_declarations(int i) {
			return getRuleContext(Function_declarationsContext.class,i);
		}
		public List<Variable_declarationsContext> variable_declarations() {
			return getRuleContexts(Variable_declarationsContext.class);
		}
		public Variable_declarationsContext variable_declarations(int i) {
			return getRuleContext(Variable_declarationsContext.class,i);
		}
		public Source_extContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_source_ext; }
	}

	public final Source_extContext source_ext() throws RecognitionException {
		Source_extContext _localctx = new Source_extContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_source_ext);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			Vector<AstTrees> declarations = new Vector<>();
			setState(64); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(64);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case KEYWORD_TYP:
					{
					setState(55);
					((Source_extContext)_localctx).type_declarations = type_declarations();
					declarations.add(((Source_extContext)_localctx).type_declarations.ast);
					}
					break;
				case KEYWORD_FUN:
					{
					setState(58);
					((Source_extContext)_localctx).function_declarations = function_declarations();
					declarations.add(((Source_extContext)_localctx).function_declarations.ast);
					}
					break;
				case KEYWORD_VAR:
					{
					setState(61);
					((Source_extContext)_localctx).variable_declarations = variable_declarations();
					declarations.add(((Source_extContext)_localctx).variable_declarations.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(66); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 211655988346880L) != 0) );
			((Source_extContext)_localctx).ast =  new AstTrees("Declarations", declarations);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Type_declarationsContext extends ParserRuleContext {
		public AstTrees ast;
		public Token KEYWORD_TYP;
		public Token IDENTIFIER;
		public TypeContext type;
		public Token SYMBOL_SEMICOLON;
		public TerminalNode KEYWORD_TYP() { return getToken(PrevParser.KEYWORD_TYP, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(PrevParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PrevParser.IDENTIFIER, i);
		}
		public List<TerminalNode> SYMBOL_EQUAL() { return getTokens(PrevParser.SYMBOL_EQUAL); }
		public TerminalNode SYMBOL_EQUAL(int i) {
			return getToken(PrevParser.SYMBOL_EQUAL, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode SYMBOL_SEMICOLON() { return getToken(PrevParser.SYMBOL_SEMICOLON, 0); }
		public List<TerminalNode> SYMBOL_COMMA() { return getTokens(PrevParser.SYMBOL_COMMA); }
		public TerminalNode SYMBOL_COMMA(int i) {
			return getToken(PrevParser.SYMBOL_COMMA, i);
		}
		public Type_declarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_declarations; }
	}

	public final Type_declarationsContext type_declarations() throws RecognitionException {
		Type_declarationsContext _localctx = new Type_declarationsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_type_declarations);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			Vector<AstTypDecl> type_declarations = new Vector<>();
			{
			setState(71);
			((Type_declarationsContext)_localctx).KEYWORD_TYP = match(KEYWORD_TYP);
			setState(72);
			((Type_declarationsContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(73);
			match(SYMBOL_EQUAL);
			setState(74);
			((Type_declarationsContext)_localctx).type = type();

			        type_declarations.add(new AstTypDecl(new Location((((Type_declarationsContext)_localctx).IDENTIFIER!=null?((Type_declarationsContext)_localctx).IDENTIFIER.getLine():0), ((Type_declarationsContext)_localctx).IDENTIFIER.getCharPositionInLine(), ((Type_declarationsContext)_localctx).type.endLine, ((Type_declarationsContext)_localctx).type.endColumn), (((Type_declarationsContext)_localctx).IDENTIFIER!=null?((Type_declarationsContext)_localctx).IDENTIFIER.getText():null), (AstType)((Type_declarationsContext)_localctx).type.ast));
			    
			setState(84);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(76);
					match(SYMBOL_COMMA);
					setState(77);
					((Type_declarationsContext)_localctx).IDENTIFIER = match(IDENTIFIER);
					setState(78);
					match(SYMBOL_EQUAL);
					setState(79);
					((Type_declarationsContext)_localctx).type = type();

					        type_declarations.add(new AstTypDecl(new Location((((Type_declarationsContext)_localctx).IDENTIFIER!=null?((Type_declarationsContext)_localctx).IDENTIFIER.getLine():0),((Type_declarationsContext)_localctx).IDENTIFIER.getCharPositionInLine(), ((Type_declarationsContext)_localctx).type.endLine, ((Type_declarationsContext)_localctx).type.endColumn), (((Type_declarationsContext)_localctx).IDENTIFIER!=null?((Type_declarationsContext)_localctx).IDENTIFIER.getText():null), (AstType)((Type_declarationsContext)_localctx).type.ast));
					    
					}
					} 
				}
				setState(86);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(87);
			((Type_declarationsContext)_localctx).SYMBOL_SEMICOLON = match(SYMBOL_SEMICOLON);
			}

			        ((Type_declarationsContext)_localctx).ast =  new AstTrees(new Location((((Type_declarationsContext)_localctx).KEYWORD_TYP!=null?((Type_declarationsContext)_localctx).KEYWORD_TYP.getLine():0), ((Type_declarationsContext)_localctx).KEYWORD_TYP.getCharPositionInLine(), (((Type_declarationsContext)_localctx).SYMBOL_SEMICOLON!=null?((Type_declarationsContext)_localctx).SYMBOL_SEMICOLON.getLine():0), ((Type_declarationsContext)_localctx).SYMBOL_SEMICOLON.getCharPositionInLine()+((Type_declarationsContext)_localctx).SYMBOL_SEMICOLON.getText().length()), "Typ-Declaration", type_declarations);
			  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Function_declarationsContext extends ParserRuleContext {
		public AstTrees ast;
		public Token KEYWORD_FUN;
		public Token funid_out;
		public Token IDENTIFIER;
		public TypeContext type;
		public Token SYMBOL_COMMA;
		public StatementContext statement;
		public Token funid_in;
		public Token SYMBOL_SEMICOLON;
		public TerminalNode SYMBOL_SEMICOLON() { return getToken(PrevParser.SYMBOL_SEMICOLON, 0); }
		public TerminalNode KEYWORD_FUN() { return getToken(PrevParser.KEYWORD_FUN, 0); }
		public List<TerminalNode> SYMBOL_ROUND_BRACKET_OPEN() { return getTokens(PrevParser.SYMBOL_ROUND_BRACKET_OPEN); }
		public TerminalNode SYMBOL_ROUND_BRACKET_OPEN(int i) {
			return getToken(PrevParser.SYMBOL_ROUND_BRACKET_OPEN, i);
		}
		public List<TerminalNode> SYMBOL_ROUND_BRACKET_CLOSE() { return getTokens(PrevParser.SYMBOL_ROUND_BRACKET_CLOSE); }
		public TerminalNode SYMBOL_ROUND_BRACKET_CLOSE(int i) {
			return getToken(PrevParser.SYMBOL_ROUND_BRACKET_CLOSE, i);
		}
		public List<TerminalNode> SYMBOL_COLON() { return getTokens(PrevParser.SYMBOL_COLON); }
		public TerminalNode SYMBOL_COLON(int i) {
			return getToken(PrevParser.SYMBOL_COLON, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(PrevParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PrevParser.IDENTIFIER, i);
		}
		public List<TerminalNode> SYMBOL_EQUAL() { return getTokens(PrevParser.SYMBOL_EQUAL); }
		public TerminalNode SYMBOL_EQUAL(int i) {
			return getToken(PrevParser.SYMBOL_EQUAL, i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> SYMBOL_COMMA() { return getTokens(PrevParser.SYMBOL_COMMA); }
		public TerminalNode SYMBOL_COMMA(int i) {
			return getToken(PrevParser.SYMBOL_COMMA, i);
		}
		public Function_declarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_declarations; }
	}

	public final Function_declarationsContext function_declarations() throws RecognitionException {
		Function_declarationsContext _localctx = new Function_declarationsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_function_declarations);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			Vector<AstFunDecl> fun_declarations = new Vector<>();
			  AstTrees<AstParDecl> parameters = null;
			  int lastLine = 0;
			  int lastColumn = 0;
			{
			setState(92);
			((Function_declarationsContext)_localctx).KEYWORD_FUN = match(KEYWORD_FUN);
			setState(93);
			((Function_declarationsContext)_localctx).funid_out = match(IDENTIFIER);
			setState(94);
			match(SYMBOL_ROUND_BRACKET_OPEN);
			Vector<AstParDecl> parameters_vector = new Vector<>();
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(96);
				((Function_declarationsContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(97);
				match(SYMBOL_COLON);
				setState(98);
				((Function_declarationsContext)_localctx).type = type();
				AstParDecl param = new AstParDecl(new Location((((Function_declarationsContext)_localctx).IDENTIFIER!=null?((Function_declarationsContext)_localctx).IDENTIFIER.getLine():0),((Function_declarationsContext)_localctx).IDENTIFIER.getCharPositionInLine(), ((Function_declarationsContext)_localctx).type.endLine, ((Function_declarationsContext)_localctx).type.endColumn), (((Function_declarationsContext)_localctx).IDENTIFIER!=null?((Function_declarationsContext)_localctx).IDENTIFIER.getText():null), (AstType)((Function_declarationsContext)_localctx).type.ast); parameters_vector.add(param);
				setState(108);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1+1 ) {
						{
						{
						setState(100);
						((Function_declarationsContext)_localctx).SYMBOL_COMMA = match(SYMBOL_COMMA);
						setState(101);
						((Function_declarationsContext)_localctx).IDENTIFIER = match(IDENTIFIER);
						setState(102);
						match(SYMBOL_COLON);
						setState(103);
						((Function_declarationsContext)_localctx).type = type();
						AstParDecl param_add = new AstParDecl(new Location((((Function_declarationsContext)_localctx).SYMBOL_COMMA!=null?((Function_declarationsContext)_localctx).SYMBOL_COMMA.getLine():0),((Function_declarationsContext)_localctx).SYMBOL_COMMA.getCharPositionInLine(), ((Function_declarationsContext)_localctx).type.endLine, ((Function_declarationsContext)_localctx).type.endColumn), (((Function_declarationsContext)_localctx).IDENTIFIER!=null?((Function_declarationsContext)_localctx).IDENTIFIER.getText():null), (AstType)((Function_declarationsContext)_localctx).type.ast); parameters_vector.add(param_add);
						}
						} 
					}
					setState(110);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				}
				parameters = new AstTrees("Par-Declarations", parameters_vector);
				}
			}

			setState(115);
			match(SYMBOL_ROUND_BRACKET_CLOSE);
			setState(116);
			match(SYMBOL_COLON);
			setState(117);
			((Function_declarationsContext)_localctx).type = type();
			AstType fun_type = (AstType)((Function_declarationsContext)_localctx).type.ast; AstStmt fun_stmt = null; lastLine = ((Function_declarationsContext)_localctx).type.endLine; lastColumn = ((Function_declarationsContext)_localctx).type.endColumn;
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SYMBOL_EQUAL) {
				{
				setState(119);
				match(SYMBOL_EQUAL);
				setState(120);
				((Function_declarationsContext)_localctx).statement = statement();

				                fun_stmt = ((Function_declarationsContext)_localctx).statement.ast;
				                lastLine = getCurrentToken().getLine(); lastColumn = getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length();
				            
				}
			}

			AstFunDecl func_decl = new AstFunDecl(new Location((((Function_declarationsContext)_localctx).KEYWORD_FUN!=null?((Function_declarationsContext)_localctx).KEYWORD_FUN.getLine():0),((Function_declarationsContext)_localctx).KEYWORD_FUN.getCharPositionInLine(), lastLine, lastColumn), (((Function_declarationsContext)_localctx).funid_out!=null?((Function_declarationsContext)_localctx).funid_out.getText():null), parameters, fun_type, fun_stmt); fun_declarations.add(func_decl);
			setState(163);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(126);
					((Function_declarationsContext)_localctx).SYMBOL_COMMA = match(SYMBOL_COMMA);
					setState(127);
					((Function_declarationsContext)_localctx).funid_in = match(IDENTIFIER);
					setState(128);
					match(SYMBOL_ROUND_BRACKET_OPEN);
					AstTrees<AstParDecl> parameters_more = null;
					        Vector<AstParDecl> parameters_vector_more = new Vector<>();
					        int lastLine_more = 0;
					        int lastColumn_more = 0;
					setState(147);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==IDENTIFIER) {
						{
						setState(130);
						((Function_declarationsContext)_localctx).IDENTIFIER = match(IDENTIFIER);
						setState(131);
						match(SYMBOL_COLON);
						setState(132);
						((Function_declarationsContext)_localctx).type = type();
						AstParDecl param_more = new AstParDecl(new Location((((Function_declarationsContext)_localctx).IDENTIFIER!=null?((Function_declarationsContext)_localctx).IDENTIFIER.getLine():0),((Function_declarationsContext)_localctx).IDENTIFIER.getCharPositionInLine(), ((Function_declarationsContext)_localctx).type.endLine, ((Function_declarationsContext)_localctx).type.endColumn), (((Function_declarationsContext)_localctx).IDENTIFIER!=null?((Function_declarationsContext)_localctx).IDENTIFIER.getText():null), (AstType)((Function_declarationsContext)_localctx).type.ast); parameters_vector_more.add(param_more);
						setState(142);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
						while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1+1 ) {
								{
								{
								setState(134);
								((Function_declarationsContext)_localctx).SYMBOL_COMMA = match(SYMBOL_COMMA);
								setState(135);
								((Function_declarationsContext)_localctx).IDENTIFIER = match(IDENTIFIER);
								setState(136);
								match(SYMBOL_COLON);
								setState(137);
								((Function_declarationsContext)_localctx).type = type();
								AstParDecl param_add_more = new AstParDecl(new Location((((Function_declarationsContext)_localctx).SYMBOL_COMMA!=null?((Function_declarationsContext)_localctx).SYMBOL_COMMA.getLine():0),((Function_declarationsContext)_localctx).SYMBOL_COMMA.getCharPositionInLine(), ((Function_declarationsContext)_localctx).type.endLine, ((Function_declarationsContext)_localctx).type.endColumn), (((Function_declarationsContext)_localctx).IDENTIFIER!=null?((Function_declarationsContext)_localctx).IDENTIFIER.getText():null), (AstType)((Function_declarationsContext)_localctx).type.ast); parameters_vector_more.add(param_add_more);
								}
								} 
							}
							setState(144);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
						}
						parameters_more = new AstTrees("Par-Declarations", parameters_vector_more);
						}
					}

					setState(149);
					match(SYMBOL_ROUND_BRACKET_CLOSE);
					setState(150);
					match(SYMBOL_COLON);
					setState(151);
					((Function_declarationsContext)_localctx).type = type();
					AstType fun_type_more = (AstType)((Function_declarationsContext)_localctx).type.ast; AstStmt fun_stmt_more = null; lastLine_more = ((Function_declarationsContext)_localctx).type.endLine; lastColumn_more = ((Function_declarationsContext)_localctx).type.endColumn;
					setState(157);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SYMBOL_EQUAL) {
						{
						setState(153);
						match(SYMBOL_EQUAL);
						setState(154);
						((Function_declarationsContext)_localctx).statement = statement();

						              fun_stmt_more = ((Function_declarationsContext)_localctx).statement.ast;
						              lastLine_more = getCurrentToken().getLine(); lastColumn_more = getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length();
						          
						}
					}

					AstFunDecl func_decl_more = new AstFunDecl(new Location((((Function_declarationsContext)_localctx).SYMBOL_COMMA!=null?((Function_declarationsContext)_localctx).SYMBOL_COMMA.getLine():0),((Function_declarationsContext)_localctx).SYMBOL_COMMA.getCharPositionInLine(), lastLine_more, lastColumn_more), (((Function_declarationsContext)_localctx).funid_in!=null?((Function_declarationsContext)_localctx).funid_in.getText():null), parameters_more, fun_type_more, fun_stmt_more); fun_declarations.add(func_decl_more);
					}
					} 
				}
				setState(165);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
			setState(166);
			((Function_declarationsContext)_localctx).SYMBOL_SEMICOLON = match(SYMBOL_SEMICOLON);
			((Function_declarationsContext)_localctx).ast =  new AstTrees(new Location((((Function_declarationsContext)_localctx).KEYWORD_FUN!=null?((Function_declarationsContext)_localctx).KEYWORD_FUN.getLine():0),((Function_declarationsContext)_localctx).KEYWORD_FUN.getCharPositionInLine(), (((Function_declarationsContext)_localctx).SYMBOL_SEMICOLON!=null?((Function_declarationsContext)_localctx).SYMBOL_SEMICOLON.getLine():0),((Function_declarationsContext)_localctx).SYMBOL_SEMICOLON.getCharPositionInLine()+((Function_declarationsContext)_localctx).SYMBOL_SEMICOLON.getText().length()),"Fun-Declaration", fun_declarations);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Variable_declarationsContext extends ParserRuleContext {
		public AstTrees ast;
		public Token KEYWORD_VAR;
		public Token IDENTIFIER;
		public TypeContext type;
		public Token SYMBOL_SEMICOLON;
		public TerminalNode KEYWORD_VAR() { return getToken(PrevParser.KEYWORD_VAR, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(PrevParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PrevParser.IDENTIFIER, i);
		}
		public List<TerminalNode> SYMBOL_COLON() { return getTokens(PrevParser.SYMBOL_COLON); }
		public TerminalNode SYMBOL_COLON(int i) {
			return getToken(PrevParser.SYMBOL_COLON, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode SYMBOL_SEMICOLON() { return getToken(PrevParser.SYMBOL_SEMICOLON, 0); }
		public List<TerminalNode> SYMBOL_COMMA() { return getTokens(PrevParser.SYMBOL_COMMA); }
		public TerminalNode SYMBOL_COMMA(int i) {
			return getToken(PrevParser.SYMBOL_COMMA, i);
		}
		public Variable_declarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_declarations; }
	}

	public final Variable_declarationsContext variable_declarations() throws RecognitionException {
		Variable_declarationsContext _localctx = new Variable_declarationsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variable_declarations);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			Vector<AstVarDecl> var_declarations = new Vector<>();
			{
			setState(170);
			((Variable_declarationsContext)_localctx).KEYWORD_VAR = match(KEYWORD_VAR);
			setState(171);
			((Variable_declarationsContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(172);
			match(SYMBOL_COLON);
			setState(173);
			((Variable_declarationsContext)_localctx).type = type();
			var_declarations.add(new AstVarDecl(new Location((((Variable_declarationsContext)_localctx).IDENTIFIER!=null?((Variable_declarationsContext)_localctx).IDENTIFIER.getLine():0),((Variable_declarationsContext)_localctx).IDENTIFIER.getCharPositionInLine(), ((Variable_declarationsContext)_localctx).type.endLine, ((Variable_declarationsContext)_localctx).type.endColumn), (((Variable_declarationsContext)_localctx).IDENTIFIER!=null?((Variable_declarationsContext)_localctx).IDENTIFIER.getText():null), (AstType)((Variable_declarationsContext)_localctx).type.ast));
			setState(183);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(175);
					match(SYMBOL_COMMA);
					setState(176);
					((Variable_declarationsContext)_localctx).IDENTIFIER = match(IDENTIFIER);
					setState(177);
					match(SYMBOL_COLON);
					setState(178);
					((Variable_declarationsContext)_localctx).type = type();
					var_declarations.add(new AstVarDecl(new Location((((Variable_declarationsContext)_localctx).IDENTIFIER!=null?((Variable_declarationsContext)_localctx).IDENTIFIER.getLine():0),((Variable_declarationsContext)_localctx).IDENTIFIER.getCharPositionInLine(), ((Variable_declarationsContext)_localctx).type.endLine, ((Variable_declarationsContext)_localctx).type.endColumn), (((Variable_declarationsContext)_localctx).IDENTIFIER!=null?((Variable_declarationsContext)_localctx).IDENTIFIER.getText():null), (AstType)((Variable_declarationsContext)_localctx).type.ast));
					}
					} 
				}
				setState(185);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			setState(186);
			((Variable_declarationsContext)_localctx).SYMBOL_SEMICOLON = match(SYMBOL_SEMICOLON);
			}

			        ((Variable_declarationsContext)_localctx).ast =  new AstTrees(new Location((((Variable_declarationsContext)_localctx).KEYWORD_VAR!=null?((Variable_declarationsContext)_localctx).KEYWORD_VAR.getLine():0),((Variable_declarationsContext)_localctx).KEYWORD_VAR.getCharPositionInLine(), (((Variable_declarationsContext)_localctx).SYMBOL_SEMICOLON!=null?((Variable_declarationsContext)_localctx).SYMBOL_SEMICOLON.getLine():0),((Variable_declarationsContext)_localctx).SYMBOL_SEMICOLON.getCharPositionInLine()+((Variable_declarationsContext)_localctx).SYMBOL_SEMICOLON.getText().length()), "Var-Declaration", var_declarations);
			  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public AstTree ast;
		public int begLine;
		public int begColumn;
		public int endLine;
		public int endColumn;
		public Token KEYWORD_VOID;
		public Token KEYWORD_CHAR;
		public Token KEYWORD_INT;
		public Token KEYWORD_BOOL;
		public Token IDENTIFIER;
		public Token SYMBOL_SQUARE_BRACKET_OPEN;
		public ExpressionContext expression;
		public TypeContext type;
		public Token SYMBOL_CIRCUMFLEX;
		public Token SYMBOL_CURLY_BRACKET_OPEN;
		public Token SYMBOL_COMMA;
		public Token SYMBOL_CURLY_BRACKET_CLOSE;
		public Token SYMBOL_ROUND_BRACKET_OPEN;
		public Token SYMBOL_ROUND_BRACKET_CLOSE;
		public TerminalNode KEYWORD_VOID() { return getToken(PrevParser.KEYWORD_VOID, 0); }
		public TerminalNode KEYWORD_CHAR() { return getToken(PrevParser.KEYWORD_CHAR, 0); }
		public TerminalNode KEYWORD_INT() { return getToken(PrevParser.KEYWORD_INT, 0); }
		public TerminalNode KEYWORD_BOOL() { return getToken(PrevParser.KEYWORD_BOOL, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(PrevParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PrevParser.IDENTIFIER, i);
		}
		public TerminalNode SYMBOL_SQUARE_BRACKET_OPEN() { return getToken(PrevParser.SYMBOL_SQUARE_BRACKET_OPEN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SYMBOL_SQUARE_BRACKET_CLOSE() { return getToken(PrevParser.SYMBOL_SQUARE_BRACKET_CLOSE, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode SYMBOL_CIRCUMFLEX() { return getToken(PrevParser.SYMBOL_CIRCUMFLEX, 0); }
		public TerminalNode SYMBOL_CURLY_BRACKET_OPEN() { return getToken(PrevParser.SYMBOL_CURLY_BRACKET_OPEN, 0); }
		public List<TerminalNode> SYMBOL_COLON() { return getTokens(PrevParser.SYMBOL_COLON); }
		public TerminalNode SYMBOL_COLON(int i) {
			return getToken(PrevParser.SYMBOL_COLON, i);
		}
		public TerminalNode SYMBOL_CURLY_BRACKET_CLOSE() { return getToken(PrevParser.SYMBOL_CURLY_BRACKET_CLOSE, 0); }
		public List<TerminalNode> SYMBOL_COMMA() { return getTokens(PrevParser.SYMBOL_COMMA); }
		public TerminalNode SYMBOL_COMMA(int i) {
			return getToken(PrevParser.SYMBOL_COMMA, i);
		}
		public TerminalNode SYMBOL_ROUND_BRACKET_OPEN() { return getToken(PrevParser.SYMBOL_ROUND_BRACKET_OPEN, 0); }
		public TerminalNode SYMBOL_ROUND_BRACKET_CLOSE() { return getToken(PrevParser.SYMBOL_ROUND_BRACKET_CLOSE, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_type);
		try {
			int _alt;
			setState(236);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KEYWORD_VOID:
				enterOuterAlt(_localctx, 1);
				{
				setState(190);
				((TypeContext)_localctx).KEYWORD_VOID = match(KEYWORD_VOID);

				         AstAtomType atom = new AstAtomType(new Location((((TypeContext)_localctx).KEYWORD_VOID!=null?((TypeContext)_localctx).KEYWORD_VOID.getLine():0), ((TypeContext)_localctx).KEYWORD_VOID.getCharPositionInLine(), (((TypeContext)_localctx).KEYWORD_VOID!=null?((TypeContext)_localctx).KEYWORD_VOID.getLine():0), ((TypeContext)_localctx).KEYWORD_VOID.getCharPositionInLine()+((TypeContext)_localctx).KEYWORD_VOID.getText().length()), AstAtomType.Type.VOID);
				         ((TypeContext)_localctx).ast =  atom;
				         ((TypeContext)_localctx).begLine =  (((TypeContext)_localctx).KEYWORD_VOID!=null?((TypeContext)_localctx).KEYWORD_VOID.getLine():0);
				         ((TypeContext)_localctx).begColumn =  ((TypeContext)_localctx).KEYWORD_VOID.getCharPositionInLine();
				         ((TypeContext)_localctx).endLine =  (((TypeContext)_localctx).KEYWORD_VOID!=null?((TypeContext)_localctx).KEYWORD_VOID.getLine():0);
				         ((TypeContext)_localctx).endColumn =  ((TypeContext)_localctx).KEYWORD_VOID.getCharPositionInLine()+((TypeContext)_localctx).KEYWORD_VOID.getText().length();
				  
				}
				break;
			case KEYWORD_CHAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(192);
				((TypeContext)_localctx).KEYWORD_CHAR = match(KEYWORD_CHAR);

				         AstAtomType atom = new AstAtomType(new Location((((TypeContext)_localctx).KEYWORD_CHAR!=null?((TypeContext)_localctx).KEYWORD_CHAR.getLine():0), ((TypeContext)_localctx).KEYWORD_CHAR.getCharPositionInLine(), (((TypeContext)_localctx).KEYWORD_CHAR!=null?((TypeContext)_localctx).KEYWORD_CHAR.getLine():0), ((TypeContext)_localctx).KEYWORD_CHAR.getCharPositionInLine()+((TypeContext)_localctx).KEYWORD_CHAR.getText().length()), AstAtomType.Type.CHAR);
				         ((TypeContext)_localctx).ast =  atom;
				         ((TypeContext)_localctx).begLine =  (((TypeContext)_localctx).KEYWORD_CHAR!=null?((TypeContext)_localctx).KEYWORD_CHAR.getLine():0);
				         ((TypeContext)_localctx).begColumn =  ((TypeContext)_localctx).KEYWORD_CHAR.getCharPositionInLine();
				         ((TypeContext)_localctx).endLine =  (((TypeContext)_localctx).KEYWORD_CHAR!=null?((TypeContext)_localctx).KEYWORD_CHAR.getLine():0);
				         ((TypeContext)_localctx).endColumn =  ((TypeContext)_localctx).KEYWORD_CHAR.getCharPositionInLine()+((TypeContext)_localctx).KEYWORD_CHAR.getText().length();
				  
				}
				break;
			case KEYWORD_INT:
				enterOuterAlt(_localctx, 3);
				{
				setState(194);
				((TypeContext)_localctx).KEYWORD_INT = match(KEYWORD_INT);

				         AstAtomType atom = new AstAtomType(new Location((((TypeContext)_localctx).KEYWORD_INT!=null?((TypeContext)_localctx).KEYWORD_INT.getLine():0), ((TypeContext)_localctx).KEYWORD_INT.getCharPositionInLine(), (((TypeContext)_localctx).KEYWORD_INT!=null?((TypeContext)_localctx).KEYWORD_INT.getLine():0), ((TypeContext)_localctx).KEYWORD_INT.getCharPositionInLine()+((TypeContext)_localctx).KEYWORD_INT.getText().length()), AstAtomType.Type.INT);
				         ((TypeContext)_localctx).ast =  atom;
				         ((TypeContext)_localctx).begLine =  (((TypeContext)_localctx).KEYWORD_INT!=null?((TypeContext)_localctx).KEYWORD_INT.getLine():0);
				         ((TypeContext)_localctx).begColumn =  ((TypeContext)_localctx).KEYWORD_INT.getCharPositionInLine();
				         ((TypeContext)_localctx).endLine =  (((TypeContext)_localctx).KEYWORD_INT!=null?((TypeContext)_localctx).KEYWORD_INT.getLine():0);
				         ((TypeContext)_localctx).endColumn =  ((TypeContext)_localctx).KEYWORD_INT.getCharPositionInLine()+((TypeContext)_localctx).KEYWORD_INT.getText().length();
				  
				}
				break;
			case KEYWORD_BOOL:
				enterOuterAlt(_localctx, 4);
				{
				setState(196);
				((TypeContext)_localctx).KEYWORD_BOOL = match(KEYWORD_BOOL);

				         AstAtomType atom = new AstAtomType(new Location((((TypeContext)_localctx).KEYWORD_BOOL!=null?((TypeContext)_localctx).KEYWORD_BOOL.getLine():0), ((TypeContext)_localctx).KEYWORD_BOOL.getCharPositionInLine(), (((TypeContext)_localctx).KEYWORD_BOOL!=null?((TypeContext)_localctx).KEYWORD_BOOL.getLine():0), ((TypeContext)_localctx).KEYWORD_BOOL.getCharPositionInLine()+((TypeContext)_localctx).KEYWORD_BOOL.getText().length()), AstAtomType.Type.BOOL);
				         ((TypeContext)_localctx).ast =  atom;
				         ((TypeContext)_localctx).begLine =  (((TypeContext)_localctx).KEYWORD_BOOL!=null?((TypeContext)_localctx).KEYWORD_BOOL.getLine():0);
				         ((TypeContext)_localctx).begColumn =  ((TypeContext)_localctx).KEYWORD_BOOL.getCharPositionInLine();
				         ((TypeContext)_localctx).endLine =  (((TypeContext)_localctx).KEYWORD_BOOL!=null?((TypeContext)_localctx).KEYWORD_BOOL.getLine():0);
				         ((TypeContext)_localctx).endColumn =  ((TypeContext)_localctx).KEYWORD_BOOL.getCharPositionInLine()+((TypeContext)_localctx).KEYWORD_BOOL.getText().length();
				  
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 5);
				{
				setState(198);
				((TypeContext)_localctx).IDENTIFIER = match(IDENTIFIER);

				        AstNameType name = new AstNameType(new Location((((TypeContext)_localctx).IDENTIFIER!=null?((TypeContext)_localctx).IDENTIFIER.getLine():0), ((TypeContext)_localctx).IDENTIFIER.getCharPositionInLine(), (((TypeContext)_localctx).IDENTIFIER!=null?((TypeContext)_localctx).IDENTIFIER.getLine():0), ((TypeContext)_localctx).IDENTIFIER.getCharPositionInLine()+((TypeContext)_localctx).IDENTIFIER.getText().length()), (((TypeContext)_localctx).IDENTIFIER!=null?((TypeContext)_localctx).IDENTIFIER.getText():null));
				        ((TypeContext)_localctx).ast =  name;
				        ((TypeContext)_localctx).begLine =  (((TypeContext)_localctx).IDENTIFIER!=null?((TypeContext)_localctx).IDENTIFIER.getLine():0);
				        ((TypeContext)_localctx).begColumn =  ((TypeContext)_localctx).IDENTIFIER.getCharPositionInLine();
				        ((TypeContext)_localctx).endLine =  (((TypeContext)_localctx).IDENTIFIER!=null?((TypeContext)_localctx).IDENTIFIER.getLine():0);
				        ((TypeContext)_localctx).endColumn =  ((TypeContext)_localctx).IDENTIFIER.getCharPositionInLine()+((TypeContext)_localctx).IDENTIFIER.getText().length();
				    
				}
				break;
			case SYMBOL_SQUARE_BRACKET_OPEN:
				enterOuterAlt(_localctx, 6);
				{
				setState(200);
				((TypeContext)_localctx).SYMBOL_SQUARE_BRACKET_OPEN = match(SYMBOL_SQUARE_BRACKET_OPEN);
				setState(201);
				((TypeContext)_localctx).expression = expression();
				setState(202);
				match(SYMBOL_SQUARE_BRACKET_CLOSE);
				setState(203);
				((TypeContext)_localctx).type = type();

				        AstArrType arr = new AstArrType(new Location((((TypeContext)_localctx).SYMBOL_SQUARE_BRACKET_OPEN!=null?((TypeContext)_localctx).SYMBOL_SQUARE_BRACKET_OPEN.getLine():0), ((TypeContext)_localctx).SYMBOL_SQUARE_BRACKET_OPEN.getCharPositionInLine(), ((TypeContext)_localctx).type.endLine, ((TypeContext)_localctx).type.endColumn), (AstType)((TypeContext)_localctx).type.ast, ((TypeContext)_localctx).expression.ast);
				        ((TypeContext)_localctx).ast =  arr;
				        ((TypeContext)_localctx).begLine =  (((TypeContext)_localctx).SYMBOL_SQUARE_BRACKET_OPEN!=null?((TypeContext)_localctx).SYMBOL_SQUARE_BRACKET_OPEN.getLine():0);
				        ((TypeContext)_localctx).begColumn =  ((TypeContext)_localctx).SYMBOL_SQUARE_BRACKET_OPEN.getCharPositionInLine();
				        ((TypeContext)_localctx).endLine =  ((TypeContext)_localctx).type.endLine;
				        ((TypeContext)_localctx).endColumn =  ((TypeContext)_localctx).type.endColumn;
				    
				}
				break;
			case SYMBOL_CIRCUMFLEX:
				enterOuterAlt(_localctx, 7);
				{
				setState(206);
				((TypeContext)_localctx).SYMBOL_CIRCUMFLEX = match(SYMBOL_CIRCUMFLEX);
				setState(207);
				((TypeContext)_localctx).type = type();

				        AstPtrType pointer = new AstPtrType(new Location((((TypeContext)_localctx).SYMBOL_CIRCUMFLEX!=null?((TypeContext)_localctx).SYMBOL_CIRCUMFLEX.getLine():0), ((TypeContext)_localctx).SYMBOL_CIRCUMFLEX.getCharPositionInLine(), ((TypeContext)_localctx).type.endLine, ((TypeContext)_localctx).type.endColumn), (AstType)((TypeContext)_localctx).type.ast);
				        ((TypeContext)_localctx).ast =  pointer;
				        ((TypeContext)_localctx).begLine =  (((TypeContext)_localctx).SYMBOL_CIRCUMFLEX!=null?((TypeContext)_localctx).SYMBOL_CIRCUMFLEX.getLine():0);
				        ((TypeContext)_localctx).begColumn =  ((TypeContext)_localctx).SYMBOL_CIRCUMFLEX.getCharPositionInLine();
				        ((TypeContext)_localctx).endLine =  ((TypeContext)_localctx).type.endLine;
				        ((TypeContext)_localctx).endColumn =  ((TypeContext)_localctx).type.endColumn;
				    
				}
				break;
			case SYMBOL_CURLY_BRACKET_OPEN:
				enterOuterAlt(_localctx, 8);
				{
				Vector<AstTree> rec_declarations = new Vector<>();
				{
				setState(211);
				((TypeContext)_localctx).SYMBOL_CURLY_BRACKET_OPEN = match(SYMBOL_CURLY_BRACKET_OPEN);
				setState(212);
				((TypeContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(213);
				match(SYMBOL_COLON);
				setState(214);
				((TypeContext)_localctx).type = type();

				        rec_declarations.add(new AstCmpDecl(new Location((((TypeContext)_localctx).SYMBOL_CURLY_BRACKET_OPEN!=null?((TypeContext)_localctx).SYMBOL_CURLY_BRACKET_OPEN.getLine():0), ((TypeContext)_localctx).SYMBOL_CURLY_BRACKET_OPEN.getCharPositionInLine(), ((TypeContext)_localctx).type.endLine, ((TypeContext)_localctx).type.endColumn), (((TypeContext)_localctx).IDENTIFIER!=null?((TypeContext)_localctx).IDENTIFIER.getText():null), (AstType)((TypeContext)_localctx).type.ast));
				    
				setState(224);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1+1 ) {
						{
						{
						setState(216);
						((TypeContext)_localctx).SYMBOL_COMMA = match(SYMBOL_COMMA);
						setState(217);
						((TypeContext)_localctx).IDENTIFIER = match(IDENTIFIER);
						setState(218);
						match(SYMBOL_COLON);
						setState(219);
						((TypeContext)_localctx).type = type();

						            rec_declarations.add(new AstCmpDecl(new Location((((TypeContext)_localctx).SYMBOL_COMMA!=null?((TypeContext)_localctx).SYMBOL_COMMA.getLine():0), ((TypeContext)_localctx).SYMBOL_COMMA.getCharPositionInLine(), ((TypeContext)_localctx).type.endLine, ((TypeContext)_localctx).type.endColumn), (((TypeContext)_localctx).IDENTIFIER!=null?((TypeContext)_localctx).IDENTIFIER.getText():null), (AstType)((TypeContext)_localctx).type.ast));
						        
						}
						} 
					}
					setState(226);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				}
				setState(227);
				((TypeContext)_localctx).SYMBOL_CURLY_BRACKET_CLOSE = match(SYMBOL_CURLY_BRACKET_CLOSE);
				}

				        AstTrees recAst = new AstTrees("Rec-Declaration", rec_declarations);
				        AstRecType rec = new AstRecType(new Location((((TypeContext)_localctx).SYMBOL_CURLY_BRACKET_OPEN!=null?((TypeContext)_localctx).SYMBOL_CURLY_BRACKET_OPEN.getLine():0), ((TypeContext)_localctx).SYMBOL_CURLY_BRACKET_OPEN.getCharPositionInLine(), (((TypeContext)_localctx).SYMBOL_CURLY_BRACKET_CLOSE!=null?((TypeContext)_localctx).SYMBOL_CURLY_BRACKET_CLOSE.getLine():0), ((TypeContext)_localctx).SYMBOL_CURLY_BRACKET_CLOSE.getCharPositionInLine()+((TypeContext)_localctx).SYMBOL_CURLY_BRACKET_CLOSE.getText().length()), recAst);
				        ((TypeContext)_localctx).ast =  rec;
				        ((TypeContext)_localctx).begLine =  (((TypeContext)_localctx).SYMBOL_CURLY_BRACKET_OPEN!=null?((TypeContext)_localctx).SYMBOL_CURLY_BRACKET_OPEN.getLine():0);
				        ((TypeContext)_localctx).begColumn =  ((TypeContext)_localctx).SYMBOL_CURLY_BRACKET_OPEN.getCharPositionInLine();
				        ((TypeContext)_localctx).endLine =  (((TypeContext)_localctx).SYMBOL_CURLY_BRACKET_CLOSE!=null?((TypeContext)_localctx).SYMBOL_CURLY_BRACKET_CLOSE.getLine():0);
				        ((TypeContext)_localctx).endColumn =  ((TypeContext)_localctx).SYMBOL_CURLY_BRACKET_CLOSE.getCharPositionInLine()+((TypeContext)_localctx).SYMBOL_CURLY_BRACKET_CLOSE.getText().length();
				    
				}
				break;
			case SYMBOL_ROUND_BRACKET_OPEN:
				enterOuterAlt(_localctx, 9);
				{
				setState(231);
				((TypeContext)_localctx).SYMBOL_ROUND_BRACKET_OPEN = match(SYMBOL_ROUND_BRACKET_OPEN);
				setState(232);
				((TypeContext)_localctx).type = type();
				setState(233);
				((TypeContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE = match(SYMBOL_ROUND_BRACKET_CLOSE);

				        ((TypeContext)_localctx).ast =  ((TypeContext)_localctx).type.ast;
				        ((TypeContext)_localctx).begLine =  (((TypeContext)_localctx).SYMBOL_ROUND_BRACKET_OPEN!=null?((TypeContext)_localctx).SYMBOL_ROUND_BRACKET_OPEN.getLine():0);
				        ((TypeContext)_localctx).begColumn =  ((TypeContext)_localctx).SYMBOL_ROUND_BRACKET_OPEN.getCharPositionInLine();
				        ((TypeContext)_localctx).endLine =  (((TypeContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE!=null?((TypeContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE.getLine():0);
				        ((TypeContext)_localctx).endColumn =  ((TypeContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE.getCharPositionInLine()+((TypeContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE.getText().length();
				    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public AstExpr ast;
		public Expression_twoContext expression_two;
		public Expression_pContext expression_p;
		public Expression_twoContext expression_two() {
			return getRuleContext(Expression_twoContext.class,0);
		}
		public Expression_pContext expression_p() {
			return getRuleContext(Expression_pContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			((ExpressionContext)_localctx).expression_two = expression_two();
			setState(239);
			((ExpressionContext)_localctx).expression_p = expression_p(((ExpressionContext)_localctx).expression_two.ast);

			        ((ExpressionContext)_localctx).ast =  ((ExpressionContext)_localctx).expression_p.ast;
			  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expression_pContext extends ParserRuleContext {
		public AstExpr left;
		public AstExpr ast;
		public Token SYMBOL_VERTICAL_BAR;
		public Expression_twoContext expression_two;
		public Expression_pContext expression_p;
		public TerminalNode SYMBOL_VERTICAL_BAR() { return getToken(PrevParser.SYMBOL_VERTICAL_BAR, 0); }
		public Expression_twoContext expression_two() {
			return getRuleContext(Expression_twoContext.class,0);
		}
		public Expression_pContext expression_p() {
			return getRuleContext(Expression_pContext.class,0);
		}
		public Expression_pContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Expression_pContext(ParserRuleContext parent, int invokingState, AstExpr left) {
			super(parent, invokingState);
			this.left = left;
		}
		@Override public int getRuleIndex() { return RULE_expression_p; }
	}

	public final Expression_pContext expression_p(AstExpr left) throws RecognitionException {
		Expression_pContext _localctx = new Expression_pContext(_ctx, getState(), left);
		enterRule(_localctx, 14, RULE_expression_p);
		try {
			setState(248);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SYMBOL_VERTICAL_BAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(242);
				((Expression_pContext)_localctx).SYMBOL_VERTICAL_BAR = match(SYMBOL_VERTICAL_BAR);
				setState(243);
				((Expression_pContext)_localctx).expression_two = expression_two();
				setState(244);
				((Expression_pContext)_localctx).expression_p = expression_p(new AstBinExpr(new Location((((Expression_pContext)_localctx).SYMBOL_VERTICAL_BAR!=null?((Expression_pContext)_localctx).SYMBOL_VERTICAL_BAR.getLine():0),((Expression_pContext)_localctx).SYMBOL_VERTICAL_BAR.getCharPositionInLine(), getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length()), AstBinExpr.Oper.OR, _localctx.left, ((Expression_pContext)_localctx).expression_two.ast));

				         ((Expression_pContext)_localctx).ast =  ((Expression_pContext)_localctx).expression_p.ast;
				  
				}
				break;
			case SYMBOL_ROUND_BRACKET_CLOSE:
			case SYMBOL_CURLY_BRACKET_CLOSE:
			case SYMBOL_SQUARE_BRACKET_CLOSE:
			case SYMBOL_COMMA:
			case SYMBOL_COLON:
			case SYMBOL_SEMICOLON:
			case SYMBOL_EQUAL:
			case KEYWORD_DO:
			case KEYWORD_ELSE:
			case KEYWORD_THEN:
				enterOuterAlt(_localctx, 2);
				{
				((Expression_pContext)_localctx).ast =  _localctx.left;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expression_twoContext extends ParserRuleContext {
		public AstExpr ast;
		public Expression_threeContext expression_three;
		public Expression_two_pContext expression_two_p;
		public Expression_threeContext expression_three() {
			return getRuleContext(Expression_threeContext.class,0);
		}
		public Expression_two_pContext expression_two_p() {
			return getRuleContext(Expression_two_pContext.class,0);
		}
		public Expression_twoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_two; }
	}

	public final Expression_twoContext expression_two() throws RecognitionException {
		Expression_twoContext _localctx = new Expression_twoContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_expression_two);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			((Expression_twoContext)_localctx).expression_three = expression_three();
			setState(251);
			((Expression_twoContext)_localctx).expression_two_p = expression_two_p(((Expression_twoContext)_localctx).expression_three.ast);

			        ((Expression_twoContext)_localctx).ast =  ((Expression_twoContext)_localctx).expression_two_p.ast;
			  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expression_two_pContext extends ParserRuleContext {
		public AstExpr left;
		public AstExpr ast;
		public Token SYMBOL_AND;
		public Expression_threeContext expression_three;
		public Expression_two_pContext expression_two_p;
		public TerminalNode SYMBOL_AND() { return getToken(PrevParser.SYMBOL_AND, 0); }
		public Expression_threeContext expression_three() {
			return getRuleContext(Expression_threeContext.class,0);
		}
		public Expression_two_pContext expression_two_p() {
			return getRuleContext(Expression_two_pContext.class,0);
		}
		public Expression_two_pContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Expression_two_pContext(ParserRuleContext parent, int invokingState, AstExpr left) {
			super(parent, invokingState);
			this.left = left;
		}
		@Override public int getRuleIndex() { return RULE_expression_two_p; }
	}

	public final Expression_two_pContext expression_two_p(AstExpr left) throws RecognitionException {
		Expression_two_pContext _localctx = new Expression_two_pContext(_ctx, getState(), left);
		enterRule(_localctx, 18, RULE_expression_two_p);
		try {
			setState(260);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SYMBOL_AND:
				enterOuterAlt(_localctx, 1);
				{
				setState(254);
				((Expression_two_pContext)_localctx).SYMBOL_AND = match(SYMBOL_AND);
				setState(255);
				((Expression_two_pContext)_localctx).expression_three = expression_three();
				setState(256);
				((Expression_two_pContext)_localctx).expression_two_p = expression_two_p(new AstBinExpr(new Location((((Expression_two_pContext)_localctx).SYMBOL_AND!=null?((Expression_two_pContext)_localctx).SYMBOL_AND.getLine():0),((Expression_two_pContext)_localctx).SYMBOL_AND.getCharPositionInLine(), getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length()), AstBinExpr.Oper.AND, _localctx.left, ((Expression_two_pContext)_localctx).expression_three.ast));

				        ((Expression_two_pContext)_localctx).ast =  ((Expression_two_pContext)_localctx).expression_two_p.ast;
				  
				}
				break;
			case SYMBOL_ROUND_BRACKET_CLOSE:
			case SYMBOL_CURLY_BRACKET_CLOSE:
			case SYMBOL_SQUARE_BRACKET_CLOSE:
			case SYMBOL_COMMA:
			case SYMBOL_COLON:
			case SYMBOL_SEMICOLON:
			case SYMBOL_VERTICAL_BAR:
			case SYMBOL_EQUAL:
			case KEYWORD_DO:
			case KEYWORD_ELSE:
			case KEYWORD_THEN:
				enterOuterAlt(_localctx, 2);
				{
				((Expression_two_pContext)_localctx).ast =  _localctx.left;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expression_threeContext extends ParserRuleContext {
		public AstExpr ast;
		public Expression_fourContext expression_four;
		public Expression_three_pContext expression_three_p;
		public Expression_fourContext expression_four() {
			return getRuleContext(Expression_fourContext.class,0);
		}
		public Expression_three_pContext expression_three_p() {
			return getRuleContext(Expression_three_pContext.class,0);
		}
		public Expression_threeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_three; }
	}

	public final Expression_threeContext expression_three() throws RecognitionException {
		Expression_threeContext _localctx = new Expression_threeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_expression_three);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			((Expression_threeContext)_localctx).expression_four = expression_four();
			setState(263);
			((Expression_threeContext)_localctx).expression_three_p = expression_three_p(((Expression_threeContext)_localctx).expression_four.ast);

			        ((Expression_threeContext)_localctx).ast =  ((Expression_threeContext)_localctx).expression_three_p.ast;
			 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expression_three_pContext extends ParserRuleContext {
		public AstExpr left;
		public AstExpr ast;
		public Relational_operatorContext relational_operator;
		public Expression_fourContext expression_four;
		public Expression_four_pContext expression_four_p;
		public Relational_operatorContext relational_operator() {
			return getRuleContext(Relational_operatorContext.class,0);
		}
		public Expression_fourContext expression_four() {
			return getRuleContext(Expression_fourContext.class,0);
		}
		public Expression_four_pContext expression_four_p() {
			return getRuleContext(Expression_four_pContext.class,0);
		}
		public Expression_three_pContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Expression_three_pContext(ParserRuleContext parent, int invokingState, AstExpr left) {
			super(parent, invokingState);
			this.left = left;
		}
		@Override public int getRuleIndex() { return RULE_expression_three_p; }
	}

	public final Expression_three_pContext expression_three_p(AstExpr left) throws RecognitionException {
		Expression_three_pContext _localctx = new Expression_three_pContext(_ctx, getState(), left);
		enterRule(_localctx, 22, RULE_expression_three_p);
		try {
			setState(272);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SYMBOL_DOUBLE_EQUAL:
			case SYMBOL_NOT_EQUAL:
			case SYMBOL_SMALLER_THAN:
			case SYMBOL_GREATER_THAN:
			case SYMBOL_SMALLER_OR_EQUALS:
			case SYMBOL_GREATER_OR_EQUALS:
				enterOuterAlt(_localctx, 1);
				{
				setState(266);
				((Expression_three_pContext)_localctx).relational_operator = relational_operator();
				setState(267);
				((Expression_three_pContext)_localctx).expression_four = expression_four();
				setState(268);
				((Expression_three_pContext)_localctx).expression_four_p = expression_four_p(new AstBinExpr(new Location(((Expression_three_pContext)_localctx).relational_operator.begLine, ((Expression_three_pContext)_localctx).relational_operator.begColumn, ((Expression_three_pContext)_localctx).relational_operator.endLine, ((Expression_three_pContext)_localctx).relational_operator.endColumn), ((Expression_three_pContext)_localctx).relational_operator.op, _localctx.left, ((Expression_three_pContext)_localctx).expression_four.ast));

				        ((Expression_three_pContext)_localctx).ast =  ((Expression_three_pContext)_localctx).expression_four_p.ast;

				}
				break;
			case SYMBOL_ROUND_BRACKET_CLOSE:
			case SYMBOL_CURLY_BRACKET_CLOSE:
			case SYMBOL_SQUARE_BRACKET_CLOSE:
			case SYMBOL_COMMA:
			case SYMBOL_COLON:
			case SYMBOL_SEMICOLON:
			case SYMBOL_AND:
			case SYMBOL_VERTICAL_BAR:
			case SYMBOL_EQUAL:
			case KEYWORD_DO:
			case KEYWORD_ELSE:
			case KEYWORD_THEN:
				enterOuterAlt(_localctx, 2);
				{
				((Expression_three_pContext)_localctx).ast =  _localctx.left;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expression_fourContext extends ParserRuleContext {
		public AstExpr ast;
		public Expression_fiveContext expression_five;
		public Expression_four_pContext expression_four_p;
		public Expression_fiveContext expression_five() {
			return getRuleContext(Expression_fiveContext.class,0);
		}
		public Expression_four_pContext expression_four_p() {
			return getRuleContext(Expression_four_pContext.class,0);
		}
		public Expression_fourContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_four; }
	}

	public final Expression_fourContext expression_four() throws RecognitionException {
		Expression_fourContext _localctx = new Expression_fourContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_expression_four);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			((Expression_fourContext)_localctx).expression_five = expression_five();
			setState(275);
			((Expression_fourContext)_localctx).expression_four_p = expression_four_p(((Expression_fourContext)_localctx).expression_five.ast);

			        ((Expression_fourContext)_localctx).ast =  ((Expression_fourContext)_localctx).expression_four_p.ast;
			  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expression_four_pContext extends ParserRuleContext {
		public AstExpr left;
		public AstExpr ast;
		public Additive_operatorContext additive_operator;
		public Expression_fiveContext expression_five;
		public Expression_four_pContext expression_four_p;
		public Additive_operatorContext additive_operator() {
			return getRuleContext(Additive_operatorContext.class,0);
		}
		public Expression_fiveContext expression_five() {
			return getRuleContext(Expression_fiveContext.class,0);
		}
		public Expression_four_pContext expression_four_p() {
			return getRuleContext(Expression_four_pContext.class,0);
		}
		public Expression_four_pContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Expression_four_pContext(ParserRuleContext parent, int invokingState, AstExpr left) {
			super(parent, invokingState);
			this.left = left;
		}
		@Override public int getRuleIndex() { return RULE_expression_four_p; }
	}

	public final Expression_four_pContext expression_four_p(AstExpr left) throws RecognitionException {
		Expression_four_pContext _localctx = new Expression_four_pContext(_ctx, getState(), left);
		enterRule(_localctx, 26, RULE_expression_four_p);
		try {
			setState(284);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(278);
				((Expression_four_pContext)_localctx).additive_operator = additive_operator();
				setState(279);
				((Expression_four_pContext)_localctx).expression_five = expression_five();
				setState(280);
				((Expression_four_pContext)_localctx).expression_four_p = expression_four_p(new AstBinExpr(new Location(((Expression_four_pContext)_localctx).additive_operator.begLine, ((Expression_four_pContext)_localctx).additive_operator.begColumn, ((Expression_four_pContext)_localctx).additive_operator.endLine, ((Expression_four_pContext)_localctx).additive_operator.endColumn), ((Expression_four_pContext)_localctx).additive_operator.op, _localctx.left, ((Expression_four_pContext)_localctx).expression_five.ast));

				        ((Expression_four_pContext)_localctx).ast =  ((Expression_four_pContext)_localctx).expression_four_p.ast;
				  
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				((Expression_four_pContext)_localctx).ast =  _localctx.left;
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expression_fiveContext extends ParserRuleContext {
		public AstExpr ast;
		public Expression_sixContext expression_six;
		public Expression_five_pContext expression_five_p;
		public Expression_sixContext expression_six() {
			return getRuleContext(Expression_sixContext.class,0);
		}
		public Expression_five_pContext expression_five_p() {
			return getRuleContext(Expression_five_pContext.class,0);
		}
		public Expression_fiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_five; }
	}

	public final Expression_fiveContext expression_five() throws RecognitionException {
		Expression_fiveContext _localctx = new Expression_fiveContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_expression_five);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			((Expression_fiveContext)_localctx).expression_six = expression_six();
			setState(287);
			((Expression_fiveContext)_localctx).expression_five_p = expression_five_p(((Expression_fiveContext)_localctx).expression_six.ast);

			        ((Expression_fiveContext)_localctx).ast =  ((Expression_fiveContext)_localctx).expression_five_p.ast;
			  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expression_five_pContext extends ParserRuleContext {
		public AstExpr left;
		public AstExpr ast;
		public Multiplicative_operatorContext multiplicative_operator;
		public Expression_sixContext expression_six;
		public Expression_five_pContext expression_five_p;
		public Multiplicative_operatorContext multiplicative_operator() {
			return getRuleContext(Multiplicative_operatorContext.class,0);
		}
		public Expression_sixContext expression_six() {
			return getRuleContext(Expression_sixContext.class,0);
		}
		public Expression_five_pContext expression_five_p() {
			return getRuleContext(Expression_five_pContext.class,0);
		}
		public Expression_five_pContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Expression_five_pContext(ParserRuleContext parent, int invokingState, AstExpr left) {
			super(parent, invokingState);
			this.left = left;
		}
		@Override public int getRuleIndex() { return RULE_expression_five_p; }
	}

	public final Expression_five_pContext expression_five_p(AstExpr left) throws RecognitionException {
		Expression_five_pContext _localctx = new Expression_five_pContext(_ctx, getState(), left);
		enterRule(_localctx, 30, RULE_expression_five_p);
		try {
			setState(296);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SYMBOL_STAR:
			case SYMBOL_SLASH:
			case SYMBOL_PERCENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(290);
				((Expression_five_pContext)_localctx).multiplicative_operator = multiplicative_operator();
				setState(291);
				((Expression_five_pContext)_localctx).expression_six = expression_six();
				setState(292);
				((Expression_five_pContext)_localctx).expression_five_p = expression_five_p(new AstBinExpr(new Location(((Expression_five_pContext)_localctx).multiplicative_operator.begLine, ((Expression_five_pContext)_localctx).multiplicative_operator.begColumn, ((Expression_five_pContext)_localctx).multiplicative_operator.endLine, ((Expression_five_pContext)_localctx).multiplicative_operator.endColumn), ((Expression_five_pContext)_localctx).multiplicative_operator.op, _localctx.left, ((Expression_five_pContext)_localctx).expression_six.ast));

				        ((Expression_five_pContext)_localctx).ast =  ((Expression_five_pContext)_localctx).expression_five_p.ast;
				  
				}
				break;
			case SYMBOL_ROUND_BRACKET_CLOSE:
			case SYMBOL_CURLY_BRACKET_CLOSE:
			case SYMBOL_SQUARE_BRACKET_CLOSE:
			case SYMBOL_COMMA:
			case SYMBOL_COLON:
			case SYMBOL_SEMICOLON:
			case SYMBOL_AND:
			case SYMBOL_VERTICAL_BAR:
			case SYMBOL_DOUBLE_EQUAL:
			case SYMBOL_NOT_EQUAL:
			case SYMBOL_SMALLER_THAN:
			case SYMBOL_GREATER_THAN:
			case SYMBOL_SMALLER_OR_EQUALS:
			case SYMBOL_GREATER_OR_EQUALS:
			case SYMBOL_PLUS:
			case SYMBOL_MINUS:
			case SYMBOL_EQUAL:
			case KEYWORD_DO:
			case KEYWORD_ELSE:
			case KEYWORD_THEN:
				enterOuterAlt(_localctx, 2);
				{
				((Expression_five_pContext)_localctx).ast =  _localctx.left;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expression_sixContext extends ParserRuleContext {
		public AstExpr ast;
		public Prefix_operatorContext prefix_operator;
		public Expression_sixContext expression_six;
		public Expression_sevenContext expression_seven;
		public Prefix_operatorContext prefix_operator() {
			return getRuleContext(Prefix_operatorContext.class,0);
		}
		public Expression_sixContext expression_six() {
			return getRuleContext(Expression_sixContext.class,0);
		}
		public Expression_sevenContext expression_seven() {
			return getRuleContext(Expression_sevenContext.class,0);
		}
		public Expression_sixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_six; }
	}

	public final Expression_sixContext expression_six() throws RecognitionException {
		Expression_sixContext _localctx = new Expression_sixContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_expression_six);
		try {
			setState(305);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SYMBOL_EXCLAMATION_MARK:
			case SYMBOL_PLUS:
			case SYMBOL_MINUS:
			case SYMBOL_CIRCUMFLEX:
				enterOuterAlt(_localctx, 1);
				{
				setState(298);
				((Expression_sixContext)_localctx).prefix_operator = prefix_operator();
				setState(299);
				((Expression_sixContext)_localctx).expression_six = expression_six();

				        ((Expression_sixContext)_localctx).ast =  new AstPfxExpr(new Location(((Expression_sixContext)_localctx).prefix_operator.begLine, ((Expression_sixContext)_localctx).prefix_operator.begColumn, ((Expression_sixContext)_localctx).prefix_operator.endLine, ((Expression_sixContext)_localctx).prefix_operator.endColumn), ((Expression_sixContext)_localctx).prefix_operator.op, ((Expression_sixContext)_localctx).expression_six.ast);
				  
				}
				break;
			case CONSTANT_VOID:
			case CONSTANT_TRUE:
			case CONSTANT_FALSE:
			case CONSTANT_INTEGER:
			case CONSTANT_CHAR:
			case CONSTANT_STRING:
			case CONSTANT_POINTER:
			case SYMBOL_ROUND_BRACKET_OPEN:
			case KEYWORD_DEL:
			case KEYWORD_NEW:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(302);
				((Expression_sixContext)_localctx).expression_seven = expression_seven(0);
				((Expression_sixContext)_localctx).ast =  ((Expression_sixContext)_localctx).expression_seven.ast;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expression_sevenContext extends ParserRuleContext {
		public AstExpr ast;
		public Expression_sevenContext expr;
		public Expression_eightContext expression_eight;
		public Token SYMBOL_CIRCUMFLEX;
		public Token SYMBOL_SQUARE_BRACKET_OPEN;
		public ExpressionContext expression;
		public Token SYMBOL_DOT;
		public Token IDENTIFIER;
		public Expression_eightContext expression_eight() {
			return getRuleContext(Expression_eightContext.class,0);
		}
		public TerminalNode SYMBOL_CIRCUMFLEX() { return getToken(PrevParser.SYMBOL_CIRCUMFLEX, 0); }
		public Expression_sevenContext expression_seven() {
			return getRuleContext(Expression_sevenContext.class,0);
		}
		public TerminalNode SYMBOL_SQUARE_BRACKET_OPEN() { return getToken(PrevParser.SYMBOL_SQUARE_BRACKET_OPEN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SYMBOL_SQUARE_BRACKET_CLOSE() { return getToken(PrevParser.SYMBOL_SQUARE_BRACKET_CLOSE, 0); }
		public TerminalNode SYMBOL_DOT() { return getToken(PrevParser.SYMBOL_DOT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PrevParser.IDENTIFIER, 0); }
		public Expression_sevenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_seven; }
	}

	public final Expression_sevenContext expression_seven() throws RecognitionException {
		return expression_seven(0);
	}

	private Expression_sevenContext expression_seven(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expression_sevenContext _localctx = new Expression_sevenContext(_ctx, _parentState);
		Expression_sevenContext _prevctx = _localctx;
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_expression_seven, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(308);
			((Expression_sevenContext)_localctx).expression_eight = expression_eight();
			((Expression_sevenContext)_localctx).ast =  ((Expression_sevenContext)_localctx).expression_eight.ast;
			}
			_ctx.stop = _input.LT(-1);
			setState(326);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(324);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new Expression_sevenContext(_parentctx, _parentState);
						_localctx.expr = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression_seven);
						setState(311);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(312);
						((Expression_sevenContext)_localctx).SYMBOL_CIRCUMFLEX = match(SYMBOL_CIRCUMFLEX);

						                                        ((Expression_sevenContext)_localctx).ast =  new AstSfxExpr(new Location((((Expression_sevenContext)_localctx).SYMBOL_CIRCUMFLEX!=null?((Expression_sevenContext)_localctx).SYMBOL_CIRCUMFLEX.getLine():0), ((Expression_sevenContext)_localctx).SYMBOL_CIRCUMFLEX.getCharPositionInLine(), getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length()), AstSfxExpr.Oper.PTR, ((Expression_sevenContext)_localctx).expr.ast);
						                                   
						}
						break;
					case 2:
						{
						_localctx = new Expression_sevenContext(_parentctx, _parentState);
						_localctx.expr = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression_seven);
						setState(314);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(315);
						((Expression_sevenContext)_localctx).SYMBOL_SQUARE_BRACKET_OPEN = match(SYMBOL_SQUARE_BRACKET_OPEN);
						setState(316);
						((Expression_sevenContext)_localctx).expression = expression();
						setState(317);
						match(SYMBOL_SQUARE_BRACKET_CLOSE);

						                                        ((Expression_sevenContext)_localctx).ast =  new AstArrExpr(new Location((((Expression_sevenContext)_localctx).SYMBOL_SQUARE_BRACKET_OPEN!=null?((Expression_sevenContext)_localctx).SYMBOL_SQUARE_BRACKET_OPEN.getLine():0), ((Expression_sevenContext)_localctx).SYMBOL_SQUARE_BRACKET_OPEN.getCharPositionInLine(), getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length()), ((Expression_sevenContext)_localctx).expr.ast, ((Expression_sevenContext)_localctx).expression.ast);
						                                   
						}
						break;
					case 3:
						{
						_localctx = new Expression_sevenContext(_parentctx, _parentState);
						_localctx.expr = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression_seven);
						setState(320);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(321);
						((Expression_sevenContext)_localctx).SYMBOL_DOT = match(SYMBOL_DOT);
						setState(322);
						((Expression_sevenContext)_localctx).IDENTIFIER = match(IDENTIFIER);

						                                        AstNameExpr name_expr = new AstNameExpr(new Location((((Expression_sevenContext)_localctx).IDENTIFIER!=null?((Expression_sevenContext)_localctx).IDENTIFIER.getLine():0), ((Expression_sevenContext)_localctx).IDENTIFIER.getCharPositionInLine(), getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length()), (((Expression_sevenContext)_localctx).IDENTIFIER!=null?((Expression_sevenContext)_localctx).IDENTIFIER.getText():null));
						                                        AstRecExpr rec_expr = new AstRecExpr(new Location((((Expression_sevenContext)_localctx).SYMBOL_DOT!=null?((Expression_sevenContext)_localctx).SYMBOL_DOT.getLine():0), ((Expression_sevenContext)_localctx).SYMBOL_DOT.getCharPositionInLine(), getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length()), ((Expression_sevenContext)_localctx).expr.ast, name_expr);
						                                        ((Expression_sevenContext)_localctx).ast =  rec_expr;
						                                   
						}
						break;
					}
					} 
				}
				setState(328);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expression_eightContext extends ParserRuleContext {
		public AstExpr ast;
		public int begLine;
		public int begColumn;
		public int endLine;
		public int endColumn;
		public ConstantContext constant;
		public Token IDENTIFIER;
		public ExpressionContext expression;
		public Token SYMBOL_ROUND_BRACKET_OPEN;
		public TypeContext type;
		public Token SYMBOL_ROUND_BRACKET_CLOSE;
		public Token KEYWORD_NEW;
		public Token KEYWORD_DEL;
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(PrevParser.IDENTIFIER, 0); }
		public TerminalNode SYMBOL_ROUND_BRACKET_OPEN() { return getToken(PrevParser.SYMBOL_ROUND_BRACKET_OPEN, 0); }
		public TerminalNode SYMBOL_ROUND_BRACKET_CLOSE() { return getToken(PrevParser.SYMBOL_ROUND_BRACKET_CLOSE, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> SYMBOL_COMMA() { return getTokens(PrevParser.SYMBOL_COMMA); }
		public TerminalNode SYMBOL_COMMA(int i) {
			return getToken(PrevParser.SYMBOL_COMMA, i);
		}
		public TerminalNode SYMBOL_COLON() { return getToken(PrevParser.SYMBOL_COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode KEYWORD_NEW() { return getToken(PrevParser.KEYWORD_NEW, 0); }
		public TerminalNode KEYWORD_DEL() { return getToken(PrevParser.KEYWORD_DEL, 0); }
		public Expression_eightContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_eight; }
	}

	public final Expression_eightContext expression_eight() throws RecognitionException {
		Expression_eightContext _localctx = new Expression_eightContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_expression_eight);
		int _la;
		try {
			int _alt;
			setState(378);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONSTANT_VOID:
			case CONSTANT_TRUE:
			case CONSTANT_FALSE:
			case CONSTANT_INTEGER:
			case CONSTANT_CHAR:
			case CONSTANT_STRING:
			case CONSTANT_POINTER:
				enterOuterAlt(_localctx, 1);
				{
				setState(329);
				((Expression_eightContext)_localctx).constant = constant();

				        ((Expression_eightContext)_localctx).ast =  ((Expression_eightContext)_localctx).constant.const_;
				        ((Expression_eightContext)_localctx).begLine =  ((Expression_eightContext)_localctx).constant.begLine;
				        ((Expression_eightContext)_localctx).begColumn =  ((Expression_eightContext)_localctx).constant.begColumn;
				        ((Expression_eightContext)_localctx).endLine =  ((Expression_eightContext)_localctx).constant.endLine;
				        ((Expression_eightContext)_localctx).endColumn =  ((Expression_eightContext)_localctx).constant.endColumn;
				  
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				Vector<AstExpr> expr_vec = new Vector<>(); boolean isFunctionCall = false;
				{
				setState(333);
				((Expression_eightContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(350);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					{
					setState(334);
					match(SYMBOL_ROUND_BRACKET_OPEN);
					isFunctionCall = true;
					setState(347);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1143568329605630L) != 0)) {
						{
						setState(336);
						((Expression_eightContext)_localctx).expression = expression();
						expr_vec.add(((Expression_eightContext)_localctx).expression.ast);
						setState(344);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
						while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1+1 ) {
								{
								{
								setState(338);
								match(SYMBOL_COMMA);
								setState(339);
								((Expression_eightContext)_localctx).expression = expression();
								expr_vec.add(((Expression_eightContext)_localctx).expression.ast);
								}
								} 
							}
							setState(346);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
						}
						}
					}

					setState(349);
					match(SYMBOL_ROUND_BRACKET_CLOSE);
					}
					break;
				}
				}
				if (!isFunctionCall){
				        ((Expression_eightContext)_localctx).ast =  new AstNameExpr(new Location((((Expression_eightContext)_localctx).IDENTIFIER!=null?((Expression_eightContext)_localctx).IDENTIFIER.getLine():0), ((Expression_eightContext)_localctx).IDENTIFIER.getCharPositionInLine(), (((Expression_eightContext)_localctx).IDENTIFIER!=null?((Expression_eightContext)_localctx).IDENTIFIER.getLine():0), ((Expression_eightContext)_localctx).IDENTIFIER.getCharPositionInLine()+((Expression_eightContext)_localctx).IDENTIFIER.getText().length()), (((Expression_eightContext)_localctx).IDENTIFIER!=null?((Expression_eightContext)_localctx).IDENTIFIER.getText():null));
				        ((Expression_eightContext)_localctx).begLine =  (((Expression_eightContext)_localctx).IDENTIFIER!=null?((Expression_eightContext)_localctx).IDENTIFIER.getLine():0);
				        ((Expression_eightContext)_localctx).begColumn =  ((Expression_eightContext)_localctx).IDENTIFIER.getCharPositionInLine();
				        ((Expression_eightContext)_localctx).endLine =  (((Expression_eightContext)_localctx).IDENTIFIER!=null?((Expression_eightContext)_localctx).IDENTIFIER.getLine():0);
				        ((Expression_eightContext)_localctx).endColumn =  ((Expression_eightContext)_localctx).IDENTIFIER.getCharPositionInLine()+((Expression_eightContext)_localctx).IDENTIFIER.getText().length();
				  }else{
				        AstTrees trees = new AstTrees("Par-Declarations-Call", expr_vec);
				        ((Expression_eightContext)_localctx).ast =  new AstCallExpr(new Location((((Expression_eightContext)_localctx).IDENTIFIER!=null?((Expression_eightContext)_localctx).IDENTIFIER.getLine():0), ((Expression_eightContext)_localctx).IDENTIFIER.getCharPositionInLine(), getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length()), (((Expression_eightContext)_localctx).IDENTIFIER!=null?((Expression_eightContext)_localctx).IDENTIFIER.getText():null), trees);}
				        ((Expression_eightContext)_localctx).begLine =  (((Expression_eightContext)_localctx).IDENTIFIER!=null?((Expression_eightContext)_localctx).IDENTIFIER.getLine():0);
				        ((Expression_eightContext)_localctx).begColumn =  ((Expression_eightContext)_localctx).IDENTIFIER.getCharPositionInLine();
				        ((Expression_eightContext)_localctx).endLine =  getCurrentToken().getLine();
				        ((Expression_eightContext)_localctx).endColumn =  getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length();
				  
				}
				break;
			case SYMBOL_ROUND_BRACKET_OPEN:
				enterOuterAlt(_localctx, 3);
				{
				AstType type_type = null;
				{
				setState(354);
				((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_OPEN = match(SYMBOL_ROUND_BRACKET_OPEN);
				setState(355);
				((Expression_eightContext)_localctx).expression = expression();
				setState(360);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SYMBOL_COLON) {
					{
					setState(356);
					match(SYMBOL_COLON);
					setState(357);
					((Expression_eightContext)_localctx).type = type();
					type_type = (AstType)((Expression_eightContext)_localctx).type.ast;
					}
				}

				setState(362);
				((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE = match(SYMBOL_ROUND_BRACKET_CLOSE);
				}

				        ((Expression_eightContext)_localctx).ast =  new AstCastExpr(new Location((((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_OPEN!=null?((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_OPEN.getLine():0), ((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_OPEN.getCharPositionInLine(), (((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE!=null?((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE.getLine():0), ((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE.getCharPositionInLine()+((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE.getText().length()),((Expression_eightContext)_localctx).expression.ast,type_type);
				        ((Expression_eightContext)_localctx).begLine =  (((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_OPEN!=null?((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_OPEN.getLine():0);
				        ((Expression_eightContext)_localctx).begColumn =  ((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_OPEN.getCharPositionInLine();
				        ((Expression_eightContext)_localctx).endLine =  (((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE!=null?((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE.getLine():0);
				        ((Expression_eightContext)_localctx).endColumn =  ((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE.getCharPositionInLine()+((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE.getText().length();
				  
				}
				break;
			case KEYWORD_NEW:
				enterOuterAlt(_localctx, 4);
				{
				setState(366);
				((Expression_eightContext)_localctx).KEYWORD_NEW = match(KEYWORD_NEW);
				setState(367);
				match(SYMBOL_ROUND_BRACKET_OPEN);
				setState(368);
				((Expression_eightContext)_localctx).type = type();
				setState(369);
				((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE = match(SYMBOL_ROUND_BRACKET_CLOSE);

				        ((Expression_eightContext)_localctx).ast =  new AstNewExpr(new Location((((Expression_eightContext)_localctx).KEYWORD_NEW!=null?((Expression_eightContext)_localctx).KEYWORD_NEW.getLine():0), ((Expression_eightContext)_localctx).KEYWORD_NEW.getCharPositionInLine(), (((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE!=null?((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE.getLine():0), ((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE.getCharPositionInLine()+((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE.getText().length()),(AstType)((Expression_eightContext)_localctx).type.ast);
				        ((Expression_eightContext)_localctx).begLine =  (((Expression_eightContext)_localctx).KEYWORD_NEW!=null?((Expression_eightContext)_localctx).KEYWORD_NEW.getLine():0);
				        ((Expression_eightContext)_localctx).begColumn =  ((Expression_eightContext)_localctx).KEYWORD_NEW.getCharPositionInLine();
				        ((Expression_eightContext)_localctx).endLine =  (((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE!=null?((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE.getLine():0);
				        ((Expression_eightContext)_localctx).endColumn =  ((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE.getCharPositionInLine()+((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE.getText().length();
				  
				}
				break;
			case KEYWORD_DEL:
				enterOuterAlt(_localctx, 5);
				{
				setState(372);
				((Expression_eightContext)_localctx).KEYWORD_DEL = match(KEYWORD_DEL);
				setState(373);
				match(SYMBOL_ROUND_BRACKET_OPEN);
				setState(374);
				((Expression_eightContext)_localctx).expression = expression();
				setState(375);
				((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE = match(SYMBOL_ROUND_BRACKET_CLOSE);

				        ((Expression_eightContext)_localctx).ast =  new AstDelExpr(new Location((((Expression_eightContext)_localctx).KEYWORD_DEL!=null?((Expression_eightContext)_localctx).KEYWORD_DEL.getLine():0), ((Expression_eightContext)_localctx).KEYWORD_DEL.getCharPositionInLine(), (((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE!=null?((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE.getLine():0), ((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE.getCharPositionInLine()+((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE.getText().length()), ((Expression_eightContext)_localctx).expression.ast);
				        ((Expression_eightContext)_localctx).begLine =  (((Expression_eightContext)_localctx).KEYWORD_DEL!=null?((Expression_eightContext)_localctx).KEYWORD_DEL.getLine():0);
				        ((Expression_eightContext)_localctx).begColumn =  ((Expression_eightContext)_localctx).KEYWORD_DEL.getCharPositionInLine();
				        ((Expression_eightContext)_localctx).endLine =  (((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE!=null?((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE.getLine():0);
				        ((Expression_eightContext)_localctx).endColumn =  ((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE.getCharPositionInLine()+((Expression_eightContext)_localctx).SYMBOL_ROUND_BRACKET_CLOSE.getText().length();
				  
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstantContext extends ParserRuleContext {
		public AstAtomExpr const_;
		public int begLine;
		public int begColumn;
		public int endLine;
		public int endColumn;
		public Token CONSTANT_VOID;
		public Token CONSTANT_TRUE;
		public Token CONSTANT_FALSE;
		public Token CONSTANT_INTEGER;
		public Token CONSTANT_CHAR;
		public Token CONSTANT_STRING;
		public Token CONSTANT_POINTER;
		public TerminalNode CONSTANT_VOID() { return getToken(PrevParser.CONSTANT_VOID, 0); }
		public TerminalNode CONSTANT_TRUE() { return getToken(PrevParser.CONSTANT_TRUE, 0); }
		public TerminalNode CONSTANT_FALSE() { return getToken(PrevParser.CONSTANT_FALSE, 0); }
		public TerminalNode CONSTANT_INTEGER() { return getToken(PrevParser.CONSTANT_INTEGER, 0); }
		public TerminalNode CONSTANT_CHAR() { return getToken(PrevParser.CONSTANT_CHAR, 0); }
		public TerminalNode CONSTANT_STRING() { return getToken(PrevParser.CONSTANT_STRING, 0); }
		public TerminalNode CONSTANT_POINTER() { return getToken(PrevParser.CONSTANT_POINTER, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_constant);
		try {
			setState(394);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONSTANT_VOID:
				enterOuterAlt(_localctx, 1);
				{
				setState(380);
				((ConstantContext)_localctx).CONSTANT_VOID = match(CONSTANT_VOID);

				      ((ConstantContext)_localctx).const_ =  new AstAtomExpr(new Location((((ConstantContext)_localctx).CONSTANT_VOID!=null?((ConstantContext)_localctx).CONSTANT_VOID.getLine():0),((ConstantContext)_localctx).CONSTANT_VOID.getCharPositionInLine(),(((ConstantContext)_localctx).CONSTANT_VOID!=null?((ConstantContext)_localctx).CONSTANT_VOID.getLine():0),((ConstantContext)_localctx).CONSTANT_VOID.getCharPositionInLine()+((ConstantContext)_localctx).CONSTANT_VOID.getText().length()), AstAtomExpr.Type.VOID, (((ConstantContext)_localctx).CONSTANT_VOID!=null?((ConstantContext)_localctx).CONSTANT_VOID.getText():null));
				      ((ConstantContext)_localctx).begLine =  (((ConstantContext)_localctx).CONSTANT_VOID!=null?((ConstantContext)_localctx).CONSTANT_VOID.getLine():0);
				      ((ConstantContext)_localctx).begColumn =  ((ConstantContext)_localctx).CONSTANT_VOID.getCharPositionInLine();
				      ((ConstantContext)_localctx).endLine =  (((ConstantContext)_localctx).CONSTANT_VOID!=null?((ConstantContext)_localctx).CONSTANT_VOID.getLine():0);
				      ((ConstantContext)_localctx).endColumn =  ((ConstantContext)_localctx).CONSTANT_VOID.getCharPositionInLine()+((ConstantContext)_localctx).CONSTANT_VOID.getText().length();
				  
				}
				break;
			case CONSTANT_TRUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(382);
				((ConstantContext)_localctx).CONSTANT_TRUE = match(CONSTANT_TRUE);

				      ((ConstantContext)_localctx).const_ =  new AstAtomExpr(new Location((((ConstantContext)_localctx).CONSTANT_TRUE!=null?((ConstantContext)_localctx).CONSTANT_TRUE.getLine():0),((ConstantContext)_localctx).CONSTANT_TRUE.getCharPositionInLine(),(((ConstantContext)_localctx).CONSTANT_TRUE!=null?((ConstantContext)_localctx).CONSTANT_TRUE.getLine():0),((ConstantContext)_localctx).CONSTANT_TRUE.getCharPositionInLine()+((ConstantContext)_localctx).CONSTANT_TRUE.getText().length()), AstAtomExpr.Type.BOOL, (((ConstantContext)_localctx).CONSTANT_TRUE!=null?((ConstantContext)_localctx).CONSTANT_TRUE.getText():null));
				      ((ConstantContext)_localctx).begLine =  (((ConstantContext)_localctx).CONSTANT_TRUE!=null?((ConstantContext)_localctx).CONSTANT_TRUE.getLine():0);
				      ((ConstantContext)_localctx).begColumn =  ((ConstantContext)_localctx).CONSTANT_TRUE.getCharPositionInLine();
				      ((ConstantContext)_localctx).endLine =  (((ConstantContext)_localctx).CONSTANT_TRUE!=null?((ConstantContext)_localctx).CONSTANT_TRUE.getLine():0);
				      ((ConstantContext)_localctx).endColumn =  ((ConstantContext)_localctx).CONSTANT_TRUE.getCharPositionInLine()+((ConstantContext)_localctx).CONSTANT_TRUE.getText().length();
				  
				}
				break;
			case CONSTANT_FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(384);
				((ConstantContext)_localctx).CONSTANT_FALSE = match(CONSTANT_FALSE);

				      ((ConstantContext)_localctx).const_ =  new AstAtomExpr(new Location((((ConstantContext)_localctx).CONSTANT_FALSE!=null?((ConstantContext)_localctx).CONSTANT_FALSE.getLine():0),((ConstantContext)_localctx).CONSTANT_FALSE.getCharPositionInLine(),(((ConstantContext)_localctx).CONSTANT_FALSE!=null?((ConstantContext)_localctx).CONSTANT_FALSE.getLine():0),((ConstantContext)_localctx).CONSTANT_FALSE.getCharPositionInLine()+((ConstantContext)_localctx).CONSTANT_FALSE.getText().length()), AstAtomExpr.Type.BOOL, (((ConstantContext)_localctx).CONSTANT_FALSE!=null?((ConstantContext)_localctx).CONSTANT_FALSE.getText():null));
				      ((ConstantContext)_localctx).begLine =  (((ConstantContext)_localctx).CONSTANT_FALSE!=null?((ConstantContext)_localctx).CONSTANT_FALSE.getLine():0);
				      ((ConstantContext)_localctx).begColumn =  ((ConstantContext)_localctx).CONSTANT_FALSE.getCharPositionInLine();
				      ((ConstantContext)_localctx).endLine =  (((ConstantContext)_localctx).CONSTANT_FALSE!=null?((ConstantContext)_localctx).CONSTANT_FALSE.getLine():0);
				      ((ConstantContext)_localctx).endColumn =  ((ConstantContext)_localctx).CONSTANT_FALSE.getCharPositionInLine()+((ConstantContext)_localctx).CONSTANT_FALSE.getText().length();
				  
				}
				break;
			case CONSTANT_INTEGER:
				enterOuterAlt(_localctx, 4);
				{
				setState(386);
				((ConstantContext)_localctx).CONSTANT_INTEGER = match(CONSTANT_INTEGER);

				      ((ConstantContext)_localctx).const_ =  new AstAtomExpr(new Location((((ConstantContext)_localctx).CONSTANT_INTEGER!=null?((ConstantContext)_localctx).CONSTANT_INTEGER.getLine():0),((ConstantContext)_localctx).CONSTANT_INTEGER.getCharPositionInLine(),(((ConstantContext)_localctx).CONSTANT_INTEGER!=null?((ConstantContext)_localctx).CONSTANT_INTEGER.getLine():0),((ConstantContext)_localctx).CONSTANT_INTEGER.getCharPositionInLine()+((ConstantContext)_localctx).CONSTANT_INTEGER.getText().length()), AstAtomExpr.Type.INT, (((ConstantContext)_localctx).CONSTANT_INTEGER!=null?((ConstantContext)_localctx).CONSTANT_INTEGER.getText():null));
				      ((ConstantContext)_localctx).begLine =  (((ConstantContext)_localctx).CONSTANT_INTEGER!=null?((ConstantContext)_localctx).CONSTANT_INTEGER.getLine():0);
				      ((ConstantContext)_localctx).begColumn =  ((ConstantContext)_localctx).CONSTANT_INTEGER.getCharPositionInLine();
				      ((ConstantContext)_localctx).endLine =  (((ConstantContext)_localctx).CONSTANT_INTEGER!=null?((ConstantContext)_localctx).CONSTANT_INTEGER.getLine():0);
				      ((ConstantContext)_localctx).endColumn =  ((ConstantContext)_localctx).CONSTANT_INTEGER.getCharPositionInLine()+((ConstantContext)_localctx).CONSTANT_INTEGER.getText().length();
				  
				}
				break;
			case CONSTANT_CHAR:
				enterOuterAlt(_localctx, 5);
				{
				setState(388);
				((ConstantContext)_localctx).CONSTANT_CHAR = match(CONSTANT_CHAR);

				      ((ConstantContext)_localctx).const_ =  new AstAtomExpr(new Location((((ConstantContext)_localctx).CONSTANT_CHAR!=null?((ConstantContext)_localctx).CONSTANT_CHAR.getLine():0),((ConstantContext)_localctx).CONSTANT_CHAR.getCharPositionInLine(),(((ConstantContext)_localctx).CONSTANT_CHAR!=null?((ConstantContext)_localctx).CONSTANT_CHAR.getLine():0),((ConstantContext)_localctx).CONSTANT_CHAR.getCharPositionInLine()+((ConstantContext)_localctx).CONSTANT_CHAR.getText().length()), AstAtomExpr.Type.CHAR, (((ConstantContext)_localctx).CONSTANT_CHAR!=null?((ConstantContext)_localctx).CONSTANT_CHAR.getText():null));
				      ((ConstantContext)_localctx).begLine =  (((ConstantContext)_localctx).CONSTANT_CHAR!=null?((ConstantContext)_localctx).CONSTANT_CHAR.getLine():0);
				      ((ConstantContext)_localctx).begColumn =  ((ConstantContext)_localctx).CONSTANT_CHAR.getCharPositionInLine();
				      ((ConstantContext)_localctx).endLine =  (((ConstantContext)_localctx).CONSTANT_CHAR!=null?((ConstantContext)_localctx).CONSTANT_CHAR.getLine():0);
				      ((ConstantContext)_localctx).endColumn =  ((ConstantContext)_localctx).CONSTANT_CHAR.getCharPositionInLine()+((ConstantContext)_localctx).CONSTANT_CHAR.getText().length();
				  
				}
				break;
			case CONSTANT_STRING:
				enterOuterAlt(_localctx, 6);
				{
				setState(390);
				((ConstantContext)_localctx).CONSTANT_STRING = match(CONSTANT_STRING);

				      ((ConstantContext)_localctx).const_ =  new AstAtomExpr(new Location((((ConstantContext)_localctx).CONSTANT_STRING!=null?((ConstantContext)_localctx).CONSTANT_STRING.getLine():0),((ConstantContext)_localctx).CONSTANT_STRING.getCharPositionInLine(),(((ConstantContext)_localctx).CONSTANT_STRING!=null?((ConstantContext)_localctx).CONSTANT_STRING.getLine():0),((ConstantContext)_localctx).CONSTANT_STRING.getCharPositionInLine()+((ConstantContext)_localctx).CONSTANT_STRING.getText().length()), AstAtomExpr.Type.STR, (((ConstantContext)_localctx).CONSTANT_STRING!=null?((ConstantContext)_localctx).CONSTANT_STRING.getText():null));
				      ((ConstantContext)_localctx).begLine =  (((ConstantContext)_localctx).CONSTANT_STRING!=null?((ConstantContext)_localctx).CONSTANT_STRING.getLine():0);
				      ((ConstantContext)_localctx).begColumn =  ((ConstantContext)_localctx).CONSTANT_STRING.getCharPositionInLine();
				      ((ConstantContext)_localctx).endLine =  (((ConstantContext)_localctx).CONSTANT_STRING!=null?((ConstantContext)_localctx).CONSTANT_STRING.getLine():0);
				      ((ConstantContext)_localctx).endColumn =  ((ConstantContext)_localctx).CONSTANT_STRING.getCharPositionInLine()+((ConstantContext)_localctx).CONSTANT_STRING.getText().length();
				  
				}
				break;
			case CONSTANT_POINTER:
				enterOuterAlt(_localctx, 7);
				{
				setState(392);
				((ConstantContext)_localctx).CONSTANT_POINTER = match(CONSTANT_POINTER);

				      ((ConstantContext)_localctx).const_ =  new AstAtomExpr(new Location((((ConstantContext)_localctx).CONSTANT_POINTER!=null?((ConstantContext)_localctx).CONSTANT_POINTER.getLine():0),((ConstantContext)_localctx).CONSTANT_POINTER.getCharPositionInLine(),(((ConstantContext)_localctx).CONSTANT_POINTER!=null?((ConstantContext)_localctx).CONSTANT_POINTER.getLine():0),((ConstantContext)_localctx).CONSTANT_POINTER.getCharPositionInLine()+((ConstantContext)_localctx).CONSTANT_POINTER.getText().length()), AstAtomExpr.Type.PTR, (((ConstantContext)_localctx).CONSTANT_POINTER!=null?((ConstantContext)_localctx).CONSTANT_POINTER.getText():null));
				      ((ConstantContext)_localctx).begLine =  (((ConstantContext)_localctx).CONSTANT_POINTER!=null?((ConstantContext)_localctx).CONSTANT_POINTER.getLine():0);
				      ((ConstantContext)_localctx).begColumn =  ((ConstantContext)_localctx).CONSTANT_POINTER.getCharPositionInLine();
				      ((ConstantContext)_localctx).endLine =  (((ConstantContext)_localctx).CONSTANT_POINTER!=null?((ConstantContext)_localctx).CONSTANT_POINTER.getLine():0);
				      ((ConstantContext)_localctx).endColumn =  ((ConstantContext)_localctx).CONSTANT_POINTER.getCharPositionInLine()+((ConstantContext)_localctx).CONSTANT_POINTER.getText().length();
				  
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Prefix_operatorContext extends ParserRuleContext {
		public AstPfxExpr.Oper op;
		public int begLine;
		public int begColumn;
		public int endLine;
		public int endColumn;
		public Token SYMBOL_EXCLAMATION_MARK;
		public Token SYMBOL_PLUS;
		public Token SYMBOL_MINUS;
		public Token SYMBOL_CIRCUMFLEX;
		public TerminalNode SYMBOL_EXCLAMATION_MARK() { return getToken(PrevParser.SYMBOL_EXCLAMATION_MARK, 0); }
		public TerminalNode SYMBOL_PLUS() { return getToken(PrevParser.SYMBOL_PLUS, 0); }
		public TerminalNode SYMBOL_MINUS() { return getToken(PrevParser.SYMBOL_MINUS, 0); }
		public TerminalNode SYMBOL_CIRCUMFLEX() { return getToken(PrevParser.SYMBOL_CIRCUMFLEX, 0); }
		public Prefix_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefix_operator; }
	}

	public final Prefix_operatorContext prefix_operator() throws RecognitionException {
		Prefix_operatorContext _localctx = new Prefix_operatorContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_prefix_operator);
		try {
			setState(404);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SYMBOL_EXCLAMATION_MARK:
				enterOuterAlt(_localctx, 1);
				{
				setState(396);
				((Prefix_operatorContext)_localctx).SYMBOL_EXCLAMATION_MARK = match(SYMBOL_EXCLAMATION_MARK);

				        ((Prefix_operatorContext)_localctx).op =  AstPfxExpr.Oper.NOT;
				        ((Prefix_operatorContext)_localctx).begLine =  (((Prefix_operatorContext)_localctx).SYMBOL_EXCLAMATION_MARK!=null?((Prefix_operatorContext)_localctx).SYMBOL_EXCLAMATION_MARK.getLine():0);
				        ((Prefix_operatorContext)_localctx).begColumn =  ((Prefix_operatorContext)_localctx).SYMBOL_EXCLAMATION_MARK.getCharPositionInLine();
				        ((Prefix_operatorContext)_localctx).endLine =  (((Prefix_operatorContext)_localctx).SYMBOL_EXCLAMATION_MARK!=null?((Prefix_operatorContext)_localctx).SYMBOL_EXCLAMATION_MARK.getLine():0);
				        ((Prefix_operatorContext)_localctx).endColumn =  ((Prefix_operatorContext)_localctx).SYMBOL_EXCLAMATION_MARK.getCharPositionInLine()+((Prefix_operatorContext)_localctx).SYMBOL_EXCLAMATION_MARK.getText().length();
				  
				}
				break;
			case SYMBOL_PLUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(398);
				((Prefix_operatorContext)_localctx).SYMBOL_PLUS = match(SYMBOL_PLUS);

				        ((Prefix_operatorContext)_localctx).op =  AstPfxExpr.Oper.ADD;
				        ((Prefix_operatorContext)_localctx).begLine =  (((Prefix_operatorContext)_localctx).SYMBOL_PLUS!=null?((Prefix_operatorContext)_localctx).SYMBOL_PLUS.getLine():0);
				        ((Prefix_operatorContext)_localctx).begColumn =  ((Prefix_operatorContext)_localctx).SYMBOL_PLUS.getCharPositionInLine();
				        ((Prefix_operatorContext)_localctx).endLine =  (((Prefix_operatorContext)_localctx).SYMBOL_PLUS!=null?((Prefix_operatorContext)_localctx).SYMBOL_PLUS.getLine():0);
				        ((Prefix_operatorContext)_localctx).endColumn =  ((Prefix_operatorContext)_localctx).SYMBOL_PLUS.getCharPositionInLine()+((Prefix_operatorContext)_localctx).SYMBOL_PLUS.getText().length();
				  
				}
				break;
			case SYMBOL_MINUS:
				enterOuterAlt(_localctx, 3);
				{
				setState(400);
				((Prefix_operatorContext)_localctx).SYMBOL_MINUS = match(SYMBOL_MINUS);

				        ((Prefix_operatorContext)_localctx).op =  AstPfxExpr.Oper.SUB;
				        ((Prefix_operatorContext)_localctx).begLine =  (((Prefix_operatorContext)_localctx).SYMBOL_MINUS!=null?((Prefix_operatorContext)_localctx).SYMBOL_MINUS.getLine():0);
				        ((Prefix_operatorContext)_localctx).begColumn =  ((Prefix_operatorContext)_localctx).SYMBOL_MINUS.getCharPositionInLine();
				        ((Prefix_operatorContext)_localctx).endLine =  (((Prefix_operatorContext)_localctx).SYMBOL_MINUS!=null?((Prefix_operatorContext)_localctx).SYMBOL_MINUS.getLine():0);
				        ((Prefix_operatorContext)_localctx).endColumn =  ((Prefix_operatorContext)_localctx).SYMBOL_MINUS.getCharPositionInLine()+((Prefix_operatorContext)_localctx).SYMBOL_MINUS.getText().length();
				  
				}
				break;
			case SYMBOL_CIRCUMFLEX:
				enterOuterAlt(_localctx, 4);
				{
				setState(402);
				((Prefix_operatorContext)_localctx).SYMBOL_CIRCUMFLEX = match(SYMBOL_CIRCUMFLEX);

				        ((Prefix_operatorContext)_localctx).op =  AstPfxExpr.Oper.PTR;
				        ((Prefix_operatorContext)_localctx).begLine =  (((Prefix_operatorContext)_localctx).SYMBOL_CIRCUMFLEX!=null?((Prefix_operatorContext)_localctx).SYMBOL_CIRCUMFLEX.getLine():0);
				        ((Prefix_operatorContext)_localctx).begColumn =  ((Prefix_operatorContext)_localctx).SYMBOL_CIRCUMFLEX.getCharPositionInLine();
				        ((Prefix_operatorContext)_localctx).endLine =  (((Prefix_operatorContext)_localctx).SYMBOL_CIRCUMFLEX!=null?((Prefix_operatorContext)_localctx).SYMBOL_CIRCUMFLEX.getLine():0);
				        ((Prefix_operatorContext)_localctx).endColumn =  ((Prefix_operatorContext)_localctx).SYMBOL_CIRCUMFLEX.getCharPositionInLine()+((Prefix_operatorContext)_localctx).SYMBOL_CIRCUMFLEX.getText().length();
				  
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Multiplicative_operatorContext extends ParserRuleContext {
		public AstBinExpr.Oper op;
		public int begLine;
		public int begColumn;
		public int endLine;
		public int endColumn;
		public Token SYMBOL_STAR;
		public Token SYMBOL_SLASH;
		public Token SYMBOL_PERCENT;
		public TerminalNode SYMBOL_STAR() { return getToken(PrevParser.SYMBOL_STAR, 0); }
		public TerminalNode SYMBOL_SLASH() { return getToken(PrevParser.SYMBOL_SLASH, 0); }
		public TerminalNode SYMBOL_PERCENT() { return getToken(PrevParser.SYMBOL_PERCENT, 0); }
		public Multiplicative_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicative_operator; }
	}

	public final Multiplicative_operatorContext multiplicative_operator() throws RecognitionException {
		Multiplicative_operatorContext _localctx = new Multiplicative_operatorContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_multiplicative_operator);
		try {
			setState(412);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SYMBOL_STAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(406);
				((Multiplicative_operatorContext)_localctx).SYMBOL_STAR = match(SYMBOL_STAR);

				        ((Multiplicative_operatorContext)_localctx).op =  AstBinExpr.Oper.MUL;
				        ((Multiplicative_operatorContext)_localctx).begLine =  (((Multiplicative_operatorContext)_localctx).SYMBOL_STAR!=null?((Multiplicative_operatorContext)_localctx).SYMBOL_STAR.getLine():0);
				        ((Multiplicative_operatorContext)_localctx).begColumn =  ((Multiplicative_operatorContext)_localctx).SYMBOL_STAR.getCharPositionInLine();
				        ((Multiplicative_operatorContext)_localctx).endLine =  (((Multiplicative_operatorContext)_localctx).SYMBOL_STAR!=null?((Multiplicative_operatorContext)_localctx).SYMBOL_STAR.getLine():0);
				        ((Multiplicative_operatorContext)_localctx).endColumn =  ((Multiplicative_operatorContext)_localctx).SYMBOL_STAR.getCharPositionInLine()+((Multiplicative_operatorContext)_localctx).SYMBOL_STAR.getText().length();
				  
				}
				break;
			case SYMBOL_SLASH:
				enterOuterAlt(_localctx, 2);
				{
				setState(408);
				((Multiplicative_operatorContext)_localctx).SYMBOL_SLASH = match(SYMBOL_SLASH);

				        ((Multiplicative_operatorContext)_localctx).op =  AstBinExpr.Oper.DIV;
				        ((Multiplicative_operatorContext)_localctx).begLine =  (((Multiplicative_operatorContext)_localctx).SYMBOL_SLASH!=null?((Multiplicative_operatorContext)_localctx).SYMBOL_SLASH.getLine():0);
				        ((Multiplicative_operatorContext)_localctx).begColumn =  ((Multiplicative_operatorContext)_localctx).SYMBOL_SLASH.getCharPositionInLine();
				        ((Multiplicative_operatorContext)_localctx).endLine =  (((Multiplicative_operatorContext)_localctx).SYMBOL_SLASH!=null?((Multiplicative_operatorContext)_localctx).SYMBOL_SLASH.getLine():0);
				        ((Multiplicative_operatorContext)_localctx).endColumn =  ((Multiplicative_operatorContext)_localctx).SYMBOL_SLASH.getCharPositionInLine()+((Multiplicative_operatorContext)_localctx).SYMBOL_SLASH.getText().length();
				  
				}
				break;
			case SYMBOL_PERCENT:
				enterOuterAlt(_localctx, 3);
				{
				setState(410);
				((Multiplicative_operatorContext)_localctx).SYMBOL_PERCENT = match(SYMBOL_PERCENT);

				        ((Multiplicative_operatorContext)_localctx).op =  AstBinExpr.Oper.MOD;
				        ((Multiplicative_operatorContext)_localctx).begLine =  (((Multiplicative_operatorContext)_localctx).SYMBOL_PERCENT!=null?((Multiplicative_operatorContext)_localctx).SYMBOL_PERCENT.getLine():0);
				        ((Multiplicative_operatorContext)_localctx).begColumn =  ((Multiplicative_operatorContext)_localctx).SYMBOL_PERCENT.getCharPositionInLine();
				        ((Multiplicative_operatorContext)_localctx).endLine =  (((Multiplicative_operatorContext)_localctx).SYMBOL_PERCENT!=null?((Multiplicative_operatorContext)_localctx).SYMBOL_PERCENT.getLine():0);
				        ((Multiplicative_operatorContext)_localctx).endColumn =  ((Multiplicative_operatorContext)_localctx).SYMBOL_PERCENT.getCharPositionInLine()+((Multiplicative_operatorContext)_localctx).SYMBOL_PERCENT.getText().length();
				  
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Additive_operatorContext extends ParserRuleContext {
		public AstBinExpr.Oper op;
		public int begLine;
		public int begColumn;
		public int endLine;
		public int endColumn;
		public Token SYMBOL_PLUS;
		public Token SYMBOL_MINUS;
		public TerminalNode SYMBOL_PLUS() { return getToken(PrevParser.SYMBOL_PLUS, 0); }
		public TerminalNode SYMBOL_MINUS() { return getToken(PrevParser.SYMBOL_MINUS, 0); }
		public Additive_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additive_operator; }
	}

	public final Additive_operatorContext additive_operator() throws RecognitionException {
		Additive_operatorContext _localctx = new Additive_operatorContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_additive_operator);
		try {
			setState(418);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SYMBOL_PLUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(414);
				((Additive_operatorContext)_localctx).SYMBOL_PLUS = match(SYMBOL_PLUS);

				        ((Additive_operatorContext)_localctx).op =  AstBinExpr.Oper.ADD;
				        ((Additive_operatorContext)_localctx).begLine =  (((Additive_operatorContext)_localctx).SYMBOL_PLUS!=null?((Additive_operatorContext)_localctx).SYMBOL_PLUS.getLine():0);
				        ((Additive_operatorContext)_localctx).begColumn =  ((Additive_operatorContext)_localctx).SYMBOL_PLUS.getCharPositionInLine();
				        ((Additive_operatorContext)_localctx).endLine =  (((Additive_operatorContext)_localctx).SYMBOL_PLUS!=null?((Additive_operatorContext)_localctx).SYMBOL_PLUS.getLine():0);
				        ((Additive_operatorContext)_localctx).endColumn =  ((Additive_operatorContext)_localctx).SYMBOL_PLUS.getCharPositionInLine()+((Additive_operatorContext)_localctx).SYMBOL_PLUS.getText().length();
				  
				}
				break;
			case SYMBOL_MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(416);
				((Additive_operatorContext)_localctx).SYMBOL_MINUS = match(SYMBOL_MINUS);

				        ((Additive_operatorContext)_localctx).op =  AstBinExpr.Oper.SUB;
				        ((Additive_operatorContext)_localctx).begLine =  (((Additive_operatorContext)_localctx).SYMBOL_MINUS!=null?((Additive_operatorContext)_localctx).SYMBOL_MINUS.getLine():0);
				        ((Additive_operatorContext)_localctx).begColumn =  ((Additive_operatorContext)_localctx).SYMBOL_MINUS.getCharPositionInLine();
				        ((Additive_operatorContext)_localctx).endLine =  (((Additive_operatorContext)_localctx).SYMBOL_MINUS!=null?((Additive_operatorContext)_localctx).SYMBOL_MINUS.getLine():0);
				        ((Additive_operatorContext)_localctx).endColumn =  ((Additive_operatorContext)_localctx).SYMBOL_MINUS.getCharPositionInLine()+((Additive_operatorContext)_localctx).SYMBOL_MINUS.getText().length();
				  
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Relational_operatorContext extends ParserRuleContext {
		public AstBinExpr.Oper op;
		public int begLine;
		public int begColumn;
		public int endLine;
		public int endColumn;
		public Token SYMBOL_DOUBLE_EQUAL;
		public Token SYMBOL_NOT_EQUAL;
		public Token SYMBOL_SMALLER_THAN;
		public Token SYMBOL_GREATER_THAN;
		public Token SYMBOL_SMALLER_OR_EQUALS;
		public Token SYMBOL_GREATER_OR_EQUALS;
		public TerminalNode SYMBOL_DOUBLE_EQUAL() { return getToken(PrevParser.SYMBOL_DOUBLE_EQUAL, 0); }
		public TerminalNode SYMBOL_NOT_EQUAL() { return getToken(PrevParser.SYMBOL_NOT_EQUAL, 0); }
		public TerminalNode SYMBOL_SMALLER_THAN() { return getToken(PrevParser.SYMBOL_SMALLER_THAN, 0); }
		public TerminalNode SYMBOL_GREATER_THAN() { return getToken(PrevParser.SYMBOL_GREATER_THAN, 0); }
		public TerminalNode SYMBOL_SMALLER_OR_EQUALS() { return getToken(PrevParser.SYMBOL_SMALLER_OR_EQUALS, 0); }
		public TerminalNode SYMBOL_GREATER_OR_EQUALS() { return getToken(PrevParser.SYMBOL_GREATER_OR_EQUALS, 0); }
		public Relational_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relational_operator; }
	}

	public final Relational_operatorContext relational_operator() throws RecognitionException {
		Relational_operatorContext _localctx = new Relational_operatorContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_relational_operator);
		try {
			setState(432);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SYMBOL_DOUBLE_EQUAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(420);
				((Relational_operatorContext)_localctx).SYMBOL_DOUBLE_EQUAL = match(SYMBOL_DOUBLE_EQUAL);

				        ((Relational_operatorContext)_localctx).op =  AstBinExpr.Oper.EQU;
				        ((Relational_operatorContext)_localctx).begLine =  (((Relational_operatorContext)_localctx).SYMBOL_DOUBLE_EQUAL!=null?((Relational_operatorContext)_localctx).SYMBOL_DOUBLE_EQUAL.getLine():0);
				        ((Relational_operatorContext)_localctx).begColumn =  ((Relational_operatorContext)_localctx).SYMBOL_DOUBLE_EQUAL.getCharPositionInLine();
				        ((Relational_operatorContext)_localctx).endLine =  (((Relational_operatorContext)_localctx).SYMBOL_DOUBLE_EQUAL!=null?((Relational_operatorContext)_localctx).SYMBOL_DOUBLE_EQUAL.getLine():0);
				        ((Relational_operatorContext)_localctx).endColumn =  ((Relational_operatorContext)_localctx).SYMBOL_DOUBLE_EQUAL.getCharPositionInLine()+((Relational_operatorContext)_localctx).SYMBOL_DOUBLE_EQUAL.getText().length();
				  
				}
				break;
			case SYMBOL_NOT_EQUAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(422);
				((Relational_operatorContext)_localctx).SYMBOL_NOT_EQUAL = match(SYMBOL_NOT_EQUAL);

				        ((Relational_operatorContext)_localctx).op =  AstBinExpr.Oper.NEQ;
				        ((Relational_operatorContext)_localctx).begLine =  (((Relational_operatorContext)_localctx).SYMBOL_NOT_EQUAL!=null?((Relational_operatorContext)_localctx).SYMBOL_NOT_EQUAL.getLine():0);
				        ((Relational_operatorContext)_localctx).begColumn =  ((Relational_operatorContext)_localctx).SYMBOL_NOT_EQUAL.getCharPositionInLine();
				        ((Relational_operatorContext)_localctx).endLine =  (((Relational_operatorContext)_localctx).SYMBOL_NOT_EQUAL!=null?((Relational_operatorContext)_localctx).SYMBOL_NOT_EQUAL.getLine():0);
				        ((Relational_operatorContext)_localctx).endColumn =  ((Relational_operatorContext)_localctx).SYMBOL_NOT_EQUAL.getCharPositionInLine()+((Relational_operatorContext)_localctx).SYMBOL_NOT_EQUAL.getText().length();
				  
				}
				break;
			case SYMBOL_SMALLER_THAN:
				enterOuterAlt(_localctx, 3);
				{
				setState(424);
				((Relational_operatorContext)_localctx).SYMBOL_SMALLER_THAN = match(SYMBOL_SMALLER_THAN);

				        ((Relational_operatorContext)_localctx).op =  AstBinExpr.Oper.LTH;
				        ((Relational_operatorContext)_localctx).begLine =  (((Relational_operatorContext)_localctx).SYMBOL_SMALLER_THAN!=null?((Relational_operatorContext)_localctx).SYMBOL_SMALLER_THAN.getLine():0);
				        ((Relational_operatorContext)_localctx).begColumn =  ((Relational_operatorContext)_localctx).SYMBOL_SMALLER_THAN.getCharPositionInLine();
				        ((Relational_operatorContext)_localctx).endLine =  (((Relational_operatorContext)_localctx).SYMBOL_SMALLER_THAN!=null?((Relational_operatorContext)_localctx).SYMBOL_SMALLER_THAN.getLine():0);
				        ((Relational_operatorContext)_localctx).endColumn =  ((Relational_operatorContext)_localctx).SYMBOL_SMALLER_THAN.getCharPositionInLine()+((Relational_operatorContext)_localctx).SYMBOL_SMALLER_THAN.getText().length();
				  
				}
				break;
			case SYMBOL_GREATER_THAN:
				enterOuterAlt(_localctx, 4);
				{
				setState(426);
				((Relational_operatorContext)_localctx).SYMBOL_GREATER_THAN = match(SYMBOL_GREATER_THAN);

				        ((Relational_operatorContext)_localctx).op =  AstBinExpr.Oper.GTH;
				        ((Relational_operatorContext)_localctx).begLine =  (((Relational_operatorContext)_localctx).SYMBOL_GREATER_THAN!=null?((Relational_operatorContext)_localctx).SYMBOL_GREATER_THAN.getLine():0);
				        ((Relational_operatorContext)_localctx).begColumn =  ((Relational_operatorContext)_localctx).SYMBOL_GREATER_THAN.getCharPositionInLine();
				        ((Relational_operatorContext)_localctx).endLine =  (((Relational_operatorContext)_localctx).SYMBOL_GREATER_THAN!=null?((Relational_operatorContext)_localctx).SYMBOL_GREATER_THAN.getLine():0);
				        ((Relational_operatorContext)_localctx).endColumn =  ((Relational_operatorContext)_localctx).SYMBOL_GREATER_THAN.getCharPositionInLine()+((Relational_operatorContext)_localctx).SYMBOL_GREATER_THAN.getText().length();
				  
				}
				break;
			case SYMBOL_SMALLER_OR_EQUALS:
				enterOuterAlt(_localctx, 5);
				{
				setState(428);
				((Relational_operatorContext)_localctx).SYMBOL_SMALLER_OR_EQUALS = match(SYMBOL_SMALLER_OR_EQUALS);

				        ((Relational_operatorContext)_localctx).op =  AstBinExpr.Oper.LEQ;
				        ((Relational_operatorContext)_localctx).begLine =  (((Relational_operatorContext)_localctx).SYMBOL_SMALLER_OR_EQUALS!=null?((Relational_operatorContext)_localctx).SYMBOL_SMALLER_OR_EQUALS.getLine():0);
				        ((Relational_operatorContext)_localctx).begColumn =  ((Relational_operatorContext)_localctx).SYMBOL_SMALLER_OR_EQUALS.getCharPositionInLine();
				        ((Relational_operatorContext)_localctx).endLine =  (((Relational_operatorContext)_localctx).SYMBOL_SMALLER_OR_EQUALS!=null?((Relational_operatorContext)_localctx).SYMBOL_SMALLER_OR_EQUALS.getLine():0);
				        ((Relational_operatorContext)_localctx).endColumn =  ((Relational_operatorContext)_localctx).SYMBOL_SMALLER_OR_EQUALS.getCharPositionInLine()+((Relational_operatorContext)_localctx).SYMBOL_SMALLER_OR_EQUALS.getText().length();
				  
				}
				break;
			case SYMBOL_GREATER_OR_EQUALS:
				enterOuterAlt(_localctx, 6);
				{
				setState(430);
				((Relational_operatorContext)_localctx).SYMBOL_GREATER_OR_EQUALS = match(SYMBOL_GREATER_OR_EQUALS);

				        ((Relational_operatorContext)_localctx).op =  AstBinExpr.Oper.GEQ;
				        ((Relational_operatorContext)_localctx).begLine =  (((Relational_operatorContext)_localctx).SYMBOL_GREATER_OR_EQUALS!=null?((Relational_operatorContext)_localctx).SYMBOL_GREATER_OR_EQUALS.getLine():0);
				        ((Relational_operatorContext)_localctx).begColumn =  ((Relational_operatorContext)_localctx).SYMBOL_GREATER_OR_EQUALS.getCharPositionInLine();
				        ((Relational_operatorContext)_localctx).endLine =  (((Relational_operatorContext)_localctx).SYMBOL_GREATER_OR_EQUALS!=null?((Relational_operatorContext)_localctx).SYMBOL_GREATER_OR_EQUALS.getLine():0);
				        ((Relational_operatorContext)_localctx).endColumn =  ((Relational_operatorContext)_localctx).SYMBOL_GREATER_OR_EQUALS.getCharPositionInLine()+((Relational_operatorContext)_localctx).SYMBOL_GREATER_OR_EQUALS.getText().length();
				  
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public AstStmt ast;
		public ExpressionContext expr_1;
		public Token SYMBOL_EQUAL;
		public ExpressionContext expr_2;
		public Token KEYWORD_IF;
		public ExpressionContext expression;
		public StatementContext stat1;
		public StatementContext stat2;
		public Token KEYWORD_WHILE;
		public StatementContext statement;
		public Token KEYWORD_LET;
		public Source_extContext source_ext;
		public Token SYMBOL_CURLY_BRACKET_OPEN;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode SYMBOL_EQUAL() { return getToken(PrevParser.SYMBOL_EQUAL, 0); }
		public TerminalNode KEYWORD_IF() { return getToken(PrevParser.KEYWORD_IF, 0); }
		public TerminalNode KEYWORD_THEN() { return getToken(PrevParser.KEYWORD_THEN, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode KEYWORD_ELSE() { return getToken(PrevParser.KEYWORD_ELSE, 0); }
		public TerminalNode KEYWORD_WHILE() { return getToken(PrevParser.KEYWORD_WHILE, 0); }
		public TerminalNode KEYWORD_DO() { return getToken(PrevParser.KEYWORD_DO, 0); }
		public TerminalNode KEYWORD_LET() { return getToken(PrevParser.KEYWORD_LET, 0); }
		public Source_extContext source_ext() {
			return getRuleContext(Source_extContext.class,0);
		}
		public TerminalNode KEYWORD_IN() { return getToken(PrevParser.KEYWORD_IN, 0); }
		public TerminalNode SYMBOL_CURLY_BRACKET_OPEN() { return getToken(PrevParser.SYMBOL_CURLY_BRACKET_OPEN, 0); }
		public TerminalNode SYMBOL_CURLY_BRACKET_CLOSE() { return getToken(PrevParser.SYMBOL_CURLY_BRACKET_CLOSE, 0); }
		public List<TerminalNode> SYMBOL_SEMICOLON() { return getTokens(PrevParser.SYMBOL_SEMICOLON); }
		public TerminalNode SYMBOL_SEMICOLON(int i) {
			return getToken(PrevParser.SYMBOL_SEMICOLON, i);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_statement);
		int _la;
		try {
			int _alt;
			setState(489);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CONSTANT_VOID:
			case CONSTANT_TRUE:
			case CONSTANT_FALSE:
			case CONSTANT_INTEGER:
			case CONSTANT_CHAR:
			case CONSTANT_STRING:
			case CONSTANT_POINTER:
			case SYMBOL_ROUND_BRACKET_OPEN:
			case SYMBOL_EXCLAMATION_MARK:
			case SYMBOL_PLUS:
			case SYMBOL_MINUS:
			case SYMBOL_CIRCUMFLEX:
			case KEYWORD_DEL:
			case KEYWORD_NEW:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				AstExpr expr_source = null;AstExpr expr_destination = null;
				{
				setState(435);
				((StatementContext)_localctx).expr_1 = expression();
				expr_destination = ((StatementContext)_localctx).expr_1.ast;
				setState(441);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SYMBOL_EQUAL) {
					{
					setState(437);
					((StatementContext)_localctx).SYMBOL_EQUAL = match(SYMBOL_EQUAL);
					setState(438);
					((StatementContext)_localctx).expr_2 = expression();
					expr_source = ((StatementContext)_localctx).expr_2.ast;
					}
				}

				}
				if (expr_source == null) {
				    ((StatementContext)_localctx).ast =  new AstExprStmt(new Location(0,0),expr_destination);

				  } else{
				    ((StatementContext)_localctx).ast =  new AstAssignStmt(new Location((((StatementContext)_localctx).SYMBOL_EQUAL!=null?((StatementContext)_localctx).SYMBOL_EQUAL.getLine():0),((StatementContext)_localctx).SYMBOL_EQUAL.getCharPositionInLine(), getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length()),expr_destination, expr_source);
				  }
				  
				}
				break;
			case KEYWORD_IF:
				enterOuterAlt(_localctx, 2);
				{
				AstExpr cond = null; AstStmt thenStmt = null; AstStmt elseStmt = null;int lastLine = 0; int lastColumn = 0;
				{
				setState(446);
				((StatementContext)_localctx).KEYWORD_IF = match(KEYWORD_IF);
				setState(447);
				((StatementContext)_localctx).expression = expression();
				cond = ((StatementContext)_localctx).expression.ast;
				setState(449);
				match(KEYWORD_THEN);
				setState(450);
				((StatementContext)_localctx).stat1 = statement();
				thenStmt = ((StatementContext)_localctx).stat1.ast;lastLine=getCurrentToken().getLine();lastColumn=getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length();
				setState(456);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
				case 1:
					{
					setState(452);
					match(KEYWORD_ELSE);
					setState(453);
					((StatementContext)_localctx).stat2 = statement();
					elseStmt = ((StatementContext)_localctx).stat2.ast;lastLine=getCurrentToken().getLine();lastColumn=getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length();
					}
					break;
				}
				}
				((StatementContext)_localctx).ast =  new AstIfStmt(new Location((((StatementContext)_localctx).KEYWORD_IF!=null?((StatementContext)_localctx).KEYWORD_IF.getLine():0),((StatementContext)_localctx).KEYWORD_IF.getCharPositionInLine(), lastLine, lastColumn), cond, thenStmt, elseStmt);
				}
				break;
			case KEYWORD_WHILE:
				enterOuterAlt(_localctx, 3);
				{
				setState(460);
				((StatementContext)_localctx).KEYWORD_WHILE = match(KEYWORD_WHILE);
				setState(461);
				((StatementContext)_localctx).expression = expression();
				setState(462);
				match(KEYWORD_DO);
				setState(463);
				((StatementContext)_localctx).statement = statement();
				((StatementContext)_localctx).ast =  new AstWhileStmt(new Location((((StatementContext)_localctx).KEYWORD_WHILE!=null?((StatementContext)_localctx).KEYWORD_WHILE.getLine():0),((StatementContext)_localctx).KEYWORD_WHILE.getCharPositionInLine(), getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length()), ((StatementContext)_localctx).expression.ast, ((StatementContext)_localctx).statement.ast);
				}
				break;
			case KEYWORD_LET:
				enterOuterAlt(_localctx, 4);
				{
				setState(466);
				((StatementContext)_localctx).KEYWORD_LET = match(KEYWORD_LET);
				setState(467);
				((StatementContext)_localctx).source_ext = source_ext();
				setState(468);
				match(KEYWORD_IN);
				setState(469);
				((StatementContext)_localctx).statement = statement();
				((StatementContext)_localctx).ast =  new AstDeclStmt(new Location((((StatementContext)_localctx).KEYWORD_LET!=null?((StatementContext)_localctx).KEYWORD_LET.getLine():0),((StatementContext)_localctx).KEYWORD_LET.getCharPositionInLine(), getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length()), ((StatementContext)_localctx).source_ext.ast, ((StatementContext)_localctx).statement.ast);
				}
				break;
			case SYMBOL_CURLY_BRACKET_OPEN:
				enterOuterAlt(_localctx, 5);
				{
				Vector<AstStmt> stat_vec = new Vector<>();
				{
				setState(473);
				((StatementContext)_localctx).SYMBOL_CURLY_BRACKET_OPEN = match(SYMBOL_CURLY_BRACKET_OPEN);
				setState(474);
				((StatementContext)_localctx).stat1 = statement();
				stat_vec.add(((StatementContext)_localctx).stat1.ast);
				setState(482);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
				while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1+1 ) {
						{
						{
						setState(476);
						match(SYMBOL_SEMICOLON);
						setState(477);
						((StatementContext)_localctx).stat2 = statement();
						stat_vec.add(((StatementContext)_localctx).stat2.ast);
						}
						} 
					}
					setState(484);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
				}
				setState(485);
				match(SYMBOL_CURLY_BRACKET_CLOSE);
				}
				((StatementContext)_localctx).ast =  new AstStmts(new Location((((StatementContext)_localctx).SYMBOL_CURLY_BRACKET_OPEN!=null?((StatementContext)_localctx).SYMBOL_CURLY_BRACKET_OPEN.getLine():0),((StatementContext)_localctx).SYMBOL_CURLY_BRACKET_OPEN.getCharPositionInLine(), getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine()+getCurrentToken().getText().length()), new AstTrees("statements", stat_vec));
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 17:
			return expression_seven_sempred((Expression_sevenContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_seven_sempred(Expression_sevenContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		case 2:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00016\u01ec\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0004\u0001A\b\u0001\u000b\u0001\f\u0001B\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0005\u0002S\b\u0002\n\u0002\f\u0002V\t\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0005\u0003k\b\u0003\n\u0003\f\u0003n\t\u0003\u0001\u0003\u0001"+
		"\u0003\u0003\u0003r\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003|\b"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003\u008d\b\u0003\n"+
		"\u0003\f\u0003\u0090\t\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u0094"+
		"\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u009e\b\u0003\u0001\u0003\u0001"+
		"\u0003\u0005\u0003\u00a2\b\u0003\n\u0003\f\u0003\u00a5\t\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0005\u0004\u00b6\b\u0004\n\u0004\f\u0004\u00b9"+
		"\t\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0005\u0005\u00df\b\u0005\n\u0005\f\u0005\u00e2\t\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0003\u0005\u00ed\b\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0003\u0007\u00f9\b\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u0105"+
		"\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0111\b\u000b\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0003\r\u011d\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003"+
		"\u000f\u0129\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u0132\b\u0010\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u0145\b\u0011\n"+
		"\u0011\f\u0011\u0148\t\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u0157\b\u0012\n"+
		"\u0012\f\u0012\u015a\t\u0012\u0003\u0012\u015c\b\u0012\u0001\u0012\u0003"+
		"\u0012\u015f\b\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u0169\b\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u017b\b\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0003\u0013\u018b\b\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003"+
		"\u0014\u0195\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0003\u0015\u019d\b\u0015\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0003\u0016\u01a3\b\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u01b1\b\u0017\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0003\u0018\u01ba\b\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u01c9\b\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u01e1\b\u0018\n"+
		"\u0018\f\u0018\u01e4\t\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0003\u0018\u01ea\b\u0018\u0001\u0018\bTl\u008e\u00a3\u00b7\u00e0"+
		"\u0158\u01e2\u0001\"\u0019\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.0\u0000\u0000\u0210\u0000"+
		"2\u0001\u0000\u0000\u0000\u00026\u0001\u0000\u0000\u0000\u0004F\u0001"+
		"\u0000\u0000\u0000\u0006[\u0001\u0000\u0000\u0000\b\u00a9\u0001\u0000"+
		"\u0000\u0000\n\u00ec\u0001\u0000\u0000\u0000\f\u00ee\u0001\u0000\u0000"+
		"\u0000\u000e\u00f8\u0001\u0000\u0000\u0000\u0010\u00fa\u0001\u0000\u0000"+
		"\u0000\u0012\u0104\u0001\u0000\u0000\u0000\u0014\u0106\u0001\u0000\u0000"+
		"\u0000\u0016\u0110\u0001\u0000\u0000\u0000\u0018\u0112\u0001\u0000\u0000"+
		"\u0000\u001a\u011c\u0001\u0000\u0000\u0000\u001c\u011e\u0001\u0000\u0000"+
		"\u0000\u001e\u0128\u0001\u0000\u0000\u0000 \u0131\u0001\u0000\u0000\u0000"+
		"\"\u0133\u0001\u0000\u0000\u0000$\u017a\u0001\u0000\u0000\u0000&\u018a"+
		"\u0001\u0000\u0000\u0000(\u0194\u0001\u0000\u0000\u0000*\u019c\u0001\u0000"+
		"\u0000\u0000,\u01a2\u0001\u0000\u0000\u0000.\u01b0\u0001\u0000\u0000\u0000"+
		"0\u01e9\u0001\u0000\u0000\u000023\u0003\u0002\u0001\u000034\u0005\u0000"+
		"\u0000\u000145\u0006\u0000\uffff\uffff\u00005\u0001\u0001\u0000\u0000"+
		"\u00006@\u0006\u0001\uffff\uffff\u000078\u0003\u0004\u0002\u000089\u0006"+
		"\u0001\uffff\uffff\u00009A\u0001\u0000\u0000\u0000:;\u0003\u0006\u0003"+
		"\u0000;<\u0006\u0001\uffff\uffff\u0000<A\u0001\u0000\u0000\u0000=>\u0003"+
		"\b\u0004\u0000>?\u0006\u0001\uffff\uffff\u0000?A\u0001\u0000\u0000\u0000"+
		"@7\u0001\u0000\u0000\u0000@:\u0001\u0000\u0000\u0000@=\u0001\u0000\u0000"+
		"\u0000AB\u0001\u0000\u0000\u0000B@\u0001\u0000\u0000\u0000BC\u0001\u0000"+
		"\u0000\u0000CD\u0001\u0000\u0000\u0000DE\u0006\u0001\uffff\uffff\u0000"+
		"E\u0003\u0001\u0000\u0000\u0000FG\u0006\u0002\uffff\uffff\u0000GH\u0005"+
		".\u0000\u0000HI\u00052\u0000\u0000IJ\u0005!\u0000\u0000JK\u0003\n\u0005"+
		"\u0000KT\u0006\u0002\uffff\uffff\u0000LM\u0005\u000f\u0000\u0000MN\u0005"+
		"2\u0000\u0000NO\u0005!\u0000\u0000OP\u0003\n\u0005\u0000PQ\u0006\u0002"+
		"\uffff\uffff\u0000QS\u0001\u0000\u0000\u0000RL\u0001\u0000\u0000\u0000"+
		"SV\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000TR\u0001\u0000\u0000"+
		"\u0000UW\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000WX\u0005\u0011"+
		"\u0000\u0000XY\u0001\u0000\u0000\u0000YZ\u0006\u0002\uffff\uffff\u0000"+
		"Z\u0005\u0001\u0000\u0000\u0000[\\\u0006\u0003\uffff\uffff\u0000\\]\u0005"+
		"\'\u0000\u0000]^\u00052\u0000\u0000^_\u0005\b\u0000\u0000_q\u0006\u0003"+
		"\uffff\uffff\u0000`a\u00052\u0000\u0000ab\u0005\u0010\u0000\u0000bc\u0003"+
		"\n\u0005\u0000cl\u0006\u0003\uffff\uffff\u0000de\u0005\u000f\u0000\u0000"+
		"ef\u00052\u0000\u0000fg\u0005\u0010\u0000\u0000gh\u0003\n\u0005\u0000"+
		"hi\u0006\u0003\uffff\uffff\u0000ik\u0001\u0000\u0000\u0000jd\u0001\u0000"+
		"\u0000\u0000kn\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000lj\u0001"+
		"\u0000\u0000\u0000mo\u0001\u0000\u0000\u0000nl\u0001\u0000\u0000\u0000"+
		"op\u0006\u0003\uffff\uffff\u0000pr\u0001\u0000\u0000\u0000q`\u0001\u0000"+
		"\u0000\u0000qr\u0001\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000st\u0005"+
		"\t\u0000\u0000tu\u0005\u0010\u0000\u0000uv\u0003\n\u0005\u0000v{\u0006"+
		"\u0003\uffff\uffff\u0000wx\u0005!\u0000\u0000xy\u00030\u0018\u0000yz\u0006"+
		"\u0003\uffff\uffff\u0000z|\u0001\u0000\u0000\u0000{w\u0001\u0000\u0000"+
		"\u0000{|\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000}\u00a3\u0006"+
		"\u0003\uffff\uffff\u0000~\u007f\u0005\u000f\u0000\u0000\u007f\u0080\u0005"+
		"2\u0000\u0000\u0080\u0081\u0005\b\u0000\u0000\u0081\u0093\u0006\u0003"+
		"\uffff\uffff\u0000\u0082\u0083\u00052\u0000\u0000\u0083\u0084\u0005\u0010"+
		"\u0000\u0000\u0084\u0085\u0003\n\u0005\u0000\u0085\u008e\u0006\u0003\uffff"+
		"\uffff\u0000\u0086\u0087\u0005\u000f\u0000\u0000\u0087\u0088\u00052\u0000"+
		"\u0000\u0088\u0089\u0005\u0010\u0000\u0000\u0089\u008a\u0003\n\u0005\u0000"+
		"\u008a\u008b\u0006\u0003\uffff\uffff\u0000\u008b\u008d\u0001\u0000\u0000"+
		"\u0000\u008c\u0086\u0001\u0000\u0000\u0000\u008d\u0090\u0001\u0000\u0000"+
		"\u0000\u008e\u008f\u0001\u0000\u0000\u0000\u008e\u008c\u0001\u0000\u0000"+
		"\u0000\u008f\u0091\u0001\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000"+
		"\u0000\u0091\u0092\u0006\u0003\uffff\uffff\u0000\u0092\u0094\u0001\u0000"+
		"\u0000\u0000\u0093\u0082\u0001\u0000\u0000\u0000\u0093\u0094\u0001\u0000"+
		"\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000\u0095\u0096\u0005\t\u0000"+
		"\u0000\u0096\u0097\u0005\u0010\u0000\u0000\u0097\u0098\u0003\n\u0005\u0000"+
		"\u0098\u009d\u0006\u0003\uffff\uffff\u0000\u0099\u009a\u0005!\u0000\u0000"+
		"\u009a\u009b\u00030\u0018\u0000\u009b\u009c\u0006\u0003\uffff\uffff\u0000"+
		"\u009c\u009e\u0001\u0000\u0000\u0000\u009d\u0099\u0001\u0000\u0000\u0000"+
		"\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u009f\u0001\u0000\u0000\u0000"+
		"\u009f\u00a0\u0006\u0003\uffff\uffff\u0000\u00a0\u00a2\u0001\u0000\u0000"+
		"\u0000\u00a1~\u0001\u0000\u0000\u0000\u00a2\u00a5\u0001\u0000\u0000\u0000"+
		"\u00a3\u00a4\u0001\u0000\u0000\u0000\u00a3\u00a1\u0001\u0000\u0000\u0000"+
		"\u00a4\u00a6\u0001\u0000\u0000\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000"+
		"\u00a6\u00a7\u0005\u0011\u0000\u0000\u00a7\u00a8\u0006\u0003\uffff\uffff"+
		"\u0000\u00a8\u0007\u0001\u0000\u0000\u0000\u00a9\u00aa\u0006\u0004\uffff"+
		"\uffff\u0000\u00aa\u00ab\u0005/\u0000\u0000\u00ab\u00ac\u00052\u0000\u0000"+
		"\u00ac\u00ad\u0005\u0010\u0000\u0000\u00ad\u00ae\u0003\n\u0005\u0000\u00ae"+
		"\u00b7\u0006\u0004\uffff\uffff\u0000\u00af\u00b0\u0005\u000f\u0000\u0000"+
		"\u00b0\u00b1\u00052\u0000\u0000\u00b1\u00b2\u0005\u0010\u0000\u0000\u00b2"+
		"\u00b3\u0003\n\u0005\u0000\u00b3\u00b4\u0006\u0004\uffff\uffff\u0000\u00b4"+
		"\u00b6\u0001\u0000\u0000\u0000\u00b5\u00af\u0001\u0000\u0000\u0000\u00b6"+
		"\u00b9\u0001\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000\u0000\u0000\u00b7"+
		"\u00b5\u0001\u0000\u0000\u0000\u00b8\u00ba\u0001\u0000\u0000\u0000\u00b9"+
		"\u00b7\u0001\u0000\u0000\u0000\u00ba\u00bb\u0005\u0011\u0000\u0000\u00bb"+
		"\u00bc\u0001\u0000\u0000\u0000\u00bc\u00bd\u0006\u0004\uffff\uffff\u0000"+
		"\u00bd\t\u0001\u0000\u0000\u0000\u00be\u00bf\u00050\u0000\u0000\u00bf"+
		"\u00ed\u0006\u0005\uffff\uffff\u0000\u00c0\u00c1\u0005#\u0000\u0000\u00c1"+
		"\u00ed\u0006\u0005\uffff\uffff\u0000\u00c2\u00c3\u0005*\u0000\u0000\u00c3"+
		"\u00ed\u0006\u0005\uffff\uffff\u0000\u00c4\u00c5\u0005\"\u0000\u0000\u00c5"+
		"\u00ed\u0006\u0005\uffff\uffff\u0000\u00c6\u00c7\u00052\u0000\u0000\u00c7"+
		"\u00ed\u0006\u0005\uffff\uffff\u0000\u00c8\u00c9\u0005\f\u0000\u0000\u00c9"+
		"\u00ca\u0003\f\u0006\u0000\u00ca\u00cb\u0005\r\u0000\u0000\u00cb\u00cc"+
		"\u0003\n\u0005\u0000\u00cc\u00cd\u0006\u0005\uffff\uffff\u0000\u00cd\u00ed"+
		"\u0001\u0000\u0000\u0000\u00ce\u00cf\u0005 \u0000\u0000\u00cf\u00d0\u0003"+
		"\n\u0005\u0000\u00d0\u00d1\u0006\u0005\uffff\uffff\u0000\u00d1\u00ed\u0001"+
		"\u0000\u0000\u0000\u00d2\u00d3\u0006\u0005\uffff\uffff\u0000\u00d3\u00d4"+
		"\u0005\n\u0000\u0000\u00d4\u00d5\u00052\u0000\u0000\u00d5\u00d6\u0005"+
		"\u0010\u0000\u0000\u00d6\u00d7\u0003\n\u0005\u0000\u00d7\u00e0\u0006\u0005"+
		"\uffff\uffff\u0000\u00d8\u00d9\u0005\u000f\u0000\u0000\u00d9\u00da\u0005"+
		"2\u0000\u0000\u00da\u00db\u0005\u0010\u0000\u0000\u00db\u00dc\u0003\n"+
		"\u0005\u0000\u00dc\u00dd\u0006\u0005\uffff\uffff\u0000\u00dd\u00df\u0001"+
		"\u0000\u0000\u0000\u00de\u00d8\u0001\u0000\u0000\u0000\u00df\u00e2\u0001"+
		"\u0000\u0000\u0000\u00e0\u00e1\u0001\u0000\u0000\u0000\u00e0\u00de\u0001"+
		"\u0000\u0000\u0000\u00e1\u00e3\u0001\u0000\u0000\u0000\u00e2\u00e0\u0001"+
		"\u0000\u0000\u0000\u00e3\u00e4\u0005\u000b\u0000\u0000\u00e4\u00e5\u0001"+
		"\u0000\u0000\u0000\u00e5\u00e6\u0006\u0005\uffff\uffff\u0000\u00e6\u00ed"+
		"\u0001\u0000\u0000\u0000\u00e7\u00e8\u0005\b\u0000\u0000\u00e8\u00e9\u0003"+
		"\n\u0005\u0000\u00e9\u00ea\u0005\t\u0000\u0000\u00ea\u00eb\u0006\u0005"+
		"\uffff\uffff\u0000\u00eb\u00ed\u0001\u0000\u0000\u0000\u00ec\u00be\u0001"+
		"\u0000\u0000\u0000\u00ec\u00c0\u0001\u0000\u0000\u0000\u00ec\u00c2\u0001"+
		"\u0000\u0000\u0000\u00ec\u00c4\u0001\u0000\u0000\u0000\u00ec\u00c6\u0001"+
		"\u0000\u0000\u0000\u00ec\u00c8\u0001\u0000\u0000\u0000\u00ec\u00ce\u0001"+
		"\u0000\u0000\u0000\u00ec\u00d2\u0001\u0000\u0000\u0000\u00ec\u00e7\u0001"+
		"\u0000\u0000\u0000\u00ed\u000b\u0001\u0000\u0000\u0000\u00ee\u00ef\u0003"+
		"\u0010\b\u0000\u00ef\u00f0\u0003\u000e\u0007\u0000\u00f0\u00f1\u0006\u0006"+
		"\uffff\uffff\u0000\u00f1\r\u0001\u0000\u0000\u0000\u00f2\u00f3\u0005\u0013"+
		"\u0000\u0000\u00f3\u00f4\u0003\u0010\b\u0000\u00f4\u00f5\u0003\u000e\u0007"+
		"\u0000\u00f5\u00f6\u0006\u0007\uffff\uffff\u0000\u00f6\u00f9\u0001\u0000"+
		"\u0000\u0000\u00f7\u00f9\u0006\u0007\uffff\uffff\u0000\u00f8\u00f2\u0001"+
		"\u0000\u0000\u0000\u00f8\u00f7\u0001\u0000\u0000\u0000\u00f9\u000f\u0001"+
		"\u0000\u0000\u0000\u00fa\u00fb\u0003\u0014\n\u0000\u00fb\u00fc\u0003\u0012"+
		"\t\u0000\u00fc\u00fd\u0006\b\uffff\uffff\u0000\u00fd\u0011\u0001\u0000"+
		"\u0000\u0000\u00fe\u00ff\u0005\u0012\u0000\u0000\u00ff\u0100\u0003\u0014"+
		"\n\u0000\u0100\u0101\u0003\u0012\t\u0000\u0101\u0102\u0006\t\uffff\uffff"+
		"\u0000\u0102\u0105\u0001\u0000\u0000\u0000\u0103\u0105\u0006\t\uffff\uffff"+
		"\u0000\u0104\u00fe\u0001\u0000\u0000\u0000\u0104\u0103\u0001\u0000\u0000"+
		"\u0000\u0105\u0013\u0001\u0000\u0000\u0000\u0106\u0107\u0003\u0018\f\u0000"+
		"\u0107\u0108\u0003\u0016\u000b\u0000\u0108\u0109\u0006\n\uffff\uffff\u0000"+
		"\u0109\u0015\u0001\u0000\u0000\u0000\u010a\u010b\u0003.\u0017\u0000\u010b"+
		"\u010c\u0003\u0018\f\u0000\u010c\u010d\u0003\u001a\r\u0000\u010d\u010e"+
		"\u0006\u000b\uffff\uffff\u0000\u010e\u0111\u0001\u0000\u0000\u0000\u010f"+
		"\u0111\u0006\u000b\uffff\uffff\u0000\u0110\u010a\u0001\u0000\u0000\u0000"+
		"\u0110\u010f\u0001\u0000\u0000\u0000\u0111\u0017\u0001\u0000\u0000\u0000"+
		"\u0112\u0113\u0003\u001c\u000e\u0000\u0113\u0114\u0003\u001a\r\u0000\u0114"+
		"\u0115\u0006\f\uffff\uffff\u0000\u0115\u0019\u0001\u0000\u0000\u0000\u0116"+
		"\u0117\u0003,\u0016\u0000\u0117\u0118\u0003\u001c\u000e\u0000\u0118\u0119"+
		"\u0003\u001a\r\u0000\u0119\u011a\u0006\r\uffff\uffff\u0000\u011a\u011d"+
		"\u0001\u0000\u0000\u0000\u011b\u011d\u0006\r\uffff\uffff\u0000\u011c\u0116"+
		"\u0001\u0000\u0000\u0000\u011c\u011b\u0001\u0000\u0000\u0000\u011d\u001b"+
		"\u0001\u0000\u0000\u0000\u011e\u011f\u0003 \u0010\u0000\u011f\u0120\u0003"+
		"\u001e\u000f\u0000\u0120\u0121\u0006\u000e\uffff\uffff\u0000\u0121\u001d"+
		"\u0001\u0000\u0000\u0000\u0122\u0123\u0003*\u0015\u0000\u0123\u0124\u0003"+
		" \u0010\u0000\u0124\u0125\u0003\u001e\u000f\u0000\u0125\u0126\u0006\u000f"+
		"\uffff\uffff\u0000\u0126\u0129\u0001\u0000\u0000\u0000\u0127\u0129\u0006"+
		"\u000f\uffff\uffff\u0000\u0128\u0122\u0001\u0000\u0000\u0000\u0128\u0127"+
		"\u0001\u0000\u0000\u0000\u0129\u001f\u0001\u0000\u0000\u0000\u012a\u012b"+
		"\u0003(\u0014\u0000\u012b\u012c\u0003 \u0010\u0000\u012c\u012d\u0006\u0010"+
		"\uffff\uffff\u0000\u012d\u0132\u0001\u0000\u0000\u0000\u012e\u012f\u0003"+
		"\"\u0011\u0000\u012f\u0130\u0006\u0010\uffff\uffff\u0000\u0130\u0132\u0001"+
		"\u0000\u0000\u0000\u0131\u012a\u0001\u0000\u0000\u0000\u0131\u012e\u0001"+
		"\u0000\u0000\u0000\u0132!\u0001\u0000\u0000\u0000\u0133\u0134\u0006\u0011"+
		"\uffff\uffff\u0000\u0134\u0135\u0003$\u0012\u0000\u0135\u0136\u0006\u0011"+
		"\uffff\uffff\u0000\u0136\u0146\u0001\u0000\u0000\u0000\u0137\u0138\n\u0004"+
		"\u0000\u0000\u0138\u0139\u0005 \u0000\u0000\u0139\u0145\u0006\u0011\uffff"+
		"\uffff\u0000\u013a\u013b\n\u0003\u0000\u0000\u013b\u013c\u0005\f\u0000"+
		"\u0000\u013c\u013d\u0003\f\u0006\u0000\u013d\u013e\u0005\r\u0000\u0000"+
		"\u013e\u013f\u0006\u0011\uffff\uffff\u0000\u013f\u0145\u0001\u0000\u0000"+
		"\u0000\u0140\u0141\n\u0002\u0000\u0000\u0141\u0142\u0005\u000e\u0000\u0000"+
		"\u0142\u0143\u00052\u0000\u0000\u0143\u0145\u0006\u0011\uffff\uffff\u0000"+
		"\u0144\u0137\u0001\u0000\u0000\u0000\u0144\u013a\u0001\u0000\u0000\u0000"+
		"\u0144\u0140\u0001\u0000\u0000\u0000\u0145\u0148\u0001\u0000\u0000\u0000"+
		"\u0146\u0144\u0001\u0000\u0000\u0000\u0146\u0147\u0001\u0000\u0000\u0000"+
		"\u0147#\u0001\u0000\u0000\u0000\u0148\u0146\u0001\u0000\u0000\u0000\u0149"+
		"\u014a\u0003&\u0013\u0000\u014a\u014b\u0006\u0012\uffff\uffff\u0000\u014b"+
		"\u017b\u0001\u0000\u0000\u0000\u014c\u014d\u0006\u0012\uffff\uffff\u0000"+
		"\u014d\u015e\u00052\u0000\u0000\u014e\u014f\u0005\b\u0000\u0000\u014f"+
		"\u015b\u0006\u0012\uffff\uffff\u0000\u0150\u0151\u0003\f\u0006\u0000\u0151"+
		"\u0158\u0006\u0012\uffff\uffff\u0000\u0152\u0153\u0005\u000f\u0000\u0000"+
		"\u0153\u0154\u0003\f\u0006\u0000\u0154\u0155\u0006\u0012\uffff\uffff\u0000"+
		"\u0155\u0157\u0001\u0000\u0000\u0000\u0156\u0152\u0001\u0000\u0000\u0000"+
		"\u0157\u015a\u0001\u0000\u0000\u0000\u0158\u0159\u0001\u0000\u0000\u0000"+
		"\u0158\u0156\u0001\u0000\u0000\u0000\u0159\u015c\u0001\u0000\u0000\u0000"+
		"\u015a\u0158\u0001\u0000\u0000\u0000\u015b\u0150\u0001\u0000\u0000\u0000"+
		"\u015b\u015c\u0001\u0000\u0000\u0000\u015c\u015d\u0001\u0000\u0000\u0000"+
		"\u015d\u015f\u0005\t\u0000\u0000\u015e\u014e\u0001\u0000\u0000\u0000\u015e"+
		"\u015f\u0001\u0000\u0000\u0000\u015f\u0160\u0001\u0000\u0000\u0000\u0160"+
		"\u017b\u0006\u0012\uffff\uffff\u0000\u0161\u0162\u0006\u0012\uffff\uffff"+
		"\u0000\u0162\u0163\u0005\b\u0000\u0000\u0163\u0168\u0003\f\u0006\u0000"+
		"\u0164\u0165\u0005\u0010\u0000\u0000\u0165\u0166\u0003\n\u0005\u0000\u0166"+
		"\u0167\u0006\u0012\uffff\uffff\u0000\u0167\u0169\u0001\u0000\u0000\u0000"+
		"\u0168\u0164\u0001\u0000\u0000\u0000\u0168\u0169\u0001\u0000\u0000\u0000"+
		"\u0169\u016a\u0001\u0000\u0000\u0000\u016a\u016b\u0005\t\u0000\u0000\u016b"+
		"\u016c\u0001\u0000\u0000\u0000\u016c\u016d\u0006\u0012\uffff\uffff\u0000"+
		"\u016d\u017b\u0001\u0000\u0000\u0000\u016e\u016f\u0005,\u0000\u0000\u016f"+
		"\u0170\u0005\b\u0000\u0000\u0170\u0171\u0003\n\u0005\u0000\u0171\u0172"+
		"\u0005\t\u0000\u0000\u0172\u0173\u0006\u0012\uffff\uffff\u0000\u0173\u017b"+
		"\u0001\u0000\u0000\u0000\u0174\u0175\u0005$\u0000\u0000\u0175\u0176\u0005"+
		"\b\u0000\u0000\u0176\u0177\u0003\f\u0006\u0000\u0177\u0178\u0005\t\u0000"+
		"\u0000\u0178\u0179\u0006\u0012\uffff\uffff\u0000\u0179\u017b\u0001\u0000"+
		"\u0000\u0000\u017a\u0149\u0001\u0000\u0000\u0000\u017a\u014c\u0001\u0000"+
		"\u0000\u0000\u017a\u0161\u0001\u0000\u0000\u0000\u017a\u016e\u0001\u0000"+
		"\u0000\u0000\u017a\u0174\u0001\u0000\u0000\u0000\u017b%\u0001\u0000\u0000"+
		"\u0000\u017c\u017d\u0005\u0001\u0000\u0000\u017d\u018b\u0006\u0013\uffff"+
		"\uffff\u0000\u017e\u017f\u0005\u0002\u0000\u0000\u017f\u018b\u0006\u0013"+
		"\uffff\uffff\u0000\u0180\u0181\u0005\u0003\u0000\u0000\u0181\u018b\u0006"+
		"\u0013\uffff\uffff\u0000\u0182\u0183\u0005\u0004\u0000\u0000\u0183\u018b"+
		"\u0006\u0013\uffff\uffff\u0000\u0184\u0185\u0005\u0005\u0000\u0000\u0185"+
		"\u018b\u0006\u0013\uffff\uffff\u0000\u0186\u0187\u0005\u0006\u0000\u0000"+
		"\u0187\u018b\u0006\u0013\uffff\uffff\u0000\u0188\u0189\u0005\u0007\u0000"+
		"\u0000\u0189\u018b\u0006\u0013\uffff\uffff\u0000\u018a\u017c\u0001\u0000"+
		"\u0000\u0000\u018a\u017e\u0001\u0000\u0000\u0000\u018a\u0180\u0001\u0000"+
		"\u0000\u0000\u018a\u0182\u0001\u0000\u0000\u0000\u018a\u0184\u0001\u0000"+
		"\u0000\u0000\u018a\u0186\u0001\u0000\u0000\u0000\u018a\u0188\u0001\u0000"+
		"\u0000\u0000\u018b\'\u0001\u0000\u0000\u0000\u018c\u018d\u0005\u0014\u0000"+
		"\u0000\u018d\u0195\u0006\u0014\uffff\uffff\u0000\u018e\u018f\u0005\u001e"+
		"\u0000\u0000\u018f\u0195\u0006\u0014\uffff\uffff\u0000\u0190\u0191\u0005"+
		"\u001f\u0000\u0000\u0191\u0195\u0006\u0014\uffff\uffff\u0000\u0192\u0193"+
		"\u0005 \u0000\u0000\u0193\u0195\u0006\u0014\uffff\uffff\u0000\u0194\u018c"+
		"\u0001\u0000\u0000\u0000\u0194\u018e\u0001\u0000\u0000\u0000\u0194\u0190"+
		"\u0001\u0000\u0000\u0000\u0194\u0192\u0001\u0000\u0000\u0000\u0195)\u0001"+
		"\u0000\u0000\u0000\u0196\u0197\u0005\u001b\u0000\u0000\u0197\u019d\u0006"+
		"\u0015\uffff\uffff\u0000\u0198\u0199\u0005\u001c\u0000\u0000\u0199\u019d"+
		"\u0006\u0015\uffff\uffff\u0000\u019a\u019b\u0005\u001d\u0000\u0000\u019b"+
		"\u019d\u0006\u0015\uffff\uffff\u0000\u019c\u0196\u0001\u0000\u0000\u0000"+
		"\u019c\u0198\u0001\u0000\u0000\u0000\u019c\u019a\u0001\u0000\u0000\u0000"+
		"\u019d+\u0001\u0000\u0000\u0000\u019e\u019f\u0005\u001e\u0000\u0000\u019f"+
		"\u01a3\u0006\u0016\uffff\uffff\u0000\u01a0\u01a1\u0005\u001f\u0000\u0000"+
		"\u01a1\u01a3\u0006\u0016\uffff\uffff\u0000\u01a2\u019e\u0001\u0000\u0000"+
		"\u0000\u01a2\u01a0\u0001\u0000\u0000\u0000\u01a3-\u0001\u0000\u0000\u0000"+
		"\u01a4\u01a5\u0005\u0015\u0000\u0000\u01a5\u01b1\u0006\u0017\uffff\uffff"+
		"\u0000\u01a6\u01a7\u0005\u0016\u0000\u0000\u01a7\u01b1\u0006\u0017\uffff"+
		"\uffff\u0000\u01a8\u01a9\u0005\u0017\u0000\u0000\u01a9\u01b1\u0006\u0017"+
		"\uffff\uffff\u0000\u01aa\u01ab\u0005\u0018\u0000\u0000\u01ab\u01b1\u0006"+
		"\u0017\uffff\uffff\u0000\u01ac\u01ad\u0005\u0019\u0000\u0000\u01ad\u01b1"+
		"\u0006\u0017\uffff\uffff\u0000\u01ae\u01af\u0005\u001a\u0000\u0000\u01af"+
		"\u01b1\u0006\u0017\uffff\uffff\u0000\u01b0\u01a4\u0001\u0000\u0000\u0000"+
		"\u01b0\u01a6\u0001\u0000\u0000\u0000\u01b0\u01a8\u0001\u0000\u0000\u0000"+
		"\u01b0\u01aa\u0001\u0000\u0000\u0000\u01b0\u01ac\u0001\u0000\u0000\u0000"+
		"\u01b0\u01ae\u0001\u0000\u0000\u0000\u01b1/\u0001\u0000\u0000\u0000\u01b2"+
		"\u01b3\u0006\u0018\uffff\uffff\u0000\u01b3\u01b4\u0003\f\u0006\u0000\u01b4"+
		"\u01b9\u0006\u0018\uffff\uffff\u0000\u01b5\u01b6\u0005!\u0000\u0000\u01b6"+
		"\u01b7\u0003\f\u0006\u0000\u01b7\u01b8\u0006\u0018\uffff\uffff\u0000\u01b8"+
		"\u01ba\u0001\u0000\u0000\u0000\u01b9\u01b5\u0001\u0000\u0000\u0000\u01b9"+
		"\u01ba\u0001\u0000\u0000\u0000\u01ba\u01bb\u0001\u0000\u0000\u0000\u01bb"+
		"\u01bc\u0006\u0018\uffff\uffff\u0000\u01bc\u01ea\u0001\u0000\u0000\u0000"+
		"\u01bd\u01be\u0006\u0018\uffff\uffff\u0000\u01be\u01bf\u0005(\u0000\u0000"+
		"\u01bf\u01c0\u0003\f\u0006\u0000\u01c0\u01c1\u0006\u0018\uffff\uffff\u0000"+
		"\u01c1\u01c2\u0005-\u0000\u0000\u01c2\u01c3\u00030\u0018\u0000\u01c3\u01c8"+
		"\u0006\u0018\uffff\uffff\u0000\u01c4\u01c5\u0005&\u0000\u0000\u01c5\u01c6"+
		"\u00030\u0018\u0000\u01c6\u01c7\u0006\u0018\uffff\uffff\u0000\u01c7\u01c9"+
		"\u0001\u0000\u0000\u0000\u01c8\u01c4\u0001\u0000\u0000\u0000\u01c8\u01c9"+
		"\u0001\u0000\u0000\u0000\u01c9\u01ca\u0001\u0000\u0000\u0000\u01ca\u01cb"+
		"\u0006\u0018\uffff\uffff\u0000\u01cb\u01ea\u0001\u0000\u0000\u0000\u01cc"+
		"\u01cd\u00051\u0000\u0000\u01cd\u01ce\u0003\f\u0006\u0000\u01ce\u01cf"+
		"\u0005%\u0000\u0000\u01cf\u01d0\u00030\u0018\u0000\u01d0\u01d1\u0006\u0018"+
		"\uffff\uffff\u0000\u01d1\u01ea\u0001\u0000\u0000\u0000\u01d2\u01d3\u0005"+
		"+\u0000\u0000\u01d3\u01d4\u0003\u0002\u0001\u0000\u01d4\u01d5\u0005)\u0000"+
		"\u0000\u01d5\u01d6\u00030\u0018\u0000\u01d6\u01d7\u0006\u0018\uffff\uffff"+
		"\u0000\u01d7\u01ea\u0001\u0000\u0000\u0000\u01d8\u01d9\u0006\u0018\uffff"+
		"\uffff\u0000\u01d9\u01da\u0005\n\u0000\u0000\u01da\u01db\u00030\u0018"+
		"\u0000\u01db\u01e2\u0006\u0018\uffff\uffff\u0000\u01dc\u01dd\u0005\u0011"+
		"\u0000\u0000\u01dd\u01de\u00030\u0018\u0000\u01de\u01df\u0006\u0018\uffff"+
		"\uffff\u0000\u01df\u01e1\u0001\u0000\u0000\u0000\u01e0\u01dc\u0001\u0000"+
		"\u0000\u0000\u01e1\u01e4\u0001\u0000\u0000\u0000\u01e2\u01e3\u0001\u0000"+
		"\u0000\u0000\u01e2\u01e0\u0001\u0000\u0000\u0000\u01e3\u01e5\u0001\u0000"+
		"\u0000\u0000\u01e4\u01e2\u0001\u0000\u0000\u0000\u01e5\u01e6\u0005\u000b"+
		"\u0000\u0000\u01e6\u01e7\u0001\u0000\u0000\u0000\u01e7\u01e8\u0006\u0018"+
		"\uffff\uffff\u0000\u01e8\u01ea\u0001\u0000\u0000\u0000\u01e9\u01b2\u0001"+
		"\u0000\u0000\u0000\u01e9\u01bd\u0001\u0000\u0000\u0000\u01e9\u01cc\u0001"+
		"\u0000\u0000\u0000\u01e9\u01d2\u0001\u0000\u0000\u0000\u01e9\u01d8\u0001"+
		"\u0000\u0000\u0000\u01ea1\u0001\u0000\u0000\u0000#@BTlq{\u008e\u0093\u009d"+
		"\u00a3\u00b7\u00e0\u00ec\u00f8\u0104\u0110\u011c\u0128\u0131\u0144\u0146"+
		"\u0158\u015b\u015e\u0168\u017a\u018a\u0194\u019c\u01a2\u01b0\u01b9\u01c8"+
		"\u01e2\u01e9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}