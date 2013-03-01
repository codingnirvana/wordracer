package codingnirvana.wordracer.samples;

import codingnirvana.wordracer.Result;

import java.util.Random;

public class RogueWordRacer extends DemoWordRacer {

    private int count;

    @Override
    public void initGameBoard(char letter) {
        super.initGameBoard(letter);
        this.count = 0;
    }

    @Override
    public Result pickLetter() {
        Result result = super.pickLetter();
        count++;

        if (count < new Random().nextInt(1000)) {
            return result;
        } else {
            return new Result(result.Position, 'a');
        }
    }

    @Override
    public int pickPosition(char letter) {
        count++;
        if (count < new Random().nextInt(1000)) {
            return super.pickPosition(letter);
        }   else {
            return 50 + new Random().nextInt(75);
        }
    }
}
