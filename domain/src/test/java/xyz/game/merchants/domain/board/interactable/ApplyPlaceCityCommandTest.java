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
import xyz.game.merchants.domain.building.City;
import xyz.game.merchants.domain.building.Settlement;
import xyz.game.merchants.domain.building.VertexBuilding;

public class ApplyPlaceCityCommandTest {
    @Test
    void shouldThrowIfVertexDoesNotExistOnTheBoard() {
        InteractableBoard board = new InteractableGridBoard(2, 2);
        PlaceCityCommand command = new PlaceCityCommand(null, new VertexCoordinate(15, 5));
        assertThrows(BuildingOnNonExistingVertexException.class, () -> board.applyPlaceCityCommand(command));
    }

    @Test
    void shouldThrowIfVertexDoesNotHaveAnySettlement() {
        InteractableBoard board = new InteractableGridBoard(2, 2, Map.ofEntries(Map.entry(new TileCoordinate(0, 0), new DesertTile(new TileCoordinate(0, 0)))));
        Player p1 = new Player("Dummy");
                
        PlaceCityCommand command = new PlaceCityCommand(p1, new VertexCoordinate(0, 0));

        assertThrows(BuildingCityWithoutOwnedSettlementException.class, () -> board.applyPlaceCityCommand(command));
    }

    @Test
    void shouldThrowIfVertexDoesNotHaveSettlementOfTheSamePlayer() {
        InteractableBoard board = new InteractableGridBoard(2, 2, Map.ofEntries(Map.entry(new TileCoordinate(0, 0), new DesertTile(new TileCoordinate(0, 0)))));
        Player p1 = new Player("Dummy");
        Player p2 = new Player("Opponent");
        
        board.getVertex(new VertexCoordinate(0, 0)).setBuilding(new Settlement(p2));;
        
        PlaceCityCommand command = new PlaceCityCommand(p1, new VertexCoordinate(0, 0));

        assertThrows(BuildingCityWithoutOwnedSettlementException.class, () -> board.applyPlaceCityCommand(command));
    }    

    @Test
    void shouldReplaceSettlementWithVertex() {
        InteractableBoard board = new InteractableGridBoard(2, 2, Map.ofEntries(Map.entry(new TileCoordinate(0, 0), new DesertTile(new TileCoordinate(0, 0)))));
        Player p1 = new Player("Dummy");
        
        board.getVertex(new VertexCoordinate(0, 0)).setBuilding(new Settlement(p1));;
        
        PlaceCityCommand command = new PlaceCityCommand(p1, new VertexCoordinate(0, 0));

        assertDoesNotThrow(() -> board.applyPlaceCityCommand(command));
        VertexBuilding building = board.getVertex(new VertexCoordinate(0, 0)).getBuilding();
        assertInstanceOf(City.class, building);
        assertEquals(p1, building.getOwner());
    }
}
