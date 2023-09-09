package com.example.FootballRest.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "Leagues")
@Data
@ToString
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "league_id")
    private int id;


    @JoinColumn(name = "league_manager", referencedColumnName = "user_id")
    private String leagueManager;


    @Column(name = "league_name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String leagueName;


    @OneToMany(mappedBy = "league")
    private List<Referee> referees;

    @OneToMany(mappedBy = "league")
    private List<Team> teams;

}











//JsonBackReferences are used to return display objects with parent child relationship. from my point

// @JsonManagedReference  the one that gets serialized ( called ) normally. cause its returning the whole obj
//  @JsonBackReference  // returning only the response body json not the actual object.

//    @JsonManagedReference is the forward part of reference – the one that gets serialized normally
//    @JsonBackReference is the back part of reference – it will be omitted from serialization.
//    public class User {
//    public int id;
//    public String name;
//
//    @JsonBackReference
//    public List<Item> userItems;
//}
//
//    public class Item {
//        public int id;
//        public String itemName;
//
//        @JsonManagedReference
//        public User owner;
//    }

//@JsonProperty // no idea to use ..