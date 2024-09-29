package com.jbrod.servidorprincipal;

import com.jbrod.servidorprincipal.analizadores.Lexer;
import com.jbrod.servidorprincipal.analizadores.Parser;
import com.jbrod.servidorprincipal.analizadores.carga.trivias.LexerTrivias;
import com.jbrod.servidorprincipal.analizadores.carga.trivias.ParserTrivias;
import com.jbrod.servidorprincipal.sockets.HiloAnalizadorPrincipal;
import com.jbrod.servidorprincipal.trivias.Motor;
import com.jbrod.servidorprincipal.trivias.Trivia;
import com.jbrod.servidorprincipal.ui.UI;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class ServidorPrincipal {

    public static void main(String[] args) {

        Motor motor = new Motor();
        UI ui = new UI();

        ServerSocket serverSocket;
        Socket clientSocket;
        DataInputStream in;
        DataOutputStream out = null;

        final int PUERTO = 6000;

        ui.setVisible(true);
        //HiloAnalizadorPrincipal hiloAnalizadorPrincipal = new HiloAnalizadorPrincipal(motor, ui);

        Parser parser = null;
        Lexer lex;
        int contador = 0;
        while (true) {
            try {

                serverSocket = new ServerSocket(PUERTO);
                contador++;
                System.out.println("Contador: " + contador);
                clientSocket = serverSocket.accept();
                in = new DataInputStream(clientSocket.getInputStream());
                out = new DataOutputStream(clientSocket.getOutputStream());

                String entrada = in.readUTF();
                
                
                //
                if(entrada.startsWith("CARGAR_TRIVIAS")){
                    entrada = entrada.substring("CARGAR_TRIVIAS".length());
                    StringReader sb = new StringReader(entrada);
                    LexerTrivias l = new LexerTrivias(sb);
                    ParserTrivias p = new ParserTrivias(l, motor);
                    p.parse();
                    out.writeUTF(p.resultado);
                    serverSocket.close();
                    
                }else{
                
                }
                
                switch (entrada) {
                    //Exportar las trivias a quien lo solicite
                    case "OBTENER_TRIVIAS":
                        out.writeUTF(motor.exportarTrivias());
                        serverSocket.close();
                        break;
                    case "OBTENER_PUNTUACIONES":
                        out.writeUTF(motor.obtenerPuntuaciones());
                        serverSocket.close();
                        break;
                        
                    case "OBTENER_BASE_DE_DATOS":
                        String bd = "<BASE_DE_DATOS>{\n";
                        bd += motor.exportarTrivias() + "\n";
                        bd += motor.obtenerUsuarios() + "\n";
                        bd += motor.obtenerCodigoPuntuaciones() + "\n}";
                        out.writeUTF(bd);
                        serverSocket.close();
                        break;
                        
                    default:
                        ui.addLog(" - - - - - - - - - - - - - - - - Hilo analizador principal: - - - - - - - - - - - - - - - - - - - -");
                        ui.addLog("Entrada:\n" + entrada + "\n");

                        /* Analizando la entrada */
                        StringReader sb = new StringReader(entrada);
                        lex = new Lexer(sb);
                        parser = new Parser(lex, motor);
                        parser.parse();

                        //Si se aprobo un inicio de sesion solo enviar eso
                        if (parser.sesionEvaluada) {
                            if (parser.usuarioSesionAprobada.length() > 0) {
                                out.writeUTF(parser.usuarioSesionAprobada);
                            } else {
                                out.writeUTF("");
                            }
                        } else {
                            out.writeUTF(parser.resultado);
                        }

                        ui.addLog("Resultado: \n" + parser.resultado);
                        serverSocket.close();
                        ui.addLog("Hilo analizador principal: Cliente desconectado");

                }

            } catch (BindException b){
                if (parser != null) {
                    try {
                        out.writeUTF(parser.resultado);
                    } catch (IOException ex) {
                        Logger.getLogger(ServidorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } 
            catch (SocketException s){
                if (parser != null) {
                    try {
                        out.writeUTF(parser.resultado);
                    } catch (IOException ex) {
                        Logger.getLogger(ServidorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                if (parser != null) {
                    try {
                        out.writeUTF(parser.resultado);
                    } catch (IOException ex) {
                        Logger.getLogger(ServidorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

    }

}
