package com.example.examen2daparcial2.BaseDeDatos;

import java.io.Serializable;

public class Clientes implements Serializable {

    Integer id;
    String nombre_cliente;
    String rfc;

    public Clientes(Integer id, String nombre_cliente, String rfc) {
        this.id = id;
        this.nombre_cliente = nombre_cliente;
        this.rfc = rfc;
    }

    public Clientes(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
}
