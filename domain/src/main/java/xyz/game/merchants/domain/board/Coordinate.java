package xyz.game.merchants.domain.board;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Coordinate {
    abstract TileCoordinate[] getSurroundingTileCoordinates();
    abstract EdgeCoordinate[] getSurroundingEdgeCoordinates();
    abstract VertexCoordinate[] getSurroundingVertexCoordinates();

    @Getter
    @EqualsAndHashCode.Include
    protected final int x;
    
    @Getter
    @EqualsAndHashCode.Include
    protected final int y;
    
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
