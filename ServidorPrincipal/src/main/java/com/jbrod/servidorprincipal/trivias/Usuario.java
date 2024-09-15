
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

}
