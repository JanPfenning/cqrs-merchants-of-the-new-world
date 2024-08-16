package xyz.game.merchants.domain.board.harbours;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.game.merchants.domain.board.EdgeCoordinate;

@AllArgsConstructor
public abstract class Harbour {
    @Getter
    private EdgeCoordinate coordinate;
    @Getter
    private int costs;
}