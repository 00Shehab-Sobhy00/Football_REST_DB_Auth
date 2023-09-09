package com.example.FootballRest.controllers;


import com.example.FootballRest.model.entity.League;
import com.example.FootballRest.model.entity.Referee;
import com.example.FootballRest.service.LeagueService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/leagues")

public class LeagueController {

    private LeagueService leagueService;
    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    // return all leagues
    @GetMapping("")
    public List<League> getAllLeagues() {
        return leagueService.findAll();
    }


    // return league by id
    @GetMapping("/{league_id}")
    public League getLeagueById(@PathVariable int league_id) {
        // + exposition handler  > better to be custom
        League foundLeague = leagueService.findById(league_id);
        if (foundLeague == null) {
            throw new RuntimeException("League id not found - " + league_id);
        }
        return foundLeague;
    }

//
    // add new league
    @PostMapping("")
    public League addLeague(@RequestBody League theLeague) {
        //  case if they pass an id in JSON ... we set to 0
        // this will force a save new item ... instead of update
        theLeague.setId(0);
        return leagueService.save(theLeague);
    }

    // update league : by object itself
    @PutMapping("")
    public League updateLeague(@RequestBody League theUpdateLeague) {
        return leagueService.save(theUpdateLeague);
    }


    // delete league
    @DeleteMapping("/{league_id}")
    public String deleteLeague(@PathVariable int league_id) {
        // + exposition handler  > better to be custom
        League theDeleteLeague = leagueService.findById(league_id);
        if (theDeleteLeague == null) {
            throw new RuntimeException("league id not found  - " + league_id);
        }
        leagueService.deleteById(league_id);

        return "Deleted league id of - " + league_id;
    }


}
