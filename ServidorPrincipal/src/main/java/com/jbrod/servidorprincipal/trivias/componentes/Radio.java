
package com.jbrod.servidorprincipal.trivias.componentes;

import com.jbrod.servidorprincipal.trivias.Componente;

/**
 *
 * @author jorge
 */
public class Radio extends Componente{
    
    private String opciones; 

    public Radio(String opciones, String id_componente, String id_trivia, int indice, String texto_visible, String respuesta) {
        super(id_componente, id_trivia, indice, texto_visible, respuesta);
        this.opciones = opciones;
    }

    @Override
    public String obtenerCodigo() {
        return    "    <RADIO: "           + id_componente + ">{\n"
                + "        \"TRIVIA\" : "        + id_trivia + ",     \n"
                //+ "        \"CLASE\" : "         + "RADIO,            \n"
                + "        \"INDICE\" : "        + indice + ",        \n"
                + "        \"TEXTO_VISIBLE\" : " + texto_visible.replace(":", "") + ", \n"
                + "        \"RESPUESTA\" :"      + respuesta.replace(":", "") + ",     \n"
                + "        \"OPCIONES\" : "      + opciones.replace(":", "") + "       \n"
                + "    }";
    }

    


    
    
    
    
    
    public String getOpciones() {
        return opciones;
    }

    public void setOpciones(String opciones) {
        this.opciones = opciones;
    }
}
