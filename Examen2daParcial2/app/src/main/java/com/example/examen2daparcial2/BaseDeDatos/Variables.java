package com.example.examen2daparcial2.BaseDeDatos;

public class Variables {

    public static final String NOMBRE_BD = "libreria";

    //VARIABLES DE LA TABLA USUARIO


    public static final String NOMBRE_TABLA_CLIENTES = "clientes";
    public static final String CAMPO_ID_CLIENTES = "id_clientes";
    public static final String CAMPO_NOMBRE_CLIENTE = "nombre";
    public static final String CAMPO_RFC = "rfc";

    public static final String CREAR_TABLA_CLIENTES = "CREATE TABLE "
            + NOMBRE_TABLA_CLIENTES + " ("
            + CAMPO_ID_CLIENTES + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CAMPO_NOMBRE_CLIENTE + " TEXT, "
            + CAMPO_RFC + " TEXT )";
    public static final String ELIMINAR_TABLA_CLIENTES = "DROP TABLE IF EXIST " + NOMBRE_TABLA_CLIENTES;


    //VARIABLES DE LA TABLA LIBROS


    public static final String NOMBRE_TABLA_LIBROS = "libros";
    public static final String CAMPO_ID_LIBROS = "id_libros";
    public static final String CAMPO_ISBN = "isbn";
    public static final String CAMPO_TITULO = "titulo";
    public static final String CAMPO_AUTOR = "autor";
    public static final String CAMPO_EDITORIAL = "editorial";
    public static final String CAMPO_PAGINAS = "npPginas";
    public static final String CAMPO_PRECIO = "precio";

    public static final String CREAR_TABLA_LIBROS = "CREATE TABLE "
            + NOMBRE_TABLA_LIBROS + " ("
            + CAMPO_ID_LIBROS + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CAMPO_TITULO + " TEXT, "
            + CAMPO_AUTOR + " TEXT, "
            + CAMPO_EDITORIAL + " TEXT, "
            + CAMPO_PAGINAS + " INTEGER, "
            + CAMPO_ISBN + " INTEGER,"
            + CAMPO_PRECIO + " REAL )";
    public static final String ELIMINAR_TABLA_LIBROS = "DROP TABLE IF EXIST " + NOMBRE_TABLA_LIBROS;


    //VARIABLES DE LA TABLA VENTAS


    public static final String NOMBRE_TABLA_VENTAS = "ventas";
    public static final String CAMPO_ID_VENTA = "id_venta";
    public static final String CAMPO_CANTIDAD_LIBROS = "cantidad";
    public static final String CAMPO_COSTO_TOTAL = "costo_total";

    public static final String CREAR_TABLA_VENTAS = "CREATE TABLE "
            + NOMBRE_TABLA_VENTAS + " ("
            + CAMPO_ID_VENTA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CAMPO_ID_CLIENTES + " STRING, "
            + CAMPO_ID_LIBROS + " INTEGER, "
            + CAMPO_CANTIDAD_LIBROS + " INTEGER, "
            + CAMPO_COSTO_TOTAL + " REAL )";
    public static final String ELIMINAR_TABLA_VENTAS = "DROP TABLE IF EXIST " + NOMBRE_TABLA_VENTAS;
}
