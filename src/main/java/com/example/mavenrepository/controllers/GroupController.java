package com.example.mavenrepository.controllers;

import com.example.mavenrepository.entity.Group;
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
@RequestMapping("/api/v1/group")
public class GroupController {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> get(@PathVariable String id) {
        return ResponseEntity.of(groupRepository.findById(id));
    }


    @PostMapping("/")
    public Group post(@RequestBody Group group) {
        return groupRepository.save(group);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        groupRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Group> put(@PathVariable String id,@RequestBody Group group) {
        return ResponseEntity.of(groupRepository.findById(id).map(g -> {
            g.setName(group.getName());
            return groupRepository.save(g);
        }));
    }

}
