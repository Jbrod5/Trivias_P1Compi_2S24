package trivias

class AreaTexto(
    id_componente: String,
    id_trivia: String,
    indice: Int,
    texto_visible: String,
    respuesta: String,
    public var filas:Int,
    public var columnas:Int
) : Componente(id_componente, id_trivia, indice, texto_visible, respuesta) {



}