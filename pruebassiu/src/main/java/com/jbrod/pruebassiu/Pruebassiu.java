/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.jbrod.pruebassiu;

import com.jbrod.apptrivias.analizadores.Lexer;
import com.jbrod.apptrivias.analizadores.Parser;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class Pruebassiu {

    public static void main(String[] args) {
        try {
            System.out.println("Hello World!");
            String entrada = "                trivia(\n" +
"    \"ID_TRIVIA\" : $trivia1\n" +
"    \"NOMBRE\"    : Cultura de Guatemala\n" +
"    \"CREADOR\"   : Falta agregar usuario\n" +
"    \"FECHA\"     : Falta agregar fecha\n" +
"    \"TIEMPO\"    : 45\n" +
"    \"TEMA\"      : cultura\n" +
"\n" +
"    <CAMPO_TEXTO: $_text_nombre_autor>{ \n" +
"        \"TRIVIA\": $trivia1,      \n" +
"        \"INDICE\": 2,         \n" +
"        \"TEXTO_VISIBLE\": Nombre del autor de la letra del Himno de Guatemala ,  \n" +
"        \"RESPUESTA\": Jose Joaquin Palma       \n" +
"    }\n" +
"    <AREA_TEXTO: $_text_nombre_autores>{\n" +
"        \"TRIVIA\" : $trivia1,     \n" +
"        \"INDICE\" : 2,        \n" +
"        \"TEXTO_VISIBLE\" : Nombre del autor de la letra del Himno de Guatemala , \n" +
"        \"RESPUESTA\" :Jose Joaquin Palma,     \n" +
"        \"FILAS\" : 3,         \n" +
"        \"COLUMNAS\" : 10       \n" +
"    }\n" +
"    <CHECK-BOX: $_soldado_presidente>{   \n" +
"        \"TRIVIA\" : $trivia1,     \n" +
"        \"INDICE\" : 4,        \n" +
"        \"TEXTO_VISIBLE\" : A quie se le conoce como el soldado del pueblo? , \n" +
"        \"RESPUESTA\" :Jacobo Arbenz,     \n" +
"        \"OPCIONES\" : Jacobo Arbenz|Jose Arebalo|Jorge Ubico|Otro       \n" +
"    }\n" +
"    <RADIO: $_soldado_presidentes>{\n" +
"        \"TRIVIA\" : $trivia1,     \n" +
"        \"INDICE\" : 5,        \n" +
"        \"TEXTO_VISIBLE\" : A quie se le conoce como el soldado del pueblo? , \n" +
"        \"RESPUESTA\" :Jacobo Arbenz,     \n" +
"        \"OPCIONES\" : Jacobo Arbenz|Jose Arebalo|Jorge Ubico|Otro       \n" +
"    }\n" +
"    <FICHERO: $_soldado_presidentez>{\n" +
"        \"TRIVIA\" : $trivia1,     \n" +
"        \"INDICE\" : 5,        \n" +
"        \"TEXTO_VISIBLE\" : A quie se le conoce como el soldado del pueblo?   \n" +
"     }\n" +
"    <COMBO: $_soldado_presidentex>{\n" +
"        \"TRIVIA\" : $trivia1,     \n" +
"        \"INDICE\" : 5,        \n" +
"        \"TEXTO_VISIBLE\" : A quie se le conoce como el soldado del pueblo? , \n" +
"        \"RESPUESTA\" :Jacobo Arbenz,     \n" +
"        \"OPCIONES\" : Jacobo Arbenz|Jose Arebalo|Jorge Ubico|Otro       \n" +
"    }\n" +
")";
            
            StringReader reader = new StringReader(entrada);
            Lexer lexer = new Lexer(reader);
            Parser parser = new Parser(lexer);
            parser.parse();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
                
    }
    
    
}
