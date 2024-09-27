package trivias

import java.util.LinkedList

class Trivia(
    private var id_trivia: String,
    private var nombre: String,
    private var usuario_creador: String,
    private var fecha_creacion: String,
    private var tiempo_pregunta: Int,
    private var tema: String
    //Agregar la lista de componentes
) {



    protected var componentes: LinkedList<Componente>


    // Puedes agregar un bloque init si necesitas realizar alguna acción al crear la instancia
    init {
        println("Trivia creada: $nombre, ID: $id_trivia")
        componentes = LinkedList()
    }








    // Getters
    fun getIdTrivia(): String {
        return id_trivia
    }

    fun getNombre(): String {
        return nombre
    }

    fun getUsuarioCreador(): String {
        return usuario_creador
    }

    fun getFechaCreacion(): String {
        return fecha_creacion
    }

    fun getTiempoPregunta(): Int {
        return tiempo_pregunta
    }

    fun getTema(): String {
        return tema
    }

}
