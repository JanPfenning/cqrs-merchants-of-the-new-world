package xyz.game.merchants.domain.board.designable;

import xyz.game.merchants.domain.board.GridBoard;
import xyz.game.merchants.domain.board.Tile;

public class DesignableGridBoard extends GridBoard implements DesignableBoard {

    public DesignableGridBoard(int width, int height) {
        super(width, height);
    }

    @Override
    public void applySetTileCommand(SetTileCommand command) throws InvalidTileCoordinateException {
        Tile t = this.getTile(command.tile.getCoordinate());
        if(t == null) {
            throw new InvalidTileCoordinateException();
        }
        // TODO adapt harbours if required
        this.tiles.put(command.tile.getCoordinate(), command.tile);
    }
}
