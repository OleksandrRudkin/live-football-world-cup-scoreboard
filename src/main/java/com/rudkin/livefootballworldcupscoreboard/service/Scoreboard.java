package com.rudkin.livefootballworldcupscoreboard.service;

import com.rudkin.livefootballworldcupscoreboard.domain.Match;
import java.util.List;

public interface Scoreboard {

   void startGame(String homeTeam, String awayTeam);

   void updateScore(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore);

   void finishGame(String homeTeam, String awayTeam);

   List<Match> getSummary();

}
