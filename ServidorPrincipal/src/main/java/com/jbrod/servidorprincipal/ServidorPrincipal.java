
package com.jbrod.servidorprincipal;

import com.jbrod.servidorprincipal.analizadores.Lexer;
import com.jbrod.servidorprincipal.analizadores.Parser;
import com.jbrod.servidorprincipal.trivias.Motor;
import com.jbrod.servidorprincipal.trivias.Trivia;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class ServidorPrincipal {

    public static void main(String[] args) {
        try {
            System.out.println("Hello World!");
            String ruta = "/home/jorge/Compi_1/Trivias_P1Compi_2S24/ServidorPrincipal/Analizadores/Pruebas.xson";
            String txt = "";
            
            try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    txt += linea + "\n";
                }
            }   catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
            
            
            /*String txt ="<!realizar_solicitud: \"AGREGAR_COMPONENTE\" > \n" +
"      { \"PARAMETROS_COMPONENTE\":[{ \n" +
"                \"ID\": \"$_soldado_presidente\", \n" +
"                \"TRIVIA\": \"$trivia1\", \n" +
"                \"CLASE\": \"Combo\", \n" +
"                \"INDICE\" : 5, \n" +
"                \"TEXTO_VISIBLE\": \"A quie se le conoce como el soldado del pueblo? \", \n" +
"                \"RESPUESTA\": \"Jacobo Arbenz\", \n" +
"                \"OPCIONES\": \"Jacobo Arbenz|Jose Arebalo|Jorge Ubico|Otro\" \n" +
"        } \n" +
"        ]} \n" +
"<fin_solicitud_realizada!> ";*/
            
            
            Motor motor = new Motor();
            
            
            StringReader sb = new StringReader(txt);
            Lexer lex = new Lexer(sb);
            Parser parser = new Parser(lex, motor);
            
            parser.parse();
            
            System.out.println(parser.resultado);
            
            String contenido = motor.exportarTrivias();
            String rutaEx = "/home/jorge/Escritorio/";
            File ar = new File(rutaEx + "Ar.exp");
            if(!ar.exists()){
                ar.createNewFile();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ar))) {
                writer.write(contenido);
                System.out.println("El contenido se ha escrito en el archivo exitosamente.");
            } catch (IOException e) {
                System.err.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(ServidorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
