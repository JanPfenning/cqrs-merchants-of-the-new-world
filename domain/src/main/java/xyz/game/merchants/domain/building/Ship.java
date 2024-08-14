package xyz.game.merchants.domain.building;

import lombok.SneakyThrows;
import xyz.game.merchants.domain.Player;
import xyz.game.merchants.domain.resources.InvalidResourceAmountException;
import xyz.game.merchants.domain.resources.Resources;

public class Ship extends EdgeBuilding {

    public Ship(Player owner) {
        super(owner);
    }

    @Override
    @SneakyThrows(InvalidResourceAmountException.class)
    public Resources getCosts() {
        return new Resources(0,1,1,0,0);
    }
}
