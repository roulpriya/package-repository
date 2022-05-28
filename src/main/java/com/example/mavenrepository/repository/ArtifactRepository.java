package com.example.mavenrepository.repository;

import com.example.mavenrepository.entity.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtifactRepository extends JpaRepository<Artifact, Long> {

}
