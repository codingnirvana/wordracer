package codingnirvana.wordracer.samples;

import codingnirvana.wordracer.Result;
import codingnirvana.wordracer.WordRacer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GlassRoom implements WordRacer {

    char[][] board = new char[7][7];
    char[][] playBoard = new char[7][7];
    List<String> words = new ArrayList<String>();

    @Override
    public void initGameBoard(char letter) throws IOException {

        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 7; j++)
                playBoard[i][j] = '*';

        readWordsList();

        board[3][3] = playBoard[3][3] = letter;

        String _word = "";

        for (String word : words) {
            if (word.charAt(3) == letter) {
                _word = word;
                break;
            }
        }

        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 7; j++) {
                if (i == 3)
                    board[i][j] = _word.charAt(j);
                else
                    board[i][j] = words.get(i).charAt(j);
            }

    }

    private void readWordsList() {

        List<String> _sevenLetter = new ArrayList<String>();
        List<String> _sixLetter = new ArrayList<String>();
        List<String> _fiveLetter = new ArrayList<String>();
        List<String> _fourLetter = new ArrayList<String>();
        List<String> _threeLetter = new ArrayList<String>();
        List<String> _twoLetter = new ArrayList<String>();

        Scanner scanner = new Scanner(MyWordRacer.class.getResourceAsStream("/words.dat"));
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.length() == 7)
                _sevenLetter.add(input);
            else if (input.length() == 6)
                _sixLetter.add(input);
            else if (input.length() == 5)
                _fiveLetter.add(input);
            else if (input.length() == 4)
                _fourLetter.add(input);
            else if (input.length() == 3)
                _threeLetter.add(input);
            else if (input.length() == 2)
                _twoLetter.add(input);
        }

        Collections.sort(_sevenLetter);
        Collections.reverse(_sevenLetter);
        words.addAll(_sevenLetter);
        words.addAll(_sixLetter);
        words.addAll(_fiveLetter);
        words.addAll(_fourLetter);
        words.addAll(_threeLetter);
        words.addAll(_twoLetter);
    }

    @Override
    public Result pickLetter() throws IOException {
        int pos = 0;
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 7; j++)
                if (playBoard[i][j] == '*') {
                    pos = i * 7 + j;
                    playBoard[i][j] = board[i][j];
                    return new Result(pos, playBoard[i][j]);
                }
        return new Result(pos, '*');
    }

    @Override
    public int pickPosition(char letter) throws IOException {
        /*
         * Try to fit the letter in the existing board
         * */
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 7; j++)
                if (playBoard[i][j] == '*' && board[i][j] == letter) {
                    playBoard[i][j] = letter;
                    return (i * 7 + j);
                }

        /*
       * Now try to change some word in the pre-made board to fit the current letter
       * */
        for (int i = 0; i < words.size(); i++)            // Loop through all the words starting from the largest word
        {
            int index = words.get(i).indexOf(letter);
            for (int j = 6; j >= 0; j--)                            // Loop through all the play board rows starting from the bottom most
            {
                boolean flag = true;
                if (index >= 0 && playBoard[j][index] == '*') {
                    for (int k = 0; k < words.get(i).length(); k++)        // Board row column should be * or matching the letter
                    {
                        if (!(playBoard[j][k] == '*' || playBoard[j][k] == words.get(i).charAt(k))) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        playBoard[j][index] = letter;
                        for (int k = 0; k < words.get(i).length(); k++)
                            board[j][k] = words.get(i).charAt(k);
                        return (j * 7 + index);
                    }
                }
            }
        }
        /*
       * Now the letter is not fitting anywhere so place it in the first empty box
       * starting from the bottom-right most corner
       * */
        for (int i = 6; i >= 0; i--)
            for (int j = 6; j >= 0; j--)
                if (playBoard[i][j] == '*') {
                    playBoard[i][j] = letter;
                    return (i * 7 + j);
                }
        return 0;
    }
}
