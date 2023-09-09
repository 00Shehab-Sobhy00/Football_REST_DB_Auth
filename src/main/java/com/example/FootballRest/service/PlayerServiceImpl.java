package com.example.FootballRest.service;

import com.example.FootballRest.model.entity.Player;
import com.example.FootballRest.model.entity.Team;
import com.example.FootballRest.model.repo.PLayerRepo;
import com.example.FootballRest.model.repo.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PLayerRepo pLayerRepo;
    private TeamRepo teamRepo;

    @Autowired
    public PlayerServiceImpl(PLayerRepo pLayerRepo, TeamRepo teamRepo) {
        this.pLayerRepo = pLayerRepo;
        this.teamRepo = teamRepo;
    }

    @Override
    public Player savePlayerInTeam(String playerName, Integer teamId) {
        Optional<Team> team = teamRepo.findById(teamId);
        if (team.isPresent()) {


            Player player = new Player();
            player.setPlayerName(playerName);
            player.setTeam(team.get());
            return pLayerRepo.save(player);
        } else {
            throw new RuntimeException("Team with ID " + teamId + " not found");
        }
    }

    @Override
    public List<Player> findAllPlayers() {
        return pLayerRepo.findAll();
    }

    @Override
    public List<Player> findAllPlayersInTeam(Integer teamId) {
        return pLayerRepo.findPlayersByTeamId(teamId);
    }

    @Override
    public Player findPlayerById(Integer playerId) {
        Optional<Player> result = pLayerRepo.findById(playerId);
        Player foundPlayer = null;
        if (result.isPresent()) {
            foundPlayer = result.get();

        } else {
            throw new RuntimeException("Player id not found - " + playerId);
        }
        return foundPlayer;
    }

    @Override
    public Player updatePlayerNameOrTeamById(Integer playerId, Player updatedPlayer) {
        Player theUpdatedPlayer = findPlayerById(playerId);
        Team team = updatedPlayer.getTeam();

        Optional<Team> optionalTeam = teamRepo.findById(team.getId());
        if (optionalTeam.isPresent()) {
            theUpdatedPlayer.setPlayerName(updatedPlayer.getPlayerName());
            theUpdatedPlayer.setTeam(optionalTeam.get());
        } else {
            throw new RuntimeException("Team id not found to update player's team");
        }

        pLayerRepo.save(theUpdatedPlayer);
        return theUpdatedPlayer;
    }

    @Override
    public void deletePlayer(int playerId) {
        Optional<Player> theDeletePlayer = pLayerRepo.findById(playerId);
        if (theDeletePlayer.isPresent())
            pLayerRepo.deleteById(playerId);
        else
            throw new RuntimeException("Player id not found  - " + playerId);

    }
}