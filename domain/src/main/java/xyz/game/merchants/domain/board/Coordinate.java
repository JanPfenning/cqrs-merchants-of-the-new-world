package xyz.game.merchants.domain.board;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import xyz.game.merchants.domain.board.tiles.TileCoordinate;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Coordinate {
    public abstract TileCoordinate[] getSurroundingTileCoordinates();
    public abstract EdgeCoordinate[] getSurroundingEdgeCoordinates();
    public abstract VertexCoordinate[] getSurroundingVertexCoordinates();

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
