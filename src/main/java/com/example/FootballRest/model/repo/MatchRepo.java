package com.example.FootballRest.model.repo;


import com.example.FootballRest.model.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MatchRepo extends JpaRepository <Match,Integer>  {


}
