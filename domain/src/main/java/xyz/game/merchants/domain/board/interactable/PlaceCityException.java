package xyz.game.merchants.domain.board.interactable;

import xyz.game.merchants.domain.Player;
import xyz.game.merchants.domain.board.Vertex;

public abstract class PlaceCityException extends PlaceVertexBuildingException {
    PlaceCityException(String reason) {
        super(reason);
    }
}

class BuildingCityWithoutOwnedSettlementException extends PlaceCityException {
    BuildingCityWithoutOwnedSettlementException(Vertex v, Player p) {
        super("Vertex "+v.getCoordinate().toString()+" does not have a seetlement of player "+p.toString());
    }
}