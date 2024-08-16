package xyz.game.merchants.domain.board.designable;

import xyz.game.merchants.domain.board.Board;

public interface DesignableBoard extends Board {
    void applySetTileCommand(SetTileCommand command) throws InvalidTileCoordinateException;
    void applyPlaceHarbourCommand(PlaceHarbourCommand command) throws PlaceHarbourException;
    void applyRemoveHarbourCommand(RemoveHarbourCommand command);
}
