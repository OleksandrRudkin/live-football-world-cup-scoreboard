package com.rudkin.livefootballworldcupscoreboard.domain;

import com.rudkin.livefootballworldcupscoreboard.exception.UpdateScoreException;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents a football match.
 */
@Data
@EqualsAndHashCode(exclude = {"createdAt", "id"})
public class Match {

  private final String id;
  private final LocalDateTime createdAt;
  private final String homeTeam;
  private final String awayTeam;
  private int homeTeamScore;
  private int awayTeamScore;

  public Match(String homeTeam, String awayTeam) {
    this.createdAt = LocalDateTime.now();
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.id = generateID();
  }

  public String asTeamNames() {
    return String.join("_", homeTeam, awayTeam);
  }

  private String generateID() {
    return String.join("_", homeTeam, awayTeam, createdAt.toString());
  }

  public int calculateTotalScore() {
    return homeTeamScore + awayTeamScore;
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