package com.jbrod.servidorprincipal;

import com.jbrod.servidorprincipal.analizadores.Lexer;
import com.jbrod.servidorprincipal.analizadores.Parser;
import com.jbrod.servidorprincipal.analizadores.carga.puntuaciones.LexerPuntuaciones;
import com.jbrod.servidorprincipal.analizadores.carga.puntuaciones.ParserPuntuaciones;
import com.jbrod.servidorprincipal.analizadores.carga.trivias.LexerTrivias;
import com.jbrod.servidorprincipal.analizadores.carga.trivias.ParserTrivias;
import com.jbrod.servidorprincipal.analizadores.carga.usuarios.LexerUsuarios;
import com.jbrod.servidorprincipal.analizadores.carga.usuarios.ParserUsuarios;
import com.jbrod.servidorprincipal.analizadores.consultas.LexerCons;
import com.jbrod.servidorprincipal.analizadores.consultas.ParserCons;
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

    static String rTrivias = "./Persistencia/Trivias.tpc";
    static String rUsuarios = "./Persistencia/Usuarios.upc";
    static String rPuntuaciones = "./Persistencia/Puntuaciones.ppc";

    static String cTrivias = "";
    static String cUsuarios = "";
    static String cPuntuaciones = "";
    
    static String respuesta = "";

    public static void actualizarDatos(Motor motor, String lugar) {
        /* ACTUALIZAR BASES DE DATOS */

        // Crear la carpeta si no existe
        File carpeta = new File("./Persistencia");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        /* = Trivias = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = */
        // Crear el archivo y escribir el contenido
        File aTrivias = new File(rTrivias);
        try (FileWriter writer = new FileWriter(aTrivias, false)) {
            writer.write(motor.exportarTrivias());
            System.out.println("Contenido escrito en " + rTrivias + " por " + lugar);
        } catch (IOException e) {
            System.err.println("Error al escribir en base de trivias: " + e.getMessage());
        }

        /* = Usuarios = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = */
        // Crear el archivo y escribir el contenido
        File aUsuarios = new File(rUsuarios);
        try (FileWriter writer = new FileWriter(aUsuarios, false)) {
            writer.write(motor.obtenerUsuarios());
            System.out.println("Contenido escrito en " + rUsuarios + " por " + lugar);
        } catch (IOException e) {
            System.err.println("Error al escribir en base de trivias: " + e.getMessage());
        }

        /* = Puntuaciones = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = */
        // Crear el archivo y escribir el contenido
        File aPuntuaciones = new File(rPuntuaciones);
        try (FileWriter writer = new FileWriter(aPuntuaciones, false)) {
            writer.write(motor.obtenerCodigoPuntuaciones());
            System.out.println("Contenido escrito en " + rPuntuaciones + " por " + lugar);
        } catch (IOException e) {
            System.err.println("Error al escribir en base de trivias: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        Motor motor = new Motor();
        UI ui = new UI();

        ServerSocket serverSocket = null;
        Socket clientSocket;
        DataInputStream in;
        DataOutputStream out = null;

        final int PUERTO = 6000;
        ui.setVisible(true);

        Parser parser = null;
        Lexer lex = null        ;
        int contador = 0;

        /* = = = = = = = = = CARGAR LOS DATOS ALMACENADOS SI LOS HAY = = = = = = = = = */
 /* - Trivias - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
        try (BufferedReader br = new BufferedReader(new FileReader(rTrivias))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                cTrivias += linea + "\n";
            }

            StringReader sb = new StringReader(cTrivias);
            LexerTrivias l = new LexerTrivias(sb);
            ParserTrivias p = new ParserTrivias(l, motor);
            p.parse();
            System.out.println("Trivias cargadas correctamente desde su archivo de persistencia.");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo:   " + e.getMessage());
        } catch (Exception e) {
        }
        /* - Usuarios - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
        try (BufferedReader br = new BufferedReader(new FileReader(rUsuarios))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                cUsuarios += linea + "\n";
            }

            StringReader sb = new StringReader(cUsuarios);
            LexerUsuarios l = new LexerUsuarios(sb);
            ParserUsuarios p = new ParserUsuarios(l, motor);
            p.parse();
            System.out.println("Usuarios cargados correctamente desde su archivo de persistencia.");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo:   " + e.getMessage());
        } catch (Exception e) {
        }
        /* - Puntuaciones  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
        try (BufferedReader br = new BufferedReader(new FileReader(rPuntuaciones))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                cPuntuaciones += linea + "\n";
            }

            StringReader sb = new StringReader(cPuntuaciones);
            LexerPuntuaciones l = new LexerPuntuaciones(sb);
            ParserPuntuaciones p = new ParserPuntuaciones(l, motor);
            p.parse();
            System.out.println("Puntuaciones cargadas correctamente desde su archivo de persistencia.");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo:   " + e.getMessage());
        } catch (Exception e) {
        }

        /* - - - - - - - - - - - - - - - BUCLE DE ESCUCHA DEL SERVIDOR - - - - - - - - - - - - - - - */
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
                if (entrada.startsWith("CARGAR_TRIVIAS")) {
                    entrada = entrada.substring("CARGAR_TRIVIAS".length());
                    StringReader sb = new StringReader(entrada);
                    LexerTrivias l = new LexerTrivias(sb);
                    ParserTrivias p = new ParserTrivias(l, motor);
                    p.parse();
                    out.writeUTF(p.resultado);
                    serverSocket.close();

                    actualizarDatos(motor, "cargar trivias");

                } else if (entrada.startsWith("CONSULTA")) {
                    entrada = entrada.substring("CONSULTA".length());
                    StringReader sb = new StringReader(entrada);
                    LexerCons l = new LexerCons(sb);
                    ParserCons p = new ParserCons(l, motor);
                    p.parse();
                    out.writeUTF(p.resultado);
                    serverSocket.close();

                } else if(entrada.startsWith("SELECCIONAR")){
                    StringReader sb = new StringReader(entrada);
                    LexerCons l = new LexerCons(sb);
                    ParserCons p = new ParserCons(l, motor);
                    p.parse();
                    out.writeUTF(p.resultado);
                    serverSocket.close();
                }else {

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
                            System.out.println("Por default: ");
                            actualizarDatos(motor, " default");

                            //Si se aprobo un inicio de sesion solo enviar eso
                            if (parser.sesionEvaluada) {
                                if (parser.usuarioSesionAprobada.length() > 0) {
                                    out.writeUTF(parser.usuarioSesionAprobada);
                                } else {
                                    
                                    out.writeUTF(parser.resultado + "\n\n\n\n\n\n\n\n\n" + lex.errores + parser.errores);
                                }
                            } else {
                                out.writeUTF(parser.resultado + "\n\n\n\n\n\n\n\n\n" + lex.errores + parser.errores);
                            }

                            ui.addLog("Resultado: \n" + parser.resultado + "\n\n\n" + lex.errores + parser.errores);
                            serverSocket.close();
                            ui.addLog("Hilo analizador principal: Cliente desconectado");

                    }
                }

            } catch (BindException b) {
               
                b.printStackTrace();
                actualizarDatos(motor, "exc b");

               
            } catch (SocketException s) {
               
                s.printStackTrace();
                actualizarDatos(motor, "exc s");
                
            } catch (Exception e) {
                e.printStackTrace();
               
                actualizarDatos(motor, "exc e");

                
            } finally {
                try {
                    if(parser!= null && lex != null){
                     out.writeUTF(parser.resultado + "\n\n\n\n\n\n\n\n\n" + lex.errores + parser.errores);
                    }else{
                     out.writeUTF("HUBO UN ERROR GRAVE: LEXER Y PARSER NULOS");
                    }
                    serverSocket.close();
                } catch (IOException ex1) {
                    Logger.getLogger(ServidorPrincipal.class.getName()).log(Level.SEVERE, null, ex1);
                }
                serverSocket = null;
            }

        }

    }

}
