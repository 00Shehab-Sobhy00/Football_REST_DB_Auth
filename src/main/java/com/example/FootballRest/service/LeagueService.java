package com.example.FootballRest.service;

import com.example.FootballRest.model.entity.League;

import java.util.List;

public interface LeagueService {

    League save(League league);

    League findById(int id);

    List<League> findAll();

    void deleteById(int id);


}
