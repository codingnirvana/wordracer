package com.codingnirvana.wordracer;

import com.codingnirvana.wordracer.gamerunner.Game;
import com.codingnirvana.wordracer.gamerunner.Player;
import com.codingnirvana.wordracer.gamerunner.Tournament;
import com.codingnirvana.wordracer.visualizer.ConsoleVisualizer;
import com.codingnirvana.wordracer.visualizer.HtmlVisualizer;
import freemarker.template.TemplateException;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Runner {
    public static void main(String[] args) throws Exception {

        if (args.length != 2) {
            System.out.println("Please specify the path to the players.yml configuration and the run directory");
            return;
        }

        String configPath = args[0];
        String runDirectory = args[1];

        Yaml yaml = new Yaml();
        HashMap playerMap = (HashMap) yaml.load(new FileReader(configPath));

        List<HashMap> allPlayers = (List<HashMap>)playerMap.get("players");

        Tournament tournament = new Tournament();
        for (HashMap player : allPlayers) {
            tournament.addPlayer(new Player(player, runDirectory));
        }

        tournament.play();

        for (Game game : tournament.getGames()) {
            ConsoleVisualizer.printGameBoard(game);
        }

        ConsoleVisualizer.printRankings(tournament.getRankings());

        new HtmlVisualizer().printRankings(tournament, runDirectory);
    }
}
