package com.rudkin.livefootballworldcupscoreboard.matchservice;

import com.rudkin.livefootballworldcupscoreboard.entities.Match;
import com.rudkin.livefootballworldcupscoreboard.exception.MatchAlreadyExistsException;
import com.rudkin.livefootballworldcupscoreboard.exception.NoSuchGameException;
import java.util.ArrayList;
import java.util.Comparator;
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
    var gameToStart = new Match(homeTeam, awayTeam);

    boolean objectExists = matches.stream()
        .anyMatch(obj -> obj.equals(gameToStart));

    if (objectExists) {
      throw new MatchAlreadyExistsException(
          String.format("The match %s is already on thr Scoreboard", gameToStart));
    } else {
      matches.add(gameToStart);
    }
  }

  public void updateScore(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {
    for (Match match : matches) {
      if (match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam)) {
        log.info("Updating score for game between {} and {}: {} - {}", homeTeam, awayTeam,
            homeTeamScore, awayTeamScore);
        match.updateScore(homeTeamScore, awayTeamScore);
        break;
      }
    }
  }

  public void finishGame(String homeTeam, String awayTeam) {
    log.info("Finishing game between {} and {}", homeTeam, awayTeam);
    boolean isNotRemoved = !matches.removeIf(
        match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam));

    if (isNotRemoved) {
      throw new NoSuchGameException(
          String.format("The game between %s and %s is not on the Scoreboard", homeTeam, awayTeam));
    }
  }

  public List<Match> getSummary() {
    List<Match> summary = new ArrayList<>(matches);
    summary.sort(Comparator.comparingInt(
            (Match match) -> match.getHomeTeamScore() + match.getAwayTeamScore())
        .reversed()
        .thenComparing(Comparator.comparingLong((Match match) -> matches.indexOf(match))
            .reversed()));
    log.info("Retrieving match summary");
    return summary;
  }

}