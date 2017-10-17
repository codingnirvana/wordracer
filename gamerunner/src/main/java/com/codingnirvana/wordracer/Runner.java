package com.codingnirvana.wordracer;

import com.codingnirvana.wordracer.gamerunner.Player;
import com.codingnirvana.wordracer.gamerunner.Tournament;
import com.codingnirvana.wordracer.visualizer.ConsoleVisualizer;
import com.codingnirvana.wordracer.visualizer.HtmlVisualizer;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws Exception {

        String configPath = "./conf/players.yml";
        String runDirectory = "./run";

        Yaml yaml = new Yaml();
        HashMap playerMap = (HashMap) yaml.load(new FileReader(configPath));

        List<HashMap> allPlayers = (List<HashMap>)playerMap.get("players");

        Tournament tournament = new Tournament();
        for (HashMap player : allPlayers) {
            tournament.addPlayer(new Player(player, runDirectory));
        }

        tournament.play();

        ConsoleVisualizer.printRankings(tournament.getRankings());

        new HtmlVisualizer().printRankings(tournament, runDirectory);
    }
}
