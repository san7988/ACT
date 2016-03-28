package parser;

import java_cup.runtime.*;
import stateChartToGraph.*;
import java.lang.System;
enum TokenType{SYSTEM,STATES,STATE,STATEID,TRANSITION,GUARD,TRIGGER,ACTION,TRANSITIONID,ID,LOGEXPR,CLICKELE,LPAREN,
					RPAREN,LBRACKET,RBRACKET,LBRACES,RBRACES,LANGBRACKET,RANGBRACKET}



%%

%{
  private int comment_count = 0;
%}
%cup
%line
%char
%state COMMENT

ALPHA=[A-Za-z]
DIGIT=[0-9]
NONNEWLINE_WHITE_SPACE_CHAR=[\ \t\b\012]
WHITE_SPACE_CHAR=[\n\ \t\b\012]
STRING_TEXT=(\\\"|[^\n\"]|\\{WHITE_SPACE_CHAR}+\\)*
COMMENT_TEXT=([^/*\n]|[^*\n]"/"[^*\n]|[^/\n]"*"[^/\n]|"*"[^/\n]|"/"[^*\n])*
LOGICAL_EXPR=(\\|[^\n\"]|\\{WHITE_SPACE_CHAR}+\\)*
CLICK=(#[^\n]*[#])
%% 
":" { System.out.println("colon");return new Symbol(sym.COLON); }
"=" { System.out.println("equal");return new Symbol(sym.EQUAL); }
"{" { System.out.println("{");return new Symbol(sym.LBRACE); }
"}" { System.out.println("}");return new Symbol(sym.RBRACE); }
SYSTEM { System.out.println("system");return new Symbol(sym.SYSTEM); }
STATE { System.out.println("state");return new Symbol(sym.STATE); }
TRANSITION { System.out.println("transition");return new Symbol(sym.TRANSITION); }
SOURCE { System.out.println("source");return new Symbol(sym.SOURCE); }
DEST { System.out.println("dest");return new Symbol(sym.DEST); }
GUARD { System.out.println("guard");return new Symbol(sym.GUARD); }
ACTI { System.out.println("action");return new Symbol(sym.ACTI); }
[A-Za-z][A-Za-z0-9]* {System.out.println("string");return new Symbol(sym.ID, new String(yytext()));}
[0-9]+ { System.out.println("number");return new Symbol(sym.NUMBER, new Integer(yytext())); }

\n { }
{NONNEWLINE_WHITE_SPACE_CHAR}+ { }
. {System.out.println("Illegal character: <" + yytext() + ">");}

