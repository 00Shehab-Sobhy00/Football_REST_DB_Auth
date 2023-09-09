package com.example.FootballRest.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;


@Entity
@Table(name = "players")
@Data
@ToString

public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private int id;

    @Column(name = "player_name", columnDefinition = "VARCHAR(50)")
    private String playerName;


    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    @JsonBackReference
    private Team team;


    @Column(name = "goals" , nullable = true)
    private Integer goals;


}
