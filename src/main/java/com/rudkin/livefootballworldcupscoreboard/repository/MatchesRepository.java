package com.rudkin.livefootballworldcupscoreboard.repository;

import com.rudkin.livefootballworldcupscoreboard.domain.Match;
import java.util.List;
import java.util.Optional;

public interface MatchesRepository {

  boolean add(Match match);

  Optional<Match> findByTeamNames(String homeTeam , String awayTeam);

  boolean remove(String homeTeam , String awayTeam);

  boolean update(Match match);

  List<Match> findAll();

}
