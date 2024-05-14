package com.rudkin.livefootballworldcupscoreboard.repository;

import com.rudkin.livefootballworldcupscoreboard.domain.Match;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class HashMapMatchesRepository implements MatchesRepository {

  private final Map<String, Match> matches;
  private final Comparator<Match> comparator;

  public HashMapMatchesRepository(Comparator<Match> comparator) {
    this.comparator = comparator;
    this.matches = new ConcurrentHashMap<>();
  }

  @Override
  public boolean add(Match match) {
    var key = match.asTeamNames();

    Optional<Match> optionalMatch = Optional.ofNullable(matches.putIfAbsent(key, match));

    return optionalMatch.isEmpty();
  }

  @Override
  public Optional<Match> findByTeamNames(String homeTeam, String awayTeam) {
    String key = generateMatchKey(homeTeam, awayTeam);

    return Optional.of(matches.get(key));
  }

  @Override
  public boolean remove(String homeTeam , String awayTeam) {
    String key = generateMatchKey(homeTeam, awayTeam);
    Optional<Match> optionalMatch = Optional.ofNullable(matches.remove(key));

    return optionalMatch.isPresent();
  }

  @Override
  public boolean update(Match match) {
    var key = match.asTeamNames();
    Optional<Match> optionalMatch = Optional.ofNullable(
        matches.computeIfPresent(key, (k, v) -> match));

    return optionalMatch.isPresent();
  }

  @Override
  public List<Match> findAll() {
    return matches.values().stream()
        .sorted(comparator)
        .toList();
  }

  private static String generateMatchKey(String homeTeam , String awayTeam) {
    return String.join("_", homeTeam, awayTeam);
  }
}