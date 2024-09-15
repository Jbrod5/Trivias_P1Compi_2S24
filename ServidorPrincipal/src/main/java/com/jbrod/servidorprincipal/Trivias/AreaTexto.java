
package com.jbrod.servidorprincipal.Trivias;

/**
 *
 * @author jorge
 */
public class AreaTexto extends Componente{

    private int filas, columnas; 

    public AreaTexto(int filas, int columnas, String id_componente, String id_trivia, int indice, String texto_visible, String respuesta) {
        super(id_componente, id_trivia, indice, texto_visible, respuesta);
        this.filas = filas;
        this.columnas = columnas;
    }

    @Override
    public String obtenerCodigo() {
        return    "<CAMPO_TEXTO: "           + id_componente + ">{\n"
                + "    \"TRIVIA\" : "        + id_trivia + ",     \n"
                + "    \"CLASE\" : "         + "AREA_TEXTO,       \n"
                + "    \"INDICE\" : "        + indice + ",        \n"
                + "    \"TEXTO_VISIBLE\" : " + texto_visible + ", \n"
                + "    \"RESPUESTA\" :"      + respuesta + ",     \n"
                + "    \"FILAS\" : "         + filas + ",         \n"
                + "    \"COLUMNAS\" : "      + columnas + "       \n"
                + "}";
    }

    
    
    
    
    
    
    
    
    
    
    
    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }
    
    
    
}
