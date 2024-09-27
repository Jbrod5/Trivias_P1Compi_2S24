package com.jbrod.apptrivias

import android.app.Application
import trivias.Trivia
import java.util.LinkedList

class AdministradorTrivias : Application(){
    val trivias = LinkedList<Trivia>()
}