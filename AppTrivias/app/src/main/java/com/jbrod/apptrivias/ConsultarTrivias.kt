package com.jbrod.apptrivias

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.DataInputStream
import java.io.DataOutputStream
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

    private fun conectarServidor(){
        var puerto = 6000

        try {
            var clientSocket = Socket(ip, puerto)
            var inp = DataInputStream(clientSocket.getInputStream())
            var out = DataOutputStream(clientSocket.getOutputStream())

            var textE = "OBTENER_TRIVIAS"
            Log.d("ENVIANDO: ", textE)

            out.writeUTF(textE)
            var respuesta:String = inp.readUTF()
            findViewById<TextView>(R.id.txtTrivias).text = respuesta

            clientSocket.close()
            runOnUiThread {
                Toast.makeText(this, "Conectado y mensaje recibido: $respuesta", Toast.LENGTH_LONG).show()
            }
            Log.d("RECIBIDO: ", respuesta)


        }catch (e: Exception){
            e.printStackTrace()
        }
    }

}