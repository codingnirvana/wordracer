package com.codingnirvana.wordracer.gamerunner;

import java.util.HashMap;

public class Player {

    private String name;
    private String language;
    private String command;

    public Player(HashMap playerMap) {
        this.name = (String) playerMap.get("name");
        this.language = (String) playerMap.get("language");
        this.command = (String) playerMap.get("command");
    }

    public String getCommand() {
        return command;
    }

    public String getName() {
        return this.name;
    }
}
