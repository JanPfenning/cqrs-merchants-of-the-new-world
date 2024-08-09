package xyz.game.board;

public record EdgeCoordinate(int x, int y){
    @Override
    public String toString() {
        return "E(" + x + "," + y + ")";
    }
}