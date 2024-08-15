package xyz.game.merchants.domain.board.interactable;

public abstract class PlaceRoadException extends PlaceEdgeBuildingException {
    PlaceRoadException(String reason) {
        super(reason);
    }
}

class PlaceRoadWithoutLandException extends PlaceRoadException {
    PlaceRoadWithoutLandException() {
        super("All adjacent tiles are water");
    }
}
class PlaceRoadWithoutConnectionException extends PlaceRoadException {
    PlaceRoadWithoutConnectionException() {
        super("Edge has neither an adjacent Road nor an adjacent Settlement or City");
    }
}

class PlaceRoadOnOccupiedSpaceException extends PlaceRoadException {
    PlaceRoadOnOccupiedSpaceException() {
        super("Edge is already occupied");
    }
}