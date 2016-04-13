
java -jar .\JLex.jar .\src\parser\ACT3.lex
del .\src\parser\Yylex.java
ren .\src\parser\ACT3.lex.java Yylex.java 

