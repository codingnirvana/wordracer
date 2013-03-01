package codingnirvana.wordracer.samples;

// This is a demo implementation. It randomly generates
// letters and also places them randomly on the board
// On an average,it scores 30 points. Every program that works
// should be able to beat this one.

import codingnirvana.wordracer.Result;
import codingnirvana.wordracer.WordRacer;

import java.util.Random;


public class DemoWordRacer implements WordRacer {

    char[][] board;

    public DemoWordRacer() {
        board = new char[7][7];
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 7; j++)
                board[i][j] = '*';
    }


    // This function makes the game board empty and
    // sets the first letter on the board
    public void initGameBoard(char letter) {
        board[3][3] = letter;
    }

    // This function selects the best possible letter
    public Result pickLetter() {
        int rand = new Random().nextInt(25);
        char letter = (char) (rand + 'A');
        return new Result(pickPosition(letter), letter);
    }


    // This function selects the best position for the given letter
    public int pickPosition(char letter) {
        Boolean done = false;
        int pos = 0;
        while (!done) {
            pos = new Random().nextInt(49);
            if (pos == 24)
                continue;
            int x = pos / 7;
            int y = pos % 7;

            if (board[x][y] != '*')
                continue;
            board[x][y] = letter;
            done = true;
        }
        return pos;
    }
}


