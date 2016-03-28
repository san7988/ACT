#!/bin/sh

base_dir="/home/sanjeev/workspace_mars/ACT"

java -jar $base_dir/JLex.jar $base_dir/src/parser/ACT3.lex

mv $base_dir/src/parser/ACT3.lex.java $base_dir/src/parser/Yylex.java 

