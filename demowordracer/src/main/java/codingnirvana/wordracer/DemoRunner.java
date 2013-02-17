package codingnirvana.wordracer;

import codingnirvana.wordracer.samples.GlassRoom;
import codingnirvana.wordracer.samples.MyWordRacer;
import codingnirvana.wordracer.samples.ZRacer;

import java.io.IOException;
import java.util.Scanner;

public class DemoRunner {
    public static void main(String[] args) throws IOException {
        char[][] firstBoard = new char[7][7];

        WordRacer dRacer = new DemoWordRacer();

        if (args.length == 1) {
            if (args[0].equals("M")) {
                dRacer = new MyWordRacer();
            } else if (args[0].equals("Z")) {
                dRacer = new ZRacer();
            } else {
                dRacer = new GlassRoom();
            }
        }

        Scanner input = new Scanner(System.in);
        char firstLetter = input.nextLine().charAt(0);

        boolean myTurn = input.nextLine().charAt(0) == '1';

        dRacer.initGameBoard(firstLetter);
        firstBoard[3][3] = firstLetter;
        char letter;
        for (int i = 0; i < 48; i++) {

            if (myTurn) {
                Result output1 = dRacer.pickLetter();
                firstBoard[output1.Position / 7][output1.Position % 7] = output1.Letter;
                System.out.println(output1.Letter + " " + output1.Position);
            } else {
                letter = input.nextLine().charAt(0);

                int position = dRacer.pickPosition(letter);
                firstBoard[position / 7][position % 7] = letter;
                System.out.println(position);
                System.out.flush();
            }

            myTurn = !myTurn;
        }
    }
}
