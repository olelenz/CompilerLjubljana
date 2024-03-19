// Generated from PrevLexer.g4 by ANTLR 4.13.1

	package prev23.phase.lexan;
	import prev23.common.report.*;
	import prev23.data.sym.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class PrevLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"CONSTANT_VOID", "CONSTANT_TRUE", "CONSTANT_FALSE", "CONSTANT_INTEGER", 
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
		public Token nextToken() {
			return (Token) super.nextToken();
		}


	public PrevLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PrevLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 52:
			WHITE_TAB_action((RuleContext)_localctx, actionIndex);
			break;
		case 53:
			ERROR_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void WHITE_TAB_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:

			    setCharPositionInLine(getCharPositionInLine() + 8 - getCharPositionInLine()%8);

			break;
		}
	}
	private void ERROR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:

			    if (1==1) {
			        String allChars = getInputStream().toString();
			        char lastChar = allChars.charAt(getCharIndex());
			        throw new Report.Error(new Location(getLine(), getCharPositionInLine()), "Illegal character or expression at "+lastChar);
			    }

			break;
		}
	}

	public static final String _serializedATN =
		"\u0004\u00006\u0139\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007"+
		"&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007"+
		"+\u0002,\u0007,\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u0007"+
		"0\u00021\u00071\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u0007"+
		"5\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0005\u0003\u0081\b\u0003\n\u0003\f\u0003\u0084\t\u0003\u0003"+
		"\u0003\u0086\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004\u008d\b\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u0096\b\u0005\n"+
		"\u0005\f\u0005\u0099\t\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001"+
		"\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001"+
		" \u0001 \u0001!\u0001!\u0001!\u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0001#\u0001#\u0001#\u0001#\u0001$\u0001$\u0001$\u0001%\u0001"+
		"%\u0001%\u0001%\u0001%\u0001&\u0001&\u0001&\u0001&\u0001\'\u0001\'\u0001"+
		"\'\u0001(\u0001(\u0001(\u0001)\u0001)\u0001)\u0001)\u0001*\u0001*\u0001"+
		"*\u0001*\u0001+\u0001+\u0001+\u0001+\u0001,\u0001,\u0001,\u0001,\u0001"+
		",\u0001-\u0001-\u0001-\u0001-\u0001.\u0001.\u0001.\u0001.\u0001/\u0001"+
		"/\u0001/\u0001/\u0001/\u00010\u00010\u00010\u00010\u00010\u00010\u0001"+
		"1\u00031\u011e\b1\u00011\u00051\u0121\b1\n1\f1\u0124\t1\u00012\u00012"+
		"\u00052\u0128\b2\n2\f2\u012b\t2\u00012\u00012\u00013\u00013\u00013\u0001"+
		"3\u00014\u00014\u00014\u00014\u00014\u00015\u00015\u0000\u00006\u0001"+
		"\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007"+
		"\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d"+
		"\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/"+
		"\u00181\u00193\u001a5\u001b7\u001c9\u001d;\u001e=\u001f? A!C\"E#G$I%K"+
		"&M\'O(Q)S*U+W,Y-[.]/_0a1c2e3g4i5k6\u0001\u0000\u000b\u0001\u000000\u0001"+
		"\u000019\u0001\u000009\u0001\u0000 &\u0001\u0000(~\u0001\u0000 !\u0001"+
		"\u0000#~\u0003\u0000AZ__az\u0004\u000009AZ__az\u0002\u0000\n\n\r\r\u0003"+
		"\u0000\n\n\r\r  \u0141\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003"+
		"\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007"+
		"\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001"+
		"\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000"+
		"\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000"+
		"\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000"+
		"\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000"+
		"\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000"+
		"\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000"+
		"\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000"+
		")\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001"+
		"\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000"+
		"\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u0000"+
		"7\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0000;\u0001"+
		"\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000\u0000?\u0001\u0000\u0000"+
		"\u0000\u0000A\u0001\u0000\u0000\u0000\u0000C\u0001\u0000\u0000\u0000\u0000"+
		"E\u0001\u0000\u0000\u0000\u0000G\u0001\u0000\u0000\u0000\u0000I\u0001"+
		"\u0000\u0000\u0000\u0000K\u0001\u0000\u0000\u0000\u0000M\u0001\u0000\u0000"+
		"\u0000\u0000O\u0001\u0000\u0000\u0000\u0000Q\u0001\u0000\u0000\u0000\u0000"+
		"S\u0001\u0000\u0000\u0000\u0000U\u0001\u0000\u0000\u0000\u0000W\u0001"+
		"\u0000\u0000\u0000\u0000Y\u0001\u0000\u0000\u0000\u0000[\u0001\u0000\u0000"+
		"\u0000\u0000]\u0001\u0000\u0000\u0000\u0000_\u0001\u0000\u0000\u0000\u0000"+
		"a\u0001\u0000\u0000\u0000\u0000c\u0001\u0000\u0000\u0000\u0000e\u0001"+
		"\u0000\u0000\u0000\u0000g\u0001\u0000\u0000\u0000\u0000i\u0001\u0000\u0000"+
		"\u0000\u0000k\u0001\u0000\u0000\u0000\u0001m\u0001\u0000\u0000\u0000\u0003"+
		"r\u0001\u0000\u0000\u0000\u0005w\u0001\u0000\u0000\u0000\u0007\u0085\u0001"+
		"\u0000\u0000\u0000\t\u0087\u0001\u0000\u0000\u0000\u000b\u0090\u0001\u0000"+
		"\u0000\u0000\r\u009c\u0001\u0000\u0000\u0000\u000f\u00a0\u0001\u0000\u0000"+
		"\u0000\u0011\u00a2\u0001\u0000\u0000\u0000\u0013\u00a4\u0001\u0000\u0000"+
		"\u0000\u0015\u00a6\u0001\u0000\u0000\u0000\u0017\u00a8\u0001\u0000\u0000"+
		"\u0000\u0019\u00aa\u0001\u0000\u0000\u0000\u001b\u00ac\u0001\u0000\u0000"+
		"\u0000\u001d\u00ae\u0001\u0000\u0000\u0000\u001f\u00b0\u0001\u0000\u0000"+
		"\u0000!\u00b2\u0001\u0000\u0000\u0000#\u00b4\u0001\u0000\u0000\u0000%"+
		"\u00b6\u0001\u0000\u0000\u0000\'\u00b8\u0001\u0000\u0000\u0000)\u00ba"+
		"\u0001\u0000\u0000\u0000+\u00bd\u0001\u0000\u0000\u0000-\u00c0\u0001\u0000"+
		"\u0000\u0000/\u00c2\u0001\u0000\u0000\u00001\u00c4\u0001\u0000\u0000\u0000"+
		"3\u00c7\u0001\u0000\u0000\u00005\u00ca\u0001\u0000\u0000\u00007\u00cc"+
		"\u0001\u0000\u0000\u00009\u00ce\u0001\u0000\u0000\u0000;\u00d0\u0001\u0000"+
		"\u0000\u0000=\u00d2\u0001\u0000\u0000\u0000?\u00d4\u0001\u0000\u0000\u0000"+
		"A\u00d6\u0001\u0000\u0000\u0000C\u00d8\u0001\u0000\u0000\u0000E\u00dd"+
		"\u0001\u0000\u0000\u0000G\u00e2\u0001\u0000\u0000\u0000I\u00e6\u0001\u0000"+
		"\u0000\u0000K\u00e9\u0001\u0000\u0000\u0000M\u00ee\u0001\u0000\u0000\u0000"+
		"O\u00f2\u0001\u0000\u0000\u0000Q\u00f5\u0001\u0000\u0000\u0000S\u00f8"+
		"\u0001\u0000\u0000\u0000U\u00fc\u0001\u0000\u0000\u0000W\u0100\u0001\u0000"+
		"\u0000\u0000Y\u0104\u0001\u0000\u0000\u0000[\u0109\u0001\u0000\u0000\u0000"+
		"]\u010d\u0001\u0000\u0000\u0000_\u0111\u0001\u0000\u0000\u0000a\u0116"+
		"\u0001\u0000\u0000\u0000c\u011d\u0001\u0000\u0000\u0000e\u0125\u0001\u0000"+
		"\u0000\u0000g\u012e\u0001\u0000\u0000\u0000i\u0132\u0001\u0000\u0000\u0000"+
		"k\u0137\u0001\u0000\u0000\u0000mn\u0005n\u0000\u0000no\u0005o\u0000\u0000"+
		"op\u0005n\u0000\u0000pq\u0005e\u0000\u0000q\u0002\u0001\u0000\u0000\u0000"+
		"rs\u0005t\u0000\u0000st\u0005r\u0000\u0000tu\u0005u\u0000\u0000uv\u0005"+
		"e\u0000\u0000v\u0004\u0001\u0000\u0000\u0000wx\u0005f\u0000\u0000xy\u0005"+
		"a\u0000\u0000yz\u0005l\u0000\u0000z{\u0005s\u0000\u0000{|\u0005e\u0000"+
		"\u0000|\u0006\u0001\u0000\u0000\u0000}\u0086\u0007\u0000\u0000\u0000~"+
		"\u0082\u0007\u0001\u0000\u0000\u007f\u0081\u0007\u0002\u0000\u0000\u0080"+
		"\u007f\u0001\u0000\u0000\u0000\u0081\u0084\u0001\u0000\u0000\u0000\u0082"+
		"\u0080\u0001\u0000\u0000\u0000\u0082\u0083\u0001\u0000\u0000\u0000\u0083"+
		"\u0086\u0001\u0000\u0000\u0000\u0084\u0082\u0001\u0000\u0000\u0000\u0085"+
		"}\u0001\u0000\u0000\u0000\u0085~\u0001\u0000\u0000\u0000\u0086\b\u0001"+
		"\u0000\u0000\u0000\u0087\u008c\u0005\'\u0000\u0000\u0088\u008d\u0007\u0003"+
		"\u0000\u0000\u0089\u008a\u0005\\\u0000\u0000\u008a\u008d\u0005\'\u0000"+
		"\u0000\u008b\u008d\u0007\u0004\u0000\u0000\u008c\u0088\u0001\u0000\u0000"+
		"\u0000\u008c\u0089\u0001\u0000\u0000\u0000\u008c\u008b\u0001\u0000\u0000"+
		"\u0000\u008d\u008e\u0001\u0000\u0000\u0000\u008e\u008f\u0005\'\u0000\u0000"+
		"\u008f\n\u0001\u0000\u0000\u0000\u0090\u0097\u0005\"\u0000\u0000\u0091"+
		"\u0096\u0007\u0005\u0000\u0000\u0092\u0093\u0005\\\u0000\u0000\u0093\u0096"+
		"\u0005\"\u0000\u0000\u0094\u0096\u0007\u0006\u0000\u0000\u0095\u0091\u0001"+
		"\u0000\u0000\u0000\u0095\u0092\u0001\u0000\u0000\u0000\u0095\u0094\u0001"+
		"\u0000\u0000\u0000\u0096\u0099\u0001\u0000\u0000\u0000\u0097\u0095\u0001"+
		"\u0000\u0000\u0000\u0097\u0098\u0001\u0000\u0000\u0000\u0098\u009a\u0001"+
		"\u0000\u0000\u0000\u0099\u0097\u0001\u0000\u0000\u0000\u009a\u009b\u0005"+
		"\"\u0000\u0000\u009b\f\u0001\u0000\u0000\u0000\u009c\u009d\u0005n\u0000"+
		"\u0000\u009d\u009e\u0005i\u0000\u0000\u009e\u009f\u0005l\u0000\u0000\u009f"+
		"\u000e\u0001\u0000\u0000\u0000\u00a0\u00a1\u0005(\u0000\u0000\u00a1\u0010"+
		"\u0001\u0000\u0000\u0000\u00a2\u00a3\u0005)\u0000\u0000\u00a3\u0012\u0001"+
		"\u0000\u0000\u0000\u00a4\u00a5\u0005{\u0000\u0000\u00a5\u0014\u0001\u0000"+
		"\u0000\u0000\u00a6\u00a7\u0005}\u0000\u0000\u00a7\u0016\u0001\u0000\u0000"+
		"\u0000\u00a8\u00a9\u0005[\u0000\u0000\u00a9\u0018\u0001\u0000\u0000\u0000"+
		"\u00aa\u00ab\u0005]\u0000\u0000\u00ab\u001a\u0001\u0000\u0000\u0000\u00ac"+
		"\u00ad\u0005.\u0000\u0000\u00ad\u001c\u0001\u0000\u0000\u0000\u00ae\u00af"+
		"\u0005,\u0000\u0000\u00af\u001e\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005"+
		":\u0000\u0000\u00b1 \u0001\u0000\u0000\u0000\u00b2\u00b3\u0005;\u0000"+
		"\u0000\u00b3\"\u0001\u0000\u0000\u0000\u00b4\u00b5\u0005&\u0000\u0000"+
		"\u00b5$\u0001\u0000\u0000\u0000\u00b6\u00b7\u0005|\u0000\u0000\u00b7&"+
		"\u0001\u0000\u0000\u0000\u00b8\u00b9\u0005!\u0000\u0000\u00b9(\u0001\u0000"+
		"\u0000\u0000\u00ba\u00bb\u0005=\u0000\u0000\u00bb\u00bc\u0005=\u0000\u0000"+
		"\u00bc*\u0001\u0000\u0000\u0000\u00bd\u00be\u0005!\u0000\u0000\u00be\u00bf"+
		"\u0005=\u0000\u0000\u00bf,\u0001\u0000\u0000\u0000\u00c0\u00c1\u0005<"+
		"\u0000\u0000\u00c1.\u0001\u0000\u0000\u0000\u00c2\u00c3\u0005>\u0000\u0000"+
		"\u00c30\u0001\u0000\u0000\u0000\u00c4\u00c5\u0005<\u0000\u0000\u00c5\u00c6"+
		"\u0005=\u0000\u0000\u00c62\u0001\u0000\u0000\u0000\u00c7\u00c8\u0005>"+
		"\u0000\u0000\u00c8\u00c9\u0005=\u0000\u0000\u00c94\u0001\u0000\u0000\u0000"+
		"\u00ca\u00cb\u0005*\u0000\u0000\u00cb6\u0001\u0000\u0000\u0000\u00cc\u00cd"+
		"\u0005/\u0000\u0000\u00cd8\u0001\u0000\u0000\u0000\u00ce\u00cf\u0005%"+
		"\u0000\u0000\u00cf:\u0001\u0000\u0000\u0000\u00d0\u00d1\u0005+\u0000\u0000"+
		"\u00d1<\u0001\u0000\u0000\u0000\u00d2\u00d3\u0005-\u0000\u0000\u00d3>"+
		"\u0001\u0000\u0000\u0000\u00d4\u00d5\u0005^\u0000\u0000\u00d5@\u0001\u0000"+
		"\u0000\u0000\u00d6\u00d7\u0005=\u0000\u0000\u00d7B\u0001\u0000\u0000\u0000"+
		"\u00d8\u00d9\u0005b\u0000\u0000\u00d9\u00da\u0005o\u0000\u0000\u00da\u00db"+
		"\u0005o\u0000\u0000\u00db\u00dc\u0005l\u0000\u0000\u00dcD\u0001\u0000"+
		"\u0000\u0000\u00dd\u00de\u0005c\u0000\u0000\u00de\u00df\u0005h\u0000\u0000"+
		"\u00df\u00e0\u0005a\u0000\u0000\u00e0\u00e1\u0005r\u0000\u0000\u00e1F"+
		"\u0001\u0000\u0000\u0000\u00e2\u00e3\u0005d\u0000\u0000\u00e3\u00e4\u0005"+
		"e\u0000\u0000\u00e4\u00e5\u0005l\u0000\u0000\u00e5H\u0001\u0000\u0000"+
		"\u0000\u00e6\u00e7\u0005d\u0000\u0000\u00e7\u00e8\u0005o\u0000\u0000\u00e8"+
		"J\u0001\u0000\u0000\u0000\u00e9\u00ea\u0005e\u0000\u0000\u00ea\u00eb\u0005"+
		"l\u0000\u0000\u00eb\u00ec\u0005s\u0000\u0000\u00ec\u00ed\u0005e\u0000"+
		"\u0000\u00edL\u0001\u0000\u0000\u0000\u00ee\u00ef\u0005f\u0000\u0000\u00ef"+
		"\u00f0\u0005u\u0000\u0000\u00f0\u00f1\u0005n\u0000\u0000\u00f1N\u0001"+
		"\u0000\u0000\u0000\u00f2\u00f3\u0005i\u0000\u0000\u00f3\u00f4\u0005f\u0000"+
		"\u0000\u00f4P\u0001\u0000\u0000\u0000\u00f5\u00f6\u0005i\u0000\u0000\u00f6"+
		"\u00f7\u0005n\u0000\u0000\u00f7R\u0001\u0000\u0000\u0000\u00f8\u00f9\u0005"+
		"i\u0000\u0000\u00f9\u00fa\u0005n\u0000\u0000\u00fa\u00fb\u0005t\u0000"+
		"\u0000\u00fbT\u0001\u0000\u0000\u0000\u00fc\u00fd\u0005l\u0000\u0000\u00fd"+
		"\u00fe\u0005e\u0000\u0000\u00fe\u00ff\u0005t\u0000\u0000\u00ffV\u0001"+
		"\u0000\u0000\u0000\u0100\u0101\u0005n\u0000\u0000\u0101\u0102\u0005e\u0000"+
		"\u0000\u0102\u0103\u0005w\u0000\u0000\u0103X\u0001\u0000\u0000\u0000\u0104"+
		"\u0105\u0005t\u0000\u0000\u0105\u0106\u0005h\u0000\u0000\u0106\u0107\u0005"+
		"e\u0000\u0000\u0107\u0108\u0005n\u0000\u0000\u0108Z\u0001\u0000\u0000"+
		"\u0000\u0109\u010a\u0005t\u0000\u0000\u010a\u010b\u0005y\u0000\u0000\u010b"+
		"\u010c\u0005p\u0000\u0000\u010c\\\u0001\u0000\u0000\u0000\u010d\u010e"+
		"\u0005v\u0000\u0000\u010e\u010f\u0005a\u0000\u0000\u010f\u0110\u0005r"+
		"\u0000\u0000\u0110^\u0001\u0000\u0000\u0000\u0111\u0112\u0005v\u0000\u0000"+
		"\u0112\u0113\u0005o\u0000\u0000\u0113\u0114\u0005i\u0000\u0000\u0114\u0115"+
		"\u0005d\u0000\u0000\u0115`\u0001\u0000\u0000\u0000\u0116\u0117\u0005w"+
		"\u0000\u0000\u0117\u0118\u0005h\u0000\u0000\u0118\u0119\u0005i\u0000\u0000"+
		"\u0119\u011a\u0005l\u0000\u0000\u011a\u011b\u0005e\u0000\u0000\u011bb"+
		"\u0001\u0000\u0000\u0000\u011c\u011e\u0007\u0007\u0000\u0000\u011d\u011c"+
		"\u0001\u0000\u0000\u0000\u011e\u0122\u0001\u0000\u0000\u0000\u011f\u0121"+
		"\u0007\b\u0000\u0000\u0120\u011f\u0001\u0000\u0000\u0000\u0121\u0124\u0001"+
		"\u0000\u0000\u0000\u0122\u0120\u0001\u0000\u0000\u0000\u0122\u0123\u0001"+
		"\u0000\u0000\u0000\u0123d\u0001\u0000\u0000\u0000\u0124\u0122\u0001\u0000"+
		"\u0000\u0000\u0125\u0129\u0005#\u0000\u0000\u0126\u0128\b\t\u0000\u0000"+
		"\u0127\u0126\u0001\u0000\u0000\u0000\u0128\u012b\u0001\u0000\u0000\u0000"+
		"\u0129\u0127\u0001\u0000\u0000\u0000\u0129\u012a\u0001\u0000\u0000\u0000"+
		"\u012a\u012c\u0001\u0000\u0000\u0000\u012b\u0129\u0001\u0000\u0000\u0000"+
		"\u012c\u012d\u00062\u0000\u0000\u012df\u0001\u0000\u0000\u0000\u012e\u012f"+
		"\u0007\n\u0000\u0000\u012f\u0130\u0001\u0000\u0000\u0000\u0130\u0131\u0006"+
		"3\u0000\u0000\u0131h\u0001\u0000\u0000\u0000\u0132\u0133\u0005\t\u0000"+
		"\u0000\u0133\u0134\u00064\u0001\u0000\u0134\u0135\u0001\u0000\u0000\u0000"+
		"\u0135\u0136\u00064\u0000\u0000\u0136j\u0001\u0000\u0000\u0000\u0137\u0138"+
		"\u00065\u0002\u0000\u0138l\u0001\u0000\u0000\u0000\n\u0000\u0082\u0085"+
		"\u008c\u0095\u0097\u011d\u0120\u0122\u0129\u0003\u0006\u0000\u0000\u0001"+
		"4\u0000\u00015\u0001";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}