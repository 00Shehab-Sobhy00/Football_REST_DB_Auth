package com.example.FootballRest.model.repo;

import com.example.FootballRest.model.entity.Coach;
import com.example.FootballRest.model.entity.Match;
import com.example.FootballRest.model.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface TeamRepo  extends JpaRepository <Team,Integer> {

    // jpa repo can made the query for me ..
   List <Team> findTeamsByLeagueId (Integer leagueId);

    @Query("SELECT t.league.id FROM Team t JOIN t.league l WHERE t.id = :teamId")
    Integer findLeagueIdByTeamId(Integer teamId);


    @Query("SELECT c.coachName FROM Coach c JOIN c.team t WHERE t.id = :teamId")
    List<String> findAllCoachesNameOnATeam(Integer teamId);
//    List <Coach>
}



