package com.codingnirvana.wordracer;

public class Result
{
    public Result(int position, char letter) {

        if (position < 0 || position > 48) {
            throw new RuntimeException(String.format("Position should be between 0 and 48 - %d given", position));
        }

        this.position = position;
        this.letter = letter;
    }

    public int position;
    public char letter;
}
