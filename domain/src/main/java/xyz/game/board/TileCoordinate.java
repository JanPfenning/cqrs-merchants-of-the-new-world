package xyz.game.board;

import java.util.Map;

import lombok.Getter;

public class TileCoordinate {

    private final int x, y;

    @Getter
    private final TileCoordinate[] surroundingTileCoordinates;

    @Getter
    private final EdgeCoordinate[] surroundingEdgeCoordindates;
    
    public TileCoordinate(int x, int y) {
        this.x = x;
        this.y = y;

        this.surroundingTileCoordinates = new TileCoordinate[]{
            new TileCoordinate(x + 0, y - 1), // N
            new TileCoordinate(x + 1, y + (x % 2 == 0 ? -1 : 0)), // NE
            new TileCoordinate(x + 1, y + (x % 2 == 0 ? 0 : +1)), // SE
            new TileCoordinate(x + 0, y + 1), // S
            new TileCoordinate(x - 1, y + (x % 2 == 0 ? 0 : +1)), // SW
            new TileCoordinate(x - 1, y + (x % 2 == 0 ? -1 : 0)), // NW
        };

        int baseEdgeX = x * 2;
        int baseEdgeOfsetY = x % 2 == 1 ? 2 : 0;
        int baseEdgeY = y * 4 + baseEdgeOfsetY;
        this.surroundingEdgeCoordindates = new EdgeCoordinate[]{
            new EdgeCoordinate(baseEdgeX+1, baseEdgeY+0), // N
            new EdgeCoordinate(baseEdgeX+2, baseEdgeY+1), // NE
            new EdgeCoordinate(baseEdgeX+2, baseEdgeY+3), // SE
            new EdgeCoordinate(baseEdgeX+1, baseEdgeY+4), // S
            new EdgeCoordinate(baseEdgeX+0, baseEdgeY+3), // SW
            new EdgeCoordinate(baseEdgeX+0, baseEdgeY+1),  // NW
        };
    }

    @Override
    public String toString() {
        return "T(" + x + "," + y + ")";
    }

}