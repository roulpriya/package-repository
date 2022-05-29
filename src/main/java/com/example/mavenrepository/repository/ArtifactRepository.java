package com.example.mavenrepository.repository;

import com.example.mavenrepository.entity.Artifact;
import com.example.mavenrepository.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtifactRepository extends JpaRepository<Artifact, Long> {

    Optional<Artifact> findByGroupIdAndId(String groupId, String id);


}
