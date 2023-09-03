/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import entidad.Products;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Moreno Jack
 */
public interface InterfaceProducts {
    List<Products> listadoProductos();
    Products BuscarCodido(int codigo);
    int AgregarProducto(Products pro);
    int CodigoAgregar();
    int DesactivarProducto(int codigo);
    int ActualizarProducto(Products pro);
    List<Products> Categoria();
}
