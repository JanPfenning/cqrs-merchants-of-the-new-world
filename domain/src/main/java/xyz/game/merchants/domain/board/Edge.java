package xyz.game.merchants.domain.board;

import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.NonNull;
import xyz.game.merchants.domain.building.EdgeBuilding;

public class Edge {

    @Nullable
    @Getter
    private EdgeBuilding building;

    @NonNull
    @Getter
    private final EdgeCoordinate coordinate;
    Edge(EdgeCoordinate c){
        this.coordinate = c;
    }

    public void setBuilding(EdgeBuilding building) {
        if(this.building != null) {
            throw new RuntimeException("illegal state");
        }
        this.building = building;
    }
}