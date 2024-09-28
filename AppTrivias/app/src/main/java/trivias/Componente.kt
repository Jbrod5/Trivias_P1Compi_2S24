package trivias

abstract class Componente(
    public var id_componente: String,
    public var id_trivia: String,
    public var indice: Int,
    public var texto_visible: String,
    public var respuesta: String
) {

    public var respuestaUsuario:String = ""

    fun obtenerPuntuacion():Int{
        var p = 0;

        if(respuesta.contains("|")){
            var respuestas = respuesta.lowercase().split("|")
            for (r in respuestas){
                if(respuestaUsuario.lowercase().contains(r)){
                    p += 100
                }
            }

            //Regla de 3 para dimensionar la respuesta
            p = p * 100 / (respuestas.size * 100)
        }else{
            if(respuestaUsuario.lowercase().contains(respuesta.lowercase())){
                p = 100
            }
        }

        return p
    }

}
