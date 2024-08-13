package xyz.game.merchants.domain.board;

import lombok.Getter;
import lombok.NonNull;

public class Edge {
    @NonNull
    @Getter
    private final EdgeCoordinate coordinate;
    Edge(EdgeCoordinate c){
        this.coordinate = c;
    }
}