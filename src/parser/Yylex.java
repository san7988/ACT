package parser;
import java_cup.runtime.*;
import stateChartToGraph.*;
import graph.*;
import java.lang.System;
enum TokenType{SYSTEM,STATES,STATE,STATEID,DESC,INPUT,CLICKABLE,TRANSITION,GUARD,TRIGGER,ACTION,TRANSITIONID,ID,LOGEXPR,CLICKELE,LPAREN,
					RPAREN,LBRACKET,RBRACKET,LBRACES,RBRACES,LANGBRACKET,RANGBRACKET}


class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

  private int comment_count = 0;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int COMMENT = 1;
	private final int yy_state_dtrans[] = {
		0,
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NO_ANCHOR,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"25:8,24:2,23,25:2,0,25:18,24,25:11,2,25:3,22:10,1,25:2,3,25:3,11,21,13,12,9" +
",21,20,21,14,21:3,10,15,19,16,21,18,6,8,17,21:3,7,21,25:6,21:26,4,25,5,25:2" +
",26:2")[0];

	private int yy_rmap[] = unpackFromString(1,57,
"0,1:5,2:2,1:2,3:9,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24," +
"25,26,27,28,29,30,31,32,33,34,35,3,36,37,38,28,39")[0];

	private int yy_nxt[][] = unpackFromString(40,27,
"-1,1,2,3,4,5,19,51,52,51:2,36,37,51,53,51:5,54,51,55,6,7,8,9,-1:50,7:2,-1:8" +
",51:17,-1:10,51,56,38,51:10,39,51:3,-1:10,51:8,10,51:8,-1:10,51:2,11,51:4,1" +
"2,51:9,-1:10,51:3,13,51:13,-1:10,51:2,14,51:14,-1:10,51:6,15,51:10,-1:10,51" +
":4,16,51:12,-1:10,51:3,17,51:13,-1:10,51:9,18,51:7,-1:10,51:2,20,51:14,-1:1" +
"0,21,51:16,-1:10,51:2,22,51:14,-1:10,51:11,23,51:5,-1:10,51:12,24,51:4,-1:1" +
"0,51:3,25,51:13,-1:10,51:7,26,51:9,-1:10,51:13,27,51:3,-1:10,51:7,28,51:9,-" +
"1:10,51:3,29,51:13,-1:10,51:5,30,51:11,-1:10,51:11,45,51:5,-1:10,51:5,46,51" +
":11,-1:10,51:10,31,51:6,-1:10,51:5,32,51:11,-1:10,51:16,43,-1:10,51:2,33,51" +
":14,-1:10,51:12,34,51:4,-1:10,51:9,47,51:7,-1:10,48,51:16,-1:10,51:8,49,51:" +
"8,-1:10,51:2,50,51:14,-1:10,51:8,35,51:8,-1:10,51:12,40,51:4,-1:10,51:9,41," +
"51:7,-1:10,51:11,42,51:5,-1:10,44,51:16,-1:4");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 0:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -2:
						break;
					case 1:
						{ System.out.println("Lex: colon");return new Symbol(sym.COLON); }
					case -3:
						break;
					case 2:
						{ System.out.println("Lex: comma");return new Symbol(sym.COMMA); }
					case -4:
						break;
					case 3:
						{ System.out.println("Lex: equal");return new Symbol(sym.EQUAL); }
					case -5:
						break;
					case 4:
						{ System.out.println("Lex: {");return new Symbol(sym.LBRACE); }
					case -6:
						break;
					case 5:
						{ System.out.println("Lex: }");return new Symbol(sym.RBRACE); }
					case -7:
						break;
					case 6:
						{ }
					case -8:
						break;
					case 7:
						{ }
					case -9:
						break;
					case 8:
						{System.out.println("Lex: Illegal character: <" + yytext() + ">");}
					case -10:
						break;
					case 9:
						
					case -11:
						break;
					case 10:
						{ System.out.println("Lex: action");return new Symbol(sym.ACTI); }
					case -12:
						break;
					case 11:
						{ System.out.println("Lex: dest");return new Symbol(sym.DEST); }
					case -13:
						break;
					case 12:
						{ System.out.println("Lex: desc");return new Symbol(sym.DESC); }
					case -14:
						break;
					case 13:
						{ System.out.println("Lex: state");return new Symbol(sym.STATE); }
					case -15:
						break;
					case 14:
						{ System.out.println("Lex: input");return new Symbol(sym.INPUT); }
					case -16:
						break;
					case 15:
						{ System.out.println("Lex: guard");return new Symbol(sym.GUARD); }
					case -17:
						break;
					case 16:
						{ System.out.println("Lex: system");return new Symbol(sym.SYSTEM); }
					case -18:
						break;
					case 17:
						{ System.out.println("Lex: source");return new Symbol(sym.SOURCE); }
					case -19:
						break;
					case 18:
						{ System.out.println("Lex: transition");return new Symbol(sym.TRANSITION); }
					case -20:
						break;
					case 19:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -21:
						break;
					case 20:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -22:
						break;
					case 21:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -23:
						break;
					case 22:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -24:
						break;
					case 23:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -25:
						break;
					case 24:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -26:
						break;
					case 25:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -27:
						break;
					case 26:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -28:
						break;
					case 27:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -29:
						break;
					case 28:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -30:
						break;
					case 29:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -31:
						break;
					case 30:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -32:
						break;
					case 31:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -33:
						break;
					case 32:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -34:
						break;
					case 33:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -35:
						break;
					case 34:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -36:
						break;
					case 35:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -37:
						break;
					case 36:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -38:
						break;
					case 37:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -39:
						break;
					case 38:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -40:
						break;
					case 39:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -41:
						break;
					case 40:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -42:
						break;
					case 41:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -43:
						break;
					case 42:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -44:
						break;
					case 43:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -45:
						break;
					case 44:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -46:
						break;
					case 45:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -47:
						break;
					case 46:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -48:
						break;
					case 47:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -49:
						break;
					case 48:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -50:
						break;
					case 49:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -51:
						break;
					case 50:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -52:
						break;
					case 51:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -53:
						break;
					case 52:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -54:
						break;
					case 53:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -55:
						break;
					case 54:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -56:
						break;
					case 55:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -57:
						break;
					case 56:
						{System.out.println("Lex: string/ID");return new Symbol(sym.ID, new String(yytext()));}
					case -58:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
