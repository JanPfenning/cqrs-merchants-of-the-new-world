package xyz.game.board;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VertexCoordinate{

    @EqualsAndHashCode.Include
    private final int x;

    @EqualsAndHashCode.Include
    private final int y;

    public VertexCoordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public VertexCoordinate[] getSurroundingVertexCoordinates() {
        boolean extendLeft = x%2 == 0 && y%2 == 1 || x%2 == 1 && y%2 == 0;
        return new VertexCoordinate[]{
            new VertexCoordinate(x, y-1),
            new VertexCoordinate(x + (extendLeft ? -1 : 1), y),
            new VertexCoordinate(x, y+1),
        };
    }

    public TileCoordinate[] getSurroundingTileCoordinates() {
        boolean xEven = x % 2 == 0;
        boolean yEven = y % 2 == 0;
        if(xEven && yEven){
            return new TileCoordinate[]{
                new TileCoordinate(x, y/2-1),
                new TileCoordinate(x-1, y/2-1),
                new TileCoordinate(x, y/2),
            };
        }
        if(xEven && !yEven){
            return new TileCoordinate[]{
                new TileCoordinate(x-1, y/2-1),
                new TileCoordinate(x, y/2),
                new TileCoordinate(x-1, y/2),
            };
        }
        if(!xEven && yEven){
            return new TileCoordinate[]{
                new TileCoordinate(x-1, y/2-1),
                new TileCoordinate(x, y/2-1),
                new TileCoordinate(x-1, y/2),
            };
        }
        // if(!xEven && !yEven){
        return new TileCoordinate[]{
            new TileCoordinate(x, y/2-1),
            new TileCoordinate(x-1, y/2),
            new TileCoordinate(x, y/2),
        };
    }

    public EdgeCoordinate[] getSurroundingEdgeCoordinates() {
        boolean bothEvenOrBothOdd = x % 2 == y % 2;
        if(bothEvenOrBothOdd) {
            return new EdgeCoordinate[]{
                new EdgeCoordinate(2*x, y*2-1),
                new EdgeCoordinate(2*x+1, y*2),
                new EdgeCoordinate(2*x, y*2+1),
            };
        }
        return new EdgeCoordinate[]{
            new EdgeCoordinate(2*x, y*2-1),
            new EdgeCoordinate(2*x-1, y*2),
            new EdgeCoordinate(2*x, y*2+1),
        };
    }

    @Override
    public String toString() {
        return "V(" + x + "," + y + ")";
    }
}