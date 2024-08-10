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

    @Override
    public String toString() {
        return "V(" + x + "," + y + ")";
    }
}