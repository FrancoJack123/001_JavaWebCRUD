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
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        daoProducto dao = new daoProducto();
        List<Products> listaProducto = dao.Categoria();
        
        // Convierte el ArrayList a JSON utilizando la librería Gson
        Gson gson = new Gson();
        String json = gson.toJson(listaProducto);

        // Configurar el tipo de contenido de la respuesta
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // Envía la respuesta JSON al cliente
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

