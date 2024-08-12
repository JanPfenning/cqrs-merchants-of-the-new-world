package xyz.game.domain.board;

import lombok.Getter;
import lombok.NonNull;

public abstract class Tile {
    @NonNull
    @Getter
    protected final TileCoordinate coordinate;
    @Getter
    protected final TileNumber number;

    Tile(TileCoordinate c) {
        this.coordinate = c;
        this.number = null;
    }

    Tile(TileCoordinate c, TileNumber tileNumber) {
        this.coordinate = c;
        this.number = tileNumber;
    }
}