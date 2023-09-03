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
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "NewServlet2", urlPatterns = {"/NewServlet2"})
public class NewServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
          try {
            Products pro = new Products();
            pro.setDescrip_prod(request.getParameter("nombre"));
            pro.setStock(Integer.parseInt(request.getParameter("stock")));
            pro.setPventa(Double.parseDouble(request.getParameter("precio")));
            pro.setFecha_venc(java.sql.Date.valueOf(request.getParameter("fecha")));
            System.out.println(pro.getFecha_venc());
            
            daoProducto dao = new daoProducto();
            int salida = dao.AgregarProducto(pro);
            
            // Configurar el tipo de contenido de la respuesta
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            
            // Enviar la respuesta JSON al cliente (la página web)
            PrintWriter out = response.getWriter();
            out.print("El producto fue agregado correctamente");
            out.flush();
            
        } catch (Exception e) {
               System.out.println(e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
