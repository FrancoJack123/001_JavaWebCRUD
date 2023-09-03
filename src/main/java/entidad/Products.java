/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidad;

import java.sql.Date;

/**
 *
 * @author Moreno Jack
 */

public class Products {

    public int cod_producto, stock;
    public String descrip_prod;
    public double pventa;
    public Date fecha_venc;
    public int cod_categoria;
    public String descrip_categ;

    public int getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(int cod_producto) {
        this.cod_producto = cod_producto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescrip_prod() {
        return descrip_prod;
    }

    public void setDescrip_prod(String descrip_prod) {
        this.descrip_prod = descrip_prod;
    }

    public double getPventa() {
        return pventa;
    }

    public void setPventa(double pventa) {
        this.pventa = pventa;
    }

    public Date getFecha_venc() {
        return fecha_venc;
    }

    public void setFecha_venc(Date fecha_venc) {
        this.fecha_venc = fecha_venc;
    }

    public int getCod_categoria() {
        return cod_categoria;
    }

    public void setCod_categoria(int cod_categoria) {
        this.cod_categoria = cod_categoria;
    }

    public String getDescrip_categ() {
        return descrip_categ;
    }

    public void setDescrip_categ(String descrip_categ) {
        this.descrip_categ = descrip_categ;
    }
}
