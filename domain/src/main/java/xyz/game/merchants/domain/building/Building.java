package xyz.game.merchants.domain.building;

import lombok.Getter;
import xyz.game.merchants.domain.Payable;
import xyz.game.merchants.domain.Player;

public abstract class Building implements Payable {
    @Getter
    final Player owner;

    Building(Player owner) {
        this.owner = owner;
    }
}
