package com.codingnirvana.wordracer

import com.codingnirvana.wordracer.impl.Board
import org.scalatest.FlatSpec
import org.scalatest.Matchers._

class DemoWordRacerSpec extends FlatSpec {

  val stream = getClass.getResourceAsStream("/words.dat")
  val words = scala.io.Source.fromInputStream(stream).getLines.toIndexedSeq.sorted

  it should "calculate scores for a single row for a given length" in {
    val board = Board(null, words)
    board.calc("ABALONE", 0, 7) should be(13)
    board.calc("ABALONE", 0, 5) should be(5)
    board.calc("ABALONE", 0, 3) should be(4)
    board.calc("ABALONE", 0, 2) should be(2)
  }


  it should "calculate scores for a single row for all lengths - Board 1" in {
    val cells = IndexedSeq(
      "ABALONE",
      "XIBIULA",
      "IQIPTIC",
      "SNOBWAY",
      "ELSAONM",
      "SHEBRAA",
      "WESYKYD")
      .map(_.toIndexedSeq)
    val board = Board(cells, words)
    val score = board.calculateScore()
    score.rowScore should be(Vector(13, 3, 2, 5, 3, 4, 1))
    score.colScore should be(Vector(8, 2, 13, 5, 15, 5, 3))
  }
}
