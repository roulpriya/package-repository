package com.example.mavenrepository.controllers;

import org.springframework.web.server.ResponseStatusException;

public class GroupNotFoundException extends ResponseStatusException {
    public GroupNotFoundException() {
        super(org.springframework.http.HttpStatus.NOT_FOUND, "Group not found");
    }
}
