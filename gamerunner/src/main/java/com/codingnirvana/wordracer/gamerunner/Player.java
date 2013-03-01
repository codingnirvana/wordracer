package com.codingnirvana.wordracer.gamerunner;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.HashMap;

public class Player {

    public static final int TOTAL_ALLOWED_TIME = 10 * 1000;
    private String name;
    private String language;
    private String command;
    private String directory;
    private String runDirectory;
    private long timeLeft;

    private Process process;
    private PrintWriter writer;
    private BufferedReader reader;

    public Player(HashMap playerMap, String runDirectory) {
        this.name = (String) playerMap.get("name");
        this.language = (String) playerMap.get("language");
        this.command = (String) playerMap.get("command");
        this.runDirectory = runDirectory;
        this.directory = (String) playerMap.get("directory");
    }

    public String getName() {
        return this.name;
    }

    public String getPlayerDirectory() {
       return String.format("%s/%s/", runDirectory, this.directory);
    }

    public void initGameBoard(char letter, boolean isFirstPlayer) throws IOException {
        this.timeLeft = TOTAL_ALLOWED_TIME;
        initializeProcess();

        writer.println(letter);
        writer.flush();
        writer.println(isFirstPlayer ? '1' : '0');
        writer.flush();
    }

    public Result pickLetter() throws IOException, InvalidGameException {
        String line = readLineAsync();
        try {
            String[] split = line.split(" ");
            char letter = split[0].charAt(0);
            int position = Integer.parseInt(split[1]);
            return new Result(position, letter);
        } catch (Exception e) {
            throw new InvalidGameException(this, String.format("%s gave invalid input %s instead of '{letter} {Position}; More Details - %s'", this.getName(), line, e.getMessage()));
        }
    }

    public int pickPosition(char letter) throws IOException, InvalidGameException {
        writer.println(letter);
        writer.flush();

        String line = readLineAsync();
        try {
            return Integer.parseInt(line);
        } catch (Exception e) {
            throw new InvalidGameException(this, String.format("%s gave invalid input %s instead of '{Position}; More Details - %s'", this.getName(), line, e.getMessage()));
        }
    }

    public void endGame() throws IOException {
        writer.close();
        process.destroy();
    }

    public long getTimeTakenInMs() {
        return TOTAL_ALLOWED_TIME - timeLeft;
    }

    private void initializeProcess() throws IOException {
        ProcessBuilder pb = new ProcessBuilder(this.command.split(" "));
        pb.directory(new File(getPlayerDirectory()));
        IOUtils.copy(Player.class.getResourceAsStream("/words.dat"), new FileOutputStream(getPlayerDirectory() + "/words.dat"));
        process = pb.start();
        writer = new PrintWriter(process.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    }

    private String readLineAsync() throws IOException, InvalidGameException {
        final String[] line = {""};
        long start = System.currentTimeMillis();

        Thread readerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    line[0] = reader.readLine();
                } catch (IOException ignored) {

                }
            }
        });

        readerThread.start();

        try {
            readerThread.join(timeLeft);
        } catch (InterruptedException ignored) {

        }

        timeLeft -= System.currentTimeMillis() - start;

        if (timeLeft <= 0) {
            throw new InvalidGameException(this, String.format("%s exceeded the time limit", this.getName()));
        }

        return line[0];
    }


    public class InvalidGameException extends Exception {
        private Player playerDefaulted;

        InvalidGameException(Player player, String message) {
            super(message);
            this.playerDefaulted = player;
        }

        public Player getPlayerDefaulted() {
            return playerDefaulted;
        }
    }

}
