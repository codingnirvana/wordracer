package com.codingnirvana.wordracer;

import com.codingnirvana.wordracer.impl.DemoWordRacer;
import com.codingnirvana.wordracer.samples.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Runner {


    public static void main(String[] args) throws IOException {

        List<String> words = readWordsList();

        WordRacer dRacer = new JDemoWordRacer();

        if (args.length == 1) {
            String type = args[0].trim();
            if (type.equals("S")) {
                dRacer = new SlowWordRacer();
            } else if (type.equals("R")) {
                dRacer = new RogueWordRacer();
            } else {
                dRacer = new JDemoWordRacer();
            }

        }

        Scanner input = new Scanner(System.in);
        char firstLetter = input.nextLine().charAt(0);

        boolean myTurn = input.nextLine().charAt(0) == '1';

        dRacer.initGameBoard(firstLetter);
        char letter;
        for (int i = 0; i < 48; i++) {

            if (myTurn) {
                Result result = dRacer.pickLetter();
                System.out.println(result.letter + " " + result.position);
            } else {
                letter = input.nextLine().charAt(0);

                int position = dRacer.pickPosition(letter);
                System.out.println(position);
                System.out.flush();
            }

            myTurn = !myTurn;
        }
    }

    private static List<String> readWordsList() {
        List<String> words = new ArrayList<String>();
        Scanner scanner = new Scanner(Runner.class.getResourceAsStream("/words.dat"));
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            words.add(line);
        }
        return words;
    }

}
