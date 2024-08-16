package xyz.game.merchants.domain.board.designable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.Test;

import xyz.game.merchants.domain.board.EdgeCoordinate;
import xyz.game.merchants.domain.board.Resource;
import xyz.game.merchants.domain.board.harbours.AnyResourceHarbour;
import xyz.game.merchants.domain.board.harbours.Harbour;
import xyz.game.merchants.domain.board.harbours.SpecificResourceHarbour;
import xyz.game.merchants.domain.board.tiles.DesertTile;
import xyz.game.merchants.domain.board.tiles.TileCoordinate;

public class ApplyPlaceHarbourCommandTest {
    @Test
    void shouldThrowIfEdgeDoesNotExist() {
        DesignableBoard board = new DesignableGridBoard(2, 2);
        EdgeCoordinate location = new EdgeCoordinate(8, 9);
        PlaceHarbourCommand command = new PlaceHarbourCommand(location, new AnyResourceHarbour(location));
        assertThrows(HarbourOnNonExistingEdgeException.class, () -> board.applyPlaceHarbourCommand(command));
    }

    @Test
    void shouldThrowIfEdgeIsBetweenWater() {
        DesignableBoard board = new DesignableGridBoard(2, 2);
        EdgeCoordinate location = new EdgeCoordinate(2, 3);
        PlaceHarbourCommand command = new PlaceHarbourCommand(location, new AnyResourceHarbour(location));
        assertThrows(HarbourWithoutCoastException.class, () -> board.applyPlaceHarbourCommand(command));
    }

    @Test
    void shouldThrowIfEdgeIsBetweenLand() {
        DesignableBoard board = new DesignableGridBoard(2, 2, Map.ofEntries(
            Map.entry(new TileCoordinate(0, 0), new DesertTile(new TileCoordinate(0, 0))),
            Map.entry(new TileCoordinate(1, 0), new DesertTile(new TileCoordinate(1, 0)))
        ));
        EdgeCoordinate location = new EdgeCoordinate(2, 3);
        PlaceHarbourCommand command = new PlaceHarbourCommand(location, new AnyResourceHarbour(location));
        assertThrows(HarbourWithoutCoastException.class, () -> board.applyPlaceHarbourCommand(command));
    }

    @Test
    void shouldHaveCreatedTheHarbourAndRemovedAdjacentHarbours() {
        DesignableBoard board = new DesignableGridBoard(2, 2, Map.ofEntries(Map.entry(new TileCoordinate(0, 0), new DesertTile(new TileCoordinate(0, 0)))));
        EdgeCoordinate locationA = new EdgeCoordinate(2, 3);

        PlaceHarbourCommand commandA = new PlaceHarbourCommand(locationA, new SpecificResourceHarbour(locationA, Resource.Brick));

        assertDoesNotThrow(() -> board.applyPlaceHarbourCommand(commandA));
        SpecificResourceHarbour actualCreatedHarbour = (SpecificResourceHarbour) board.getHarbours().get(locationA); 
        assertEquals(Resource.Brick, actualCreatedHarbour.getResoruce());
        
        EdgeCoordinate locationB = new EdgeCoordinate(1, 4);
        PlaceHarbourCommand commandB = new PlaceHarbourCommand(locationB, new SpecificResourceHarbour(locationB, Resource.Lumber));
        
        assertDoesNotThrow(() -> board.applyPlaceHarbourCommand(commandB));
        Harbour removedHarbour = board.getHarbours().get(locationA);
        assertNull(removedHarbour);
        SpecificResourceHarbour actualCreatedHarbourB = (SpecificResourceHarbour) board.getHarbours().get(locationB); 
        assertEquals(Resource.Lumber, actualCreatedHarbourB.getResoruce());
    }
}
