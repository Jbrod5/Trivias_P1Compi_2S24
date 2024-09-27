package com.jbrod.apptrivias.vistas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jbrod.apptrivias.AdministradorTrivias
import com.jbrod.apptrivias.R
import com.jbrod.apptrivias.analizadores.Lexer
import com.jbrod.apptrivias.analizadores.Parser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.StringReader
import java.net.Socket

class ConsultarTrivias : AppCompatActivity() {

    private lateinit var ip:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_consultar_trivias)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val intent = intent
        ip = intent.getStringExtra("ip").toString()

        val btnObtenerTrivias = findViewById<Button>(R.id.btnConsultarTrivias)
        btnObtenerTrivias.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                conectarServidor()
            }
        }

    }

    private suspend fun conectarServidor(){
        var puerto = 6000

        try {
            var clientSocket = Socket(ip, puerto)
            var inp = DataInputStream(clientSocket.getInputStream())
            var out = DataOutputStream(clientSocket.getOutputStream())

            var textE = "OBTENER_TRIVIAS"
            Log.d("ENVIANDO: ", textE)

            out.writeUTF(textE)
            var respuesta:String = inp.readUTF()

            //findViewById<TextView>(R.id.txtTrivias).text = respuesta

            clientSocket.close()
            runOnUiThread {
                Toast.makeText(this, "Conectado y mensaje recibido: $respuesta", Toast.LENGTH_LONG).show()
            }
            Log.d("RECIBIDO: ", respuesta)

            //Parsear
            parsearCodigoTrivias(respuesta)


        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    private suspend fun parsearCodigoTrivias(codigo:String){
        var reader = StringReader(codigo)
        var lexer = Lexer(reader)
        val app = application as AdministradorTrivias
        var parser = Parser(lexer, app)

        try {
            parser.parse()
            Log.d("PARSEO", "PARSEO BIENNNNNNN")
        }catch (e:Exception){
            Log.d("PARSEO", "Hubo un error al parsear: " + e.toString())
        }

        withContext(Dispatchers.Main) {
            mostrarTrivias()
        }
    }

    private  fun mostrarTrivias(){
        // En cualquier clase de tu aplicación
        val administradorTrivias = applicationContext as AdministradorTrivias
        var trivias = administradorTrivias.trivias


        for(elemento in trivias){
            //val inflater = LayoutInflater.from(this)
            //val tarjetaTrivia = inflater.inflate(R.layout.tarjeta_trivia, findViewById(R.id.linearComponentes), false)
            //indViewById<TextView>(R.id.txtTrivias)

            println(elemento.getNombre())



            // Obtener el LinearLayout donde se añadirán los componentes
            val linearComponentes = findViewById<LinearLayout>(R.id.linearComponentes)

            // Inflar el layout del card
            val inflater = LayoutInflater.from(this)
            val cardView:View = inflater.inflate(R.layout.tarjeta_trivia, linearComponentes, false)
            // Establecer clickListener para acceder a la trivia
            cardView.setOnClickListener{
                val app = application as AdministradorTrivias
                app.establecerTriviaActual(elemento.getIdTrivia())

                val intent = Intent(this, ActivityComponente::class.java)
                startActivity(intent)
            }

            // Modificar los TextViews si es necesario
            val nombreTrivia = cardView.findViewById<TextView>(R.id.nombreTrivia)
            nombreTrivia.text = elemento.getNombre()

            val temaTrivia = cardView.findViewById<TextView>(R.id.temaTrivia)
            temaTrivia.text = elemento.getTema()

            val usuarioCreador = cardView.findViewById<TextView>(R.id.usuarioCreador)
            usuarioCreador.text = elemento.getUsuarioCreador()

            val tiempoPregunta = cardView.findViewById<TextView>(R.id.tiempoPregunta)
            tiempoPregunta.text = elemento.getTiempoPregunta().toString()

            // Añadir la vista inflada al LinearLayout
            linearComponentes.addView(cardView)
        }
    }

}