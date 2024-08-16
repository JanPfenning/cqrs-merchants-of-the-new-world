package xyz.game.merchants.domain.board.tiles;

import lombok.Getter;
import lombok.NonNull;

public abstract class Tile {
    @NonNull
    @Getter
    protected final TileCoordinate coordinate;
    @Getter
    protected final TileNumber number;

    protected Tile(TileCoordinate c) {
        this.coordinate = c;
        this.number = null;
    }

    protected Tile(TileCoordinate c, TileNumber tileNumber) {
        this.coordinate = c;
        this.number = tileNumber;
    }
}