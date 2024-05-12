package com.rudkin.livefootballworldcupscoreboard.entities;

import com.rudkin.livefootballworldcupscoreboard.exception.UpdateScoreException;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Match {

  private final String homeTeam;
  private final String awayTeam;
  private int homeTeamScore;
  private int awayTeamScore;

  public void updateScore(int homeTeamScore, int awayTeamScore) {
    if (homeTeamScore < 0 || awayTeamScore < 0) {
      throw new UpdateScoreException("Score can not be negative");
    }
    this.homeTeamScore = homeTeamScore;
    this.awayTeamScore = awayTeamScore;
  }

}