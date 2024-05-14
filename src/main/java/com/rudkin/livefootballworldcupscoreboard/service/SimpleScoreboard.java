package com.rudkin.livefootballworldcupscoreboard.service;

import com.rudkin.livefootballworldcupscoreboard.domain.Match;
import com.rudkin.livefootballworldcupscoreboard.exception.MatchAlreadyExistsException;
import com.rudkin.livefootballworldcupscoreboard.exception.NoSuchGameException;
import com.rudkin.livefootballworldcupscoreboard.exception.UpdateScoreException;
import com.rudkin.livefootballworldcupscoreboard.repository.MatchesRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleScoreboard implements Scoreboard {

  private final MatchesRepository repository;

  @Override
  public void startGame(String homeTeam, String awayTeam) {
    var gameToStart = new Match(homeTeam, awayTeam);

    log.info("Starting a new game between {} and {}", homeTeam, awayTeam);

    boolean isAdded = repository.add(gameToStart);

    if (!isAdded) {
      throw new MatchAlreadyExistsException(
          String.format("The match %s is already on the Scoreboard", gameToStart.asTeamNames()));
    }
  }

  @Override
  public void updateScore(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore) {
    Optional<Match> matchToUpdate = repository.findByTeamNames(homeTeam, awayTeam);

    Match match = matchToUpdate.orElseThrow(() ->
        new UpdateScoreException(String.format("Match not found: %s vs %s", homeTeam, awayTeam)));

    match.updateScore(homeTeamScore, awayTeamScore);

    log.info("Updating score for game between {} and {}: {} - {}", homeTeam, awayTeam,
                                                                   homeTeamScore, awayTeamScore);
    repository.update(match);
  }

  @Override
  public void finishGame(String homeTeam, String awayTeam) {
    log.info("Finishing game between {} and {}", homeTeam, awayTeam);

    boolean isNotRemoved = !repository.remove(homeTeam, awayTeam);

    if (isNotRemoved) {
      throw new NoSuchGameException(
          String.format("The game between %s and %s is not on the Scoreboard", homeTeam, awayTeam));
    }
  }

  @Override
  public List<Match> getSummary() {
    log.info("Retrieving match summary");

    return repository.findAll();
  }

}