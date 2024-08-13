package xyz.game.merchants.domain.board;

import lombok.Getter;
import lombok.NonNull;

public class Vertex {
    @NonNull
    @Getter
    private final VertexCoordinate coordinate;
    Vertex(VertexCoordinate v) {
        this.coordinate = v;
    }
}