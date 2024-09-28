package com.jbrod.apptrivias.vistas

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jbrod.apptrivias.AdministradorTrivias
import com.jbrod.apptrivias.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.DataInputStream
import java.io.DataOutputStream
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
        (applicationContext as AdministradorTrivias).ip = ip
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


            //Establecer el usuario para toda la aplicacion
            var app = (applicationContext as AdministradorTrivias)
            Log.d("RECIBIDO: ", respuesta)

            val regex = "([a-zA-Z]|_|-|\\$)([a-zA-Z0-9]|_|-|\\$)*".toRegex()


            if (respuesta.matches(regex)) {
                println("El string cumple con la expresión regular.")

                runOnUiThread {
                    Toast.makeText(this, "Sesion iniciada como: $respuesta", Toast.LENGTH_LONG).show()
                }

                val intent = Intent(this, ConsultarTrivias::class.java)
                intent.putExtra("ip", ip)
                startActivity(intent)
                app.usuario = respuesta
            } else {
                println("El string NO cumple con la expresión regular.")
                runOnUiThread {
                    Toast.makeText(this, "$respuesta no es un usuario valido", Toast.LENGTH_LONG).show()
                }
            }



        }catch (e: Exception){
            e.printStackTrace()
            runOnUiThread {
                Toast.makeText(this, "Puede que la ip sea incorrecta.", Toast.LENGTH_LONG).show()
            }
        }
    }

}