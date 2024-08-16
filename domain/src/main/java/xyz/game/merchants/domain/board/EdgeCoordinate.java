package xyz.game.merchants.domain.board;

import lombok.SneakyThrows;
import xyz.game.merchants.domain.board.tiles.TileCoordinate;

public class EdgeCoordinate extends Coordinate{

    public EdgeCoordinate(int x, int y) {
        super(x, y);
    }

    @SneakyThrows(MissingCaseException.class)
    public TileCoordinate[] getSurroundingTileCoordinates() {
        if(x % 2 == 1){
            return new TileCoordinate[]{
                new TileCoordinate((x/2) + 0, y / 4),
                new TileCoordinate((x/2) + 0, y / 4 - 1),
            };
        }
        if(x % 4 == 0) {
            if(y % 4 == 1) {
                return new TileCoordinate[]{
                    new TileCoordinate(x / 2    , y / 4    ),
                    new TileCoordinate(x / 2 - 1, y / 4 - 1),
                };
            }
            if(y % 4 == 3) {
                return new TileCoordinate[]{
                    new TileCoordinate(x / 2    , y / 4),
                    new TileCoordinate(x / 2 - 1, y / 4),
                };
            }
        }
        if(x % 4 == 2) {
            if(y % 4 == 1) {
                return new TileCoordinate[]{
                    new TileCoordinate(x / 2    , y / 4 - 1),
                    new TileCoordinate(x / 2 - 1, y / 4),
                };
            }
            if(y % 4 == 3) {
                return new TileCoordinate[]{
                    new TileCoordinate(x / 2    , y / 4),
                    new TileCoordinate(x / 2 - 1, y / 4),
                };
            }
        }
        // x%2 == 0 && y % 4 == 2 => error
        // x%2 == 0 && y % 4 == 0 => error
        throw new MissingCaseException(x, y);
    }

    @SneakyThrows(MissingCaseException.class)
    public EdgeCoordinate[] getSurroundingEdgeCoordinates() {
        if(x % 2 == 1){
            return new EdgeCoordinate[]{
                new EdgeCoordinate(x - 1, y - 1),
                new EdgeCoordinate(x - 1, y + 1),
                new EdgeCoordinate(x + 1, y - 1),
                new EdgeCoordinate(x + 1, y + 1),
            };
        }
        if((x % 4 + y % 4) % 4 == 3 /* SW / NE */) {
            return new EdgeCoordinate[]{
                new EdgeCoordinate(x + 0, y - 2),
                new EdgeCoordinate(x + 1, y + 1),
                new EdgeCoordinate(x - 1, y - 1),
                new EdgeCoordinate(x + 0, y + 2),
            };
        }
        if((x % 4 + y % 4) % 4 == 1 /* SE / NW */) {
            return new EdgeCoordinate[]{
                new EdgeCoordinate(x + 0, y - 2),
                new EdgeCoordinate(x + 1, y - 1),
                new EdgeCoordinate(x - 1, y + 1),
                new EdgeCoordinate(x + 0, y + 2),
            };
        }
        // x%2 == 0 && y % 4 == 2 => error
        // x%2 == 0 && y % 4 == 0 => error
        throw new MissingCaseException(x, y);
    }

    public VertexCoordinate[] getSurroundingVertexCoordinates() {
        boolean horizontal = y % 2 == 0;
        if(horizontal) {
            return new VertexCoordinate[]{
                new VertexCoordinate(x / 2 + 0, y / 2),
                new VertexCoordinate(x / 2 + 1, y / 2),
            };
        }
        return new VertexCoordinate[]{
            new VertexCoordinate(x / 2, y / 2 + 0),
            new VertexCoordinate(x / 2, y / 2 + 1),
        };
    }

    @Override
    public String toString() {
        return "E(" + x + "," + y + ")";
    }

    public class MissingCaseException extends Exception {
        MissingCaseException(int x, int y){
            super("E("+x+", "+y+") is not a valid edge");
        }
    }
    
}