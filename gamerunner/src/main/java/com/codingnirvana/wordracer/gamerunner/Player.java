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
    // Added new Member
    private char[][] board;

    private Process process;
    private PrintWriter writer;
    private BufferedReader reader;

    public Player(HashMap playerMap, String runDirectory) {
        this.name = (String) playerMap.get("name");
        this.language = (String) playerMap.get("language");
        this.command = (String) playerMap.get("command");
        this.runDirectory = runDirectory;
        this.directory = (String) playerMap.get("directory");
        this.board = new char[7][7];
        // - denotes the position is already occupied
        this.board[3][3] = '-'
    }

    public String getName() {
        return this.name;
    }

    public String getPlayerDirectory() {
       return String.format("%s/%s/", runDirectory, this.directory);
    }

    public void initGameBoard(char letter, boolean isFirstPlayer) throws InvalidGameException {
        this.timeLeft = TOTAL_ALLOWED_TIME;
        initializeProcess();

        writer.println(letter);
        writer.flush();
        writer.println(isFirstPlayer ? '1' : '0');
        writer.flush();
    }

    public Result pickLetter() throws InvalidGameException {
        String line = readLineAsync();
        char letter;
        int position;
        try {
            String[] split = line.split(" ");
            letter = split[0].charAt(0);
            position = Integer.parseInt(split[1]);

        } catch (Exception e) {
            throw new InvalidGameException(this, String.format("%s gave invalid input '%s' instead of '{letter} {Position}; More Details - %s'", this.getName(), line, e.getMessage()));
        }

        validateLetter(letter);
        validatePosition(position);

        return new Result(position, letter);
    }

    public int pickPosition(char letter) throws InvalidGameException {
        writer.println(letter);
        writer.flush();

        String line = readLineAsync();
        int position;
        try {
            position = Integer.parseInt(line);
        } catch (Exception e) {
            throw new InvalidGameException(this, String.format("%s gave invalid input %s instead of '{Position}; More Details - %s'", this.getName(), line, e.getMessage()));
        }
        validatePosition(position);
        return position;
    }

    public void endGame() throws IOException {
        if (writer != null) {
            writer.close();
        }
        if (process != null) {
            process.destroy();
        }
    }

    public long getTimeTakenInMs() {
        return TOTAL_ALLOWED_TIME - timeLeft;
    }

    private void initializeProcess() throws InvalidGameException {
        ProcessBuilder pb = new ProcessBuilder(this.command.split(" "));
        pb.directory(new File(getPlayerDirectory()));
        try {
            IOUtils.copy(Player.class.getResourceAsStream("/words.dat"), new FileOutputStream(getPlayerDirectory() + "/words.dat"));
            process = pb.start();
        } catch (IOException e) {
            throw new InvalidGameException(this, String.format("Error running the bot of player %s. More Details - %s", this.getName(), e.getMessage()));
        }
        writer = new PrintWriter(process.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    }

    private String readLineAsync() throws InvalidGameException {
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

    private void validateLetter(char letter) throws InvalidGameException {
        if (!(letter >= 'A' && letter <= 'Z')) {
            throw new InvalidGameException(this, String.format("%s gave invalid letter '%s' instead of a valid letter between 'A' and 'Z'", this.getName(), letter));
        }
    }

    private void validatePosition(int position) throws InvalidGameException {
        if (!(position >= 0 && position < 49)) {
            throw new InvalidGameException(this, String.format("%s gave invalid position '%s' instead of a valid position between 0 and 48", this.getName(), position));
        }
        else if(board[position / 7][position % 7] == '-') {
            throw new InvalidGameException(this, String.format("%s gave invalid position '%s' which is already occupied", this.getName(), position));
        }
        else
            this.board[position / 7][position % 7] = '-';
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
