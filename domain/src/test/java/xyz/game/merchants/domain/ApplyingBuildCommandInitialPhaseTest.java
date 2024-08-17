package xyz.game.merchants.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import xyz.game.merchants.domain.InitialPlacementException.ExpectedRoadOrShipException;
import xyz.game.merchants.domain.InitialPlacementException.ExpectedSettlementException;
import xyz.game.merchants.domain.InitialPlacementException.LeftSettlementWithoutConnectionException;
import xyz.game.merchants.domain.board.EdgeCoordinate;
import xyz.game.merchants.domain.board.VertexCoordinate;
import xyz.game.merchants.domain.board.interactable.InteractableBoard;
import xyz.game.merchants.domain.board.interactable.InteractableGridBoard;
import xyz.game.merchants.domain.board.interactable.PlaceEdgeBuildingException;
import xyz.game.merchants.domain.board.interactable.PlaceVertexBuildingException;
import xyz.game.merchants.domain.board.tiles.DesertTile;
import xyz.game.merchants.domain.board.tiles.TileCoordinate;
import xyz.game.merchants.domain.building.City;
import xyz.game.merchants.domain.building.Road;
import xyz.game.merchants.domain.building.Settlement;
import xyz.game.merchants.domain.resources.Resources;

public class ApplyingBuildCommandInitialPhaseTest {

    Game game;
    
    @BeforeEach
    void initializeBoard() {
        Player[] players = new Player[]{
            new Player("jan"),
            new Player("lena")
        };
        InteractableBoard board = new InteractableGridBoard(0, 0, Map.ofEntries(
            Map.entry(new TileCoordinate(0, 0), new DesertTile(new TileCoordinate(0, 0)))
        ));
        this.game = new Game(players, board);
    }

    @Test
    void shouldThrowIfBuildOfSettlementExpectedButRoadOrShipFound() {
        Player actor = this.game.players[0];
        BuildOnEdgeCommand command = new BuildOnEdgeCommand(actor, new Road(actor), new EdgeCoordinate(2, 3));
        assertThrows(ExpectedSettlementException.class, () -> this.game.applyCommand(command));
    }

    @Test
    void shouldThrowIfRoadOrShipExpectedButSettlementFound() {
        Player actor = this.game.players[0];
        BuildOnVertexCommand command = new BuildOnVertexCommand(actor, new Settlement(actor), new VertexCoordinate(0, 0));
        assertDoesNotThrow(() -> this.game.applyCommand(command));
        assertThrows(ExpectedRoadOrShipException.class, () -> this.game.applyCommand(command));
    }

    @Test
    void shouldThrowIfBuildingCity() {
        Player actor = this.game.players[0];
        BuildOnVertexCommand command = new BuildOnVertexCommand(actor, new City(actor), new VertexCoordinate(0, 0));
        assertThrows(ExpectedSettlementException.class, () -> this.game.applyCommand(command));
    }

    @Test
    void shouldNotHavePaidSettlement() {
        Player actor = this.game.players[0];
        BuildOnVertexCommand command = new BuildOnVertexCommand(actor, new Settlement(actor), new VertexCoordinate(0, 0));
        Resources preResources = actor.resources;
        assertDoesNotThrow(() -> this.game.applyCommand(command));
        assertEquals(preResources, actor.resources);
    }

    @Test
    void shouldHaveReducedPlayersSettlementPiecesAmount() {
        Player actor = this.game.players[0];
        BuildOnVertexCommand command = new BuildOnVertexCommand(actor, new Settlement(actor), new VertexCoordinate(0, 0));
        int preSettlements = actor.pieces.settlements;
        assertDoesNotThrow(() -> this.game.applyCommand(command));
        assertEquals(preSettlements-1, actor.pieces.settlements);
    }

    @Test
    void shouldHavePlacedSettlement() {
        Player actor = this.game.players[0];
        VertexCoordinate settlementPlacementLocation = new VertexCoordinate(0, 0);
        BuildOnVertexCommand command = new BuildOnVertexCommand(actor, new Settlement(actor), settlementPlacementLocation);
        assertNull(this.game.getBoard().getVertex(settlementPlacementLocation).getBuilding());
        assertDoesNotThrow(() -> this.game.applyCommand(command));
        assertInstanceOf(Settlement.class, this.game.getBoard().getVertex(settlementPlacementLocation).getBuilding());
    }

    @Test
    void shouldNotHavePaidRoad() throws InitialPlacementException, UnexpectedActorException, PlaceVertexBuildingException, PlaceEdgeBuildingException, InvalidGameStateException {
        Player actor = this.game.players[0];
        VertexCoordinate settlementPlacementLocation = new VertexCoordinate(0, 0);
        BuildOnVertexCommand buildInitialSettlementCommand = new BuildOnVertexCommand(actor, new Settlement(actor), settlementPlacementLocation);
        this.game.applyCommand(buildInitialSettlementCommand);
        Resources preResources = actor.resources;

        EdgeCoordinate roadPlacementLocation = new EdgeCoordinate(0, 1);
        BuildOnEdgeCommand buildInitialRoadCommand = new BuildOnEdgeCommand(actor, new Road(actor), roadPlacementLocation);
        assertDoesNotThrow(() -> this.game.applyCommand(buildInitialRoadCommand));

        assertEquals(preResources, actor.resources);
    }

