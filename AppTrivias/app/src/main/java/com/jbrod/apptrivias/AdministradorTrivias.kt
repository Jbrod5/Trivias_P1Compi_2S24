package com.jbrod.apptrivias

import android.app.Application
import trivias.Trivia
import java.util.LinkedList

class AdministradorTrivias : Application(){
    val trivias = LinkedList<Trivia>()
    var triviaActual: Trivia? = null

    fun establecerTriviaActual(id:String){
        for (trivia in trivias){
            if(trivia.getIdTrivia().equals(id)){
                triviaActual = trivia
                break
            }
        }
    }

    fun establecerRespuestaActual(respuesta:String){
        triviaActual?.obtenerActual()?.respuesta ?: respuesta
    }

}