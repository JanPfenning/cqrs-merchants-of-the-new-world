package xyz.game.merchants.domain.board.interactable;

import java.util.Map;
import java.util.stream.Stream;

import xyz.game.merchants.domain.board.Edge;
import xyz.game.merchants.domain.board.GridBoard;
import xyz.game.merchants.domain.board.LandTile;
import xyz.game.merchants.domain.board.Tile;
import xyz.game.merchants.domain.board.TileCoordinate;
import xyz.game.merchants.domain.board.Vertex;
import xyz.game.merchants.domain.building.Settlement;

public class InteractableGridBoard extends GridBoard implements InteractableBoard {

    public InteractableGridBoard(int width, int height, Map<TileCoordinate, Tile> tiles) {
        this(width, height);

        for (Map.Entry<TileCoordinate, Tile> entry : tiles.entrySet()) {
                    if(tiles.get(entry.getKey()) == null) throw new RuntimeException("tile coordinate "+entry.getKey().toString()+" is out of bounds");
            this.tiles.put(entry.getKey(), entry.getValue());
        }
    }

    InteractableGridBoard(int width, int height) {
        super(width, height);
    }

    @Override 
    // TODO it must be ensured somewhere else, that the player thats placing something is currently taking their turn
    // TODO it must be ensured somewhere else, that the respective costs are paid by the actor and that they can afford it
    public void applyPlaceSettlementCommand(PlaceSettlementCommand command) throws PlaceSettlementException {
        Vertex v = this.getVertex(command.destination);
        if(v == null) {
            throw new BuildingOnNonExistingVertexException(command.destination);
        }
        boolean vertexHasBuildingAlready = v.getBuilding() != null;
        if(vertexHasBuildingAlready) {
            throw new VertexHasBuildingAlreadyException(v);
        }
        Vertex[] nearbyVerticesWithBuilding = Stream.of(v.getCoordinate().getSurroundingVertexCoordinates()).map(c -> getVertex(c)).filter(neighbour -> neighbour != null && neighbour.getBuilding() != null).toArray(Vertex[]::new);
        if(nearbyVerticesWithBuilding.length > 0) {
            throw new VertexBuildingNearbyException(v, nearbyVerticesWithBuilding);
        }
        Tile[] adjacentTiles = Stream.of(v.getCoordinate().getSurroundingTileCoordinates()).map(c -> getTile(c)).toArray(Tile[]::new);
        boolean anyAdjacentTileIsLandTile = Stream.of(adjacentTiles).anyMatch(t -> t instanceof LandTile);
        if(!anyAdjacentTileIsLandTile) {
            throw new VertexBuildingInTheOceanException(v, adjacentTiles);
        }
        @SuppressWarnings("null")
        int amountOfVerticesWherePlayerHasBuild = (int) this.getVertices().values().stream()
            .filter(x -> x.getBuilding() != null)
            .filter(x -> x.getBuilding().getOwner() == command.actor)
            .count();
        boolean initialPlacementPhase = amountOfVerticesWherePlayerHasBuild < 2;
        if(!initialPlacementPhase) {
            Edge[] adjacentEdges = Stream.of(v.getCoordinate().getSurroundingEdgeCoordinates()).map(c -> getEdge(c)).toArray(Edge[]::new);
            @SuppressWarnings("null")
            boolean edgeOfSamePlayerAdjacent = Stream.of(adjacentEdges).anyMatch(e -> e != null && e.getBuilding() != null && e.getBuilding().getOwner() == command.actor);
            if(!edgeOfSamePlayerAdjacent) {
                throw new SettlementWithoutConnectionException(v, adjacentEdges, command.actor);
            }
        }
        v.setBuilding(new Settlement(command.actor));
    }
}
