package com.example.FootballRest.controllers;


import com.example.FootballRest.model.entity.Match;
import com.example.FootballRest.service.MatchService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {
   private MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/start")
    public Match startMatch(@PathParam("leagueId") int leagueId) {
        return matchService.startMatchOnLeague(leagueId);
    }


    @GetMapping("/all")
    public List <Match> getAllMatchs(){
        return matchService.findAllMatches();
    }



    @GetMapping("/{match_id}")
    public Match getMatchById(@PathVariable int match_id){
        return matchService.findMatchById(match_id);
    }

}
