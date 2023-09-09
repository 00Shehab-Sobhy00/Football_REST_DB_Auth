# Football_REST_DB

    •	Only the default admin can create the league and its league manager.
    •	Only the league manager can create referees, create team, and create a coach to team.
    •	Only the coach can add players to his assigned team.
    •	Only the referee can create a match between two teams.
    •	Anyone should be able to access the match result and related info 
 
     1- leagues api 
     
     --request POST \
     --url http://localhost:8080/leagues  
     
     -- request GET all leagues \
     --url  http://localhost:8080/leagues
     
     --request PUT \
     --url  http://localhost:8080/leagues?id=2
     
     --request DELETE \
     --url  http://localhost:8080/leagues/2
     
     req body on post and put

     {
     "leagueName": "worldCup2"
     }
     // --------------------------------------------------------------------------------------------------------------------------------------------//
 
     2- referees api 

     --request POST \
     --url  http://localhost:8080/referees    
     
     --request GET all referees \
     --url  http://localhost:8080/referees/all
     
     --request GET all referees on league throw params\
     --url  http://localhost:8080/referees?leagueId=1
     
     --request GET referee by id \
     --url  http://localhost:8080/referees/1
     
     --request PUT \
     --url  http://localhost:8080/referees/2
     
     --request DELETE \
     --url  http://localhost:8080/referees/3 
     
     req body on post and put : u can chage referee's league as well 
     
    {
    "id": 10,
    "refereeName": "ref update .",
    "league": {
        "id": 1,
        "leagueName": "worldCup1test has been updated "
    }
    } 

      // --------------------------------------------------------------------------------------------------------------------------------------------//
      3-teams api  
      
     --request POST \
     --url  http://localhost:8080/teams    
     
     --request GET all referees \
     --url  http://localhost:8080/teams/all
     
     --request GET all teams on sp_leageue / throw params
     --url   http://localhost:8080/teams?leagueId=3
     
     --request GET teams by id \
     --url   http://localhost:8080/teams/9
     
     --request PUT \
     --url   http://localhost:8080/teams/5
     
     --request DELETE \
     --url   http://localhost:8080/teams/5
     
     req body on post and put : u can chage teams's league as well 
     
     {
      "id": 6,
    "teamName": "pla  pla pla",
    "league": {
        "id": 3,
        "leagueName": "worldCup1test has been updated "
    }
    }
     // --------------------------------------------------------------------------------------------------------------------------------------------//
     4-players api  
     
     --request POST \
     --url  http://localhost:8080/players
     
     --request GET all players \
     --url  http://localhost:8080/players/all
     
     --request GET all players on sp_team / throw params
     --url   http://localhost:8080/players?teamId=6
     
     --request GET player by id \
     --url   http://localhost:8080/players/3
     
     --request PUT \
     --url   http://localhost:8080/players/3
     
     --request DELETE \
     --url   http://localhost:8080/players/6
     
    req body on post and put : u can chage player's teams as well 
  
    {
    "id": 3,
    "playerName": "player3333333333333333 from 5 to 6  .. ",
    "team": {
        "id": 6,
        "teamName": "Team Post for players test 1 .. ",
        "league": {
            "id": 3,
            "leagueName": "worldCup1test has been updated "
        }
    }
    }
    // --------------------------------------------------------------------------------------------------------------------------------------------//
 
    5-players api  

     --request POST \
     --url http://localhost:8080/coaches
     
     --request GET all coaches \
     --url  http://localhost:8080/coaches/all
     
     --request GET all coaches on sp_team / throw params
     --url  http://localhost:8080/coaches?teamId=6
     
     --request GET coache by id \
     --url  http://localhost:8080/coaches/3
     
     --request PUT \
     --url   http://localhost:8080/coaches/1
     
     --request DELETE \
     --url   http://localhost:8080/coaches/8
     
     req body on post and put : u can chage coach's teams as well 
  
    {
    "id": 3,
    "coachName": "coach one on team 6 to 5  ",
    "team": {
        "id": 5,
        "teamName": "Team Post for players test 1 .. ",
        "league": {
            "id": 3,
            "leagueName": "worldCup1test has been updated "
        }
    }
   }
 
    // --------------------------------------------------------------------------------------------------------------------------------------------//
    6-matches api   : match is been started by selecting 
    2 random teams on league and store the result on db aslo updating player scores if they score a goal on match

    --request POST \ 
     --url http://localhost:8080/matches/start?leagueId=1
     
    --request GET all matches \
     --url  http://localhost:8080/matches/all
     
     --request GET matches by id \
     --url http://localhost:8080/matches/1

     

















