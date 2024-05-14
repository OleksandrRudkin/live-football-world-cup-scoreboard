# Live Football World Cup Score Board Project

This project implements a simple football scoreboard system using Java and Spring Framework.

## Overview

The project consists of several classes:

- `Match`: Represents a football match with details such as teams, scores, and creation timestamp.
- `MatchComparatorByScoreAndTime`: Comparator class to compare matches based on score and creation time.
- `HashMapMatchesRepository`: Repository implementation using a HashMap to store and manage matches.
- `SimpleScoreboard`: Service class to manage starting, updating, and finishing matches on the scoreboard.

## Usage

- **Starting a Game**: Use `SimpleScoreboard.startGame(String homeTeam, String awayTeam)` to start a new game.
- **Updating Score**: Use `SimpleScoreboard.updateScore(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore)` to update the score of an ongoing match.
- **Finishing a Game**: Use `SimpleScoreboard.finishGame(String homeTeam, String awayTeam)` to finish a match.
- **Get Summary**: Use `SimpleScoreboard.getSummary()` to retrieve a summary of all matches on the scoreboard.

## Contributors

- Oleksandr Rudkin (@OleksandrRudkin)

## License
