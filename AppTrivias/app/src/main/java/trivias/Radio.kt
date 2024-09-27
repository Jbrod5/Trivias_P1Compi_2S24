package trivias

class Radio(
    id_componente: String,
    id_trivia: String,
    indice: Int,
    texto_visible: String,
    respuesta: String,
    public var opciones:String
) : Componente(id_componente, id_trivia, indice, texto_visible, respuesta) {
}