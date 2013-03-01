package com.codingnirvana.wordracer.gamerunner;

import com.codingnirvana.wordracer.visualizer.ConsoleVisualizer;

import java.io.IOException;
import java.util.*;

public class Tournament {

    private List<Player> players;
    private List<Game> games;
    private List<Ranking> rankingList;
    
    public Tournament() {
        players = new ArrayList<Player>();
        games = new ArrayList<Game>();
        rankingList = new ArrayList<Ranking>();
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void play() throws IOException, InterruptedException {
        int gameNumber = 1;
        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++)  {
                for (int c = 0; c < 2; c++) {
                    Player firstPlayer = (c == 0) ? players.get(i) : players.get(j);
                    Player secondPlayer = (c == 0) ? players.get(j) : players.get(i);

                    Game game = new Game(firstPlayer, secondPlayer, gameNumber);
                    game.play();
                    game.calculateResult();

                    ConsoleVisualizer.printGameBoard(game);

                    this.games.add(game);
                    gameNumber ++;
                }
            }
        }

        calculateRankings();
    }

    private void calculateRankings() {
        Map<Player,Ranking> rankingMap = new HashMap<Player, Tournament.Ranking>();

        for (Game game : games) {
            Player firstPlayer = game.getFirstPlayer();
            Player secondPlayer = game.getSecondPlayer();
            Game.GameResult gameResult = game.getResult();

            Ranking firstPlayerRanking = rankingMap.get(firstPlayer);
            if (firstPlayerRanking == null) {
                firstPlayerRanking = new Ranking(firstPlayer);
            }
            firstPlayerRanking.totalPoints += game.totalScore(game.getFirstPlayerBoard());
            firstPlayerRanking.totalScore += gameResult == Game.GameResult.DRAW ? 1 : gameResult == Game.GameResult.FIRST_PLAYER_WINNER ? 3 : 0;
            rankingMap.put(firstPlayer, firstPlayerRanking);

            Ranking secondPlayerRanking = rankingMap.get(secondPlayer);
            if (secondPlayerRanking == null) {
                secondPlayerRanking = new Ranking(secondPlayer);
            }
            secondPlayerRanking.totalPoints += game.totalScore(game.getSecondPlayerBoard());
            secondPlayerRanking.totalScore += gameResult == Game.GameResult.DRAW ? 1 : gameResult == Game.GameResult.SECOND_PLAYER_WINNER ? 3 : 0;
            rankingMap.put(secondPlayer, secondPlayerRanking);
        }

        rankingList.addAll(rankingMap.values());
        Collections.sort(rankingList);
        Collections.reverse(rankingList);
    }

    public List<Game> getGames() {
        return games;
    }

    public List<Ranking> getRankings() {
        return rankingList;
    }


    public static class Ranking implements Comparable<Ranking>{
        public Player player;
        public int totalScore;
        public int totalPoints;

        public Ranking(Player player) {
            this.player = player;
        }

        @Override
        public int compareTo(Ranking other) {
            if (this.totalScore == other.totalScore) {
                return this.totalPoints - other.totalPoints;
            }
            return this.totalScore - other.totalScore;
        }

        public Player getPlayer() {
            return player;
        }

        public int getTotalScore() {
            return totalScore;
        }

        public int getTotalPoints() {
            return totalPoints;
        }
    }
}
