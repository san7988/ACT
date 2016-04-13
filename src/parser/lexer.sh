#!/bin/sh

#base_dir="/home/sanjeev/workspace_mars/ACT"

java -jar ./JLex.jar ./src/parser/ACT3.lex

mv ./src/parser/ACT3.lex.java ./src/parser/Yylex.java 

