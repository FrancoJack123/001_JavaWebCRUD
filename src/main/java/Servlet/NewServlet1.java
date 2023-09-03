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
@WebServlet(name = "NewServlet1", urlPatterns = {"/NewServlet1"})
public class NewServlet1 extends HttpServlet {

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
        
        // Obtener el código del producto enviado desde la página web
        int codigoProducto = Integer.parseInt(request.getParameter("codigo"));
        daoProducto dao = new daoProducto();
        Products pro = dao.BuscarCodido(codigoProducto);

        // Utilizar Gson para convertir el objeto Producto a JSON
        Gson gson = new Gson();
        String json = gson.toJson(pro);

        // Configurar el tipo de contenido de la respuesta
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // Enviar la respuesta JSON al cliente (la página web)
        PrintWriter out = response.getWriter();
        out.print(json);
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
