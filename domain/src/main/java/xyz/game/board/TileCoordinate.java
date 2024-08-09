package xyz.game.board;

import java.util.Map;

import lombok.Getter;

public class TileCoordinate {

    private final int x, y;

    @Getter
    private final Map<CardinalDirection, TileCoordinate> surroundingTileCoordinates = Map.ofEntries(
        Map.entry(CardinalDirection.NORTH, calcNnTileCoordinate()),
        Map.entry(CardinalDirection.NORTH_EAST, calcNeTileCoordinate()),
        Map.entry(CardinalDirection.SOUTH_EAST, calcSeTileCoordinate()),
        Map.entry(CardinalDirection.SOUTH, calcSsTileCoordinate()),
        Map.entry(CardinalDirection.SOUTH_WEST, calcSwTileCoordinate()),
        Map.entry(CardinalDirection.NORTH_WEST, calcNwTileCoordinate())
    );

    @Getter
    private final EdgeCoordinate[] surroundingEdgeCoordindates;
    
    public TileCoordinate(int x, int y) {
        this.x = x;
        this.y = y;

        int baseEdgeX = x * 2;
        int baseEdgeOfsetY = x % 2 == 1 ? 2 : 0;
        int baseEdgeY = y * 4 + baseEdgeOfsetY;
        this.surroundingEdgeCoordindates = new EdgeCoordinate[]{
            new EdgeCoordinate(baseEdgeX+1, baseEdgeY+0), // N
            new EdgeCoordinate(baseEdgeX+2, baseEdgeY+1), // NE
            new EdgeCoordinate(baseEdgeX+2, baseEdgeY+3), // SE
            new EdgeCoordinate(baseEdgeX+1, baseEdgeY+4), // S
            new EdgeCoordinate(baseEdgeX+0, baseEdgeY+3), // SW
            new EdgeCoordinate(baseEdgeX+0, baseEdgeY+1)  // NW
        };
    }

    private TileCoordinate calcNnTileCoordinate() {
        return new TileCoordinate(x, y - 1);
    }
    private TileCoordinate calcNeTileCoordinate() {
        return new TileCoordinate(x+1, y + (x % 2 == 0 ? -1 : 0));
    }
    private TileCoordinate calcSeTileCoordinate() {
        return new TileCoordinate(x+1, y + (x % 2 == 0 ? 0 : +1));
    }
    private TileCoordinate calcSsTileCoordinate() {
        return new TileCoordinate(x, y + 1);
    }
    private TileCoordinate calcSwTileCoordinate() {
        return new TileCoordinate(x-1, y + (x % 2 == 0 ? 0 : +1));
    }
    private TileCoordinate calcNwTileCoordinate() {
        return new TileCoordinate(x-1, y + (x % 2 == 0 ? -1 : 0));
    }

    @Override
    public String toString() {
        return "T(" + x + "," + y + ")";
    }

}