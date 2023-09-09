package com.example.FootballRest.service;

import com.example.FootballRest.model.entity.Player;

import java.util.List;

public interface PlayerService {

    Player savePlayerInTeam(String playerName, Integer teamId);

    List<Player> findAllPlayers();

    List<Player> findAllPlayersInTeam(Integer teamId);

    Player findPlayerById(Integer playerId);

    Player updatePlayerNameOrTeamById(Integer playerId, Player updatedPlayer);

    void deletePlayer(int playerId);

}

