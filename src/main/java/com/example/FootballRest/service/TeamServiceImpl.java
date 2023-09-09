package com.example.FootballRest.service;

import com.example.FootballRest.model.entity.League;
import com.example.FootballRest.model.entity.Player;
import com.example.FootballRest.model.entity.Referee;
import com.example.FootballRest.model.entity.Team;
import com.example.FootballRest.model.repo.LeagueRepo;
import com.example.FootballRest.model.repo.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    private TeamRepo teamRepo;
    private LeagueRepo leagueRepo;

    @Autowired
    public TeamServiceImpl(TeamRepo teamRepo, LeagueRepo leagueRepo) {
        this.teamRepo = teamRepo;
        this.leagueRepo = leagueRepo;
    }

    @Override
    public Team saveTeamInLeague(String teamName, Integer leagueId) {
        Optional<League> league = leagueRepo.findById(leagueId);
        if (league.isPresent()) {
            Team team = new Team();
            team.setTeamName(teamName);
            team.setLeague(league.get());
            return teamRepo.save(team);
        } else {
            throw new RuntimeException("League with ID " + leagueId + " not found");
        }

    }

    @Override
    public List<Team> findAllTeams() {
        return teamRepo.findAll();
    }

    @Override
    public List<Team> findAllTeamsInLeague(Integer leagueId) {
        return teamRepo.findTeamsByLeagueId(leagueId);
    }

    @Override
    public Integer leagueIdByTeamId(Integer teamId) {
        return teamRepo.findLeagueIdByTeamId(teamId);
    }

    @Override
    public Team findTeamById(Integer teamId) {
        Optional<Team> result = teamRepo.findById(teamId);
        Team foundTeam = null;
        if (result.isPresent()) {
            foundTeam = result.get();

        } else {
            throw new RuntimeException("Team id not found - " + teamId);
        }
        return foundTeam;
    }

    @Override
    public Team updateTeamNameOrLeagueById(Integer id, Team commingUpdatedTeam) {
        Team theUpdatedTeam = findTeamById(id);
        League league = commingUpdatedTeam.getLeague();

        Optional<League> optionalLeague = leagueRepo.findById(league.getId());
        if (optionalLeague.isPresent()) {
            theUpdatedTeam.setTeamName(commingUpdatedTeam.getTeamName());
            theUpdatedTeam.setLeague(optionalLeague.get());
        } else {
            throw new RuntimeException("League id not found to update team's league !! ");
        }

        teamRepo.save(theUpdatedTeam);
        return theUpdatedTeam;
    }


    @Override
    public void deleteTeamById(int team_id) {
        Optional<Team> theDeleteTeam = teamRepo.findById(team_id);
        if (theDeleteTeam.isPresent()) {
            List<Player> isTherePlayers = theDeleteTeam.get().getPlayers();
            if (isTherePlayers.isEmpty()) {
                teamRepo.deleteById(team_id);
            } else {
                throw new RuntimeException("Cannot delete team id " + team_id + " case team contain players- ");
            }
        } else {
            throw new RuntimeException("Team id not found  - " + team_id);
        }

    }
}

