package com.example.FootballRest.service;

import com.example.FootballRest.model.entity.Coach;

import java.util.List;

public interface CoachService {
    Coach saveCoachInTeam(String coachName, Integer teamId);

    List<Coach> findAllCoaches();

    List<Coach> findAllCoachesInTeam(Integer teamId);

    Coach findCoachById(Integer coachId);

    Coach updateCoachNameOrTeamById(Integer coachId, Coach updatedCoach);

    void deleteCoach(int coachId);
}
