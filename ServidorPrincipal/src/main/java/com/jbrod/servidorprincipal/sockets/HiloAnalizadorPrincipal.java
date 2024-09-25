
package com.jbrod.servidorprincipal.sockets;

import com.jbrod.servidorprincipal.analizadores.Lexer;
import com.jbrod.servidorprincipal.analizadores.Parser;
import com.jbrod.servidorprincipal.trivias.Motor;
import com.jbrod.servidorprincipal.ui.UI;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author jorge
 */
public class HiloAnalizadorPrincipal extends Thread {
    
    private Motor motor; 
    private UI ui; 
    
    private ServerSocket serverSocket; 
    private Socket clientSocket; 
    private DataInputStream in; 
    private DataOutputStream out; 
    
    final int PUERTO = 6000;
    
    
    public HiloAnalizadorPrincipal(Motor motor, UI ui){
        this.ui = ui;
        this.motor = motor; 
    }
    
    public void run(){
        try {
            serverSocket = new ServerSocket(PUERTO);
            
            while(true){
                clientSocket = serverSocket.accept();
                in = new DataInputStream(clientSocket.getInputStream());
                out = new DataOutputStream(clientSocket.getOutputStream());
                
                String entrada = in.readUTF();
                ui.addLog(" - - - - - - - - - - - - - - - - Hilo analizador principal: - - - - - - - - - - - - - - - - - - - -");
                ui.addLog("Entrada:\n" + entrada + "\n");
                
                /* Analizando la entrada */
                StringReader sb = new StringReader(entrada);
                Lexer lex = new Lexer(sb);
                Parser parser = new Parser(lex, motor);
                parser.parse();
                
                
                
                out.writeUTF(parser.resultado);
                ui.addLog("Resultado: \n" + parser.resultado);
                serverSocket.close();
                ui.addLog("Hilo analizador principal: Cliente desconectado");
               
            }
            
        } catch (Exception e) {
        }
    }
    
    
}
