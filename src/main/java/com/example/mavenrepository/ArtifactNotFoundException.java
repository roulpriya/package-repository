package com.example.mavenrepository;

import org.springframework.web.server.ResponseStatusException;

public class ArtifactNotFoundException extends ResponseStatusException {

    public ArtifactNotFoundException() {
        super(org.springframework.http.HttpStatus.NOT_FOUND, "Artifact not found");
    }
}
