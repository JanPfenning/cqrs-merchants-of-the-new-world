package xyz.game.merchants.domain.board.interactable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.Test;

import xyz.game.merchants.domain.Player;
import xyz.game.merchants.domain.board.EdgeCoordinate;
import xyz.game.merchants.domain.board.tiles.DesertTile;
import xyz.game.merchants.domain.board.tiles.TileCoordinate;
import xyz.game.merchants.domain.building.Road;
import xyz.game.merchants.domain.building.Ship;

public class ApplyPlaceShipCommandTest {
    @Test
    void shouldThrowIfEdgeDoesNotExist() {
        InteractableBoard board = new InteractableGridBoard(2, 2, Map.ofEntries(Map.entry(new TileCoordinate(0, 0), new DesertTile(new TileCoordinate(0, 0)))));
        Player p = new Player("Dummy");

        PlaceShipCommand command = new PlaceShipCommand(p, new EdgeCoordinate(-1, 0));
        assertThrows(BuildingOnNonExistingEdgeException.class, () -> board.applyPlaceShipCommand(command));
    }

    @Test
    void shouldThrowIfShipIsBuildOnLand() {
        InteractableBoard board = new InteractableGridBoard(2, 2, Map.ofEntries(
            Map.entry(new TileCoordinate(0, 0), new DesertTile(new TileCoordinate(0, 0)))
        ));
        Player p = new Player("Dummy");

        PlaceShipCommand command = new PlaceShipCommand(p, new EdgeCoordinate(0, 1));
        assertThrows(PlaceShipWithoutWaterException.class, () -> board.applyPlaceShipCommand(command));
    }

    @Test
    void shouldThrowIfNoOtherOwnedAdjacentShipOrVertex() {
        InteractableBoard board = new InteractableGridBoard(2, 2);
        Player p = new Player("Dummy");

        board.getEdge(new EdgeCoordinate(1, 0)).setBuilding(new Road(p));

        PlaceShipCommand command = new PlaceShipCommand(p, new EdgeCoordinate(0, 1));
        assertThrows(PlaceShipWithoutConnectionException.class, () -> board.applyPlaceShipCommand(command));
    }

    @Test
    void shouldThrowIEdgeIsAlreadyOccupied() {
        InteractableBoard board = new InteractableGridBoard(2, 2);
        Player p = new Player("Dummy");

        EdgeCoordinate destination = new EdgeCoordinate(0, 1);
        board.getEdge(destination).setBuilding(new Ship(p));

        PlaceShipCommand command = new PlaceShipCommand(p, destination);
        assertThrows(PlaceShipOnOccupiedSpaceException.class, () -> board.applyPlaceShipCommand(command));
    }

    @Test
    void shouldBuildShipWithAdjacentShip() {
        InteractableBoard board = new InteractableGridBoard(2, 2);
        Player p = new Player("Dummy");

        board.getEdge(new EdgeCoordinate(1, 0)).setBuilding(new Ship(p));

        PlaceShipCommand command = new PlaceShipCommand(p, new EdgeCoordinate(0, 1));

        assertDoesNotThrow(() -> board.applyPlaceShipCommand(command));
        assertInstanceOf(Ship.class, board.getEdge(new EdgeCoordinate(1, 0)).getBuilding());
        assertEquals(p, board.getEdge(new EdgeCoordinate(1, 0)).getBuilding().getOwner());
    }
}
