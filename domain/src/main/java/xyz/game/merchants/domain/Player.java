package xyz.game.merchants.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Player {
    public final UUID uuid;
    public final String name;
    
    public Player(String name) {
        this.name = name;
        this.uuid = UUID.randomUUID();
    }
}
