package xyz.game;

import java.util.UUID;

public class Player {
    UUID uuid = new UUID(0, 0);
    public final String name;
    
    public Player(String name) {
        this.name = name;
    }
}
