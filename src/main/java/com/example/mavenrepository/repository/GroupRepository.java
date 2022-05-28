package com.example.mavenrepository.repository;

import com.example.mavenrepository.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {

}
