package com.upiiz.publicacionesBlog.controllers;

import com.upiiz.publicacionesBlog.models.PublicacionesBlog;
import com.upiiz.publicacionesBlog.services.FileService;
import com.upiiz.publicacionesBlog.services.PublicacionesBlogsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/publicacionesBlog")
@RestController
public class PublicacionesBlogsController {

    private final PublicacionesBlogsService publicacionesBlogsService;

    public PublicacionesBlogsController(PublicacionesBlogsService publicacionesBlogsService, FileService fileService) {
        this.publicacionesBlogsService = publicacionesBlogsService;
    }

    @GetMapping
    public ResponseEntity<List<PublicacionesBlog>> getPublicaciones() {
        return ResponseEntity.ok(publicacionesBlogsService.getAllPublicaciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionesBlog> getPublicacionPorId(@PathVariable Long id) {
        return ResponseEntity.ok(publicacionesBlogsService.getPublicacionById(id));
    }

    @PostMapping
    public ResponseEntity<PublicacionesBlog> addPublicacion(@RequestBody PublicacionesBlog empleado) {
        PublicacionesBlog nuevoCliente = publicacionesBlogsService.createPublicacion(empleado);
        return ResponseEntity.ok(nuevoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionesBlog> actualizarPublicacion(@RequestBody PublicacionesBlog empleado, @PathVariable Long id) {
        empleado.setId(id);
        return ResponseEntity.ok(publicacionesBlogsService.updatePublicacion(empleado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPublicacion(@PathVariable Long id) {
        publicacionesBlogsService.deletePublicacion(id);
        return ResponseEntity.noContent().build();
    }

}
