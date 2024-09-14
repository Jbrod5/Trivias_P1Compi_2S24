
package com.jbrod.servidorprincipal;

import com.jbrod.servidorprincipal.analizadores.Lexer;
import com.jbrod.servidorprincipal.analizadores.Parser;
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
            
            String txt ="<!realizar_solicitud: \"ELIMINAR_COMPONENTE\" > \n" +
"      { \"PARAMETROS_COMPONENTE\":[{ \n" +
"                \"ID\": \"$_soldado_presidente\", \n" +
"                \"TRIVIA\": \"$trivia1\"\n" +
"        } \n" +
"        ]} \n" +
"<fin_solicitud_realizada!> ";
            StringReader sb = new StringReader(txt);
            Lexer lex = new Lexer(sb);
            Parser parser = new Parser(lex);
            
            parser.parse();
            
            System.out.println(parser.resultado);
        } catch (Exception ex) {
            Logger.getLogger(ServidorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
