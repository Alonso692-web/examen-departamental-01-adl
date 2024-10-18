package com.upiiz.publicacionesBlog.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.upiiz.publicacionesBlog.models.PublicacionesBlog;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PublicacionesBlogRepository {
    private static final String FILE_PATH = "src/main/resources/publicacionesBlog.json";
    private List<PublicacionesBlog> publicacionesBlog = new ArrayList<>();
    private AtomicLong id = new AtomicLong();

    public PublicacionesBlogRepository() {
        // Cargar publicaciones de blog del archivo JSON cuando se inicialice el repositorio
        this.leerPublicacionesDeArchivo();
    }

    // Método para guardar una nueva publicación
    public PublicacionesBlog guardar(PublicacionesBlog publicacionBlog) {
        publicacionBlog.setId(this.id.incrementAndGet());
        this.publicacionesBlog.add(publicacionBlog);
        this.guardarPublicacionesEnArchivo();  // Guardar cambios en el archivo JSON
        return publicacionBlog;
    }

    // Método para obtener todas las publicaciones
    public List<PublicacionesBlog> obtenerTodas() {
        return this.publicacionesBlog;
    }

    // Método para obtener una publicación por su ID
    public PublicacionesBlog obtenerPorId(Long id) {
        return this.publicacionesBlog.stream().filter(publicacion -> publicacion.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Método para eliminar una publicación por su ID
    public void eliminar(Long id) {
        this.publicacionesBlog.removeIf(publicacion -> publicacion.getId().equals(id));
        this.guardarPublicacionesEnArchivo();  // Guardar cambios en el archivo JSON
    }

    // Método para actualizar una publicación
    public PublicacionesBlog actualizar(PublicacionesBlog publicacionBlog) {
        this.eliminar(publicacionBlog.getId());
        this.publicacionesBlog.add(publicacionBlog);
        this.guardarPublicacionesEnArchivo();  // Guardar cambios en el archivo JSON
        return publicacionBlog;
    }

    // Método privado para leer las publicaciones desde el archivo JSON
    private void leerPublicacionesDeArchivo() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // Registrar el módulo para manejar Java 8 fechas
            objectMapper.registerModule(new JavaTimeModule());

            File archivo = new File(FILE_PATH);
            if (archivo.exists()) {
                PublicacionesBlog[] publicacionesArray = objectMapper.readValue(archivo, PublicacionesBlog[].class);
                this.publicacionesBlog = new ArrayList<>(Arrays.asList(publicacionesArray));

                // Ajustar el contador de IDs al último ID en la lista
                if (!this.publicacionesBlog.isEmpty()) {
                    Long maxId = this.publicacionesBlog.stream().mapToLong(PublicacionesBlog::getId).max().orElse(0L);
                    this.id.set(maxId);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo JSON", e);
        }
    }

    // Método privado para guardar las publicaciones en el archivo JSON
    private void guardarPublicacionesEnArchivo() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // Registrar el módulo para manejar Java 8 fechas
            objectMapper.registerModule(new JavaTimeModule());

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), this.publicacionesBlog);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo JSON", e);
        }
    }
}
