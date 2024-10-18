package com.upiiz.publicacionesBlog.services;

import com.upiiz.publicacionesBlog.models.PublicacionesBlog;
import com.upiiz.publicacionesBlog.repository.PublicacionesBlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicacionesBlogsService {

    PublicacionesBlogRepository publicacionesBlogRepository;

    public PublicacionesBlogsService(PublicacionesBlogRepository publicacionesBlogRepository) {
        this.publicacionesBlogRepository = publicacionesBlogRepository;
    }

    // GET - Todas
    public List<PublicacionesBlog> getAllPublicaciones() {
        return publicacionesBlogRepository.obtenerTodas();
    }

    // GET - Categoría por ID
    public PublicacionesBlog getPublicacionById(Long id) {
        return publicacionesBlogRepository.obtenerPorId(id);
    }

    // POST - Crear
    public PublicacionesBlog createPublicacion(PublicacionesBlog publicacionesBlog) {
        return publicacionesBlogRepository.guardar(publicacionesBlog);
    }

    // PUT - Actualizar
    public PublicacionesBlog updatePublicacion(PublicacionesBlog publicacionesBlog) {
        return publicacionesBlogRepository.actualizar(publicacionesBlog);
    }

    // DELETE - Eliminar
    public void deletePublicacion(Long id) {
        publicacionesBlogRepository.eliminar(id);
    }


}
