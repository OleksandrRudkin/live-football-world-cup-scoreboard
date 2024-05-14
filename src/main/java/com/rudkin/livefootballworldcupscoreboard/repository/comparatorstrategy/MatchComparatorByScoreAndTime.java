package com.rudkin.livefootballworldcupscoreboard.repository.comparatorstrategy;

import com.rudkin.livefootballworldcupscoreboard.domain.Match;
import java.util.Comparator;
import org.springframework.stereotype.Component;

/**
 * Represents a comparator for {@link Match} objects based on score and time.
 */
@Component
public class MatchComparatorByScoreAndTime implements Comparator<Match> {

  /**
   * Compares two {@link Match} objects based on score and time.
   *
   * @param match1 The first match to be compared.
   * @param match2 The second match to be compared.
   * @return An integer representing the comparison result.
   */
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
