package trivias

abstract class Componente(
    public var id_componente: String,
    public var id_trivia: String,
    public var indice: Int,
    public var texto_visible: String,
    public var respuesta: String
) {

    public var respuestaUsuario:String = ""

}
