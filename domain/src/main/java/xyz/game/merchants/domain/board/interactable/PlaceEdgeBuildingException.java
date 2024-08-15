package xyz.game.merchants.domain.board.interactable;

import xyz.game.merchants.domain.board.EdgeCoordinate;

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