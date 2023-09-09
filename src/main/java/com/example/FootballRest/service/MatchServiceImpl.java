package com.example.FootballRest.service;

import com.example.FootballRest.model.entity.*;
import com.example.FootballRest.model.repo.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class MatchServiceImpl implements MatchService {
    MatchRepo matchRepo;
    TeamRepo teamRepo;
    PLayerRepo pLayerRepo;
    RefereeRepo refereeRepo;
    LeagueRepo leagueRepo;

     private DateTimeFormatter dateTimeFormatter;
    private LocalDateTime now;
    Random rand;
    private Team team1;
    private Team team2;
    private int firstRandomTeamScore;
    private int secRandomTeamScore;

    public MatchServiceImpl (
            MatchRepo matchRepo, TeamRepo teamRepo,
            PLayerRepo pLayerRepo, RefereeRepo refereeRepo,
            LeagueRepo leagueRepo
    ) {
        this.matchRepo = matchRepo;
        this.teamRepo = teamRepo;
        this.pLayerRepo = pLayerRepo;
        this.refereeRepo = refereeRepo;
        this.leagueRepo = leagueRepo;
        LocalDateTime now = null;
        this.rand = new Random();
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        this.firstRandomTeamScore = 0;
        this.secRandomTeamScore = 0;
    }

    @Override
    public Match startMatchOnLeague(int league_id) {

        List<Team> teamsInLeague = teamRepo.findTeamsByLeagueId(league_id);
        now = LocalDateTime.now();
        String theDate = dateTimeFormatter.format(now);

        int firstRandomTeam = rand.nextInt(teamsInLeague.size()); //get index team
        int secRandomTeam = rand.nextInt(teamsInLeague.size());
        while (firstRandomTeam == secRandomTeam) {
            firstRandomTeam = rand.nextInt(teamsInLeague.size());
            secRandomTeam = rand.nextInt(teamsInLeague.size());
        }
        firstRandomTeamScore = rand.nextInt(6);
        secRandomTeamScore = rand.nextInt(6);

        team1 = teamsInLeague.get(firstRandomTeam);  // prepare team for the match
        team2 = teamsInLeague.get(secRandomTeam);

        updatePlayerGoalScorer(team1, firstRandomTeamScore);
        updatePlayerGoalScorer(team2, secRandomTeamScore);

        // get random referee
        List<Referee> allRefereesOLeague = refereeRepo.findAllByLeagueId(league_id);
        int refereeIndex = rand.nextInt(allRefereesOLeague.size());
        if (refereeIndex == 0){
            refereeIndex = 1;
        }
        Referee referee = refereeRepo.getReferenceById(refereeIndex);



        // instead of setter and getters
        Match match = new Match(team1, team2,
                "" + firstRandomTeamScore + " : " + secRandomTeamScore, theDate, referee);
        matchRepo.save(match);
        return match;
    }

    private void updatePlayerGoalScorer(Team teamName, int randScore) {
        if (teamName.getPlayers().isEmpty()) {
            throw new RuntimeException("No players in the team.");
        }

        if (teamName.getPlayers().size() == 1) {
            Player nameOfTheGoal = teamName.getPlayers().get(0);
            if (nameOfTheGoal.getGoals() == null) {
                nameOfTheGoal.setGoals(1);

            } else {
                nameOfTheGoal.setGoals(nameOfTheGoal.getGoals() + 1);
            }
            pLayerRepo.save(nameOfTheGoal);
            return;
        }

        for (int i = 0; i < randScore; i++) {
            Player nameOfTheGoal = teamName.getPlayers().get(rand.nextInt(teamName.getPlayers().size()));
            if (nameOfTheGoal.getGoals() == null) {
                nameOfTheGoal.setGoals(1);
            } else {
                nameOfTheGoal.setGoals(nameOfTheGoal.getGoals() + 1);
            }
            pLayerRepo.save(nameOfTheGoal);

        }

    }

    @Override
    public List<Match> findAllMatches() {
        return matchRepo.findAll();
    }

    @Override
    public Match findMatchById(int match_id) {
        Optional<Match> optionalMatch = matchRepo.findById(match_id);
        if (optionalMatch.isPresent()) {
            return optionalMatch.get();
        } else {
            throw new RuntimeException("Match id is not found " + match_id);
        }
    }

}
