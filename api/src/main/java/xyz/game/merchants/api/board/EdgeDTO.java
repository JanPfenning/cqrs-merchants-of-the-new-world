package xyz.game.merchants.api.board;

import lombok.Getter;
import xyz.game.merchants.domain.board.Edge;
import xyz.game.merchants.domain.board.EdgeCoordinate;

public class EdgeDTO {
    @Getter
    public final CoordinateDTO coordinate;

    public EdgeDTO(EdgeCoordinate c) {
        this.coordinate = new CoordinateDTO(c.getX(), c.getY());
    }

    public static EdgeDTO from(Edge e) {
        return new EdgeDTO(e.getCoordinate());
    }
}