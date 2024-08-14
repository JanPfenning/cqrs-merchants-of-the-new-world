package xyz.game.merchants.domain.building;

import lombok.SneakyThrows;
import xyz.game.merchants.domain.Player;
import xyz.game.merchants.domain.resources.InvalidResourceAmountException;
import xyz.game.merchants.domain.resources.Resources;

public class Settlement extends VertexBuilding {

    public Settlement(Player owner) {
        super(owner);
    }

    @Override
    @SneakyThrows(InvalidResourceAmountException.class)
    public Resources getCosts() {
        return new Resources(1, 1, 1, 1, 0);
    }

}
