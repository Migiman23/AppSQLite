package com.example.sqliteprueba1.Entidades;

public class Usuario {

    private Integer id;
    private String Nombre;
    private String Telefono;


    public Usuario(){

    }
    public Usuario(Integer id,String nombre, String telefono ) {
        this.id = id;
        Nombre = nombre;
        Telefono = telefono;

    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
