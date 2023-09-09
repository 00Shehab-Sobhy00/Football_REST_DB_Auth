package com.example.FootballRest.controllers;
import com.example.FootballRest.model.entity.Player;
import com.example.FootballRest.service.AuthMemberService;
import com.example.FootballRest.service.PlayerService;
import jakarta.websocket.server.PathParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private PlayerService playerService;
    private AuthMemberService authMemberService;

    public PlayerController(PlayerService playerService, AuthMemberService authMemberService) {
        this.playerService = playerService;
        this.authMemberService = authMemberService;
    }

    @PostMapping("")
    public Player addPlayer(@RequestBody Map<String, Object> requestBody) {
        String playerName = (String) requestBody.get("playerName");
        Integer teamId = (Integer) requestBody.get("teamId");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String coachName = authentication.getName();
       authMemberService.isCoachOnTheTeam(teamId , coachName);
        return playerService.savePlayerInTeam(playerName, teamId);
    }

    @GetMapping("/all")
    public List<Player> getAllPlayers() {
        return playerService.findAllPlayers();
    }

    @GetMapping("")
    public List<Player> getPlayersInTeam(@PathParam("teamId") int teamId) {
        return playerService.findAllPlayersInTeam(teamId);
    }

    @GetMapping("/{playerId}")
    public Player getPlayerById(@PathVariable int playerId) {
        return playerService.findPlayerById(playerId);
    }

    @PutMapping("/{playerId}")
    public Player updatePlayerById(@PathVariable int playerId,
                                   @RequestBody Player updatedPlayer) {
        return playerService.updatePlayerNameOrTeamById(playerId, updatedPlayer);
    }

    @DeleteMapping("/{playerId}")
    public String deletePlayer(@PathVariable int playerId) {
        playerService.deletePlayer(playerId);

        return "Deleted Player id of - " + playerId;
    }
}
