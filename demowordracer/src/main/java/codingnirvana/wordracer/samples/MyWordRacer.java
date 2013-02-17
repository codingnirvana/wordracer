package codingnirvana.wordracer.samples;

import codingnirvana.wordracer.Result;
import codingnirvana.wordracer.WordRacer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyWordRacer implements WordRacer {


    private List<String> words;
    private List<Integer> corruptRows = new ArrayList<Integer>();
    char[][] gameBoardFixed = new char[7][7];
    char[][] board = new char[7][7];

    @Override
    public void initGameBoard(char letter) throws IOException {
        readWordsList();

        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 7; j++) {
                board[i][j] = '*';
                gameBoardFixed[i][j] = '*';
            }

        board[3][3] = letter;
        gameBoardFixed[3][3] = letter;

        if (letter != 'Q' && letter != 'S' && letter != 'E') {
            gameBoardFixed = (new char[][]{new char[]{'F', 'U', '*', '*', '*', '*', '*'},
                    new char[]{'U', 'N', '*', '*', '*', '*', '*'},
                    new char[]{'T', 'R', '*', '*', '*', '*', '*'},
                    new char[]{'H', 'A', '*', letter, '*', '*', '*'},
                    new char[]{'O', 'V', '*', '*', '*', '*', '*'},
                    new char[]{'R', 'E', '*', '*', '*', '*', '*'},
                    new char[]{'C', 'L', '*', '*', '*', '*', '*'}});
        } else {
            gameBoardFixed = (new char[][]{new char[]{'F', 'U', '*', '*', '*', '*', '*'},
                    new char[]{'U', 'N', '*', '*', '*', '*', '*'},
                    new char[]{'T', 'R', '*', '*', '*', '*', '*'},
                    new char[]{'H', 'O', '*', letter, '*', '*', '*'},
                    new char[]{'O', 'V', '*', '*', '*', '*', '*'},
                    new char[]{'R', 'E', '*', '*', '*', '*', '*'},
                    new char[]{'K', 'N', '*', '*', '*', '*', '*'}});
        }
        fixWordMiddle();
    }

    private void fixWordMiddle() {
        String pattern = createPattern(3, 0, 0, 1, gameBoardFixed, 7);

        for (String word : words) {
            if (word.length() != 7)
                continue;
            Boolean found = true;
            for (int n = 0; n < word.length(); n++)
                if (pattern.charAt(n) != '*' && word.charAt(n) != pattern.charAt(n)) {
                    found = false;
                    break;
                }
            if (found) {
                for (int a = 3, b = 0, c = 0; a < 7 && b < 7; b += 1, c++)
                    gameBoardFixed[a][b] = word.charAt(c);
            }
        }
    }

    String createPattern(int i, int j, int dx, int dy, char[][] gameBoard, int length) {
        String pattern = "";
        for (int a = i, b = j; a < 7 && b < 7 && pattern.length() <= length; a += dx, b += dy)
            pattern += gameBoard[a][b];

        return pattern;
    }

    int fixWordSingle(int i, int j, int dx, int dy, char[][] fixedGameBoard, int length) {
        String pattern = createPattern(i, j, dx, dy, fixedGameBoard, length);
        int matches = 0;
        for (String word : words) {
            Boolean found = true;
            if (word.length() != length)
                continue;
            for (int n = 0; n < word.length(); n++)
                if (pattern.charAt(n) != '*' && word.charAt(n) != pattern.charAt(n)) {
                    found = false;
                    break;
                }
            if (found)
                matches++;
        }
        // Add number of  7 - * into 10 to matches
        int cnt = 0;
        if (matches > 0) {
            for (int n = 0; n < pattern.length(); n++)
                if (pattern.charAt(n) != '*') {
                    cnt++;
                    matches += 10 * (7 - i);
                }
        }
        if (cnt == 7)
            return 1000 * matches;
        return matches;
    }

    private int pickBestPosition(char letter) {
        int maxRow = -1;
        int maxStars = 0;

        // Try picking from the row which has the maximum '*'
        for (int i = 0; i < 7; i++) {
            int c = 0;
            for (int j = 2; j < 7; j++)
                if (board[i][j] == '*')
                    c++;
            if (c > 0 && corruptRows.contains(i)) {
                maxRow = i;
                break;
            }
            if (c >= maxStars) {
                maxStars = c;
                maxRow = i;
            }
        }

        if (!corruptRows.contains(maxRow))
            corruptRows.add(maxRow);

        for (int i = 6; i >= 0; i--)
            if (board[maxRow][i] == '*')
                return maxRow * 7 + i;

        return -1;
    }


    private void readWordsList() {
        words = new ArrayList<String>();
        Scanner scanner = new Scanner(MyWordRacer.class.getResourceAsStream("/words.dat"));
        while (scanner.hasNext()) {
            words.add(scanner.nextLine());
        }
    }

    @Override
    public Result pickLetter() throws IOException {
        char letter = '%';
        int pos = -1;
        // Try to pick from the fixed game board
        for (int i = 0; i < 49; i++)
            if (board[i / 7][i % 7] == '*' && gameBoardFixed[i / 7][i % 7] != '*') {
                letter = gameBoardFixed[i / 7][i % 7];
                board[i / 7][i % 7] = letter;
                pos = i;
                break;
            }

        if (letter != '%')
            return new Result(pos, letter);

        int bestMatches = 0;

        for (int a = 0; a < 26; a++) {
            char c = (char) ('A' + a);
            int n = 7;
            for (int i = 48; i >= 0; i--)
                if (board[i / 7][i % 7] == '*' && gameBoardFixed[i / 7][i % 7] == '*') {
                    gameBoardFixed[i / 7][i % 7] = c;
                    int matches = fixWordSingle(i / 7, 0, 0, 1, gameBoardFixed, n);
                    if (bestMatches < matches) {
                        bestMatches = matches;
                        pos = i;
                        letter = c;
                    }
                    gameBoardFixed[i / 7][i % 7] = '*';
                }
        }

        if (pos == -1)
            pos = pickBestPosition(letter);

        if (letter == '%') {
            // TODO: Try for smaller letters
            letter = 'J';
        }

        board[pos / 7][pos % 7] = gameBoardFixed[pos / 7][pos % 7] = letter;
        return new Result(pos, letter);
    }

    @Override
    public int pickPosition(char letter) throws IOException {
        int pos = -1;
        // Try to pick from the fixed game board
        for (int i = 0; i < 49; i++)
            if (board[i / 7][i % 7] == '*' && gameBoardFixed[i / 7][i % 7] == letter) {
                board[i / 7][i % 7] = letter;
                return i;
            }

        int n = 7;
        int bestMatches = 0;
        for (int i = 48; i >= 0; i--)
            if (board[i / 7][i % 7] == '*' && gameBoardFixed[i / 7][i % 7] == '*') {
                gameBoardFixed[i / 7][i % 7] = letter;
                int matches = fixWordSingle(i / 7, 0, 0, 1, gameBoardFixed, n);
                if (bestMatches < matches) {
                    bestMatches = matches;
                    pos = i;
                }
                gameBoardFixed[i / 7][i % 7] = '*';
            }

        if (pos == -1)
            pos = pickBestPosition(letter);

        board[pos / 7][pos % 7] = gameBoardFixed[pos / 7][pos % 7] = letter;
        return pos;
    }
}
