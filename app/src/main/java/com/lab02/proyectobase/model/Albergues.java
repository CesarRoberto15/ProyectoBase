package com.lab02.proyectobase.model;

public class Albergues {
    private int id;
    private String nombre;
    private String distrito;
    private String latitud;
    private String longitud;
    private String celular;
    private String correo;

    public Albergues() {
        this.id = 0;
        this.nombre = "";
        this.distrito = "";
        this.latitud = "";
        this.longitud = "";
        this.celular = "";
        this.correo = "";

    }

    public Albergues(int id, String nombre, String distrito, String latitud,String longitud,String celular, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.distrito = distrito;
        this.latitud = latitud;
        this.longitud = longitud;
        this.celular = celular;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
