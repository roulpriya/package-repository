package com.example.mavenrepository.repository;

import com.example.mavenrepository.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, String> {


}
