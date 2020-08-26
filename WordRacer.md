
## Word Racer

### The Problem
You need to write a program that will play a game against other players. If you have ever played Scrabble and Battleship, the rules will be familiar.

### The Rules
The game will be played between two players on a 7 X 7 board which will be filled with letters during the course of game. 

The first letter is chosen randomly at the start of the game. Both players must put this letter in the central cell of their boards. During the remainder of the game, the players take turns in calling out a letter. Both players then write down this letter in any empty cell on their boards.
Individual board is kept hidden from the opponent until the end of the game. 
After 24 turns for each player (48 total turns), both boards are full and the game ends. 

### Goal of the Game
Each player tries to make horizontal (left to right) or vertical (top to bottom) words in the board. At the end of the game, the number of points in the board is determined. 

We use a simple and straightforward scoring scheme. Longer words give more points: a seven-letter word gives 13 points, a six-letter word gives 8 points, and words of 5, 4, 3 or 2 letters will gain 5, 3, 2 or 1 points. Each cell can be used in only one word in each direction (see below). 

The winner of the game is the player with more points than his opponent. If both players have the same number of word points, the game ends in a draw. 

![](img/board.png)

Final position for one of the players

### Counting Points
A list of all words for which points are given, is available in the file `words.dat`. Each word in this file is on a line by itself. During the game, this file will be available to your program in the current directory. Your program is allowed to use the same word in more than one place in the board. 

![](image/word-length.png)

The number of points in a board is equal to the sum of the points of all valid words in the board. However, a board cell can be used in at most one horizontal word and at most one vertical word. In the case of overlapping words in the same direction, only one of the words will get points; in such cases we always select the words that give most points. Crossing horizontal and vertical words are no problem at all; both words can get full points since the crossing cell is used in different directions. 

### How to Play
Your player program receives letters as input, and provides letters and board positions as output. Letters are given as upper case letters from the alphabet (A .. Z). Positions on the board are identified by numbers (see the figure below). 

The first line of input contains the starting letter which is automatically placed in the center of the board (at position 24). 
The second line of input specifies if you are the first one to start or your opponent is starting first. A value of ‘1’ indicates that you start first and ‘0’ otherwise.

When it is your program's turn to pick a letter, it should produce a single line of output: with the chosen letter, followed by a space and then the position where your program puts this letter on the diagram. After your opponent has picked a letter, your program will receive his chosen letter as input. You should then produce just one line of output, containing the position on the board where you want to put this letter. The two players continue like this, taking turns at picking a first letter, until the board is completely filled. 

Note that your program receives just the letters picked by your opponent, not the positions where the letters are placed on his board. The same thing is true for your opponent of course, so your board is effectively kept hidden from your opponent during the game. 


### Identification of board cells

![](img/board-cells.png)


### General Instructions

* You are allowed to use any programming language of your choice as long as you can give us a script that will allow us to run your program. 
* We will play a tournament consisting of round robin and knockout rounds. The details will be provided during the contest.
* For every game if you win, draw or lose - you earn 3, 1 or 0 points respectively. 
* For each game, each player is allowed only a maximum of 10 seconds, on timeout the player loses the match and your opponent wins. Only the time in which your program calculates will be taken into account. And your time will be the cumulative time of all your moves.
* In case of invalid output format, the player loses the match and your opponent wins i.e. you get 0 points and your opponent gets 3 points. 
* To keep the contest fair, we do not allow the following things :
    * Your program should not write any files, and should not read from any files except words.dat.
    * It is not allowed to make network connections of any sort.
    * System dependent tricks like starting other programs or creating extra processes are not allowed.
    * It is not possible to do calculations while it is your opponent's turn to make a move. 

### Getting Started

To make it easy for you to get started, we have made the following available

1. **Language Templates** - We have templates for Python and Scala languages that provide a skeleton program to write your bot so that you are not bothered about the communication mechanisms and are focused only on the logic required for the bot.
    * Python - There is a single file `wordracer.py` that handles all the IO and has a basic skeleton code to get started
    * Scala
        * There is a `DemoWordRacer.scala` implementation that can be used as a starting point
        * You can test locally on console by running `LocalTester.scala`
        * `Runner.scala` is the main class that handles all the IO logic. Do make sure to change the word racer implementation to your class in the `Runner.scala` file (otherwise it will always run with the demo implementation)
        * Running `sbt assembly` command will create the jar that you can then submit for evaluation.
    
2. **Visualizer** - Before you submit your bot, you can test the jar you created above, using the visualizer to make sure that it makes valid moves and communicates correctly with the contest system. 

    The visualizer is provided as a JAR named visualizer-*.jar. It takes 2 arguments 
    * Path to players.yml which defines the bot location and configurations (default - ./conf/players.yml)
    * Path to base location of player bots (default - ./run)

    In players.yml
    ```
    players:
    - name: MyBot
        directory: mybot
        command: ./mybot_racer
    ```
    where, 
    * “name” - Name of your Bot
    * “directory” - Directory inside your base location where your bot resides
    * “command” - Command to execute your bot 
        * `command: java -jar demoracer.jar`
        * `command: python wordracer.py`

    Once you run the visualizer, it will simulate a tournament where each player will play every other player twice and produces a `results` folder in the `run` directory with the tournament results. Open `summary.html` to check out the scores as well as to visualize each of the games by clicking on the links to each game.  

    The default `players.yml` contains two bots - the Demo bot from the Scala template and an bot called `ZRacer` that uses Z where possible and is a medium level bot.

