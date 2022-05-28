package com.example.mavenrepository.controllers;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/repository")
public class MavenRepositoryController {


    public MavenRepositoryController() {
    }


    // serves a file from data directory
    @GetMapping("/{groupId:.+}/{artifactId}/{version}/{fileName}")
    public Resource download(@PathVariable String groupId,
                             @PathVariable String artifactId,
                             @PathVariable String version,
                             @PathVariable String fileName) {
        Path filePath = Paths.get("data", groupId, artifactId, version, fileName);
        return new FileSystemResource(filePath);
    }

    //takes http request and stores it in a file
    @PutMapping("/{groupId:.+}/{artifactId}/{version}/{fileName}")
    public ResponseEntity<Void> upload(@PathVariable String groupId,
                                       @PathVariable String artifactId,
                                       @PathVariable String version,
                                       @PathVariable String fileName,
                                       HttpServletRequest request) throws IOException {
        Path filePath = Paths.get("data", groupId, artifactId, version, fileName);
        Files.createDirectories(filePath.getParent());
        InputStream requestStream = request.getInputStream();
        Files.copy(requestStream, filePath);
        requestStream.close();
        return ResponseEntity.ok().build();
    }
}
