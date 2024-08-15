package xyz.game.merchants.domain.board.designable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import xyz.game.merchants.domain.board.DesertTile;
import xyz.game.merchants.domain.board.Tile;
import xyz.game.merchants.domain.board.TileCoordinate;

public class ApplySetTileCommandTest {
    @Test
    void shouldThrowIfVertexIsOutOfBounds() {
        DesignableBoard board = new DesignableGridBoard(2, 2);
        SetTileCommand command = new SetTileCommand(new DesertTile(new TileCoordinate(5, 5)));
        assertThrows(InvalidTileCoordinateException.class, () -> board.applySetTileCommand(command));
    }

    @Test
    void shouldHaveChangedTheTile() {
        DesignableBoard board = new DesignableGridBoard(2, 2);
        SetTileCommand command = new SetTileCommand(new DesertTile(new TileCoordinate(0, 0)));
        
        Tile oldTile = board.getTile(command.tile.getCoordinate());
        assertDoesNotThrow(() -> board.applySetTileCommand(command));
        Tile newTile = board.getTile(command.tile.getCoordinate());
        assertNotEquals(oldTile, newTile);
        assertInstanceOf(DesertTile.class, newTile);
    }
}
