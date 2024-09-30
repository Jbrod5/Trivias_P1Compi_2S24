
package com.jbrod.servidorprincipal.trivias;

import com.jbrod.servidorprincipal.trivias.Componente;
import com.jbrod.servidorprincipal.trivias.Puntuacion;
import com.jbrod.servidorprincipal.trivias.Trivia;
import com.jbrod.servidorprincipal.trivias.Usuario;
import java.util.LinkedList;

/**
 *
 * @author jorge
 */
public class Motor {

    private LinkedList<Usuario> usuarios; 
    private LinkedList<Trivia> trivias; 
    private LinkedList<Puntuacion> puntuaciones; 
    
    
    public Motor(){
        usuarios = new LinkedList<>();
        trivias = new LinkedList<>(); 
        puntuaciones = new LinkedList<>(); 
    }
    
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/
    /* - - - - - - - - - - - - - - - USUARIOS  - - - - - - - - - - - - - - -*/
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/
    
    public String agregarUsuario(Usuario usuario){
        if(usuario == null){
            return "No se pudo agregar el usuario porque es nulo.";
        }else{
        
            String nm = usuario.getId_usuario();
            for (Usuario us : usuarios) {
                if(nm.equals(us.getId_usuario())){
                    return "El usuario ya existia."; 
                }
            }

            usuarios.add(usuario);
            return "Usuario " + usuario.getId_usuario() + " agregado correctamente.";
        }        
    }
    
    public String modificarUsuario(String id, String nuevoId, String nuevoPassword){
        for (Usuario usuario : usuarios) {
            if(id.equals(usuario.getId_usuario())){
                usuario.setId_usuario(nuevoId);
                usuario.setPassword(nuevoPassword);
                return "Usuario modificado correctamente.";
            }
        }
        return "No se encontro el usuario indicado para modificar.";
    }
    
    public String eliminarUsuario(String id){
        for (Usuario usuario : usuarios) {
            if(id.equals(usuario.getId_usuario())){
                usuarios.remove(usuario);
                return "Usuario eliminado correctamente";
            }
        }
        return "No se encontro el usuario indicado para eliminar";
    }
    
    public String loginUsuario(String id, String password){
        for (Usuario usuario : usuarios) {
            if(id.equals(usuario.getId_usuario())){
                if(password.equals(usuario.getPassword())){
                    return "Inicio de sesion aprobado.";
                }else{
                    return "Contrasena incorrecta.";
                }
            }
        }
        return "No se encontro el usuario especificado";
    }
    
    
    
    
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/
    /* - - - - - - - - - - - - - - - TRIVIAS - - - - - - - - - - - - - - - -*/
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -*/
    
    
    public String agregarTrivia(Trivia trivia){
        if(trivia == null){
            return "No se pudo agregar la trivia porque es nulo.";
        }else{
            String nm = trivia.getId_trivia();
            for (Trivia tv : trivias) {
                if(nm.equals(tv.getId_trivia()) ){
                    return "La trivia ya existia."; 
                }
            }
            trivias.add(trivia);
            return "Trivia " + trivia.getId_trivia()+ " agregada correctamente.";
        } 
    }
    
    public String eliminarTrivia(String id){
        for (Trivia tv : trivias) {
            if(id.equals(tv.getId_trivia())){
                trivias.remove(tv);
                return "Trivia eliminada correctamente";
            }
        }
        return "No se encontro la trivia indicada para eliminar";
    }
    
    public String modificarParametrosTrivia(String idTrivia, int tiempoPregunta, String nombre, String tema){   
        for (Trivia trivia : trivias) {
            if(idTrivia.equals(trivia.getId_trivia())){
                trivia.setTiempoPregunta(tiempoPregunta);
                trivia.setNombre(nombre);
                trivia.setTema(tema);
                return "Trivia " + idTrivia + " modificada correctamente.";
            }
        }
        return "No se encontro la trivia " + idTrivia + " para modificar.";
    }
    
    
    public String eliminarComponenteTrivia(String idTrivia, String idComponente){
        for (Trivia t : trivias) {
            if(idTrivia.equals(t.getId_trivia())){
                return t.eliminarComponente(idComponente);
            }
        }
        return "No se pudo eliminar el componente poque no se encontro la trivia con id " + idTrivia + ".";
    }
    
    public String agregarComponenteTrivia(String idTrivia, Componente c){
        for (Trivia t : trivias) {
            if(idTrivia.equals(t.getId_trivia())){
                return t.agregarComponente(c);
            }
        }
        return "No se pudo agregar el componente porque no se encontro la trivia con id " + idTrivia + ".";
    }
    
    public String modificarComponenteTrivia(String idTrivia, Componente c){
        for (Trivia t : trivias) {
            if(idTrivia.equals(t.getId_trivia())){
                return t.modificarComponente(c);
            }
        }
        return "No se pudo modificar el componente porque no se encontro la trivia con id " + idTrivia + ".";
    }
    
    public String exportarTrivias(){
        String exp = "";
        for (Trivia trivia : trivias) {
            if(trivia != null){
                exp += trivia.exportarTrivia() + "\n";
            }
        }
        exp += "\n";
        return exp;
    }

    public LinkedList<Puntuacion> getPuntuaciones() {
        return puntuaciones;
    }
    
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /* - - - - - - - - - - - - - - - PUNTUACIONES - - - - - - - - - - - - - - - -*/
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    
    public void agregarPuntuacion(Puntuacion p){
        puntuaciones.add(p);
        System.out.println("Puntuacion agregada: " + p.obtenerPuntuacion());
    }
    
    public String obtenerUsuarios(){
        String u = "usuarios(\n";
        
        for(Usuario usuario : usuarios){
            if(usuario != null){
                u += usuario.obtenerCodigoUsuario() + "\n";
            }
        }
        u+= ")\n";
        return u;
    }
    
    public String obtenerPuntuaciones(){
        String p = "puntuaciones(";
        for(Puntuacion pn : puntuaciones){
            if(pn != null){
                p += pn.obtenerPuntuacion() + "\n";
            } 
        }
        
        p += ")\n";
        return p;
    }
    
    public String obtenerCodigoPuntuaciones(){
        String p = "puntuaciones(\n";
        for(Puntuacion pn : puntuaciones){
            if(pn != null){
                p += pn.obtenerCodigoPuntuacion() + "\n";
            } 
        }
        p += ")\n";
        return p;
    }
    
}
