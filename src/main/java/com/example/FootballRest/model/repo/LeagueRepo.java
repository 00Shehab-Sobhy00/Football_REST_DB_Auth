package com.example.FootballRest.model.repo;

import com.example.FootballRest.model.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;
//Dao
public interface LeagueRepo extends JpaRepository<League, Integer> {

}
