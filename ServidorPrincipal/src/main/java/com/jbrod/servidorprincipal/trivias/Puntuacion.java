
package com.jbrod.servidorprincipal.trivias;

/**
 *
 * @author jorge
 */
public class Puntuacion {
    private String id_trivia; 
    private String id_usuario; 
    private int tiempo_total; 
    private int puntuacion; 

    
    
    public Puntuacion(String id_trivia, String id_usuario, int tiempo_total, int puntuacion) {
        this.id_trivia = id_trivia.replace("\n", "");
        this.id_usuario = id_usuario.replace("\n", "");
        this.tiempo_total = tiempo_total;
        this.puntuacion = puntuacion;
    }

    
    
    
    public String getId_trivia() {
        return id_trivia;
    }

    public void setId_trivia(String id_trivia) {
        this.id_trivia = id_trivia;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getTiempo_total() {
        return tiempo_total;
    }

    public void setTiempo_total(int tiempo_total) {
        this.tiempo_total = tiempo_total;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    
    public String obtenerPuntuacion(){
        return "Usuario: " + id_usuario + " | Trivia: " + id_trivia + " | Tiempo total: " + tiempo_total + " | Puntuacion: " + puntuacion;
    }
    
    public String obtenerCodigoPuntuacion(){
        String p = "    <PUNTUACION_TRIVIA: " + id_trivia + ">{\n";
        p +=       "        \"USUARIO\": " + id_usuario   + ",\n";
        p +=       "        \"TIEMPO\": "  + tiempo_total + ",\n";
        p +=       "        \"PUNTEO\": "  + puntuacion   +"\n";
        p +=       "    }";
        return p;
    }
    
    
}
