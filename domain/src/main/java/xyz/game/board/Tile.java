package xyz.game.board;

import lombok.Getter;

public abstract class Tile {
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