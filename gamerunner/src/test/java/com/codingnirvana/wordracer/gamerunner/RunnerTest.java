package com.codingnirvana.wordracer.gamerunner;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RunnerTest {

    @Test
    public void shouldCalculateCorrectScoreForBoard1() {

        int[][] score = Game.calculateScore(new char[][]{new char[]{'A', 'B', 'A', 'L', 'O', 'N', 'E'},
                new char[]{'X', 'I', 'B', 'I', 'U', 'L', 'A'},
                new char[]{'I', 'Q', 'I', 'P', 'T', 'I', 'C'},
                new char[]{'S', 'N', 'O', 'B', 'W', 'A', 'Y'},
                new char[]{'E', 'L', 'S', 'A', 'O', 'N', 'M'},
                new char[]{'S', 'H', 'E', 'B', 'R', 'A', 'A'},
                new char[]{'W', 'E', 'S', 'Y', 'K', 'Y', 'D'}});

        int[][] expectedScore = {new int[]{13, 3, 2, 5, 3, 4, 1},
                new int[]{8, 2, 13, 5, 13, 5, 3}};

        assertThat(score, is(expectedScore));
    }


    @Test
    public void shouldCalculateCorrectScoreForBoard2() {
        int[][] score = Game.calculateScore(new char[][]{new char[]{'E', 'P', 'M', 'J', 'J', 'Q', 'J'},
                new char[]{'E', 'A', 'E', 'U', 'U', 'U', 'U'},
                new char[]{'Y', 'B', 'J', 'M', 'M', 'A', 'D'},
                new char[]{'Q', 'U', 'I', 'P', 'P', 'E', 'D'},
                new char[]{'E', 'L', 'E', 'O', 'O', 'R', 'E'},
                new char[]{'Q', 'U', 'A', 'F', 'F', 'E', 'R'},
                new char[]{'O', 'M', 'S', 'F', 'F', 'S', 'S'}});

        int[][] expectedScore = {new int[]{0,1,2,13,3,13,2},
                new int[]{0,13,2,13,13,13,13}};

        assertThat(score, is(expectedScore));

    }


    @Test
    public void shouldCalculateCorrectScoreForBoard3() {
        int[][] score = Game.calculateScore(new char[][]{new char[]{'R', 'D', 'O', 'U', 'S', 'E', 'S'},
                new char[]{'E', 'R', 'A', 'J', 'I', 'F', 'U'},
                new char[]{'S', 'U', 'B', 'Q', 'F', 'O', 'F'},
                new char[]{'E', 'M', 'O', 'P', 'E', 'D', 'Q'},
                new char[]{'E', 'L', 'M', 'E', 'M', 'E', 'U'},
                new char[]{'J', 'Y', 'U', 'P', 'M', 'U', 'A'},
                new char[]{'P', 'J', 'F', 'A', 'E', 'J', 'Q'}});

        int[][] expectedScore = {new int[]{8,3,3,5,5,3,1},
                new int[]{5,8,3,2,6,3,2}};

        assertThat(score, is(expectedScore));

    }

    @Test
    public void shouldCalculateCorrectScoreForBoard4() {

        int[][] score = Game.calculateScore(new char[][]{new char[]{'F', 'U', 'T', 'T', 'O', 'C', 'K'},
                new char[]{'U', 'N', 'E', 'Q', 'U', 'A', 'L'},
                new char[]{'T', 'R', 'A', 'M', 'M', 'E', 'L'},
                new char[]{'H', 'O', 'L', 'Y', 'D', 'A', 'Y'},
                new char[]{'O', 'V', 'E', 'R', 'B', 'I', 'D'},
                new char[]{'R', 'E', 'B', 'A', 'T', 'E', 'S'},
                new char[]{'K', 'N', 'O', 'Z', 'J', 'J', 'I'}});

        int[][] expectedScore = {new int[]{13,13,13,13,13,13,1},
                new int[]{13,13,4,1,0,2,1}};

        assertThat(score, is(expectedScore));
    }

    @Test
    public void shouldCalculateCorrectScoreForBoard5() {

        int[][] score = Game.calculateScore(new char[][]{new char[]{'F', 'U', 'Z', 'Z', 'I', 'L', 'Y'},
                new char[]{'U', 'N', 'B', 'O', 'W', 'E', 'D'},
                new char[]{'T', 'R', 'A', 'P', 'P', 'E', 'R'},
                new char[]{'H', 'A', 'U', 'N', 'T', 'E', 'R'},
                new char[]{'O', 'V', 'E', 'R', 'L', 'A', 'P'},
                new char[]{'R', 'E', 'G', 'A', 'L', 'E', 'D'},
                new char[]{'C', 'L', 'A', 'W', 'G', 'J', 'A'}});

        int[][] expectedScore = {new int[]{13,13,13,13,13,13,3},
                new int[]{13,13,1,3,0,3,1}};

        assertThat(score, is(expectedScore));
    }

}
