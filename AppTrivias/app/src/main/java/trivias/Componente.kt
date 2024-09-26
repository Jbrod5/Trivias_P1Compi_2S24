package trivias

abstract class Componente(
    protected var id_componente: String,
    protected var id_trivia: String,
    protected var indice: Int,
    protected var texto_visible: String,
    protected var respuesta: String
) {







    // Getters
    fun getIdComponente(): String {
        return id_componente
    }

    fun getIdTrivia(): String {
        return id_trivia
    }

    fun getIndice(): Int {
        return indice
    }

    fun getTextoVisible(): String {
        return texto_visible
    }

    fun getRespuesta(): String {
        return respuesta
    }






    // Setters
    fun setIdComponente(id: String) {
        id_componente = id
    }

    fun setIdTrivia(id: String) {
        id_trivia = id
    }

    fun setIndice(indice: Int) {
        this.indice = indice
    }

    fun setTextoVisible(texto: String) {
        texto_visible = texto
    }

    fun setRespuesta(respuesta: String) {
        this.respuesta = respuesta
    }
}
