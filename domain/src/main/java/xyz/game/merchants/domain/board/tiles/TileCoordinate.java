package xyz.game.merchants.domain.board.tiles;

import xyz.game.merchants.domain.board.Coordinate;
import xyz.game.merchants.domain.board.EdgeCoordinate;
import xyz.game.merchants.domain.board.VertexCoordinate;

public class TileCoordinate extends Coordinate {

    public TileCoordinate(int x, int y) {
        super(x, y);
    }

    public TileCoordinate[] getSurroundingTileCoordinates() {
        return new TileCoordinate[]{
            new TileCoordinate(x + 0, y - 1), // N
            new TileCoordinate(x + 1, y + (x % 2 == 0 ? -1 : 0)), // NE
            new TileCoordinate(x + 1, y + (x % 2 == 0 ? 0 : +1)), // SE
            new TileCoordinate(x + 0, y + 1), // S
            new TileCoordinate(x - 1, y + (x % 2 == 0 ? 0 : +1)), // SW
            new TileCoordinate(x - 1, y + (x % 2 == 0 ? -1 : 0)), // NW
        };
    };

    public EdgeCoordinate[] getSurroundingEdgeCoordinates() {
        int baseEdgeX = x * 2;
        int baseEdgeOfsetY = x % 2 == 1 ? 2 : 0;
        int baseEdgeY = y * 4 + baseEdgeOfsetY;
        return new EdgeCoordinate[]{
            new EdgeCoordinate(baseEdgeX+1, baseEdgeY+0), // N
            new EdgeCoordinate(baseEdgeX+2, baseEdgeY+1), // NE
            new EdgeCoordinate(baseEdgeX+2, baseEdgeY+3), // SE
            new EdgeCoordinate(baseEdgeX+1, baseEdgeY+4), // S
            new EdgeCoordinate(baseEdgeX+0, baseEdgeY+3), // SW
            new EdgeCoordinate(baseEdgeX+0, baseEdgeY+1),  // NW
        };

    };

    public VertexCoordinate[] getSurroundingVertexCoordinates() {
        int baseVertexOfsetY = x % 2 == 0 ? 0 : 1;
        int baseVertexY = y * 2 + baseVertexOfsetY;
        return new VertexCoordinate[]{
            new VertexCoordinate(x+1, baseVertexY+0), // NE
            new VertexCoordinate(x+1, baseVertexY+1), // E
            new VertexCoordinate(x+1, baseVertexY+2), // SE
            new VertexCoordinate(x+0, baseVertexY+2), // SW
            new VertexCoordinate(x+0, baseVertexY+1), // W
            new VertexCoordinate(x+0, baseVertexY+0),  // NW
        };
    };

    @Override
    public String toString() {
        return "T(" + x + "," + y + ")";
    }
}