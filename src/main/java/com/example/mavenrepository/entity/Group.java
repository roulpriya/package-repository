package com.example.mavenrepository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="\"group\"")
public class Group {

    @Id
    private String id;
    @Column
    private String name;

    public Group() {
    }

    public Group(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
