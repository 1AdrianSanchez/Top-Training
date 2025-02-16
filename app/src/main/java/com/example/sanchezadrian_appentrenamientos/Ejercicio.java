package com.example.sanchezadrian_appentrenamientos;

public class Ejercicio {
    private String titulo;
    private String categoria;
    private String descripcion;
    private int imagen;
    private String idUssuario;

    public Ejercicio() {
    }

    public Ejercicio(String titulo, String categoria, String descripcion, int imagen, String idUssuario) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.idUssuario = idUssuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getIdUssuario() {
        return idUssuario;
    }

    public void setIdUssuario(String idUssuario) {
        this.idUssuario = idUssuario;
    }

    @Override
    public String toString() {
        return "Ejercicio{" +
                "titulo='" + titulo + '\'' +
                ", categoria='" + categoria + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", idUssuario='" + idUssuario + '\'' +
                '}';
    }
}