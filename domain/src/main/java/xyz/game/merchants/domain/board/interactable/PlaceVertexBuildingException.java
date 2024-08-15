package xyz.game.merchants.domain.board.interactable;

import xyz.game.merchants.domain.board.VertexCoordinate;

public class PlaceVertexBuildingException extends Exception {
    PlaceVertexBuildingException(String reason) {
        super(reason);
    }
}

class BuildingOnNonExistingVertexException extends PlaceVertexBuildingException {
    BuildingOnNonExistingVertexException(VertexCoordinate c) {
        super("No Vertex exists at "+c.toString());
    }
}