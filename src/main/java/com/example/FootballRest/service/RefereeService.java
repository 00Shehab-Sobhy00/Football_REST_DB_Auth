package com.example.FootballRest.service;

import com.example.FootballRest.model.entity.League;
import com.example.FootballRest.model.entity.Referee;

import java.util.List;

public interface RefereeService {
    Referee saveRefereeInLeague(String refereeName, Integer leagueId );

//    String manager
    List <Referee> findAllReferees();

    List<Referee> findAllRefereesInLeague(Integer leagueId);

    Referee findRefereeById(Integer id);

   Referee updateRefereeNameOrLeagueById (Integer id , Referee referee);

   void deleteReferee(int id);
}






