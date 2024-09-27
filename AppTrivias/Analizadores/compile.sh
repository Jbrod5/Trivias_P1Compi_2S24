echo "Compilación de JFlex"
java -jar /home/jorge/Apps/jflex-1.9.1/lib/jflex-full-1.9.1.jar -d ../app/src/main/java/com/jbrod/apptrivias/analizadores Lexer.flex

echo " "
echo " "
echo " "
echo " "
echo " "
echo " "
echo " "

echo "Compilacion de Cup"
java -jar /home/jorge/Apps/java-cup-bin-11b-20160615/java-cup-11b.jar -parser Parser Parser.cup
mv Parser.java ../app/src/main/java/com/jbrod/apptrivias/analizadores
mv sym.java ../app/src/main/java/com/jbrod/apptrivias/analizadores