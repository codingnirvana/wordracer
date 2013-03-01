package com.indix;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class WordRacer {

    protected char[][] board;
    protected List<String> words = new ArrayList<String>();

    protected WordRacer() {
        board = new char[7][7];
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 7; j++)
                board[i][j] = '*';
    }

    public void buildWordList() {
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader("words.dat"));
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }

    public void initGameBoard(char letter) throws IOException {
        board[3][3] = letter;
    }

    public abstract Result pickLetter() throws IOException;

    public abstract int pickPosition(char letter) throws IOException;

    public static void main(String[] args) throws IOException {

        WordRacer bot = new MyWordRacer();
        bot.buildWordList();
        Scanner input = new Scanner(System.in);
        char firstLetter = input.nextLine().charAt(0);

        boolean myTurn = input.nextLine().charAt(0) == '1';

        bot.initGameBoard(firstLetter);
        char letter;
        for (int i = 0; i < 48; i++) {
            if (myTurn) {
                Result output1 = bot.pickLetter();
                System.out.println(output1.Letter + " " + output1.Position);
            } else {
                letter = input.nextLine().charAt(0);

                int position = bot.pickPosition(letter);
                System.out.println(position);
                System.out.flush();
            }

            myTurn = !myTurn;
        }
    }
}
