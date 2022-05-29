package com.example.mavenrepository.controllers;

import com.example.mavenrepository.dto.CreateArtifact;
import com.example.mavenrepository.dto.UpdateArtifactRequest;
import com.example.mavenrepository.entity.Artifact;
import com.example.mavenrepository.entity.Group;
import com.example.mavenrepository.repository.ArtifactRepository;
import com.example.mavenrepository.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/artifacts")
public class ArtifactController {


    private final ArtifactRepository artifactRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public ArtifactController(ArtifactRepository artifactRepository, GroupRepository groupRepository) {
        this.artifactRepository = artifactRepository;
        this.groupRepository = groupRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artifact> get(@PathVariable Long id) {
        return ResponseEntity.of(artifactRepository.findById(id));
    }

    @PostMapping("/")
    public Artifact post(@RequestBody CreateArtifact createArtifact) {
        Group group = groupRepository.findById(createArtifact.groupId()).orElseThrow(GroupNotFoundException::new);
        Artifact artifact = new Artifact(group,createArtifact.id(),createArtifact.name());
        return artifactRepository.save(artifact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artifact> put(@PathVariable Long id,@RequestBody UpdateArtifactRequest updateArtifactRequest) {
        return ResponseEntity.of(artifactRepository.findById(id).map(artifact -> {
            artifact.setName(updateArtifactRequest.name());
            return artifactRepository.save(artifact);
        }));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        artifactRepository.deleteById(id);
    }
}
