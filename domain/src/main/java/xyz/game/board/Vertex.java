package xyz.game.board;

import lombok.Getter;

public class Vertex {
    @Getter
    private final VertexCoordinate coordinate;
    Vertex(VertexCoordinate v) {
        this.coordinate = v;
    }
}