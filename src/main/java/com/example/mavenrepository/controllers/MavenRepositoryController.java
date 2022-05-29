package com.example.mavenrepository.controllers;

import com.example.mavenrepository.FileInfo;
import com.example.mavenrepository.ArtifactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/repository")
public class MavenRepositoryController {

    private final ArtifactService artifactService;

    @Autowired
    public MavenRepositoryController(ArtifactService artifactService) {
        this.artifactService = artifactService;
    }

    // serves a file from data directory
    @GetMapping(value = "/**", produces = "application/octet-stream")
    @ResponseBody
    public Resource download(HttpServletRequest request) {
        Path path = Paths.get("data", getFilePath(request));
        if (!Files.exists(path)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
        }
        return new FileSystemResource(path);
    }

    private String getFilePath(HttpServletRequest request) {
        return request.getRequestURI().split(request.getContextPath() + "/repository/")[1];
    }

    //takes http request and stores it in a file
    @PutMapping("/**")
    public ResponseEntity<Void> upload(HttpServletRequest request) throws IOException {
        String filePath = getFilePath(request);
        FileInfo fileInfo = FileInfo.parse(filePath);
        Path path = Paths.get("data", filePath);
        Files.createDirectories(path.getParent());
        InputStream requestStream = request.getInputStream();
        Files.copy(requestStream, path, StandardCopyOption.REPLACE_EXISTING);
        requestStream.close();
        if(fileInfo.version() != null) {
            artifactService.save(fileInfo);
        }
        return ResponseEntity.ok().build();
    }

}
