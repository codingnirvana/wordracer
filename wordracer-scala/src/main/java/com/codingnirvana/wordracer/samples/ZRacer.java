package com.codingnirvana.wordracer.samples;

import com.codingnirvana.wordracer.Result;
import com.codingnirvana.wordracer.WordRacer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ZRacer implements WordRacer {

    List<Word> words = new ArrayList<Word>();
    List<Word> reversedWords = new ArrayList<Word>();
    char[][] gameBoardFixed = new char[7][7];
    char[][] board = new char[7][7];
    boolean gFound = false;
    int[][] diGrams = new int[26][26];

    public class Word implements Comparable {
        public String name;
        double frequency;
        double[] charFrequency = new double[]{0.4969869058076114, 0.1414417911057545, 0.20930280064551712, 0.25894225073029231, 0.71697342348783522, 0.0968684248156395, 0.18002982452556535, 0.15075684840561354, 0.4252446223929075, 0.019120380773394889, 0.0915572079341409, 0.33654730047188119, 0.17600555634996834, 0.33356484791534735, 0.36265397422017037, 0.18229730557882051, 0.010683717034706759, 0.43586705615590465, 0.56486834310461054, 0.33395297530284151, 0.22968970236757708, 0.058382530182011318, 0.080505791269176563, 0.021592139399015383, 0.12258697117643454, 0.027209772639061956};

        public Word(String name) {
            this.name = name;
            this.frequency = calcFrequency(name);
        }

        @Override
        public int compareTo(Object other) {
            String otherWord = ((Word) other).name;

            if (this.name.length() != otherWord.length())
                return this.name.length() - otherWord.length();

            double otherFreq = calcFrequency(otherWord);

            return (int) (otherFreq * 10000 - frequency * 10000);
        }

        double calcFrequency(String word) {
            double freq = 0;
            for (int i = 0; i < word.length(); i++)
                freq += charFrequency[word.charAt(i) - 'A'];

            return freq;
        }
    }

    @Override
    public void initGameBoard(char letter) throws IOException {
        readWordsList();

        char[][] fixedBoard = new char[7][7];

        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 7; j++)
            {
                board[i][j] = '*';
                gameBoardFixed[i][j] = '*';
                fixedBoard[i][j] = '*';
            }

        board[3][3] = letter;
        gameBoardFixed[3][3] = letter;
        fixedBoard[3][3] = letter;

        Collections.sort(words);
        reversedWords = new ArrayList(words);
        Collections.reverse(words);

        gameBoardFixed = (new char[][] {new char[]{'Z','I','Z','Z','L','E','S'},
                new char[]{'I','V','Y','L','I','K','E'},
                new char[]{'Z','Y','Z','Z','Y','V','A'},
                new char[]{'Z','L','Z',letter,'*','*','*'},
                new char[]{'L','I','Y','*','*','*','*'},
                new char[]{'E','K','V','*','*','*','*'},
                new char[]{'S','E','A','*','*','*','*'}});
    }

    private void readWordsList() {
        Scanner scanner = new Scanner(this.getClass().getResourceAsStream("/words.dat"));
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            words.add(new Word(line));
            diGrams[line.charAt(0) - 'A'][line.charAt(1) - 'A']++;
        }
    }

    private String createPattern(int i, int j, int dx, int dy, char[][] fixedGameBoard, int length)
    {
        String pattern = "";

        for (int a = i, b = j; a < 7 && b < 7 && pattern.length() <= length; a += dx, b += dy)
            pattern += fixedGameBoard[a][ b];

        return pattern;
    }

    private int fixWordSingle(int i, int j, int dx, int dy, char[][] fixedGameBoard, int length)
    {
        String pattern = createPattern(i, j, dx, dy, fixedGameBoard, length);
        int matches = 0;
        for (Word word : words)
        {
            boolean found = true;
            if (word.name.length() != length)
                continue;
            for (int n = 0; n < word.name.length(); n++)
                if (pattern.charAt(n) != '*' && word.name.charAt(n) != pattern.charAt(n))
                {
                    found = false;
                    break;
                }
            if (found)
                matches++;
        }

        return matches;
    }

    private void fixWord(int i, int j, int dx, int dy, char[][] fixedGameBoard, int turn)
    {
        if (gFound)
            return;

        String pattern = createPattern(i, j, dx, dy, fixedGameBoard, 7);

        List<Word> allWords = turn == 0 ? words : words;

        for (Word word : allWords)
        {
            if (word.name.length() != 7)
                continue;
            boolean found = true;
            for (int n = 0; n < word.name.length(); n++)
                if (pattern.charAt(n) != '*' && word.name.charAt(n) != pattern.charAt(n))
                {
                    found = false;
                    break;
                }
            if (found)
            {
                for (int a = i, b = j, c = 0; a < 7 && b < 7; a += dx, b += dy, c++)
                    fixedGameBoard[a][b] = word.name.charAt(c);
                if (turn == 0)
                    fixWord(0, 0, 1, 0, fixedGameBoard, 1);
                else if (turn == 1)
                    fixWord(0, 1, 1, 0, fixedGameBoard, 2);
                else if (turn == 2)
                {
                    if (checkDigrams(fixedGameBoard))
                        fixWord(0, 0, 0, 1, fixedGameBoard, 3);
                }
                else if (turn == 3)
                    fixWord(2, 0, 0, 1, fixedGameBoard, 4);
                else if (turn == 4)
                    fixWord(1, 0, 0, 1, fixedGameBoard, 5);
                else if (turn == 5)
                    fixWord(0, 2, 1, 0, fixedGameBoard, 6);
                    //else if (turn == 6)
                    //    fixWord(0, 2, 1, 0, fixedGameBoard, 7);
                else
                {
                    // The fixed game board has been successfully found.
                    gFound = true;
                    for (int a = 0; a < 7; a++)
                        for (int b = 0; b < 7; b++)
                            gameBoardFixed[a][b] = fixedGameBoard[a][b];
                }
            }
        }
    }

    boolean checkDigrams(char[][] b)
    {
        if (diGrams[b[0][0] - 'A'][ b[0][ 1] - 'A'] == 0)
        return false;

        if (diGrams[b[6][0] - 'A'][ b[6][ 1] - 'A'] == 0)
        return false;

        for (int i = 1; i < 6; i++)
        {
            if (i == 3) continue;
            if (diGrams[b[i][ 0] - 'A'][ b[i][ 1] - 'A'] < 70)
            return false;
        }
        return true;
    }


    @Override
    public Result pickLetter() throws IOException {
        char letter = '%';
        int pos = -1;
        // Try to pick from the fixed game board
        for (int i = 0; i < 49; i++)
            if (board[i / 7][ i % 7] == '*' && gameBoardFixed[i / 7 ][i % 7] != '*')
        {
            letter = gameBoardFixed[i / 7][ i % 7];
            board[i / 7][ i % 7] = letter;
            pos = i;
            break;
        }

        if (letter != '%')
            return new Result(pos, letter);

        int bestMatches = 0;
        char[][] fixedGameBoard = new char[7][ 7];
        for (int a = 0; a < 7; a++)
            for (int b = 0; b < 7; b++)
                fixedGameBoard[a][b] = gameBoardFixed[a][b];

        for (int a = 0; a < 26; a++)
        {
            letter = (char)('A' + a);
            for (int n = 7; n >= 2; n--)
            {
                for (int i = 48; i >= 0; i--)
                    if (board[i / 7][i % 7] == '*' && gameBoardFixed[i / 7][i % 7] == '*')
                {
                    fixedGameBoard[i / 7][ i % 7] = letter;
                    int matches = fixWordSingle(0, i % 7, 1, 0, fixedGameBoard, n);
                    if (bestMatches < matches)
                    {
                        bestMatches = matches;
                        pos = i;
                    }

                    matches = fixWordSingle(i % 7, 0, 0, 1, fixedGameBoard, n);
                    if (bestMatches < matches)
                    {
                        bestMatches = matches;
                        pos = i;
                    }
                    fixedGameBoard[i / 7][ i % 7] = '*';
                    if (bestMatches > 100)
                        break;
                }
                if (pos != -1 || bestMatches > 100)
                    break;
            }
        }

        if (pos == -1)
        {
            for (int i = 48; i >= 0; i--)
            {

                if (i == 43 || i == 42 || i == 35 || i == 36)
                    continue;
                if (board[i / 7][ i % 7] == '*' && gameBoardFixed[i / 7][ i % 7] != letter)
                {
                    board[i / 7][i % 7] = letter;
                    pos = i;
                    break;
                }
            }
        }

        if (pos == -1)
        {
            for (int i = 48; i >= 0; i--)
                if (board[i / 7][ i % 7] == '*')
            {
                board[i / 7][ i % 7] = letter;
                pos = i;
                break;
            }
        }

        board[pos / 7][ pos % 7] = letter;
        return new Result(pos, letter);
    }

    @Override
    public int pickPosition(char letter) throws IOException {
        int pos = -1;
        // Try to pick from the fixed game board
        for (int i = 0; i < 49; i++)
            if (board[i / 7][ i % 7] == '*' && gameBoardFixed[i / 7][ i % 7] == letter)
        {
            board[i / 7][ i % 7] = letter;
            return i;
        }

        int bestMatches = 0;
        char[][] fixedGameBoard = new char[7][ 7];
        for (int a = 0; a < 7; a++)
            for (int b = 0; b < 7; b++)
                fixedGameBoard[a][ b] = gameBoardFixed[a][ b];


        for (int n = 7; n >= 2; n--)
        {
            for (int i = 48; i >= 0; i--)
                if (board[i / 7][ i % 7] == '*' && gameBoardFixed[i / 7][ i % 7] == '*')
            {
                fixedGameBoard[i / 7][ i % 7] = letter;
                int matches = fixWordSingle(0, i % 7, 1, 0, fixedGameBoard, n);
                if (bestMatches < matches)
                {
                    bestMatches = matches;
                    pos = i;
                }

                matches = fixWordSingle(i % 7, 0, 0, 1, fixedGameBoard, n);
                if (bestMatches < matches)
                {
                    bestMatches = matches;
                    pos = i;
                }
                fixedGameBoard[i / 7][ i % 7] = '*';
            }
            if (pos != -1)
                break;
        }

        if (pos == -1)
        {
            for (int i = 48; i >= 0; i--)
            {
                if (i == 43 || i == 42 || i == 35 || i == 36)
                    continue;
                if (board[i / 7][ i % 7] == '*' && gameBoardFixed[i / 7][ i % 7] != letter)
                {
                    board[i / 7][ i % 7] = letter;
                    pos = i;
                    break;
                }
            }
        }

        if (pos == -1)
        {
            for (int i = 48; i >= 0; i--)
                if (board[i / 7][ i % 7] == '*')
            {
                board[i / 7][ i % 7] = letter;
                pos = i;
                break;
            }
        }

        board[pos / 7][ pos % 7] = letter;
        return pos;
    }
}
