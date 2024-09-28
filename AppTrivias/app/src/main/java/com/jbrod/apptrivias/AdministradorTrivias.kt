package com.jbrod.apptrivias

import android.app.Application
import trivias.Trivia
import java.util.LinkedList

class AdministradorTrivias : Application(){
    var trivias = LinkedList<Trivia>()
    var triviaActual: Trivia? = null
    var usuario = ""
    var ip = ""

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

    fun reiniciarTriviaActual(){
        if(triviaActual != null){
            this.triviaActual!!.actual = 0
        }
    }

    public fun reiniciarTrivias(){
        this.trivias = LinkedList<Trivia>()
        triviaActual = null
    }

}