package com.rudkin.livefootballworldcupscoreboard.service;

import com.rudkin.livefootballworldcupscoreboard.domain.Match;
import java.util.List;

public interface Scoreboard {

  public void startGame(String homeTeam, String awayTeam);

  public void updateScore(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore);

  public void finishGame(String homeTeam, String awayTeam);

  public List<Match> getSummary();

}
