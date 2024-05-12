package com.rudkin.livefootballworldcupscoreboard.matchservice;

import static com.rudkin.livefootballworldcupscoreboard.utils.TestUtils.addMatchesToTheScoreboard;
import static com.rudkin.livefootballworldcupscoreboard.utils.TestUtils.createMatchWithScore;
import static com.rudkin.livefootballworldcupscoreboard.utils.TestUtils.getExpectedSummary;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.rudkin.livefootballworldcupscoreboard.entities.Match;
import com.rudkin.livefootballworldcupscoreboard.exception.MatchAlreadyExistsException;
import com.rudkin.livefootballworldcupscoreboard.exception.NoSuchGameException;
import com.rudkin.livefootballworldcupscoreboard.exception.UpdateScoreException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ScoreboardTest {

  private Scoreboard scoreboard;

  @BeforeEach
  void init() {
    scoreboard = new Scoreboard();
    scoreboard.startGame("Home Team", "Away Team");
  }

  @Test
  @DisplayName("should add new match to the match list when start game")
  void shouldAddNewMatchToTheMatchListWhenStartGame() {
    List<Match> summary = scoreboard.getSummary();

    assertThat(summary)
        .hasSize(1)
        .containsExactly(createMatchWithScore(0, 0));
  }

  @Test
  @DisplayName("should throw MatchAlreadyExistsException when add duplicate match")
  void shouldThrowMatchAlreadyExistsExceptionWhenAddDuplicateMatch() {

    assertThatExceptionOfType(MatchAlreadyExistsException.class)
        .isThrownBy(() ->
            scoreboard.startGame("Home Team", "Away Team"));
  }

  @Test
  @DisplayName("should update score of existing match when valid input")
  void shouldUpdateScoreOfExistingMatchWhenValidInput() {
    scoreboard.updateScore("Home Team", "Away Team", 1, 0);

    List<Match> summary = scoreboard.getSummary();

    assertThat(summary)
        .containsExactly(createMatchWithScore(1, 0));
  }

  @ParameterizedTest(name = "when home team score: {0} and away team score: {1}")
  @MethodSource("provideInvalidTeamScoresUpdates")
  void shouldThrowAnUpdateException(int homeTeamScore, int awayTeamScore) {

    assertThatExceptionOfType(UpdateScoreException.class)
        .isThrownBy(() ->
            scoreboard.updateScore("Home Team", "Away Team", homeTeamScore, awayTeamScore));
  }

  @Test
  @DisplayName("should remove game from the match game list when present")
  void shouldRemoveGameFromTheMatchGameListWhenPresent() {
    scoreboard.finishGame("Home Team", "Away Team");

    List<Match> summary = scoreboard.getSummary();

    assertThat(summary)
        .isEmpty();
  }

  @Test
  @DisplayName("should throw NoSuchGameException when match does not exist")
  void shouldThrowNoSuchGameExceptionWhenMatchDoesNotExist() {

    assertThatExceptionOfType(NoSuchGameException.class)
        .isThrownBy(() ->
            scoreboard.finishGame("Home Team 2", "Away Team 2"));
  }

  @Test
  @DisplayName("should sort matches when return summary")
  void shouldSortMatchesWhenReturnSummary() {
    scoreboard.finishGame("Home Team", "Away Team");
    addMatchesToTheScoreboard(scoreboard);

    List<Match> summary = scoreboard.getSummary();

    assertThat(summary)
        .isEqualTo(getExpectedSummary());
  }

  private static Stream<Arguments> provideInvalidTeamScoresUpdates() {
    return Stream.of(
        Arguments.of(-3, -4),
        Arguments.of(-1, 2),
        Arguments.of(Integer.MAX_VALUE, Integer.MIN_VALUE)
    );
  }

}