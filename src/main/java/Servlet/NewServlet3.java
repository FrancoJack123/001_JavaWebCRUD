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
import org.json.simple.JSONObject;

/**
 *
 * @author Moreno Jack
 */
@WebServlet(name = "NewServlet3", urlPatterns = {"/NewServlet3"})
public class NewServlet3 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        daoProducto dao = new daoProducto();
        Products pro = new Products();
        pro.setCod_producto(Integer.parseInt(request.getParameter("codigo")));
        pro.setDescrip_prod(request.getParameter("nombre"));
        pro.setFecha_venc(java.sql.Date.valueOf(request.getParameter("fecha")));
        pro.setPventa(Double.parseDouble(request.getParameter("precio")));
        pro.setStock(Integer.parseInt(request.getParameter("stock")));
        int salida = dao.ActualizarProducto(pro);

        // Configurar el tipo de contenido de la respuesta
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        // Envía la respuesta JSON al cliente
        PrintWriter out = response.getWriter();
        out.print("El producto fue actualizado correctamente");
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
