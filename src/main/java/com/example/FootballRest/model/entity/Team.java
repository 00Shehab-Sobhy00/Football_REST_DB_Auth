package com.example.FootballRest.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;


@Entity
@Table(name = "teams")
@Data
@ToString
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int id;

    @Column(name = "team_name", columnDefinition = "VARCHAR(50)")
    private String teamName;

    @ManyToOne
    @JoinColumn(name = "league_id", referencedColumnName = "league_id")
    @JsonBackReference
    private League league;


    @OneToMany (mappedBy = "team")
    private List <Player> players;


    @OneToMany (mappedBy = "team")
    private List <Coach> coaches;


}