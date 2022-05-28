package com.example.mavenrepository.controllers;

import com.example.mavenrepository.entity.Artifact;
import com.example.mavenrepository.repository.ArtifactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ArtifactController {



    private final ArtifactRepository artifactRepository;

    @Autowired
    public ArtifactController(ArtifactRepository artifactRepository){
        this.artifactRepository = artifactRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artifact> get(@PathVariable Long id) {
        return ResponseEntity.of(artifactRepository.findById(id));
    }

    @PostMapping("/")
    public Artifact post(Artifact artifact) {
        return artifactRepository.save(artifact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artifact> put(@PathVariable Long id, Artifact newArtifact) {
        return ResponseEntity.of(artifactRepository.findById(id).map(artifact -> {
            artifact.setName(newArtifact.getName());
            return artifactRepository.save(artifact);
        }));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        artifactRepository.deleteById(id);
    }
}
