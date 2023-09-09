package com.example.FootballRest.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "members")
@Data
@ToString
@AllArgsConstructor
public class Member {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "pw")
    private String password;

    @Column(name = "active")
    private boolean active;

    public Member() {

    }


}