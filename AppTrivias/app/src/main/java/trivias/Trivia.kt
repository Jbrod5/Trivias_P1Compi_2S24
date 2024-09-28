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



    public var componentes: LinkedList<Componente>
    public var actual:Int = 0

    // Puedes agregar un bloque init si necesitas realizar alguna acción al crear la instancia
    init {
        println("Trivia creada: $nombre, ID: $id_trivia")
        componentes = LinkedList()
    }


    fun avanzar(){
        if(actual < componentes.size ){
            actual ++
            println("Actual: " + actual)
        }
    }

    fun obtenerActual():Componente{
        return componentes.get(actual)
    }


    fun agregarComponente(c:Componente){
        componentes.add(c)
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

    fun mostrarRespuestas(): String{
        var r = ""

        for (elemento in componentes){
            if(elemento != null){
                r += elemento.texto_visible + "\n" + elemento.respuestaUsuario + "\n\n"
            }
        }
        return r
    }

    fun obtenerPuntuacion(us:String, tp:Int):String{
        var p = ""

        var puntuacion = 0
        for(componente in componentes){
            puntuacion += componente.obtenerPuntuacion()
        }
        //Regla de 3 para dimensionar la puntuacion
        puntuacion = puntuacion * 100 / (componentes.size * 100)

        p += "<!realizar_solicitud: \"AGREGAR_PUNTUACION\">\n"
        p += "{  \"PARAMETROS_PUNTUACION\":[{\n"
        p += "    \"TRIVIA\": "     + id_trivia             + "\n"
        p += "    \"USUARIO\": "    + us                    + "\n"
        p += "    \"TIEMPO\": "     + tp.toString()         + "\n"
        p += "    \"PUNTUACION\": " + puntuacion.toString() + "\n"
        p += "}\n"
        p += "]}\n"
        p += "<fin_solicitud_realizada!>"

        return p
    }


}
