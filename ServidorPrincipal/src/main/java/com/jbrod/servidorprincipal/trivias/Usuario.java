
package com.jbrod.servidorprincipal.trivias;

import java.util.LinkedList;

/**
 *
 * @author jorge
 */
public class Usuario {
    
    private LinkedList<Trivia> trivias;
    private String id_usuario; 
    private String password;
    
    private String nombre;
    private String institucion; 
    
    
    
    /**
     * Crea un usuario a partir de: 
     * @param id: String con el identificador que tendra el usuario.
     * @param password: String con el password que tendra el usuario.
     **/
    public Usuario(String id, String password, String nombre, String institucion){
        trivias = new LinkedList<>();
        id_usuario = id;
        this.password = password;
        
        this.nombre = nombre;
        this.institucion = institucion; 
    }

    public LinkedList<Trivia> getTrivias() {
        return trivias;
    }

    public void setTrivias(LinkedList<Trivia> trivias) {
        this.trivias = trivias;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public String obtenerCodigoUsuario(){
        String c = "    <USUARIO: " + id_usuario + ">{\n";
        c +=       "        \"PASSWORD\": "     + password      + ",\n";
        c +=       "        \"NOMBRE\": "       + nombre        + ",\n";
        c +=       "        \"INSTITUCION\": "  + institucion   +  "\n";
        c +=       "     }";
        
        return c; 
    }
    

}
