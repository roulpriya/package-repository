package com.example.mavenrepository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"group_id", "id"})})

public class Artifact {


    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column
    private String id;

    @Column
    private String name;

    @Column(nullable = true)
    private String latestVersion;

    protected Artifact() {
    }

    public Artifact(Group group, String id, String name) {
        this.group = group;
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

    public String getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }
}
