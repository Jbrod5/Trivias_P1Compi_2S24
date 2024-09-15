
package com.jbrod.servidorprincipal.Trivias;

/**
 *
 * @author jorge
 */
public class Fichero extends Componente {

    public Fichero(String id_componente, String id_trivia, int indice, String texto_visible) {
        super(id_componente, id_trivia, indice, texto_visible, "");
    }

    @Override
    public String obtenerCodigo() {
        return    "<CAMPO_TEXTO: "           + id_componente + ">{\n"
                + "    \"TRIVIA\" : "        + id_trivia + ",     \n"
                + "    \"CLASE\" : "         + "FICHERO,          \n"
                + "    \"INDICE\" : "        + indice + ",        \n"
                + "    \"TEXTO_VISIBLE\" : " + texto_visible + "  \n"
                + "}";
    }

    
    
    
}
