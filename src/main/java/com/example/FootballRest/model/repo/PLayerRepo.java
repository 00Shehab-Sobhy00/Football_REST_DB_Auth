package com.example.FootballRest.model.repo;

import com.example.FootballRest.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PLayerRepo extends JpaRepository<Player, Integer> {
    List<Player> findPlayersByTeamId(Integer teamId);
}
