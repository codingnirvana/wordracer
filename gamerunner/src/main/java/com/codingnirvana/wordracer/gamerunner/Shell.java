package com.codingnirvana.wordracer.gamerunner;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shell {

    private Player player;
    private final boolean isFirstPlayer;
    private Process process;
    private PrintWriter writer;
    private BufferedReader reader;
    private String runDirectory;

    public Shell(String runDirectory, Player player, boolean isFirstPlayer) {
        this.runDirectory = runDirectory;
        this.player = player;
        this.isFirstPlayer = isFirstPlayer;
    }

    public void initGameBoard(char letter) throws IOException {
        initializeProcess();

        writer.println(letter);
        writer.flush();
        writer.println(isFirstPlayer ? '1' : '0');
        writer.flush();
    }

    public Result pickLetter() throws IOException {
        String line = reader.readLine();
        String[] split = line.split(" ");
        int position = Integer.parseInt(split[1]);
        return new Result(position, split[0].charAt(0));
    }

    public int pickPosition(char letter) throws IOException {
        writer.println(letter);
        writer.flush();

        String line = reader.readLine();
        return Integer.parseInt(line);
    }

    public void endGame() throws IOException, InterruptedException {
        writer.close();
        process.destroy();
    }

    private void initializeProcess() throws IOException {
        List<String> res = Collections.synchronizedList(new ArrayList<String>());
        ProcessBuilder pb = new ProcessBuilder(splitArgs(player.getCommand()));
        pb.directory(new File(String.format("%s/%s/",runDirectory, player.getDirectory())));
        process = pb.start();
        writer = new PrintWriter(process.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    }

    private String[] splitArgs(String command) {
        return command.split(" ");
    }
}
