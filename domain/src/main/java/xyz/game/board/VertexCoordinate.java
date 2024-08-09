package xyz.game.board;

public record VertexCoordinate(int x, int y){
    @Override
    public String toString() {
        return "V(" + x + "," + y + ")";
    }
}