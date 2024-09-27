package com.jbrod.apptrivias.analizadores;

import trivias.AreaTexto;
import trivias.CampoTexto;
import trivias.Checkbox;
import trivias.Combo;
import trivias.Fichero;
import trivias.Radio;

public class pruebita {

    public void d(){
        CampoTexto campoTexto = new CampoTexto(
                "id-componente",
                "id-trivia",
                1,
                "texto visible",
                "respuestað"
        );

        AreaTexto areaTexto = new AreaTexto(
                "id-componente",
                "id-trivia",
                1,
                "textovisible",
                "respuesta",
                3,
                3
                );
        Checkbox checkbox = new Checkbox(
                "id-componente",
                "id-trivia",
                1,
                "texto-visible",
                "RESPUESTA",
                "OPCIONES"
        );

        Radio radio = new Radio(
                "",
                "",
                1,
                "",
                "",
                ""
                );

        Fichero fichero = new Fichero(
                "",
                "",
                1,
                "dsajl",
                "sjkdlfañ"
        );

        Combo combo = new Combo(
                "",
                "",
                1,
                "",
                "",
                ""
        );
    }
}
