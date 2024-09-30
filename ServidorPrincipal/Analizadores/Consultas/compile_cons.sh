echo "Compilación de JFlex"
java -jar /home/jorge/Apps/jflex-1.9.1/lib/jflex-full-1.9.1.jar -d ../../src/main/java/com/jbrod/servidorprincipal/analizadores/consultas LexerCons.flex

echo " "
echo " "
echo " "
echo " "
echo " "
echo " "
echo " "

echo "Compilacion de Cup"
java -jar /home/jorge/Apps/java-cup-bin-11b-20160615/java-cup-11b.jar -parser ParserCons ParserCons.cup
mv ParserCons.java ../../src/main/java/com/jbrod/servidorprincipal/analizadores/consultas
mv sym.java ../../src/main/java/com/jbrod/servidorprincipal/analizadores/consultas