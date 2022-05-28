package com.example.mavenrepository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ArtifactVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Artifact artifact;
    @Column
    private String version;

    public ArtifactVersion() {
    }

    public ArtifactVersion(Artifact artifact, String version) {
        this.artifact = artifact;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public String getVersion() {
        return version;
    }
}
