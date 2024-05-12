package utils;

import static utils.ObjectMother.createMatchArgentinaAustralia_3_1;
import static utils.ObjectMother.createMatchGermanyFrance_2_2;
import static utils.ObjectMother.createMatchMexicoCanada_0_5;
import static utils.ObjectMother.createMatchSpainBrazil_10_2;
import static utils.ObjectMother.createMatchUruguayItaly_6_6;

import com.rudkin.livefootballworldcupscoreboard.domain.Match;
import com.rudkin.livefootballworldcupscoreboard.service.Scoreboard;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUtils {

  public static Match createMatchWithScore(int homeTeamScore, int awayTeamScore) {
    var match = new Match("Home Team", "Away Team");
    match.setHomeTeamScore(homeTeamScore);
    match.setAwayTeamScore(awayTeamScore);

    return match;
  }

  public static void addMatchesToTheScoreboard(Scoreboard scoreboard) {
    addMatchMexicoCanada_0_5(scoreboard);
    addMatchSpainBrazil_10_2(scoreboard);
    addMatchGermanyFrance_2_2(scoreboard);
    addMatchUruguayItaly_6_6(scoreboard);
    addMatchArgentinaAustralia_3_1(scoreboard);
  }

  public static List<Match> getExpectedSummary() {
    return List.of(
        createMatchUruguayItaly_6_6(),
        createMatchSpainBrazil_10_2(),
        createMatchMexicoCanada_0_5(),
        createMatchArgentinaAustralia_3_1(),
        createMatchGermanyFrance_2_2()
    );
  }

  public static void addMatchMexicoCanada_0_5(Scoreboard scoreboard) {
    scoreboard.startGame("Mexico", "Canada");
    scoreboard.updateScore("Mexico", "Canada", 0, 5);
  }

  public static void addMatchSpainBrazil_10_2(Scoreboard scoreboard) {
    scoreboard.startGame("Spain", "Brazil");
    scoreboard.updateScore("Spain", "Brazil", 10, 2);
  }

  public static void addMatchGermanyFrance_2_2(Scoreboard scoreboard) {
    scoreboard.startGame("Germany", "France");
    scoreboard.updateScore("Germany", "France", 2, 2);
  }

  public static void addMatchUruguayItaly_6_6(Scoreboard scoreboard) {
    scoreboard.startGame("Uruguay", "Italy");
    scoreboard.updateScore("Uruguay", "Italy", 6, 6);
  }

  public static void addMatchArgentinaAustralia_3_1(Scoreboard scoreboard) {
    scoreboard.startGame("Argentina", "Australia");
    scoreboard.updateScore("Argentina", "Australia", 3, 1);
  }

}
