package com.example.FootballRest.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
public class Role {
    @Id
    private String userId;
    private String role;

    public Role() {

    }
}

