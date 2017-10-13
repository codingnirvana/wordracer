package com.codingnirvana.wordracer.impl

import com.codingnirvana.wordracer.{Result, WordRacer}

class DemoWordRacer extends WordRacer {
  override def initGameBoard(letter: Char): Unit = ???

  override def pickLetter: Result = ???

  override def pickPosition(letter: Char): Int = ???
}
