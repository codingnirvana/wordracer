package com.indix;


import java.io.IOException;

public class MyWordRacer extends WordRacer {

    @Override
    public void initGameBoard(char letter) throws IOException {
        super.initGameBoard(letter);
    }

    public Result pickLetter() {
        // TODO: Fill in this method
        return new Result(pickPosition('a'), 'a');
    }

    public int pickPosition(char letter) {
        // TODO: Fill in this method
        return 1;
    }
}


