
package com.jbrod.servidorprincipal.Trivias;

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
        return    "<CAMPO_TEXTO: "           + id_componente + ">{\n"
                + "    \"TRIVIA\" : "        + id_trivia + ",     \n"
                + "    \"CLASE\" : "         + "CHECKBOX,         \n"
                + "    \"INDICE\" : "        + indice + ",        \n"
                + "    \"TEXTO_VISIBLE\" : " + texto_visible + ", \n"
                + "    \"RESPUESTA\" :"      + respuesta + ",     \n"
                + "    \"OPCIONES\" : "      + opciones + "       \n"
                + "}";
    }

    


    
    
    
    
    
    public String getOpciones() {
        return opciones;
    }

    public void setOpciones(String opciones) {
        this.opciones = opciones;
    }
}
