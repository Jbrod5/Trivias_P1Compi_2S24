
package com.jbrod.servidorprincipal.trivias;

import java.util.LinkedList;

/**
 *
 * @author jorge
 */
public class Trivia {
    private String id_trivia;
    private String usuario_creador; 
    private String fecha_creacion; 
    
    private LinkedList <Componente> componentes;

    
    
    
    /**
     * Crea una trivia en base a:
     * @param id: String con el identificador que tendra la trivia.
     * @param user: String con el identificador del usuario creador.
     * @param date: String con la fecha de creación de la trivia.
     **/
    public Trivia(String id, String user, String date){
        id_trivia = id; 
        usuario_creador = user; 
        fecha_creacion = date; 
        
        componentes = new LinkedList<>();
    }
    
    
    
    
    
    
    
    
    public String getId_trivia() {
        return id_trivia;
    }

    public void setId_trivia(String id_trivia) {
        this.id_trivia = id_trivia;
    }

    public String getUsuario_creador() {
        return usuario_creador;
    }

    public void setUsuario_creador(String usuario_creador) {
        this.usuario_creador = usuario_creador;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public LinkedList<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(LinkedList<Componente> componentes) {
        this.componentes = componentes;
    }
}
