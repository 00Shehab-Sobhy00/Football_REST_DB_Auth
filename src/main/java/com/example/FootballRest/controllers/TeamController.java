package com.example.FootballRest.controllers;


import com.example.FootballRest.model.entity.Team;
import com.example.FootballRest.service.AuthMemberService;
import com.example.FootballRest.service.TeamService;
import jakarta.websocket.server.PathParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private TeamService teamService;
    private AuthMemberService authMemberService;

    public TeamController(TeamService teamService, AuthMemberService authMemberService) {
        this.teamService = teamService;
        this.authMemberService = authMemberService;
    }

    //add team in league
    @PostMapping("")
    public Team addTeam(@RequestBody Map<String, Object> requestBody) {
        String teamName = (String) requestBody.get("teamName");
        Integer leagueId = (Integer) requestBody.get("leagueId");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String manager = authentication.getName();
        authMemberService.isLeagueManager(leagueId, manager);
        return teamService.saveTeamInLeague(teamName, leagueId);

    }

    @GetMapping("/all")  //
    public List<Team> getReferees() {
        return teamService.findAllTeams();
    }


//     get all teams in league
    @GetMapping("")  //
    public List <Team> getTeamsInLeague(@PathParam("leagueId") int leagueId) {
       return  teamService.findAllTeamsInLeague(leagueId);
    }


    @GetMapping("/{team_Id}")
    public Team getRefereeById(@PathVariable int team_Id) {

        return teamService.findTeamById(team_Id);

    }

    @PutMapping("/{team_Id}")
    public Team updateRefereeById(@PathVariable int team_Id,
                                     @RequestBody Team updatedTeam) {
        return teamService.updateTeamNameOrLeagueById(team_Id,updatedTeam);

    }



    @DeleteMapping("/{team_id}")
    public String deleteTeam(@PathVariable int team_id){
         teamService.deleteTeamById(team_id);
        return "Deleted Team id of - " + team_id;
    }




}
