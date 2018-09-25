package com.codingnirvana.wordracer;

import com.codingnirvana.wordracer.gamerunner.Player;
import com.codingnirvana.wordracer.gamerunner.Tournament;
import com.codingnirvana.wordracer.visualizer.ConsoleVisualizer;
import com.codingnirvana.wordracer.visualizer.HtmlVisualizer;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws Exception {

        List<Character> startingLetters = getStartingLettersIfSpecified(args.length > 0  ? args[0] : "");

        String configPath = "./conf/players.yml";
        String runDirectory = "./run";

        Yaml yaml = new Yaml();
        HashMap playerMap = (HashMap) yaml.load(new FileReader(configPath));

        List<HashMap> allPlayers = (List<HashMap>)playerMap.get("players");

        Tournament tournament = new Tournament(startingLetters);
        for (HashMap player : allPlayers) {
            tournament.addPlayer(new Player(player, runDirectory));
        }

        tournament.play();

        ConsoleVisualizer.printRankings(tournament.getHeadToHeads(), tournament.getRankings());

        new HtmlVisualizer().printRankings(tournament, runDirectory);
    }

    private static List<Character> getStartingLettersIfSpecified(String alphabets) {
        List<Character> startingLetters = new ArrayList<Character>();

        if (alphabets == null || alphabets.isEmpty()) {
            return startingLetters;
        }

        alphabets = alphabets.trim().toUpperCase();

        for (Character ch = 'A'; ch <= 'Z'; ch++) {
            if (alphabets.indexOf(ch) >= 0 || alphabets.equals("A-Z")) {
                startingLetters.add(ch);
            }
        }

        return startingLetters;
    }

}
