package com.rudkin.livefootballworldcupscoreboard.service;

import com.rudkin.livefootballworldcupscoreboard.domain.Match;
import com.rudkin.livefootballworldcupscoreboard.exception.MatchAlreadyExistsException;
import com.rudkin.livefootballworldcupscoreboard.exception.NoSuchGameException;
import com.rudkin.livefootballworldcupscoreboard.exception.UpdateScoreException;
import com.rudkin.livefootballworldcupscoreboard.repository.MatchesRepository;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleScoreboard implements Scoreboard {

  private List<Match> matches;
  private final MatchesRepository repository;

  @Override
  public void startGame(String homeTeam, String awayTeam) {
    var gameToStart = new Match(homeTeam, awayTeam);
    throwIfExists(gameToStart);

    log.info("Starting a new game between {} and {}", homeTeam, awayTeam);

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

  @Override
  public void updateScore(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {

    Match matchToUpdate = matches.stream()
        .filter(match -> match.compareByTeamNames(homeTeam, awayTeam))
        .findFirst()
        .orElseThrow(() -> new UpdateScoreException(String.format("Match not found: %s vs %s", homeTeam, awayTeam)));

    log.info("Updating score for game between {} and {}: {} - {}", homeTeam, awayTeam,
                                                                   homeTeamScore, awayTeamScore);

    matchToUpdate.updateScore(homeTeamScore, awayTeamScore);
  }

  @Override
  public void finishGame(String homeTeam, String awayTeam) {
    log.info("Finishing game between {} and {}", homeTeam, awayTeam);
    boolean isNotRemoved = !matches.removeIf(
        match -> match.compareByTeamNames(homeTeam, awayTeam));

    if (isNotRemoved) {
      throw new NoSuchGameException(
          String.format("The game between %s and %s is not on the Scoreboard", homeTeam, awayTeam));
    }
  }

  @Override
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