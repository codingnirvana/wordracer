package com.codingnirvana.wordracer.visualizer;

import com.codingnirvana.wordracer.gamerunner.Game;
import com.codingnirvana.wordracer.gamerunner.Tournament;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlVisualizer {

    private final Configuration config;

    public HtmlVisualizer() throws IOException, URISyntaxException {
        config = new Configuration();
        config.setClassForTemplateLoading(HtmlVisualizer.class,"/templates");
        config.setObjectWrapper(new DefaultObjectWrapper());
    }

    public void printRankings(Tournament tournament, String runDirectory) throws IOException, TemplateException {
        String resultsDirectory = String.format("%s/results", runDirectory);

        if (!new File(resultsDirectory).exists()) {
            new File(resultsDirectory).mkdir();
        }
        buildSummaryHtml(tournament, resultsDirectory);
        buildGamesHtml(tournament.getGames(), resultsDirectory);
    }

    private void buildGamesHtml(List<Game> games, String resultsDirectory) throws IOException, TemplateException {
        Template gameTemplate = config.getTemplate("game.ftl");
        for (Game game : games) {
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("game", game);
            root.put("firstPlayerScore", Game.calculateScore(game.getFirstPlayerBoard()));
            root.put("firstPlayerTotalScore", game.totalScore(game.getFirstPlayerBoard()));
            root.put("secondPlayerScore", Game.calculateScore(game.getSecondPlayerBoard()));
            root.put("secondPlayerTotalScore", game.totalScore(game.getSecondPlayerBoard()));
            Writer out = new PrintWriter(String.format("%s/game%s.html", resultsDirectory, game.getGameNumber()));
            gameTemplate.process(root, out);
            out.flush();
        }
    }

    private void buildSummaryHtml(Tournament tournament, String resultsDirectory) throws IOException, TemplateException {
        Template summaryTemplate = config.getTemplate("summary.ftl");
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("rankings", tournament.getRankings());
        root.put("games", tournament.getGames());

        Writer out = new PrintWriter(String.format("%s/summary.html", resultsDirectory));
        summaryTemplate.process(root, out);
        out.flush();
    }
}
