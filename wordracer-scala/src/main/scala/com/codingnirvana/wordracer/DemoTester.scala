package com.codingnirvana.wordracer

import com.codingnirvana.wordracer.impl.{Board, DemoWordRacer}

import scala.util.Random

object DemoTester {

  def main(args: Array[String]) = {
    val startingLetter = ('A' + Random.nextInt(26)).toChar

    var firstBoard = Board(IndexedSeq.fill(7,7)('*'))
    var secondBoard = Board(IndexedSeq.fill(7,7)('*'))

    val player1 = new DemoWordRacer
    val player2 = new DemoWordRacer

    player1.initGameBoard(startingLetter)
    player2.initGameBoard(startingLetter)
    firstBoard = firstBoard.update(3, 3, startingLetter)
    secondBoard = secondBoard.update(3, 3, startingLetter)

    for (i <- 0 until 24) {
      val result1 = player1.pickLetter
      firstBoard = firstBoard.update(result1)

      val pos2 = player2.pickPosition(result1.letter)
      val result2 = Result(pos2, result1.letter)
      secondBoard = secondBoard.update(result2)

      val result3 = player2.pickLetter
      secondBoard = secondBoard.update(result3)

      val pos4 = player1.pickPosition(result3.letter)
      val result4 = Result(pos4, result3.letter)
      firstBoard = firstBoard.update(result4)
    }

    println(firstBoard.toString)
    println(secondBoard.toString)

    System.exit(1)
  }

}
