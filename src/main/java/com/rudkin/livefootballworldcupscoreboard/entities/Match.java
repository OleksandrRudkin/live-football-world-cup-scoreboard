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

  public int calculateTotalScore() {
    return homeTeamScore + awayTeamScore;
  }

  public boolean compareByTeamNames(String homeTeam, String awayTeam) {
    return this.homeTeam.equals(homeTeam) && this.awayTeam.equals(awayTeam);
  }

  public boolean compareByTeamNames(Match matchToCompare) {
    return compareByHomeTeamName(matchToCompare) && compareByAwayTeamName(matchToCompare);
  }

  private boolean compareByAwayTeamName(Match matchToCompare) {
    return matchToCompare.getAwayTeam().equals(awayTeam);
  }

  private boolean compareByHomeTeamName(Match matchToCompare) {
    return matchToCompare.getHomeTeam().equals(homeTeam);
  }

  public void updateScore(int homeTeamScore, int awayTeamScore) {
    throwIfNegative(homeTeamScore, awayTeamScore);

    this.homeTeamScore = homeTeamScore;
    this.awayTeamScore = awayTeamScore;
  }

  private void throwIfNegative(int homeTeamScore, int awayTeamScore) {
    if (homeTeamScore < 0 || awayTeamScore < 0) {
      throw new UpdateScoreException("Score can not be negative");
    }
  }

}