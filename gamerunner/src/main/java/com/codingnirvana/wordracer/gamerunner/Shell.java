package com.codingnirvana.wordracer.gamerunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shell {

    private final String command;
    private final boolean isFirstPlayer;
    private Process process;
    private PrintWriter writer;
    private BufferedReader reader;

    public Shell(String command, boolean isFirstPlayer) {
        this.command = command;
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
        process = Runtime.getRuntime().exec(command);
        writer = new PrintWriter(process.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    }
}
