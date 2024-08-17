package xyz.game.merchants.domain.board.interactable;

import xyz.game.merchants.domain.board.EdgeCoordinate;
import xyz.game.merchants.domain.board.Vertex;

public class PlaceEdgeBuildingException extends Exception {
    PlaceEdgeBuildingException(String reason) {
        super(reason);
    }
}

class BuildingOnNonExistingEdgeException extends PlaceEdgeBuildingException {
    BuildingOnNonExistingEdgeException(EdgeCoordinate c) {
        super("No Edge exists at "+c.toString());
    }
}

class LeftSettlementWithoutConnectionException extends PlaceEdgeBuildingException {
    LeftSettlementWithoutConnectionException(Vertex v) {
        super(v.toString() + " would be left without edge connection");
    }
}