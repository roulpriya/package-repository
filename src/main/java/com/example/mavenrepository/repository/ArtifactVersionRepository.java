package com.example.mavenrepository.repository;

import com.example.mavenrepository.entity.ArtifactVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtifactVersionRepository extends JpaRepository<ArtifactVersion, Long> {

}
