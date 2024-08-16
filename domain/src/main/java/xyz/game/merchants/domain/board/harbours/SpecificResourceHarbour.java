package xyz.game.merchants.domain.board.harbours;

import lombok.Getter;
import xyz.game.merchants.domain.board.EdgeCoordinate;
import xyz.game.merchants.domain.board.Resource;

public class SpecificResourceHarbour extends Harbour {
    @Getter
    private final Resource resoruce;
    public SpecificResourceHarbour(EdgeCoordinate coordinate, Resource resource) {
        super(coordinate, 2);
        this.resoruce = resource;
    }
}
