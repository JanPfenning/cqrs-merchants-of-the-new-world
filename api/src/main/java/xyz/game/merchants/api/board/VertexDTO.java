package xyz.game.merchants.api.board;

import lombok.Getter;
import xyz.game.merchants.domain.board.Vertex;
import xyz.game.merchants.domain.board.VertexCoordinate;

public class VertexDTO {
    @Getter
    public final CoordinateDTO coordinate;

    public VertexDTO(VertexCoordinate c) {
        this.coordinate = new CoordinateDTO(c.getX(), c.getY());
    }

    public static VertexDTO from(Vertex v) {
        return new VertexDTO(v.getCoordinate());
    }
}