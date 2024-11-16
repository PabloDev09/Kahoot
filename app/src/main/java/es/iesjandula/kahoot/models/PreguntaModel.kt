package es.iesjandula.kahoot.models

data class PreguntaModel(var pregunta: String = "",
                         var respuestaPrimera: String = "",
                         var respuestaSegunda: String = "",
                         var respuestaTercera: String = "",
                         var respuestaCuarta: String = "",
                         var referenciaRespuestaCorrecta: Int = 0)
{
    companion object
    {
    }
}

