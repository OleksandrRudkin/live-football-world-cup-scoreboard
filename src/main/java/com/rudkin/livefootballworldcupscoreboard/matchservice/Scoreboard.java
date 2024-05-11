package com.rudkin.livefootballworldcupscoreboard.matchservice;

import static java.util.Collections.emptyList;

import com.rudkin.livefootballworldcupscoreboard.entities.Match;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Scoreboard {

  private final List<Match> matches;

  public Scoreboard() {
    this.matches = new ArrayList<>();
  }

  public void startGame(String homeTeam, String awayTeam) {
    log.info("Starting a new game between {} and {}", homeTeam, awayTeam);
  }

  public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
    log.info("Updating score for game between {} and {}: {} - {}", homeTeam, awayTeam, homeScore, awayScore);
  }

  public void finishGame(String homeTeam, String awayTeam) {
    log.info("Finishing game between {} and {}", homeTeam, awayTeam);
  }

  public List<Match> getSummary() {
    log.info("Retrieving match summary");

    return emptyList();
  }

}

