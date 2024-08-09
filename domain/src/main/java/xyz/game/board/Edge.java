package xyz.game.board;

import lombok.Getter;

public class Edge {
    @Getter
    private final EdgeCoordinate coordinate;
    Edge(EdgeCoordinate c){
        this.coordinate = c;
    }
}