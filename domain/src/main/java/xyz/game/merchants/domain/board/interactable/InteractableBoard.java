package xyz.game.merchants.domain.board.interactable;

import xyz.game.merchants.domain.board.Board;

public interface InteractableBoard extends Board {
    void applyPlaceSettlementCommand(PlaceSettlementCommand command) throws PlaceVertexBuildingException;
    void applyPlaceCityCommand(PlaceCityCommand command) throws PlaceVertexBuildingException;
    void applyPlaceRoadCommand(PlaceRoadCommand command) throws PlaceEdgeBuildingException;
}
