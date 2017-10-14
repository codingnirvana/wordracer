package com.codingnirvana.wordracer;

import java.io.IOException;

public interface WordRacer
{
    public void initGameBoard(char letter) throws IOException;
    public Result pickLetter() throws IOException;
    public int pickPosition(char letter) throws IOException;
}
