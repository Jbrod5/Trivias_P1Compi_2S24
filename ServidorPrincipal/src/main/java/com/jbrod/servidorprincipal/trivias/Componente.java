
package com.jbrod.servidorprincipal.trivias;

/**
 *
 * @author jorge
 */
public abstract class Componente {

    protected String id_componente; 
    protected String id_trivia; 
    protected int indice; 
    protected String texto_visible; 
    protected String respuesta; 

    public Componente(String id_componente, String id_trivia, int indice, String texto_visible, String respuesta) {
        this.id_componente = id_componente;
        this.id_trivia = id_trivia;
        this.indice = indice;
        this.texto_visible = texto_visible;
        this.respuesta = respuesta;
    }
    
    
    

    
    
    
    
    public abstract String obtenerCodigo();
    
    public String getId_componente() {
        return id_componente;
    }

    public void setId_componente(String id_componente) {
        this.id_componente = id_componente;
    }

    public String getId_trivia() {
        return id_trivia;
    }

    public void setId_trivia(String id_trivia) {
        this.id_trivia = id_trivia;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getTexto_visible() {
        return texto_visible;
    }

    public void setTexto_visible(String texto_visible) {
        this.texto_visible = texto_visible;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
}
