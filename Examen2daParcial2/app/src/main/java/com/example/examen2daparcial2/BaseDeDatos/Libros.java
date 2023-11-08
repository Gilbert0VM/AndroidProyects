package com.example.examen2daparcial2.BaseDeDatos;

import java.io.Serializable;

public class Libros implements Serializable {

    Integer id;
    Integer isbn;
    Integer paginas;
    String titulo;
    String editorial;
    String autor;
    Double precio;

    public Libros(Integer id, Integer isbn, Integer paginas, String titulo, String editorial, String autor, Double precio) {
        this.id = id;
        this.isbn = isbn;
        this.paginas = paginas;
        this.titulo = titulo;
        this.editorial = editorial;
        this.autor = autor;
        this.precio = precio;
    }

    public Libros(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
