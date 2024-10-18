package com.upiiz.publicacionesBlog.models;

import java.time.LocalDateTime;

public class PublicacionesBlog {
    private Long id;
    private String titulo;
    private String contenido;
    private LocalDateTime fecha_de_publicacion;


    public PublicacionesBlog() {

    }

    public PublicacionesBlog(Long id, String titulo, String contenido, LocalDateTime fecha_de_publicacion) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha_de_publicacion = fecha_de_publicacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFecha_de_publicacion() {
        return fecha_de_publicacion;
    }

    public void setFecha_de_publicacion(LocalDateTime fecha_de_publicacion) {
        this.fecha_de_publicacion = fecha_de_publicacion;
    }
}
