package xyz.game.merchants.domain.board;

import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.NonNull;
import xyz.game.merchants.domain.building.City;
import xyz.game.merchants.domain.building.Settlement;
import xyz.game.merchants.domain.building.VertexBuilding;

public class Vertex {
    
    @Nullable
    @Getter
    private VertexBuilding building;

    @NonNull
    @Getter
    private final VertexCoordinate coordinate;
    Vertex(VertexCoordinate v) {
        this.coordinate = v;
    }

    public void setBuilding(Settlement settlement) {
        if(this.building != null) {
            throw new RuntimeException("illegal state");
        }
        this.building = settlement;
    }

    @SuppressWarnings("null")
    public void setBuilding(City city) {
        if(this.building == null || !(this.building instanceof Settlement) || this.building.getOwner() != city.getOwner()) {
            throw new RuntimeException("illegal state");
        }
        this.building = city;
    }
}