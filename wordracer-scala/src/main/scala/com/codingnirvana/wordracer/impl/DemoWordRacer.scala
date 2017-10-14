package com.codingnirvana.wordracer.impl

import com.codingnirvana.wordracer.{Result, WordRacer}

import scala.annotation.tailrec
import scala.util.Random

case class Board(cells: IndexedSeq[IndexedSeq[Char]]) {

  def update(result: Result) : Board = {
    this.update(result.position / 7, result.position % 7, result.letter)
  }

  def update(r: Int, c: Int, letter: Char) : Board = {
    Board(cells.updated(r, cells(r).updated(c, letter)))
  }

  def apply(r: Int)(c: Int) = cells(r)(c)

  override def toString = cells.map(_.mkString(" ")).mkString("\n")

  def calculateScore(words: Array[String]) = {

    val score = Seq(0, 0, 1, 2, 3, 5, 8, 13)


  }

}

class DemoWordRacer extends WordRacer {

  var board = Board(IndexedSeq.fill(7,7)('*'))

  override def initGameBoard(letter: Char): Unit = {
    board = board.update(3, 3, letter)
  }

  override def pickLetter: Result = {
    val rand = ('A' + Random.nextInt(26)).toChar
    Result(pickPosition(rand), rand)
  }

  override def pickPosition(letter: Char): Int = {
    pickPosRec(letter)
  }

  @tailrec
  private def pickPosRec(letter : Char) : Int = {
    val pos = Random.nextInt(49)
    val row = pos / 7
    val col = pos % 7

    if ((row, col) == (3, 3)) {
      pickPosRec(letter)
    } else if (board(row)(col) != '*') {
      pickPosRec(letter)
    } else {
      board = board.update(row, col,letter)
      pos
    }
  }

}
