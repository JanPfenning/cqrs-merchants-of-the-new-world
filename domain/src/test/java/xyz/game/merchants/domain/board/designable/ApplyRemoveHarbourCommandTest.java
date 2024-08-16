package xyz.game.merchants.domain.board.designable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Map;

import org.junit.jupiter.api.Test;

import xyz.game.merchants.domain.board.EdgeCoordinate;
import xyz.game.merchants.domain.board.harbours.AnyResourceHarbour;
import xyz.game.merchants.domain.board.tiles.DesertTile;
import xyz.game.merchants.domain.board.tiles.TileCoordinate;

public class ApplyRemoveHarbourCommandTest {
    @Test
    void shouldHaveDeletedExistingHarbour() {
        DesignableBoard board = new DesignableGridBoard(2, 2, Map.ofEntries(
            Map.entry(new TileCoordinate(0, 0), new DesertTile(new TileCoordinate(0, 0)))
        ));
        EdgeCoordinate location = new EdgeCoordinate(2, 3);
        board.getHarbours().put(location, new AnyResourceHarbour(location));
        
        assertNotNull(board.getHarbours().get(location));
        assertDoesNotThrow(() -> board.applyRemoveHarbourCommand(new RemoveHarbourCommand(location)));
        assertNull(board.getHarbours().get(location));
    }

    @Test
    void shouldReturnAfterDeletingAlreadyNonExistingHarbour() {
        DesignableBoard board = new DesignableGridBoard(2, 2, Map.ofEntries(
            Map.entry(new TileCoordinate(0, 0), new DesertTile(new TileCoordinate(0, 0)))
        ));
        EdgeCoordinate location = new EdgeCoordinate(2, 3);
        assertNull(board.getHarbours().get(location));
        assertDoesNotThrow(() -> board.applyRemoveHarbourCommand(new RemoveHarbourCommand(location)));
        assertNull(board.getHarbours().get(location));
    }
}
