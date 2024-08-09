package xyz.game.board;

public record TileCoordinate(int x, int y){
    @Override
    public String toString() {
        return "T(" + x + "," + y + ")";
    }
}