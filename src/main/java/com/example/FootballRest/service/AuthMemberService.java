package com.example.FootballRest.service;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

public interface AuthMemberService {

    void isLeagueManager(Integer leagueId, String isLeagueManager);

    void isCoachOnTheTeam (Integer teamId , String coachName);
}
