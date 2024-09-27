package trivias

import android.app.Application
import java.util.LinkedList

class AdministradorTrivias : Application(){
    val trivias = LinkedList<Trivia>()
}