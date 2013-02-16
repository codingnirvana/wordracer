wordracer
=========

Word Racer Demo + Tester Program

### Introduction ###
There are two modules in the project
- GameRunner - You can use this to test your bot against the demo wordracer bot.
- DemoWordRacer - This module contains the source code for the demo wordracer, which plays randomly


### How to run your bot ###

- Ensure that you are follow the input/output instructions exactly as specified in the problem statement
- Take a look at the demo wordracer to see how it takes inputs and writes output
- Modify the players.yml file (in the conf directory of gamerunner module) to include the details of your bot
- There are two important settings in the yml file (other than name and language) - directory and command
- The directory is your working directory where your bot will be placed. All your scripts and files should be in this directory.The parent directory is the "run" directory which contains all the bots
- The command is either a single command (with args) or shell script to start your bot.
- Also ensure that the words.dat is in your directory
- Run the gamerunner with your bot and the demo bot by running the following commands. 

```
  # package the gamerunner jar
  gamerunner $ mvn clean package -P fatjar
  
  # run the gamerunner
  gamerunner $ java -jar target/gamerunner-1.0.jar <path to players.yml> <path to run directory>
  
```
- If everything is configured correctly, you should see the score when the game finishes. 


### TODOS ###
- Handle error scenarios better (like commands incorrect, killing bots that take too long, or respond with incorrect input/output"
- Visualization via JS
