echo "Compilación de JFlex"
java -jar /home/jorge/Apps/jflex-1.9.1/lib/jflex-full-1.9.1.jar -d ../src/main/java/com/jbrod/servidorprincipal/analizadores Lexer.flex

echo " "
echo " "
echo " "
echo " "
echo " "
echo " "
echo " "

echo "Compilacion de Cup"
java -jar /home/jorge/Apps/java-cup-bin-11b-20160615/java-cup-11b.jar -parser Parser -expect 1 Parser.cup  
mv Parser.java ../src/main/java/com/jbrod/servidorprincipal/analizadores
mv sym.java ../src/main/java/com/jbrod/servidorprincipal/analizadores