package com.example.FootballRest.service;

import com.example.FootballRest.model.entity.League;
import com.example.FootballRest.model.entity.Member;
import com.example.FootballRest.model.entity.Role;
import com.example.FootballRest.model.repo.LeagueRepo;
import com.example.FootballRest.model.repo.MemberRepo;
import com.example.FootballRest.model.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeagueServiceImpl implements LeagueService {

    private LeagueRepo leagueRepo;
    private MemberRepo memberRepo;
    private RoleRepo roleRepo;

    public LeagueServiceImpl(LeagueRepo leagueRepo, MemberRepo memberRepo, RoleRepo roleRepo) {
        this.leagueRepo = leagueRepo;
        this.memberRepo = memberRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public League save(League theLeague) {
        String leagueManagerName = theLeague.getLeagueManager();
        Member insertMember = new Member();
        // default data
        insertMember.setUserId(leagueManagerName);
        insertMember.setPassword("{noop}123");
        insertMember.setActive(true);
        memberRepo.save(insertMember);

        Role leagueManager = new Role();
        leagueManager.setUserId(leagueManagerName);
        leagueManager.setRole("ROLE_MANAGER");
        roleRepo.save(leagueManager);

        return leagueRepo.save(theLeague);
    }

    @Override
    public League findById(int this_Id) {
        Optional <League> result = leagueRepo.findById(this_Id);
        League foundLeague = null;

        if(result.isPresent()){
            foundLeague = result.get();
        }else {
            throw new RuntimeException ("Did not found league id - " + this_Id);
        }
        return foundLeague;
    }

    @Override
    public List<League> findAll() {
        return  leagueRepo.findAll();
    }

    @Override
    public void deleteById(int this_Id) {
       leagueRepo.deleteById(this_Id);
    }
}
