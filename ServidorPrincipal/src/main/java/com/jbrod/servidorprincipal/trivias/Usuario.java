
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
    
    
    /**
     * Crea un usuario a partir de: 
     * @param id: String con el identificador que tendra el usuario.
     * @param password: String con el password que tendra el usuario.
     **/
    public Usuario(String id, String password){
        trivias = new LinkedList<>();
        id_usuario = id; 
        this.password = password;
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
    
    
    
    

}
