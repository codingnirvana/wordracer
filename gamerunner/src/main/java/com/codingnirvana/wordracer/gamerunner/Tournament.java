package com.codingnirvana.wordracer.gamerunner;

import com.codingnirvana.wordracer.visualizer.ConsoleVisualizer;

import java.io.IOException;
import java.util.*;

public class Tournament {

    private List<Player> players;
    private List<Game> games;
    private List<Ranking> rankingList;
    private List<HeadToHead> headToHeadList;
    private List<Character> startingLetterList;
    private static Character DUMMY_CHAR = ' ';
    private static ArrayList<Character> defaultStartingLetterList = new ArrayList<Character>(Arrays.asList(DUMMY_CHAR));
    
    public Tournament(List<Character> startingLetters) {
        players = new ArrayList<Player>();
        games = new ArrayList<Game>();
        rankingList = new ArrayList<Ranking>();
        headToHeadList = new ArrayList<HeadToHead>();
        startingLetterList = (startingLetters == null || startingLetters.size() == 0) ? defaultStartingLetterList : startingLetters;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void play() throws IOException, InterruptedException {
        int gameNumber = 1;
        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++)  {
                for (Character startingLetter : startingLetterList) {
                    startingLetter = startingLetter == DUMMY_CHAR ? (char) ('A' + new Random().nextInt(25)) : startingLetter;
                    for (int c = 0; c < 2; c++) {
                        Player firstPlayer = (c == 0) ? players.get(i) : players.get(j);
                        Player secondPlayer = (c == 0) ? players.get(j) : players.get(i);

                        Game game = new Game(firstPlayer, secondPlayer, gameNumber);
                        game.play(startingLetter);
                        game.calculateResult();

                        ConsoleVisualizer.printGameBoard(game);

                        this.games.add(game);
                        gameNumber++;
                    }
                }
            }
        }

        calculateRankings();
        calculateHeadToHeads();
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

    private void calculateHeadToHeads() {
        Map<Player,Map<Player,HeadToHead>> headToHeadMap = new HashMap<Player,Map<Player,HeadToHead>>();

        for (Game game : games) {
            Player firstPlayer = game.getFirstPlayer();
            Player secondPlayer = game.getSecondPlayer();
            Game.GameResult gameResult = game.getResult();

            Map<Player,HeadToHead> headToHeadLookUp = headToHeadMap.get(firstPlayer);
            if(headToHeadLookUp == null){
                headToHeadLookUp = new HashMap<Player, HeadToHead>();
            }
            headToHeadMap.put(firstPlayer, headToHeadLookUp);

            HeadToHead headToHead = headToHeadLookUp.get(secondPlayer);
            if(headToHead == null){
                headToHead = new HeadToHead(firstPlayer, secondPlayer);
            }
            headToHeadLookUp.put(secondPlayer, headToHead);
            headToHead.firstPlayerWins += gameResult == Game.GameResult.DRAW ? 0 : gameResult == Game.GameResult.FIRST_PLAYER_WINNER ? 1 : 0;
            headToHead.secondPlayerWins += gameResult == Game.GameResult.DRAW ? 0 : gameResult == Game.GameResult.SECOND_PLAYER_WINNER ? 1 : 0;
            headToHead.drawn += gameResult == Game.GameResult.DRAW ? 1 : 0;
        }

        for(Map<Player,HeadToHead> headToHeadLookup: headToHeadMap.values()){
            headToHeadList.addAll(headToHeadLookup.values());
        }
        Collections.sort(headToHeadList);
    }

    public List<Game> getGames() {
        return games;
    }

    public List<Ranking> getRankings() {
        return rankingList;
    }

    public List<HeadToHead> getHeadToHeads() {
        return headToHeadList;
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

    public static class HeadToHead implements Comparable<HeadToHead>{
        public Player firstPlayer;
        public Player secondPlayer;
        public int firstPlayerWins;
        public int secondPlayerWins;
        public int drawn;

        public HeadToHead(Player firstPlayer, Player secondPlayer) {
            this.firstPlayer = firstPlayer;
            this.secondPlayer = secondPlayer;
        }

        @Override
        public int compareTo(HeadToHead other) {
            int firstPlayerDiff = this.firstPlayer.getName().compareTo(other.firstPlayer.getName());
            if (firstPlayerDiff == 0) {
                return this.secondPlayer.getName().compareTo(other.secondPlayer.getName());
            }
            return firstPlayerDiff;
        }

        public Player getFirstPlayer() { return firstPlayer; }

        public Player getSecondPlayer() {
            return secondPlayer;
        }

        public int getFirstPlayerWins() {
            return firstPlayerWins;
        }

        public int getSecondPlayerWins() {
            return secondPlayerWins;
        }

        public int getDrawn() {
            return drawn;
        }
    }
}
