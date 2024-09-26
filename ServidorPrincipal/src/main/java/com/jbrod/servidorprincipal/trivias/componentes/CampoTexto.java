
package com.jbrod.servidorprincipal.trivias.componentes;

import com.jbrod.servidorprincipal.trivias.Componente;

/**
 *
 * @author jorge
 */
public class CampoTexto extends Componente{

    public CampoTexto(String id_componente, String id_trivia, int indice, String texto_visible, String respuesta) {
        super(id_componente, id_trivia, indice, texto_visible, respuesta);
    }

    @Override
    public String obtenerCodigo() {
        return    "    <CAMPO_TEXTO: "          + id_componente + ">{ \n"
                + "        \"TRIVIA\": "        + id_trivia + ",      \n"
                + "        \"CLASE\" : "        + "CAMPO_TEXTO,       \n"
                + "        \"INDICE\": "        + indice + ",         \n"
                + "        \"TEXTO_VISIBLE\": " + texto_visible + ",  \n"
                + "        \"RESPUESTA\": "     + respuesta + "       \n"
                + "    }";
                
    }

    
    
    

}
