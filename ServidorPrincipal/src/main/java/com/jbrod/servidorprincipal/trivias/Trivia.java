
package com.jbrod.servidorprincipal.trivias;

import java.util.LinkedList;

/**
 *
 * @author jorge
 */
public class Trivia {
    private String id_trivia;
    private String nombre;
    private String usuario_creador; 
    private String fecha_creacion; 
    private int tiempoPregunta; 
    private String tema; 
    
    private LinkedList <Componente> componentes;

    
    
    
    /**
     * Crea una trivia en base a:
     * @param id: String con el identificador que tendra la trivia.
     * @param nombre: String con el nombre de la trivia.
     * @param tema: String con el tema de la trivia.
     * @param user: String con el identificador del usuario creador.
     * @param date: String con la fecha de creación de la trivia.
     * @param tiempo: int con el tiempo de cada pregunta.
     **/
    public Trivia(String id, String nombre, String tema, String user, String date, int tiempo){
        this.tema = tema;
        this.nombre = nombre;
        id_trivia = id; 
        usuario_creador = user; 
        fecha_creacion = date; 
        this.tiempoPregunta = tiempo;
        
        componentes = new LinkedList<>();
    }
    
    
    
    public String eliminarComponente(String id){
        for (Componente componente : componentes) {
            if(id.equals(componente.getId_componente())){
                componentes.remove(componente);
                return "El componente " + id + " ha sido eliminado correctamente.";
            }
        }
        return "No se encontro el componente " + id + " dentro de " + this.id_trivia + " para eliminar";
    }
    
    public String agregarComponente(Componente componente){
        if(componente != null){
            String nm = componente.getId_componente();
            for (Componente c : componentes) {
                if(nm.equals(c.getId_componente())){
                    return "No se puede agregar el componente " + nm + " en " + id_trivia + " porque ya existe un componente con ese id.";
                }
            }
            componentes.add(componente);
            return "El componente con id " + nm + " se ha agregado correctamente a " + id_trivia + ".";
        }else{
            return "No se puede agregar el componente en " + id_trivia + " porque es nulo.";
        }
    }
    
    public String modificarComponente(Componente componente){
        if(componente != null){
            String nm = componente.getId_componente();
            for (Componente c : componentes) {
                if(nm.equals(c.getId_componente())){
                    
                    componentes.add(componentes.indexOf(c), componente);
                    componentes.remove(c);
                    return "El componente " + nm + " se modifico correctamente en " + id_trivia + ".";
                }
            }
            return "No se pudo actualizar el componente " + nm + " en " + id_trivia + " porque no existe ningun componente con ese id.";
        }else{
            return "No se puede modificar el componente en " + id_trivia + " porque es nulo.";
        }        
    }
    
    
    public String exportarTrivia(){
        String trivia = "trivia(\n";
            trivia += "    \"ID_TRIVIA\" : " + id_trivia       + "\n";
            trivia += "    \"NOMBRE\"    : " + nombre          + "\n";
            trivia += "    \"CREADOR\"   : " + usuario_creador + "\n";
            trivia += "    \"FECHA\"     : " + fecha_creacion  + "\n";
            trivia += "    \"TIEMPO\"    : " + tiempoPregunta  + "\n";
            trivia += "    \"TEMA\"      : " + tema            + "\n";
            trivia += "\n";
        
        for (Componente componente : componentes) {
            if(componente != null){
                trivia += componente.obtenerCodigo() + "\n";
            }
        }
        
        trivia += ")";
        return trivia;
        
    }
    
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }    
    
    public int getTiempoPregunta() {
        return tiempoPregunta;
    }

    public void setTiempoPregunta(int tiempoPregunta) {
        this.tiempoPregunta = tiempoPregunta;
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
