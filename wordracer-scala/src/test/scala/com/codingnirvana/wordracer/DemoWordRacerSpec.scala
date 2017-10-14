package com.codingnirvana.wordracer

import com.codingnirvana.wordracer.impl.Board
import org.scalatest.FlatSpec
import org.scalatest.Matchers._

class DemoWordRacerSpec extends FlatSpec{

  it should "calculate scores for basic cases" in {
    val words = IndexedSeq("abalone", "al", "alone", "ne", "on", "one")
    Board(null).calc(words, "abalone", 0, 7) should be (13)
    Board(null).calc(words, "abalone", 1, 7) should be (13)
    Board(null).calc(words, "abalone", 0, 5) should be (5)
    Board(null).calc(words, "abalone", 0, 3) should be (2)
    Board(null).calc(words, "abalone", 0, 2) should be (2)
  }
}
