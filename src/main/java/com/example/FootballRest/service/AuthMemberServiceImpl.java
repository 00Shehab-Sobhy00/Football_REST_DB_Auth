package com.example.FootballRest.service;


import com.example.FootballRest.model.entity.League;
import com.example.FootballRest.model.repo.LeagueRepo;
import com.example.FootballRest.model.repo.MemberRepo;
import com.example.FootballRest.model.repo.TeamRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthMemberServiceImpl implements AuthMemberService {
    private MemberRepo memberRepo;
    private LeagueRepo leagueRepo;
    private TeamRepo teamRepo;


    public AuthMemberServiceImpl(MemberRepo memberRepo, LeagueRepo leagueRepo, TeamRepo teamRepo) {
        this.memberRepo = memberRepo;
        this.leagueRepo = leagueRepo;
        this.teamRepo = teamRepo;
    }

    public void isLeagueManager(Integer leagueId, String isLeagueManagerName) {
        League league = new League();
        Optional<League> optionalLeague = leagueRepo.findById(leagueId);
        if (optionalLeague.isPresent()) {
            String leagueManagerName = optionalLeague.get().getLeagueManager();

            if (!leagueManagerName.equals(isLeagueManagerName)) {
                throw new RuntimeException( isLeagueManagerName + " that's not the League Manager of id : " + leagueId);
            }
        }


    }

    @Override
    public void isCoachOnTheTeam(Integer teamId, String isCoachName) {
        List<String> coachesNameOnATeam = teamRepo.findAllCoachesNameOnATeam(teamId);
        if (!coachesNameOnATeam.contains(isCoachName)){
            throw new RuntimeException(  isCoachName  +  " not including to team coaches of id : " + teamId);
        }

    }


}
