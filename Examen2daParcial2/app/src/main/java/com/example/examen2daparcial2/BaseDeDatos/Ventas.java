package com.example.examen2daparcial2.BaseDeDatos;

import java.io.Serializable;

public class Ventas implements Serializable {

    Integer id_venta;
    String id_cliente;
    Integer id_libro;
    Integer cantidad;
    Double costoTotal;

    public Ventas(Integer id_venta, String id_cliente, Integer id_libro, Integer cantidad, Double costoTotal) {
        this.id_venta = id_venta;
        this.id_cliente = id_cliente;
        this.id_libro = id_libro;
        this.cantidad = cantidad;
        this.costoTotal = costoTotal;
    }
    public Ventas(){

    }

    public Integer getId_venta() {
        return id_venta;
    }

    public void setId_venta(Integer id_venta) {
        this.id_venta = id_venta;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getId_libro() {
        return id_libro;
    }

    public void setId_libro(Integer id_libro) {
        this.id_libro = id_libro;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }
}
