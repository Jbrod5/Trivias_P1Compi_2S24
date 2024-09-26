package com.jbrod.apptrivias.vistas

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jbrod.apptrivias.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.net.Socket

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val connectButton = findViewById<Button>(R.id.btn)
        connectButton.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                //connectToServer()
                conectarServidor()
            }
        }
    }


    private fun conectarServidor(){
        var ip:String = findViewById<EditText>(R.id.ip).getText().toString()
        var puerto = 6000

        try {
            var clientSocket = Socket(ip, puerto)
            var inp = DataInputStream(clientSocket.getInputStream())
            var out = DataOutputStream(clientSocket.getOutputStream())

            var textE = findViewById<EditText>(R.id.code)
            var code:String = textE.getText().toString()
            //val singleLineCode = code.replace("\n", "  ")
            Log.d("ENVIANDO: ", code)

            out.writeUTF(code)
            var respuesta:String = inp.readUTF()

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