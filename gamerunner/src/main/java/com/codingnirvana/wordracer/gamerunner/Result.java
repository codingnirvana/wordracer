package com.codingnirvana.wordracer.gamerunner;

public class Result
{
    public Result(int position, char letter) {
        this.position = position;
        this.letter = letter;
    }

    public int position;
    public char letter;

    public int getPosition() {
        return position;
    }

    public char getLetter() {
        return letter;
    }
}
