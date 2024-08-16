package xyz.game.merchants.domain.board.designable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.Test;

import xyz.game.merchants.domain.board.EdgeCoordinate;
import xyz.game.merchants.domain.board.harbours.AnyResourceHarbour;
import xyz.game.merchants.domain.board.tiles.DesertTile;
import xyz.game.merchants.domain.board.tiles.GoldTile;
import xyz.game.merchants.domain.board.tiles.Tile;
import xyz.game.merchants.domain.board.tiles.TileCoordinate;
import xyz.game.merchants.domain.board.tiles.TileNumber;
import xyz.game.merchants.domain.board.tiles.TileNumber.InvalidTileNumberException;
import xyz.game.merchants.domain.board.tiles.WaterTile;

public class ApplySetTileCommandTest {
    @Test
    void shouldThrowIfVertexIsOutOfBounds() {
        DesignableBoard board = new DesignableGridBoard(2, 2);
        SetTileCommand command = new SetTileCommand(new DesertTile(new TileCoordinate(5, 5)));
        assertThrows(InvalidTileCoordinateException.class, () -> board.applySetTileCommand(command));
    }

    @Test
    void shouldHaveRemovedTheAdjacentHarbourIfEdgeIsAdjToTwoLands() {
        DesignableBoard board = new DesignableGridBoard(2, 2, Map.ofEntries(
            Map.entry(new TileCoordinate(1, 0), new DesertTile(new TileCoordinate(1, 0)))
        ));
        SetTileCommand command = new SetTileCommand(new DesertTile(new TileCoordinate(0, 0)));
        EdgeCoordinate location = new EdgeCoordinate(2, 3);
        board.getHarbours().put(location, new AnyResourceHarbour(location));
        
        assertNotNull(board.getHarbours().get(location));
        assertDoesNotThrow(() -> board.applySetTileCommand(command));
        assertNull(board.getHarbours().get(location));
    }

    @Test
    void shouldHaveRemovedTheAdjacentHarbourIfEdgeIsAdjToTwoWaters() {
        DesignableBoard board = new DesignableGridBoard(2, 2, Map.ofEntries(
            Map.entry(new TileCoordinate(0, 0), new DesertTile(new TileCoordinate(0, 0)))
        ));
        SetTileCommand command = new SetTileCommand(new WaterTile(new TileCoordinate(0, 0)));
        EdgeCoordinate location = new EdgeCoordinate(2, 3);
        board.getHarbours().put(location, new AnyResourceHarbour(location));
        
        assertNotNull(board.getHarbours().get(location));
        assertDoesNotThrow(() -> board.applySetTileCommand(command));
        assertNull(board.getHarbours().get(location));
    }

    @Test
    void shouldNotHaveRemovedTheAdjacentHarbourIfEdgeIsStillCoast() throws InvalidTileNumberException {
        DesignableBoard board = new DesignableGridBoard(2, 2, Map.ofEntries(
            Map.entry(new TileCoordinate(0, 0), new DesertTile(new TileCoordinate(0, 0)))
        ));
        SetTileCommand command = new SetTileCommand(new GoldTile(new TileCoordinate(0, 0), new TileNumber(5)));
        EdgeCoordinate location = new EdgeCoordinate(2, 3);
        board.getHarbours().put(location, new AnyResourceHarbour(location));
        
        assertNotNull(board.getHarbours().get(location));
        assertDoesNotThrow(() -> board.applySetTileCommand(command));
        assertNotNull(board.getHarbours().get(location));
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
