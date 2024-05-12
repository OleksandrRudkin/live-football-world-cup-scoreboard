package com.rudkin.livefootballworldcupscoreboard.repository.comparatorstrategy;

import com.rudkin.livefootballworldcupscoreboard.domain.Match;
import java.util.Comparator;
import org.springframework.stereotype.Component;

@Component
public class MatchComparatorByScoreAndTime implements Comparator<Match> {

  @Override
  public int compare(Match match1, Match match2) {
    Comparator<Match> matchComparator = compareByScoreAndTime();
    return matchComparator.compare(match1, match2);
  }

  private Comparator<Match> compareByScoreAndTime() {
    return compareByScoreReversed()
        .thenComparing(compareByTimeReversed());
  }

  private Comparator<Match> compareByTimeReversed() {
    return Comparator.comparing(Match::getCreatedAt)
        .reversed();
  }

  private Comparator<Match> compareByScoreReversed() {
    return Comparator.comparingInt(Match::calculateTotalScore)
        .reversed();
  }
}
