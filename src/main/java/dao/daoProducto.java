/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Interfaces.InterfaceProducts;
import capa_datos.MysqlBDConexion;
import entidad.Products;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Moreno Jack
 */
public class daoProducto implements InterfaceProducts {

    @Override
    public List<Products> listadoProductos() {
        List<Products> listarProducto = new ArrayList<Products>();
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            c = MysqlBDConexion.getConexion();
            String sql = "SELECT p.*, c.descrip_categ " + 
                          "FROM tb_producto p " + 
                          "INNER JOIN tb_categoria c ON p.cod_categoria = c.cod_categoria where activo = 1;";
            
            pstm = c.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Products pro = new Products();

                pro.setCod_producto(rs.getInt(1));
                pro.setCod_categoria(rs.getInt(2));
                pro.setDescrip_prod(rs.getString(3));
                pro.setStock(rs.getInt(4));
                pro.setPventa(rs.getDouble(5));
                pro.setFecha_venc(rs.getDate(6));
                pro.setDescrip_categ(rs.getString(8));

                listarProducto.add(pro);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (c != null) {
                    c.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (Exception e2) {

            }
        }
        
        return listarProducto;
    }

    @Override
    public Products BuscarCodido(int codigo) {
        Products pro = new Products();
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            c = MysqlBDConexion.getConexion();
            String sql = "SELECT * FROM tb_producto where cod_producto = ?";
            pstm = c.prepareStatement(sql);
            pstm.setInt(1, codigo);
            rs = pstm.executeQuery();
            while (rs.next()) {                
               pro.setCod_producto(rs.getInt(1));
               pro.setCod_categoria(rs.getInt(2));
               pro.setDescrip_prod(rs.getString(3));
               pro.setStock(rs.getInt(4));
               pro.setPventa(rs.getDouble(5));
               pro.setFecha_venc(rs.getDate(6));
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (c != null) {
                    c.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (Exception e2) {

            }
        }
        
        return pro;
    }

    @Override
    public int AgregarProducto(Products pro) {
        int salida = -1;
        Connection c = null;
        PreparedStatement pstm = null;
        try {
            c = MysqlBDConexion.getConexion();
            String sql = "INSERT INTO tb_producto VALUES(NULL, ?, ?, ?, ?, ?, 1);";
            pstm = c.prepareStatement(sql);
            pstm.setInt(1, pro.getCod_categoria());
            pstm.setString(2, pro.getDescrip_prod());
            pstm.setInt(3,pro.getStock());
            pstm.setDouble(4,pro.getPventa());
            pstm.setDate(5, new Date(pro.getFecha_venc().getTime() + 24 * 60 * 60 * 1000));
            salida = pstm.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (Exception e2) {

            }
        }
        return salida;
    }

    @Override
    public int CodigoAgregar() {
        int codigo = 0;
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            c = MysqlBDConexion.getConexion();
            String sql = "select max(cod_producto) from tb_producto";
            pstm = c.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {                
                codigo = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (c != null) {
                    c.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
        
        return codigo;
    }

    @Override
    public int DesactivarProducto(int codigo) {
        int salida = 0;
        Connection c = null;
        PreparedStatement pstm = null;
        try {
            c = MysqlBDConexion.getConexion();
            String sql = "UPDATE tb_producto SET activo= 0 WHERE cod_producto = ?;";
            pstm = c.prepareStatement(sql);
            pstm.setInt(1, codigo);
            salida = pstm.executeUpdate();
        } catch (Exception e) {
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (Exception e2) {
            }
        }
        return salida;
    }

    @Override
    public int ActualizarProducto(Products pro) {
        int salida = 0;
        Connection c = null;
        PreparedStatement pstm = null;
        try {
            c = MysqlBDConexion.getConexion();
            String sql = "UPDATE tb_producto SET cod_categoria = ?, descrip_prod = ?, stock = ?, pventa = ?, fecha_venc = ?, activo= 1 WHERE cod_producto = ?;";
            pstm = c.prepareStatement(sql);
            pstm.setInt(1, pro.getCod_categoria());
            pstm.setString(2, pro.getDescrip_prod());
            pstm.setInt(3, pro.getStock());
            pstm.setDouble(4, pro.getPventa());
            pstm.setDate(5, new Date(pro.getFecha_venc().getTime() + 24 * 60 * 60 * 1000));
            pstm.setInt(6, pro.getCod_producto());
            salida = pstm.executeUpdate();
        } catch (Exception e) {
            
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (Exception e2) {
            }
        }
        return salida;
    }

    @Override
    public List<Products> Categoria() {
        List<Products> listadoCategoria = new ArrayList<Products>();
        Connection c = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            c = MysqlBDConexion.getConexion();
            String sql = "SELECT * FROM tb_categoria;";
            pstm = c.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {  
                Products pro = new Products();
                pro.setCod_categoria(rs.getInt(1));
                pro.setDescrip_categ(rs.getString(2));
                listadoCategoria.add(pro);
            }
        } catch (Exception e) {
            
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (c != null) {
                    c.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
        
        return listadoCategoria;
    }

}
