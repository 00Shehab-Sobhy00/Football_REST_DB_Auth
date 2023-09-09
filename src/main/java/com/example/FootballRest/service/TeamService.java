package com.example.FootballRest.service;

import com.example.FootballRest.model.entity.Referee;
import com.example.FootballRest.model.entity.Team;

import java.util.List;

public interface TeamService {

    Team saveTeamInLeague(String teamName, Integer leagueId);

    List<Team> findAllTeams();
    List<Team> findAllTeamsInLeague(Integer leagueId);

    Integer leagueIdByTeamId(Integer teamId);

    Team findTeamById(Integer id);

    Team updateTeamNameOrLeagueById(Integer id, Team team);


    void deleteTeamById(int id);
}
