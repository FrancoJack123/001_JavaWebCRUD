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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Moreno Jack
 */
@WebServlet(name = "ServletProduct", urlPatterns = {"/ServletProduct"})
public class ServletProduct extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action != null){
            switch (action) {
                case "list":
                    listProducts(request, response);
                    break;
                case "listCateg":
                    listCategorias(request, response);
                    break;
                case "add":
                    addProducts(request, response);
                    break;
                case "search":
                    searchProducts(request, response);
                    break;
                case "update":
                    updateProducts(request, response);
                    break;
                case "deactivate":
                    deactivateProducts(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        daoProducto dao = new daoProducto();
        List<Products> listaProducto = dao.listadoProductos();
        
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

    private void addProducts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Products pro = new Products();
        pro.setDescrip_prod(request.getParameter("nombre"));
        pro.setStock(Integer.parseInt(request.getParameter("stock")));
        pro.setCod_categoria(Integer.parseInt(request.getParameter("categ")));
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
    }

    private void searchProducts(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

    private void updateProducts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        daoProducto dao = new daoProducto();
        Products pro = new Products();
        pro.setCod_producto(Integer.parseInt(request.getParameter("codigo")));
        pro.setDescrip_prod(request.getParameter("nombre"));
        pro.setFecha_venc(java.sql.Date.valueOf(request.getParameter("fecha")));
        pro.setCod_categoria(Integer.parseInt(request.getParameter("categ")));
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

    private void deactivateProducts(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

    private void listCategorias(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

}
