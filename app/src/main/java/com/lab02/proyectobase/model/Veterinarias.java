package com.lab02.proyectobase.model;

public class Veterinarias {
    private int id;
    private String nombre;
    private String distrito;
    private String ubicacion;
    private String correo;

    public Veterinarias() {
        this.id = 0;
        this.nombre = "";
        this.distrito = "";
        this.ubicacion = "";
        this.correo = "";
    }

    public Veterinarias(int id, String nombre, String distrito, String ubicacion, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.distrito = distrito;
        this.ubicacion = ubicacion;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
