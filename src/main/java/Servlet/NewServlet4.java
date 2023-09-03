/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import com.google.gson.Gson;
import dao.daoProducto;
import entidad.Products;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Moreno Jack
 */
@WebServlet(name = "NewServlet4", urlPatterns = {"/NewServlet4"})
public class NewServlet4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        daoProducto dao = new daoProducto();
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        int salida = dao.DesactivarProducto(codigo);
        
        // Configurar el tipo de contenido de la respuesta
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        
        // Envía la respuesta JSON al cliente
        PrintWriter out = response.getWriter();
        out.print("El producto fue desactivado correctamente");
        out.flush();
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
