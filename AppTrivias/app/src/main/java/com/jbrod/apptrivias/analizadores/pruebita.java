package com.jbrod.apptrivias.analizadores;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;

import com.jbrod.apptrivias.AdministradorTrivias;

import java.util.LinkedList;

import trivias.AreaTexto;
import trivias.CampoTexto;
import trivias.Checkbox;
import trivias.Combo;
import trivias.Componente;
import trivias.Fichero;
import trivias.Radio;
import trivias.Trivia;

public class pruebita {

    public static void d(){

        LinkedList<Componente> componentes = new LinkedList<>();
        //componentes.add()

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
        /*

        Context context

        AdministradorTrivias administradorTrivias = (AdministradorTrivias)getApplicationContext();
        LinkedList<Trivia> trivias = administradorTrivias.getTrivias();

        */


    }


}
