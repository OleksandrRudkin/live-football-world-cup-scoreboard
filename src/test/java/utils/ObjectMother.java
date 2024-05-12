package utils;

import com.rudkin.livefootballworldcupscoreboard.domain.Match;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectMother {

  public static Match createMatchMexicoCanada_0_5() {
    Match match = new Match("Mexico", "Canada");
    match.setHomeTeamScore(0);
    match.setAwayTeamScore(5);

    return match;
  }

  public static Match createMatchSpainBrazil_10_2() {
    Match match = new Match("Spain", "Brazil");
    match.setHomeTeamScore(10);
    match.setAwayTeamScore(2);

    return match;
  }

  public static Match createMatchGermanyFrance_2_2() {
    Match match = new Match("Germany", "France");
    match.setHomeTeamScore(2);
    match.setAwayTeamScore(2);

    return match;
  }

  public static Match createMatchUruguayItaly_6_6() {
    Match match = new Match("Uruguay", "Italy");
    match.setHomeTeamScore(6);
    match.setAwayTeamScore(6);

    return match;
  }

  public static Match createMatchArgentinaAustralia_3_1() {
    Match match = new Match("Argentina", "Australia");
    match.setHomeTeamScore(3);
    match.setAwayTeamScore(1);

    return match;
  }

}
