package com.example.FootballRest.model.repo;


import com.example.FootballRest.model.entity.Referee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefereeRepo extends JpaRepository<Referee, Integer> {

    List<Referee> findAllByLeagueId(Integer leagueId);




}



