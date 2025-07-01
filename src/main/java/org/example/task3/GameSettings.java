package org.example.task3;

public class GameSettings {
    static int maxPlayers;
    final String gameName;
    int currentPlayers;

    GameSettings(String gameName, int currentPlayers) {
        this.gameName = gameName;
        this.currentPlayers = currentPlayers;
    }

    static void setMaxPlayers(int newMax) {
        maxPlayers = newMax;
    }

    void addPlayer() {
        if(currentPlayers < maxPlayers) {
            currentPlayers++;
        }
    }

    void printGameStatus() {
        System.out.println(gameName + " " + currentPlayers + " " + maxPlayers);
    }
}
