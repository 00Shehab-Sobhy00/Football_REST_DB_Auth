package com.example.FootballRest.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
@Data
@ToString

public class Match {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Integer matchId;


    @ManyToOne
    @JoinColumn(name = "team1_id")
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team2_id")
    private Team team2;

    @Column(name = "full_time_score")
    private String fullTimeScore;

    @Column(name = "match_date")
    private String matchDate;

    @ManyToOne
    @JoinColumn(name = "refree_id")
    private Referee referee;

    public Match( Team team1, Team team2, String fullTimeScore, String matchDate ,Referee referee ) {

        this.team1 = team1;
        this.team2 = team2;
        this.fullTimeScore = fullTimeScore;
        this.matchDate = matchDate;
        this.referee = referee;
    }

    public Match() {

    }
}
