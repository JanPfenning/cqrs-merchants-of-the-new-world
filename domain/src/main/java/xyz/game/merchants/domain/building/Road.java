package xyz.game.merchants.domain.building;

import lombok.SneakyThrows;
import xyz.game.merchants.domain.Player;
import xyz.game.merchants.domain.resources.InvalidResourceAmountException;
import xyz.game.merchants.domain.resources.Resources;

public class Road extends EdgeBuilding {

    public Road(Player owner) {
        super(owner);
    }

    @Override
    @SneakyThrows(InvalidResourceAmountException.class)
    public Resources getCosts() {
        return new Resources(1,1,0,0,0);
    }
}
