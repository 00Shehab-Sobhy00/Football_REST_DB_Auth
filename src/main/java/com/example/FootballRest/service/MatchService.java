package com.example.FootballRest.service;

import com.example.FootballRest.model.entity.Match;


import java.util.List;

public interface MatchService {

    Match startMatchOnLeague (int league_id);
    List<Match> findAllMatches();
    Match findMatchById(int match_id);



}
