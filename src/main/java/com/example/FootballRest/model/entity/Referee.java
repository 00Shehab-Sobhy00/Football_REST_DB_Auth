    package com.example.FootballRest.model.entity;

    import com.fasterxml.jackson.annotation.JsonBackReference;
    import com.fasterxml.jackson.annotation.JsonManagedReference;
    import com.fasterxml.jackson.annotation.JsonProperty;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.ToString;
    import org.antlr.v4.runtime.misc.NotNull;
    import org.springframework.lang.Nullable;

    @Entity
    @Table(name = "Referees")
    @Data
    @ToString
//    @AllArgsConstructor :
    public class Referee {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "referee_id")
        private int id;

        @Column(name = "referee_name", columnDefinition = "VARCHAR(50)")
        private String refereeName;

        @ManyToOne
        @JoinColumn(name = "league_id", referencedColumnName = "league_id")
        @JsonBackReference
        private League league;







    }


