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

    @Override
    public String toString() {
        return "V(" + x + "," + y + ")";
    }
}