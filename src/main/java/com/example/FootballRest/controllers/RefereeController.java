package com.example.FootballRest.controllers;


import com.example.FootballRest.model.entity.Referee;
import com.example.FootballRest.service.AuthMemberService;
import com.example.FootballRest.service.RefereeService;
import jakarta.websocket.server.PathParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/referees")
public class RefereeController {
    private RefereeService refereeService;
    private AuthMemberService authMemberService;

    public RefereeController(RefereeService refereeService, AuthMemberService authMemberService, AuthMemberService authMemberService1) {
        this.refereeService = refereeService;
        this.authMemberService = authMemberService1;
    }

    //add referee in league
    @PostMapping("")
    public Referee addReferee(@RequestBody Map<String, Object> requestBody) {
        String refereeName = (String) requestBody.get("refereeName");
        Integer leagueId = (Integer) requestBody.get("leagueId");
        // check for league manager
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String manager = authentication.getName();
        authMemberService.isLeagueManager(leagueId, manager);
        return refereeService.saveRefereeInLeague(refereeName, leagueId);

    }

    // get all referees in league
    @GetMapping("/all")  //
    public List<Referee> getReferees() {
        return refereeService.findAllReferees();
    }


    // get all referees in league
    @GetMapping("")  //
    public List<Referee> getRefereesInLeague(@PathParam("leagueId") int leagueId) {
        return refereeService.findAllRefereesInLeague(leagueId);
    }


    // get referee by id
    @GetMapping("/{referee_Id}")
    public Referee getRefereeById(@PathVariable int referee_Id) {
        Referee refereeResult = refereeService.findRefereeById(referee_Id);
        if (refereeResult == null) {
            throw new RuntimeException("League id not found - " + referee_Id);
        }
        return refereeResult;

    }

    //,consumes = MediaType.APPLICATION_JSON_VALUE,produces =  MediaType.APPLICATION_JSON_VALUE // no need
    // update one referee in
    @PutMapping(value = "/{referee_Id}")
    public Referee updateRefereeById(@PathVariable int referee_Id,
                                     @RequestBody Referee updatedReferee) {
        return refereeService.updateRefereeNameOrLeagueById(referee_Id, updatedReferee);

    }

    @DeleteMapping("/{referee_id}")
    public String deleteReferee(@PathVariable int referee_id) {
        refereeService.deleteReferee(referee_id);
        return "Referee Team id of - " + referee_id;
    }


}