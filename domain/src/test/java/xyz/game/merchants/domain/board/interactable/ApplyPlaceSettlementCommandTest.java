package xyz.game.merchants.domain.board.interactable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.Test;

import xyz.game.merchants.domain.Player;
import xyz.game.merchants.domain.board.VertexCoordinate;
import xyz.game.merchants.domain.board.tiles.DesertTile;
import xyz.game.merchants.domain.board.tiles.TileCoordinate;
import xyz.game.merchants.domain.building.Settlement;

public class ApplyPlaceSettlementCommandTest {
    @Test
    void shouldThrowIfVertexDoesNotExistOnTheBoard() {
        InteractableBoard b = new InteractableGridBoard(2, 2);
        assertThrows(BuildingOnNonExistingVertexException.class, () -> b.applyPlaceSettlementCommand(new PlaceSettlementCommand(null, new VertexCoordinate(5, 5))));
    }
    
    @Test
    void shouldThrowIfBuildingExistOnVertexAlready() {
        InteractableBoard b = new InteractableGridBoard(2, 2);
        b.getVertex(new VertexCoordinate(0, 0)).setBuilding(new Settlement(null));
        
        assertThrows(VertexHasBuildingAlreadyException.class, () -> b.applyPlaceSettlementCommand(new PlaceSettlementCommand(null, new VertexCoordinate(0, 0))));
    }

    @Test
    void shouldThrowIfBuildingExistsOnNearbyVertex() {
        InteractableBoard b = new InteractableGridBoard(2, 2);
        b.getVertex(new VertexCoordinate(1, 0)).setBuilding(new Settlement(null));
        
        assertThrows(VertexBuildingNearbyException.class, () -> b.applyPlaceSettlementCommand(new PlaceSettlementCommand(null, new VertexCoordinate(0, 0))));
    }

    @Test
    void shouldThrowIfBuildingWouldBePlacedInWater() {
        InteractableBoard b = new InteractableGridBoard(2, 2);
        
        assertThrows(VertexBuildingInTheOceanException.class, () -> b.applyPlaceSettlementCommand(new PlaceSettlementCommand(null, new VertexCoordinate(0, 0))));
    }

    @Test
    void shouldThrowIfNoOwnedConnectionExistWhileThereAreTwoVertexBuildingsOfThatPlayer() {
        TileCoordinate c = new TileCoordinate(0, 0);
        InteractableBoard b = new InteractableGridBoard(2, 2, Map.ofEntries(Map.entry(c, new DesertTile(c))));
        Player p = new Player("Dummy");
        b.getVertex(new VertexCoordinate(1, 1)).setBuilding(new Settlement(p));
        b.getVertex(new VertexCoordinate(0, 2)).setBuilding(new Settlement(p));

        assertThrows(SettlementWithoutConnectionException.class, () -> b.applyPlaceSettlementCommand(new PlaceSettlementCommand(p, new VertexCoordinate(0, 0))));
    }

    @Test
    void shouldNotThrowIfNoOwnedConnectionExistsDuringInitialPlacement() {
        TileCoordinate c = new TileCoordinate(0, 0);
        InteractableBoard b = new InteractableGridBoard(2, 2, Map.ofEntries(Map.entry(c, new DesertTile(c))));
        Player p = new Player("Dummy");

        assertDoesNotThrow(() -> b.applyPlaceSettlementCommand(new PlaceSettlementCommand(p, new VertexCoordinate(0, 0))));
    }

    @SuppressWarnings("null")
    @Test
    void shouldHavePlacedBuildingOnSite() throws PlaceVertexBuildingException {
        TileCoordinate c = new TileCoordinate(0, 0);
        InteractableBoard b = new InteractableGridBoard(2, 2, Map.ofEntries(Map.entry(c, new DesertTile(c))));
        Player p = new Player("Dummy");

        b.applyPlaceSettlementCommand(new PlaceSettlementCommand(p, new VertexCoordinate(0, 0)));

        assertInstanceOf(Settlement.class, b.getVertex(new VertexCoordinate(0, 0)).getBuilding());
        assertEquals(p, b.getVertex(new VertexCoordinate(0, 0)).getBuilding().getOwner());
    }
}
