package com.example.mavenrepository;

import com.example.mavenrepository.entity.Artifact;
import com.example.mavenrepository.entity.ArtifactVersion;
import com.example.mavenrepository.repository.ArtifactRepository;
import com.example.mavenrepository.repository.ArtifactVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtifactService {

    private ArtifactVersionRepository artifactVersionRepository;
    private ArtifactRepository artifactRepository;


    @Autowired
    public ArtifactService(ArtifactVersionRepository artifactVersionRepository, ArtifactRepository artifactRepository) {
        this.artifactVersionRepository = artifactVersionRepository;
        this.artifactRepository = artifactRepository;
    }

    public void save(FileInfo fileService) {
        Optional<Artifact> artifact = artifactRepository.findByGroupIdAndId(fileService.groupId(), fileService.artifactId());
        if(artifact.isPresent()) {
            ArtifactVersion artifactVersion = new ArtifactVersion(artifact.get(), fileService.version());
            artifactVersionRepository.save(artifactVersion);
        } else {
            throw new ArtifactNotFoundException();
        }



    }
}
