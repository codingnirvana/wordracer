package com.codingnirvana.wordracer.impl


import com.codingnirvana.wordracer.{Result, WordRacer}

import scala.annotation.tailrec
import scala.collection.Searching._
import scala.util.Random

case class Score(rowScore: IndexedSeq[Int], colScore: IndexedSeq[Int]) {
  val totalScore = rowScore.sum + colScore.sum
}

case class Board(cells: IndexedSeq[IndexedSeq[Char]]) {

  def update(result: Result) : Board = {
    this.update(result.position / 7, result.position % 7, result.letter)
  }

  def update(r: Int, c: Int, letter: Char) : Board = {
    Board(cells.updated(r, cells(r).updated(c, letter)))
  }

  def apply(r: Int)(c: Int) = cells(r)(c)

  override def toString = cells.map(_.mkString("  ")).mkString("\n")

  def toString(score: Score) = {
    val rows = cells.map(_.mkString("   "))
      .zip(score.rowScore)
      .map { case (v, s) => s"$v   $s"}
      .mkString("\n")

    val lastRow = s"${score.colScore.mkString("   ")}   ${score.totalScore}"

    rows + "\n" + lastRow
  }
  val score = Seq(0, 0, 1, 2, 3, 5, 8, 13)

  def calculateScore(words: IndexedSeq[String]) = {

    val rowScore = cells.map { cell =>
      (2 to 7).reverse.fold(0) { case (soFar, len) => soFar + calc(words, cell.mkString, 0, len) }
    }

    val colScore = cells.transpose.map { cell =>
      (2 to 7).reverse.fold(0) { case (soFar, len) => soFar + calc(words, cell.mkString, 0, len) }
    }

    Score(rowScore, colScore)
  }

  def calc(words: IndexedSeq[String], rowOrCol: String, start: Int, len: Int) : Int = {
    if (start + len > rowOrCol.length) {
      0
    } else {
      val wordToFind = rowOrCol.slice(start, start + len).mkString("")
      words.search(wordToFind) match {
        case Found(x) => score(len) + calc(words, rowOrCol, start + len, len)
        case _        => calc(words, rowOrCol, start + 1, len)
      }
    }
  }

}

class DemoWordRacer extends WordRacer {

  var board = Board(IndexedSeq.fill(7,7)('*'))

  override def initGameBoard(letter: Char): Unit = {
    board = board.update(3, 3, letter)
  }

  override def pickLetter: Result = {
    val rand = ('A' + Random.nextInt(26)).toChar
    new Result(pickPosition(rand), rand)
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
