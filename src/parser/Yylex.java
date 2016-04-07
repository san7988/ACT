package parser;
import java_cup.runtime.*;
import stateChartToGraph.*;
import graph.*;
import java.lang.System;
enum TokenType{SYSTEM,STATES,STATE,STATEID,TRANSITION,GUARD,TRIGGER,ACTION,TRANSITIONID,ID,LOGEXPR,CLICKELE,LPAREN,
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
		/* 49 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"23:8,22:2,21,23:2,0,23:18,22,23:15,20:10,1,23:2,2,23:3,10,19,16,17,8,19,18," +
"19,13,19:3,9,12,14,19:2,11,5,7,15,19:3,6,19,23:6,19:26,3,23,4,23:2,24:2")[0];

	private int yy_rmap[] = unpackFromString(1,50,
"0,1:4,2:2,1:2,3:7,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24," +
"25,26,27,28,29,30,31,32,3,33,34,25,35")[0];

	private int yy_nxt[][] = unpackFromString(36,25,
"-1,1,2,3,4,16,45,46,45:2,31,45:6,32,47,45,48,5,6,7,8,-1:46,6:2,-1:7,45:16,-" +
"1:9,45,49,33,45:6,34,45:6,-1:9,45:8,9,45:7,-1:9,45:2,10,45:13,-1:9,45:3,11," +
"45:12,-1:9,45:12,12,45:3,-1:9,45:4,13,45:11,-1:9,45:3,14,45:12,-1:9,45:7,15" +
",45:8,-1:9,45:2,17,45:13,-1:9,18,45:15,-1:9,45:2,19,45:13,-1:9,45:6,20,45:9" +
",-1:9,45:3,21,45:12,-1:9,45:11,22,45:4,-1:9,45:9,23,45:6,-1:9,45:11,24,45:4" +
",-1:9,45:3,25,45:12,-1:9,45:5,26,45:10,-1:9,45:10,39,45:5,-1:9,45:5,40,45:1" +
"0,-1:9,45:5,27,45:10,-1:9,45:15,37,-1:9,45:2,28,45:13,-1:9,45:6,29,45:9,-1:" +
"9,45:7,41,45:8,-1:9,42,45:15,-1:9,45:8,43,45:7,-1:9,45:2,44,45:13,-1:9,45:8" +
",30,45:7,-1:9,45:6,35,45:9,-1:9,45:10,36,45:5,-1:9,38,45:15,-1:4");

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
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -2:
						break;
					case 1:
						{ System.out.println("colon");return new Symbol(sym.COLON); }
					case -3:
						break;
					case 2:
						{ System.out.println("equal");return new Symbol(sym.EQUAL); }
					case -4:
						break;
					case 3:
						{ System.out.println("{");return new Symbol(sym.LBRACE); }
					case -5:
						break;
					case 4:
						{ System.out.println("}");return new Symbol(sym.RBRACE); }
					case -6:
						break;
					case 5:
						{ }
					case -7:
						break;
					case 6:
						{ }
					case -8:
						break;
					case 7:
						{System.out.println("Illegal character: <" + yytext() + ">");}
					case -9:
						break;
					case 8:
						
					case -10:
						break;
					case 9:
						{ System.out.println("action");return new Symbol(sym.ACTI); }
					case -11:
						break;
					case 10:
						{ System.out.println("dest");return new Symbol(sym.DEST); }
					case -12:
						break;
					case 11:
						{ System.out.println("state");return new Symbol(sym.STATE); }
					case -13:
						break;
					case 12:
						{ System.out.println("guard");return new Symbol(sym.GUARD); }
					case -14:
						break;
					case 13:
						{ System.out.println("system");return new Symbol(sym.SYSTEM); }
					case -15:
						break;
					case 14:
						{ System.out.println("source");return new Symbol(sym.SOURCE); }
					case -16:
						break;
					case 15:
						{ System.out.println("transition");return new Symbol(sym.TRANSITION); }
					case -17:
						break;
					case 16:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -18:
						break;
					case 17:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -19:
						break;
					case 18:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -20:
						break;
					case 19:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -21:
						break;
					case 20:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -22:
						break;
					case 21:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -23:
						break;
					case 22:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -24:
						break;
					case 23:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -25:
						break;
					case 24:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -26:
						break;
					case 25:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -27:
						break;
					case 26:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -28:
						break;
					case 27:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -29:
						break;
					case 28:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -30:
						break;
					case 29:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -31:
						break;
					case 30:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -32:
						break;
					case 31:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -33:
						break;
					case 32:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -34:
						break;
					case 33:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -35:
						break;
					case 34:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -36:
						break;
					case 35:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -37:
						break;
					case 36:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -38:
						break;
					case 37:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -39:
						break;
					case 38:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -40:
						break;
					case 39:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -41:
						break;
					case 40:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -42:
						break;
					case 41:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -43:
						break;
					case 42:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -44:
						break;
					case 43:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -45:
						break;
					case 44:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -46:
						break;
					case 45:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -47:
						break;
					case 46:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -48:
						break;
					case 47:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -49:
						break;
					case 48:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -50:
						break;
					case 49:
						{System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
					case -51:
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
