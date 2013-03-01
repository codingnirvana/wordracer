package codingnirvana.wordracer.samples;

import codingnirvana.wordracer.Result;

public class SlowWordRacer extends DemoWordRacer {
    @Override
    public Result pickLetter() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {

        }
        return super.pickLetter();
    }

    @Override
    public int pickPosition(char letter) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {

        }
        return super.pickPosition(letter);
    }
}
