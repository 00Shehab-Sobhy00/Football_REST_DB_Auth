package com.example.FootballRest.model.repo;
import com.example.FootballRest.model.entity.Coach;
import com.example.FootballRest.model.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface CoachRepo extends JpaRepository<Coach, Integer> {
    List<Coach> findCoachesByTeamId(Integer teamId);

}