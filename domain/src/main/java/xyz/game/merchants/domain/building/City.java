package xyz.game.merchants.domain.building;

import lombok.SneakyThrows;
import xyz.game.merchants.domain.Player;
import xyz.game.merchants.domain.resources.InvalidResourceAmountException;
import xyz.game.merchants.domain.resources.Resources;

public class City extends VertexBuilding {

    public City(Player owner) {
        super(owner);
    }

    @Override
    @SneakyThrows(InvalidResourceAmountException.class)
    public Resources getCosts() {
        return new Resources(0, 0, 0, 3, 2);
    }

}
