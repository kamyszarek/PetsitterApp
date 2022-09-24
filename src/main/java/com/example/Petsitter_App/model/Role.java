package com.example.Petsitter_App.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    public Long getId() {
        return id;
    }

    // remaining getters and setters
}