package xyz.game.merchants.domain.board.harbours;

import xyz.game.merchants.domain.board.EdgeCoordinate;

public class AnyResourceHarbour extends Harbour {
    public AnyResourceHarbour(EdgeCoordinate coordinate) {
        super(coordinate, 3);
    }
}