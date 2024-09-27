package com.jbrod.apptrivias.vistas

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.utils.widget.MotionLabel
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jbrod.apptrivias.AdministradorTrivias
import com.jbrod.apptrivias.R
import trivias.AreaTexto
import trivias.CampoTexto
import trivias.Checkbox
import trivias.Combo
import trivias.Fichero
import trivias.Radio

class ActivityComponente : AppCompatActivity() {

    private var checkboxes = ArrayList<android.widget.CheckBox>()
    private var radioButtons = ArrayList<RadioButton>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_componente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val app = application as AdministradorTrivias
        var trivia = app.triviaActual
        if (trivia != null) {
            findViewById<TextView>(R.id.nombreTrivia).text = "Trivia: " + trivia.getNombre()
            trivia.avanzar()

            var componente = trivia.obtenerActual()
            findViewById<MotionLabel>(R.id.textoVisible).setText(componente.texto_visible)
            val linearInfoComponente = findViewById<LinearLayout>(R.id.linearInfoComponente)

            var button = findViewById<Button>(R.id.btnGuardarRespuesta)

            //val inflater = LayoutInflater.from(this)

            when(componente){

                is AreaTexto  -> {
                    {
                        /*val textView = TextView(this).apply {
                            layoutParams = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                            )
                            hint = "Escribe tu respuesta"
                            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
                            maxLines = Integer.MAX_VALUE // Permite múltiples líneas
                            gravity = Gravity.TOP // Alinea el texto desde la parte superior
                        }

                        linearInfoComponente.addView(textView)

                        button.setOnClickListener {
                            componente.respuesta = textView.text.toString()
                            val intent = Intent(this, ActivityComponente::class.java)
                            startActivity(intent)
                        }*/}
                        // Crear un nuevo EditText
                        val editText = EditText(this).apply {
                            layoutParams = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                            )
                            hint = "Escribe tu respuesta" // Texto sugerido
                            maxLines = (componente as AreaTexto).filas
                            maxWidth = (componente as AreaTexto).columnas
                        }

                        linearInfoComponente.addView(editText)

                        button.setOnClickListener{
                            componente.respuesta = editText.text.toString()
                            val intent = Intent (this, ActivityComponente::class.java)
                            startActivity(intent)
                        }


                    }
                is CampoTexto -> {

                        // Crear un nuevo EditText
                        val editText = EditText(this).apply {
                            layoutParams = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                            )
                            hint = "Escribe tu respuesta" // Texto sugerido
                        }

                        linearInfoComponente.addView(editText)

                        button.setOnClickListener{
                            componente.respuesta = editText.text.toString()
                            val intent = Intent (this, ActivityComponente::class.java)
                            startActivity(intent)
                        }

                    }
                is trivias.Checkbox   -> {
                    var opciones = (componente as trivias.Checkbox).opciones.split("|")
                    var contador = 1
                    for(opcion in opciones){
                        val checkBox = android.widget.CheckBox(this)
                        checkBox.id = contador
                        checkBox.text = opcion
                        checkboxes.add(checkBox)
                        linearInfoComponente.addView(checkBox)
                    }

                    button.setOnClickListener{
                        var seleccionados = ""

                        for(checkbox in checkboxes){
                            if(checkbox.isChecked){
                                seleccionados += checkbox.text.toString() + ","
                            }
                        }
                        componente.respuesta = seleccionados
                        val intent = Intent (this, ActivityComponente::class.java)
                        startActivity(intent)
                    }
                }
                is Combo      -> {}
                is Fichero    -> {}
                is Radio      -> {
                    var opciones = (componente as trivias.Radio).opciones.split("|")
                    var contador = 1
                    for(opcion in opciones){
                        val radiobtn = RadioButton(this)
                        radiobtn.id = contador
                        radiobtn.text = opcion
                        radioButtons.add(radiobtn)
                        linearInfoComponente.addView(radiobtn)
                    }

                    button.setOnClickListener{
                        var seleccionados = ""
                        for(rd in radioButtons){
                            if(rd.isChecked){
                                seleccionados += rd.text.toString()+","
                            }
                        }
                        componente.respuesta = seleccionados
                        val intent = Intent (this, ActivityComponente::class.java)
                        startActivity(intent)
                    }
                }

            }

        }
    }
}