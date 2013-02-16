package codingnirvana.wordracer;

import java.util.Scanner;

public class DemoRunner {
    public static void main(String[] args) {
        char[][] firstBoard = new char[7][7];

        DemoWordRacer dRacer = new DemoWordRacer();
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
