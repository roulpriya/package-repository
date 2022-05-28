package com.example.mavenrepository.controllers;


import com.example.mavenrepository.repository.ArtifactVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/artifactVersion")
public class ArtifactVersionController {
    private final ArtifactVersionRepository artifactVersionRepository;

    @Autowired
    public ArtifactVersionController(ArtifactVersionRepository artifactVersionRepository) {
        this.artifactVersionRepository = artifactVersionRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity getArtifactVersion(@PathVariable Long id) {
        return ResponseEntity.ok(artifactVersionRepository.findById(id));
    }
}
