package codingnirvana.wordracer;

import codingnirvana.wordracer.samples.GlassRoom;
import codingnirvana.wordracer.samples.MyWordRacer;

import java.io.IOException;
import java.util.*;

public class DemoTester {
    public static void main(String[] args) throws IOException {
        char letter = (char) ('A' + new Random().nextInt(25));

        char[][] firstBoard = new char[7][7];
        char[][] secondBoard = new char[7][7];

        WordRacer wRacer = new MyWordRacer();
        WordRacer dRacer = new GlassRoom();

        wRacer.initGameBoard(letter);
        dRacer.initGameBoard(letter);
        firstBoard[3][3] = letter;
        secondBoard[3][3] = letter;


        for (int i = 0; i < 24; i++) {
            Result result = wRacer.pickLetter();
            int pos1 = result.Position;
            char letter1 = result.Letter;
            firstBoard[pos1 / 7][pos1 % 7] = letter1;

            int pos2 = dRacer.pickPosition(letter1);
            secondBoard[pos2 / 7][pos2 % 7] = letter1;

            result = dRacer.pickLetter();
            pos2 = result.Position;
            char letter2 = result.Letter;
            secondBoard[pos2 / 7][pos2 % 7] = letter2;

            pos1 = wRacer.pickPosition(letter2);
            firstBoard[pos1 / 7][pos1 % 7] = letter2;
        }


        System.out.println("Opponents board");
        calculateScore(firstBoard);

        System.out.println("Your Board");
        calculateScore(secondBoard);

    }

    static void printBoardWithTotal(char[][] board, int[][] score) {
        int points = 0;

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(board[i][j] + "   ");
            }
            System.out.print(score[0][i]);
            points += score[0][i];
            System.out.println();

        }

        for (int i = 0; i < 7; i++) {
            System.out.print(String.format("%1$-" + 4 + "s", score[1][i]));
            points += score[1][i];
        }
        System.out.println(points);

    }

    static void calculateScore(char[][] board) {
        int[] score = new int[]{0, 0, 1, 2, 3, 5, 8, 13};

        List<String> words = new ArrayList<String>();
        Scanner scanner = new Scanner(DemoTester.class.getResourceAsStream("/words.dat"));
        while (scanner.hasNext()) {
            words.add(scanner.nextLine());
        }

        int[][] total = new int[2][7];
        boolean[][] isTakenH = new boolean[7][7];
        boolean[][] isTakenV = new boolean[7][7];

        for (int length = 7; length >= 2; length--) {
            for (int row = 0; row < 7; row++)
                for (int start = 0; start < 7; start++) {
                    int end = start + length;
                    if (end > 7) break;

                    boolean quit = false;
                    for (int i = start; i < end; i++) {
                        if (isTakenH[row][i]) {
                            quit = true;
                            break;
                        }
                    }
                    if (quit)
                        continue;

                    String word = "";
                    for (int i = start; i < end; i++)
                        word += board[row][i];

                    if (Collections.binarySearch(words, word) > 0) {
                        for (int i = start; i < end; i++)
                            isTakenH[row][i] = true;
                        total[0][row] += score[word.length()];
                    }
                }

            for (int col = 0; col < 7; col++)
                for (int start = 0; start < 7; start++) {
                    int end = start + length;
                    if (end > 7) break;

                    boolean quit = false;
                    for (int i = start; i < end; i++) {
                        if (isTakenV[i][col]) {
                            quit = true;
                            break;
                        }
                    }
                    if (quit)
                        continue;

                    String word = "";
                    for (int i = start; i < end; i++)
                        word += board[i][col];

                    if (Collections.binarySearch(words, word) > 0) {

                        for (int i = start; i < end; i++)
                            isTakenV[i][col] = true;
                        total[1][col] += score[word.length()];
                    }
                }
        }

        printBoardWithTotal(board, total);
    }
}
