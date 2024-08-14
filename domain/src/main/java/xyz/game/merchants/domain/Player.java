package xyz.game.merchants.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;
import xyz.game.merchants.domain.resources.Resources;

@AllArgsConstructor
public class Player {
    public final UUID uuid;
    public final String name;
    public Resources resources = new Resources();
    
    public Player(String name) {
        this.name = name;
        this.uuid = UUID.randomUUID();
    }
}
