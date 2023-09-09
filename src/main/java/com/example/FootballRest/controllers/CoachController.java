package com.example.FootballRest.controllers;

import com.example.FootballRest.model.entity.Coach;
import com.example.FootballRest.service.AuthMemberService;
import com.example.FootballRest.service.CoachService;
import com.example.FootballRest.service.TeamService;
import jakarta.websocket.server.PathParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/coaches")
public class CoachController {

    private CoachService coachService;
    private TeamService teamService;
    private AuthMemberService authMemberService;

    public CoachController(CoachService coachService, TeamService teamService, AuthMemberService authMemberService) {
        this.coachService = coachService;
        this.teamService = teamService;
        this.authMemberService = authMemberService;
    }

    @PostMapping("")
    public Coach addCoach(@RequestBody Map<String, Object> requestBody) {
        String coachName = (String) requestBody.get("coachName");
        Integer teamId = (Integer) requestBody.get("teamId");

        int leagueId = teamService.leagueIdByTeamId(teamId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String manager = authentication.getName();
        authMemberService.isLeagueManager(leagueId, manager);

        return coachService.saveCoachInTeam(coachName, teamId);
    }

    @GetMapping("/all")
    public List<Coach> getAllCoaches() {
        return coachService.findAllCoaches();
    }

    @GetMapping("")
    public List<Coach> getCoachesInTeam(@PathParam("teamId") int teamId) {
        return coachService.findAllCoachesInTeam(teamId);
    }

    @GetMapping("/{coachId}")
    public Coach getCoachById(@PathVariable int coachId) {
        return coachService.findCoachById(coachId);
    }

    @PutMapping("/{coachId}")
    public Coach updateCoachById(@PathVariable int coachId, @RequestBody Coach updatedCoach) {
        return coachService.updateCoachNameOrTeamById(coachId, updatedCoach);
    }

    @DeleteMapping("/{coachId}")
    public String deleteCoach(@PathVariable int coachId) {
        coachService.deleteCoach(coachId);
        return "Deleted Coach id of - " + coachId;
    }
}