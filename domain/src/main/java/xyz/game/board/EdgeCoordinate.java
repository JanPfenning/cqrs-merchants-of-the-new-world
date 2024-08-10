package xyz.game.board;

import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EdgeCoordinate{

    @EqualsAndHashCode.Include
    private final int x;
    
    @EqualsAndHashCode.Include
    private final int y;

    public EdgeCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @SneakyThrows(MissingCaseException.class)
    public TileCoordinate[] getSurroundingTileCoordinates() {
        if(x % 2 == 0 && y % 4 == 1) {
            return new TileCoordinate[]{
                new TileCoordinate((x/2) - 1, (y-5) / 4),
                new TileCoordinate((x/2) + 0, (y-1) / 4),
            };
        }
        if(x % 2 == 1 && y % 4 == 0){
            return new TileCoordinate[]{
                new TileCoordinate((x/2) + 0, (y-4) / 4),
                new TileCoordinate((x/2) + 0, (y-0) / 4),
            };
        }
        if(x % 2 == 1 && y % 4 == 2){
            return new TileCoordinate[]{
                new TileCoordinate((x/2) - 0, (y-6) / 4),
                new TileCoordinate((x/2) + 0, (y-2) / 4),
            };
        }
        if(x % 2 == 0 && y % 4 == 3){
            return new TileCoordinate[]{
                new TileCoordinate((x/2) - 1, (y-3) / 4),
                new TileCoordinate((x/2) + 0, (y-3) / 4),
            };
        }
        // x%2 == 0 && y % 4 == 2 => error
        // x%2 == 0 && y % 4 == 0 => error
        throw new MissingCaseException(x, y);
    }

    public EdgeCoordinate[] getSurroundingEdgeCoordinates() {
        boolean extendLeft = x%2 == 0 && y%2 == 1 || x%2 == 1 && y%2 == 0;
        return new EdgeCoordinate[]{
            new EdgeCoordinate(x, y-1),
            new EdgeCoordinate(x + (extendLeft ? -1 : 1), y),
            new EdgeCoordinate(x, y+1),
        };
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