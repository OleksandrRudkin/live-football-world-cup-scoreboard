package com.rudkin.livefootballworldcupscoreboard.matchservice;

import com.rudkin.livefootballworldcupscoreboard.entities.Match;
import com.rudkin.livefootballworldcupscoreboard.exception.MatchAlreadyExistsException;
import com.rudkin.livefootballworldcupscoreboard.exception.NoSuchGameException;
import com.rudkin.livefootballworldcupscoreboard.exception.UpdateScoreException;
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

    throwIfExists(gameToStart);

    matches.add(gameToStart);
  }

  private void throwIfExists(Match gameToStart) {
    if (isGameOnScoreboard(gameToStart)) {
      throw new MatchAlreadyExistsException(
          String.format("The match %s is already on the Scoreboard", gameToStart));
    }
  }

  private boolean isGameOnScoreboard(Match gameToStart) {
    return matches.stream()
        .anyMatch(game -> game.compareByTeamNames(gameToStart));
  }

  public void updateScore(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {

    Match matchToUpdate = matches.stream()
        .filter(match -> match.compareByTeamNames(homeTeam, awayTeam))
        .findFirst()
        .orElseThrow(() -> new UpdateScoreException(String.format("Match not found: %s vs %s", homeTeam, awayTeam)));

    log.info("Updating score for game between {} and {}: {} - {}", homeTeam, awayTeam,
                                                                   homeTeamScore, awayTeamScore);

    matchToUpdate.updateScore(homeTeamScore, awayTeamScore);
  }

  public void finishGame(String homeTeam, String awayTeam) {
    log.info("Finishing game between {} and {}", homeTeam, awayTeam);
    boolean isNotRemoved = !matches.removeIf(
        match -> match.compareByTeamNames(homeTeam, awayTeam));

    if (isNotRemoved) {
      throw new NoSuchGameException(
          String.format("The game between %s and %s is not on the Scoreboard", homeTeam, awayTeam));
    }
  }

  public List<Match> getSummary() {
    log.info("Retrieving match summary");

    return matches.stream()
        .sorted(compareByScoreReversed()
            .thenComparing(compareByIndexReversed()))
        .toList();
  }

  private Comparator<Match> compareByIndexReversed() {
    return Comparator.comparingLong((Match match) -> matches.indexOf(match))
        .reversed();
  }

  private Comparator<Match> compareByScoreReversed() {
    return Comparator.comparingInt(Match::calculateTotalScore)
        .reversed();
  }
}