    @Test
    void shouldHaveReducedPlayersRoadPiecesAmount() throws InitialPlacementException, UnexpectedActorException, PlaceVertexBuildingException, PlaceEdgeBuildingException, InvalidGameStateException {
        Player actor = this.game.players[0];
        VertexCoordinate settlementPlacementLocation = new VertexCoordinate(0, 0);
        BuildOnVertexCommand buildInitialSettlementCommand = new BuildOnVertexCommand(actor, new Settlement(actor), settlementPlacementLocation);
        this.game.applyCommand(buildInitialSettlementCommand);
        int preRoads = actor.pieces.roads;

        EdgeCoordinate roadPlacementLocation = new EdgeCoordinate(0, 1);
        BuildOnEdgeCommand buildInitialRoadCommand = new BuildOnEdgeCommand(actor, new Road(actor), roadPlacementLocation);
        assertDoesNotThrow(() -> this.game.applyCommand(buildInitialRoadCommand));

        assertEquals(preRoads-1, actor.pieces.roads);
    }

    @Test
    void shouldHavePlacedRoad() throws InitialPlacementException, UnexpectedActorException, PlaceVertexBuildingException, PlaceEdgeBuildingException, InvalidGameStateException {
        Player actor = this.game.players[0];
        VertexCoordinate settlementPlacementLocation = new VertexCoordinate(0, 0);
        BuildOnVertexCommand buildInitialSettlementCommand = new BuildOnVertexCommand(actor, new Settlement(actor), settlementPlacementLocation);
        this.game.applyCommand(buildInitialSettlementCommand);

        EdgeCoordinate roadPlacementLocation = new EdgeCoordinate(0, 1);
        BuildOnEdgeCommand buildInitialRoadCommand = new BuildOnEdgeCommand(actor, new Road(actor), roadPlacementLocation);
        assertNull(this.game.getBoard().getEdge(roadPlacementLocation).getBuilding());
        assertDoesNotThrow(() -> this.game.applyCommand(buildInitialRoadCommand));
        assertInstanceOf(Road.class, this.game.getBoard().getEdge(roadPlacementLocation).getBuilding());
    }

    @Test
    void shouldThrowIfSecondRoadIsPlacedLeavingSecondSettlementWithoutConnection() throws InitialPlacementException, UnexpectedActorException, PlaceVertexBuildingException, PlaceEdgeBuildingException, InvalidGameStateException {
        Player p1 = this.game.players[0];
        
        VertexCoordinate settlementPlacementLocation = new VertexCoordinate(0, 0);
        BuildOnVertexCommand buildInitialSettlementCommand = new BuildOnVertexCommand(p1, new Settlement(p1), settlementPlacementLocation);
        this.game.applyCommand(buildInitialSettlementCommand);

        EdgeCoordinate roadPlacementLocation = new EdgeCoordinate(0, 1);
        BuildOnEdgeCommand buildInitialRoadCommand = new BuildOnEdgeCommand(p1, new Road(p1), roadPlacementLocation);
        this.game.applyCommand(buildInitialRoadCommand);

        this.game.turn++;
        Player p2 = this.game.players[1];

        // TODO !!!!!!!!!!!!!!!
        record InitialPlacement(Player actor, VertexCoordinate settlementLocation, EdgeCoordinate edgeLocation, boolean road){};
        Consumer<InitialPlacement> makeInitialPlacementTurn = (args) -> {
            VertexCoordinate secondSettlementPlacementLocation = new VertexCoordinate(1, 1);
            BuildOnVertexCommand buildSecondInitialSettlementCommand = new BuildOnVertexCommand(p2, new Settlement(p2), secondSettlementPlacementLocation);
            this.game.applyCommand(buildSecondInitialSettlementCommand);

        };
        VertexCoordinate secondSettlementPlacementLocation = new VertexCoordinate(1, 1);
        BuildOnVertexCommand buildSecondInitialSettlementCommand = new BuildOnVertexCommand(p2, new Settlement(p2), secondSettlementPlacementLocation);
        this.game.applyCommand(buildSecondInitialSettlementCommand);

        EdgeCoordinate secondRoadPlacementLocation = new EdgeCoordinate(0, 1);
        BuildOnEdgeCommand buildSecondInitialRoadCommand = new BuildOnEdgeCommand(p2, new Road(p2), secondRoadPlacementLocation);
        this.game.applyCommand(buildSecondInitialRoadCommand);

        this.game.turn++;


        assertThrows(LeftSettlementWithoutConnectionException.class, () -> this.game.applyCommand(buildSecondInitialRoadCommand));
    }
}
