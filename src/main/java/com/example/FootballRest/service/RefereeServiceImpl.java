package com.example.FootballRest.service;

import com.example.FootballRest.model.entity.League;
import com.example.FootballRest.model.entity.Member;
import com.example.FootballRest.model.entity.Referee;
import com.example.FootballRest.model.entity.Role;
import com.example.FootballRest.model.repo.LeagueRepo;
import com.example.FootballRest.model.repo.MemberRepo;
import com.example.FootballRest.model.repo.RefereeRepo;
import com.example.FootballRest.model.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RefereeServiceImpl implements RefereeService {
    private RefereeRepo refereeRepo;
    private LeagueRepo leagueRepo;
    private MemberRepo memberRepo;
    private RoleRepo roleRepo;

    @Autowired
    public RefereeServiceImpl(RefereeRepo refereeRepo, LeagueRepo leagueRepo, MemberRepo memberRepo, RoleRepo roleRepo) {
        this.refereeRepo = refereeRepo;
        this.leagueRepo = leagueRepo;
        this.memberRepo = memberRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public Referee saveRefereeInLeague(String refereeName, Integer leagueId) {
        Optional<League> league = leagueRepo.findById(leagueId);
        if (league.isPresent()) {
            Referee referee = new Referee();
            referee.setRefereeName(refereeName);
            referee.setLeague(league.get());
            Member refereeMember = new Member(refereeName, "{noop}123", true);
            Role refereeRole = new Role(refereeName, "ROLE_REFEREE");
            memberRepo.save(refereeMember);
            roleRepo.save(refereeRole);

            return refereeRepo.save(referee);
        } else {
            throw new RuntimeException("League with ID " + leagueId + " not found");
        }

    }


    @Override
    public List<Referee> findAllReferees() {
        return refereeRepo.findAll();
    }

    @Override
    public List<Referee> findAllRefereesInLeague(Integer leagueId) {
        // no need for find all referees manual
        // refereeRepo.findAllByLeagueId(leagueId); // jpa made this for us but i made for throw my exc not sql exc
        // but for simplicity gonna just use sql ex on teams and players
        Optional<League> foundLeague = leagueRepo.findById(leagueId);
        if (foundLeague.isPresent()) {
            return refereeRepo.findAllByLeagueId(foundLeague.get().getId());
        } else {
            throw new RuntimeException("League id not found - " + leagueId);
        }

    }

    @Override
    public Referee findRefereeById(Integer refereeId) {
        Optional<Referee> result = refereeRepo.findById(refereeId);
        Referee foundReferee = null;
        if (result.isPresent()) {
            foundReferee = result.get();

        } else {
            throw new RuntimeException("Referee id not found - " + refereeId);
        }
        return foundReferee;
    }

    @Override
    public Referee updateRefereeNameOrLeagueById(Integer id, Referee commingUpdatedReferee) {
        // check for referee id if is exsist or not
        Referee theUpdatedReferee = findRefereeById(id);

        League league = commingUpdatedReferee.getLeague();
        //2- check for league exsist or not (in case we wanna change league {by id} ) //++
        Optional<League> optionalLeague = leagueRepo.findById(league.getId());
        if (optionalLeague.isPresent()) {
            theUpdatedReferee.setRefereeName(commingUpdatedReferee.getRefereeName());
            theUpdatedReferee.setLeague(optionalLeague.get());
        } else {
            throw new RuntimeException("League id not found to update referee's league !! ");
        }

        refereeRepo.save(theUpdatedReferee);
        return theUpdatedReferee;
    }

    @Override
    public void deleteReferee(int referee_id) {
        Optional<Referee> theDeleteReferee = refereeRepo.findById(referee_id);

        if (theDeleteReferee.isPresent()) {
            refereeRepo.deleteById(referee_id);

        } else {
            throw new RuntimeException("Referee id not found  - " + referee_id);

        }
    }

}
