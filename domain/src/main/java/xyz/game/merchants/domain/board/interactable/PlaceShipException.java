package xyz.game.merchants.domain.board.interactable;

public abstract class PlaceShipException extends PlaceEdgeBuildingException {
    PlaceShipException(String reason) {
        super(reason);
    }
}

class PlaceShipWithoutWaterException extends PlaceShipException {
    PlaceShipWithoutWaterException() {
        super("No adjacent water tile found");
    }
}
class PlaceShipWithoutConnectionException extends PlaceShipException {
    PlaceShipWithoutConnectionException() {
        super("Edge has neither an adjacent Ship nor an adjacent Settlement or City");
    }
}

class PlaceShipOnOccupiedSpaceException extends PlaceShipException {
    PlaceShipOnOccupiedSpaceException() {
        super("Edge is already occupied");
    }
}