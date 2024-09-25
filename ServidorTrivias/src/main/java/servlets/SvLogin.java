
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author jorge
 */
@WebServlet(name = "SvLogin", urlPatterns = {"/Login"})
public class SvLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }
    
    
    
    
    
    
    
    
    

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    
    
    
    
    
    
    
    
    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String codigoLogin = request.getParameter("login");
        System.out.println(codigoLogin);
        
        
        /* Conexion con el servidor principal para iniciar sesion */
        String HOST = "127.0.0.1";
        int PUERTO = 6000;
        DataInputStream in; 
        DataOutputStream out; 
        
        try{
            Socket clientSocket = new Socket(HOST, PUERTO);
            
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            
            out.writeUTF(codigoLogin);
            String respuesta = in.readUTF();
            //Si se aprobo el inicio de sesion, continuar
            HttpSession session = request.getSession();
            if(respuesta.length() > 0){
                session.setAttribute("logueado", respuesta);
                System.out.println("Logueado como: ");
                
                try(PrintWriter oute = response.getWriter()){
                    oute.println("Logueado como: " + respuesta);
                    response.sendRedirect("solicitudes.jsp");
                }
                
            }else{
                session.setAttribute("logueado", "");
                System.out.println("No loguado");
            }
            System.out.println(respuesta);
            clientSocket.close();
            
        }catch(Exception e){
            System.out.println("HUBO UN ERROR");
            e.printStackTrace();
        }
    }

    
    
    
    
    
    
    
    
    
    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
