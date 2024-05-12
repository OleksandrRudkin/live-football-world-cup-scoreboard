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
    return false;
  }

  @Override
  public Optional<Match> findByTeamNames(String homeTeam, String awayTeam) {
    return Optional.empty();
  }

  @Override
  public boolean remove(String homeTeam, String awayTeam) {
    return false;
  }

  @Override
  public boolean update(Match match) {
    return false;
  }

  @Override
  public List<Match> findAll() {
    return null;
  }

}
