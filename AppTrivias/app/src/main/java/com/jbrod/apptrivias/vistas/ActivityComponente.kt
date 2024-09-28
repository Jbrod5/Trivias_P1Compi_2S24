package com.jbrod.apptrivias.vistas

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.utils.widget.MotionLabel
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setMargins
import com.jbrod.apptrivias.AdministradorTrivias
import com.jbrod.apptrivias.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import trivias.AreaTexto
import trivias.CampoTexto
import trivias.Checkbox
import trivias.Combo
import trivias.Fichero
import trivias.Radio
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket

class ActivityComponente : AppCompatActivity() {

    private var checkboxes = ArrayList<android.widget.CheckBox>()
    private var radioButtons = ArrayList<RadioButton>()
    private var contenidoArchivo = ""

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
            //trivia.avanzar()

            val linearInfoComponente = findViewById<LinearLayout>(R.id.linearInfoComponente)
            var button = findViewById<Button>(R.id.btnGuardarRespuesta)

            try{
                var componente = trivia.obtenerActual()
                findViewById<MotionLabel>(R.id.textoVisible).setText(componente.texto_visible)



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
                            trivia.avanzar()
                            componente.respuestaUsuario = editText.text.toString()
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
                            trivia.avanzar()
                            componente.respuestaUsuario = editText.text.toString()
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
                            trivia.avanzar()
                            var seleccionados = ""

                            for(checkbox in checkboxes){
                                if(checkbox.isChecked){
                                    seleccionados += checkbox.text.toString() + ","
                                }
                            }
                            componente.respuestaUsuario = seleccionados
                            val intent = Intent (this, ActivityComponente::class.java)
                            startActivity(intent)
                        }
                    }
                    is Combo      -> {

                        val spinner = Spinner(this)
                        val layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        layoutParams.setMargins(0,16,0,16)
                        spinner.layoutParams = layoutParams

                        var opciones = (componente as trivias.Combo).opciones.split("|")
                        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
                        spinner.adapter = adapter
                        linearInfoComponente.addView(spinner)


                        button.setOnClickListener{
                            trivia.avanzar()
                            componente.respuestaUsuario = spinner.selectedItem.toString()
                            val intent = Intent (this, ActivityComponente::class.java)
                            startActivity(intent)
                        }

                    }
                    is Fichero    -> {

                        var filePickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                            uri?.let { selectedUri ->
                                val contentResolver = this.contentResolver
                                val inputStream = contentResolver.openInputStream(selectedUri)

                                val fileContent = inputStream?.bufferedReader().use { reader ->
                                    reader?.readText() // Lee todo el contenido como String
                                }

                                // Asigna el contenido o un mensaje por defecto si está vacío
                                contenidoArchivo = fileContent ?: "Sin contenido en el archivo"
                                println(contenidoArchivo) // Utiliza el String como desees
                            }
                        }

                        // Crea un nuevo botón
                        val btSeleccionarAr = Button(this).apply {
                            text = "Seleccionar archivo"
                            layoutParams = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                            )

                        }

                        // Establece un listener para el botón
                        btSeleccionarAr.setOnClickListener {
                            filePickerLauncher.launch("*/*")
                        }

                        linearInfoComponente.addView(btSeleccionarAr)

                        button.setOnClickListener{
                            trivia.avanzar()
                            val intent = Intent (this, ActivityComponente::class.java)
                            startActivity(intent)
                            componente.respuestaUsuario = contenidoArchivo
                        }
                    }
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
                            trivia.avanzar()
                            var seleccionados = ""
                            for(rd in radioButtons){
                                if(rd.isChecked){
                                    seleccionados += rd.text.toString()+","
                                }
                            }
                            componente.respuestaUsuario = seleccionados
                            val intent = Intent (this, ActivityComponente::class.java)
                            startActivity(intent)
                        }
                    }

                }
            }catch (e:Exception){
                //Si hay una excepcion, termino la trivia
                button.setText("Enviar respuestas al servidor")

                // Crear un nuevo EditText
                val editText = EditText(this).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    hint = "Escribe tu respuesta" // Texto sugerido
                    maxLines = 500
                    maxWidth = 500
                }

                //mostrar respuetas
                findViewById<MotionLabel>(R.id.textoVisible).setText("Sus respuestas fueron:")

                editText.setText(trivia.mostrarRespuestas())
                linearInfoComponente.addView(editText)

                //enviar las respuestas al servidor
                var pntcn = trivia.obtenerPuntuacion(app.usuario, 100)
                var puerto = 6000
                var ip = app.ip
                var res = ""
                try {
                    GlobalScope.launch (Dispatchers.IO){
                        var clientSocket = Socket(ip, puerto)
                        var inp = DataInputStream(clientSocket.getInputStream())
                        var out = DataOutputStream(clientSocket.getOutputStream())

                        Log.d("ENVIANDO: ", pntcn)

                        out.writeUTF(pntcn)
                        res = inp.readUTF()

                        clientSocket.close()
                    }

                    runOnUiThread {
                        Toast.makeText(this, "Conectado y mensaje recibido: $res", Toast.LENGTH_LONG).show()
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                }

            }


        }
    }

}