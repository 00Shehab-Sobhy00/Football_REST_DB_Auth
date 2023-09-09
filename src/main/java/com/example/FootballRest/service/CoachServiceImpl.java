package com.example.FootballRest.service;

import com.example.FootballRest.model.entity.Coach;
import com.example.FootballRest.model.entity.Member;
import com.example.FootballRest.model.entity.Role;
import com.example.FootballRest.model.entity.Team;
import com.example.FootballRest.model.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachServiceImpl implements CoachService {
    private CoachRepo coachRepo;
    private TeamRepo teamRepo;
    private MemberRepo memberRepo;
    private RoleRepo roleRepo;

    @Autowired
    public CoachServiceImpl(CoachRepo coachRepo, TeamRepo teamRepo, MemberRepo memberRepo, RoleRepo roleRepo) {
        this.coachRepo = coachRepo;
        this.teamRepo = teamRepo;
        this.memberRepo = memberRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public Coach saveCoachInTeam(String coachName, Integer teamId) {
        Optional<Team> team = teamRepo.findById(teamId);
        if (team.isPresent()) {
            //default implementation
            Member coachMember = new Member(coachName, "{noop}123", true);
            Role coachRole = new Role(coachName, "ROLE_COACH");
            memberRepo.save(coachMember);
            roleRepo.save(coachRole);

            Coach coach = new Coach();
            coach.setCoachName(coachName);
            coach.setTeam(team.get());
            return coachRepo.save(coach);
        } else {
            throw new RuntimeException("Team with ID " + teamId + " not found");
        }
    }

    @Override
    public List<Coach> findAllCoaches() {
        return coachRepo.findAll();
    }

    @Override
    public List<Coach> findAllCoachesInTeam(Integer teamId) {
        return coachRepo.findCoachesByTeamId(teamId);
    }

    @Override
    public Coach findCoachById(Integer coachId) {
        Optional<Coach> result = coachRepo.findById(coachId);
        Coach foundCoach = null;
        if (result.isPresent()) {
            foundCoach = result.get();
        } else {
            throw new RuntimeException("Coach id not found - " + coachId);
        }
        return foundCoach;
    }

    @Override
    public Coach updateCoachNameOrTeamById(Integer coachId, Coach updatedCoach) {
        Coach theUpdatedCoach = findCoachById(coachId);
        Team team = updatedCoach.getTeam();
        Optional<Team> optionalTeam = teamRepo.findById(team.getId());
        if (optionalTeam.isPresent()) {
            theUpdatedCoach.setCoachName(updatedCoach.getCoachName());
            theUpdatedCoach.setTeam(optionalTeam.get());
        } else {
            throw new RuntimeException("Team id not found to update coach's team");
        }
        coachRepo.save(theUpdatedCoach);
        return theUpdatedCoach;
    }

    @Override
    public void deleteCoach(int coachId) {
        Optional<Coach> theDeleteCoach = coachRepo.findById(coachId);

        if (theDeleteCoach.isPresent()) {
            coachRepo.deleteById(coachId);
        } else {
            throw new RuntimeException("Coach id not found - " + coachId);
        }

    }
}

