package xyz.game.merchants.domain.board.interactable;

import java.util.Map;
import java.util.stream.Stream;

import xyz.game.merchants.domain.board.Edge;
import xyz.game.merchants.domain.board.GridBoard;
import xyz.game.merchants.domain.board.Vertex;
import xyz.game.merchants.domain.board.tiles.LandTile;
import xyz.game.merchants.domain.board.tiles.Tile;
import xyz.game.merchants.domain.board.tiles.TileCoordinate;
import xyz.game.merchants.domain.board.tiles.WaterTile;
import xyz.game.merchants.domain.building.Settlement;
import xyz.game.merchants.domain.building.Ship;
import xyz.game.merchants.domain.building.City;
import xyz.game.merchants.domain.building.Road;

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
    public void applyPlaceSettlementCommand(PlaceSettlementCommand command) throws PlaceVertexBuildingException {
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

    @Override
    public void applyPlaceCityCommand(PlaceCityCommand command) throws PlaceVertexBuildingException  {
        Vertex v = this.getVertex(command.destination);
        if(v == null) {
            throw new BuildingOnNonExistingVertexException(command.destination);
        }
        if(v.getBuilding() == null || v.getBuilding().getOwner() != command.actor) {
            throw new BuildingCityWithoutOwnedSettlementException(v, command.actor);
        }
        v.setBuilding(new City(command.actor));
    }

    @Override
    public void applyPlaceRoadCommand(PlaceRoadCommand command) throws PlaceEdgeBuildingException {
        Edge e = this.getEdge(command.destination);
        if(e == null) {
            throw new BuildingOnNonExistingEdgeException(command.destination);
        }
        if(e.getBuilding() != null) {
            throw new PlaceRoadOnOccupiedSpaceException();
        }
        boolean anyAdjacentTileIsLand = Stream.of(e.getCoordinate().getSurroundingTileCoordinates()).map(c -> this.getTile(c)).anyMatch(t -> t instanceof LandTile);
        if(!anyAdjacentTileIsLand) {
            throw new PlaceRoadWithoutLandException();
        }
        boolean adjacentRoad = Stream.of(command.destination.getSurroundingEdgeCoordinates())
            .anyMatch(c -> 
                this.getEdge(c) != null &&
                this.getEdge(c).getBuilding() instanceof Road &&
                this.getEdge(c).getBuilding().getOwner() == command.actor
            );
        boolean adjancentVertexBuilding = Stream.of(command.destination.getSurroundingVertexCoordinates())
            .anyMatch(c -> 
                this.getVertex(c) != null &&
                this.getVertex(c).getBuilding() != null &&
                this.getVertex(c).getBuilding().getOwner() == command.actor
            );
        if(!adjacentRoad && !adjancentVertexBuilding) {
            throw new PlaceRoadWithoutConnectionException();
        }
        e.setBuilding(new Road(command.actor));
    }

    @Override
    public void applyPlaceShipCommand(PlaceShipCommand command) throws PlaceEdgeBuildingException {
        Edge e = this.getEdge(command.destination);
        if(e == null) {
            throw new BuildingOnNonExistingEdgeException(command.destination);
        }
        if(e.getBuilding() != null) {
            throw new PlaceShipOnOccupiedSpaceException();
        }
        boolean anyAdjacentTileIsWater = Stream.of(e.getCoordinate().getSurroundingTileCoordinates()).map(c -> this.getTile(c)).anyMatch(t -> t instanceof WaterTile);
        if(!anyAdjacentTileIsWater) {
            throw new PlaceShipWithoutWaterException();
        }
        boolean adjacentShip = Stream.of(command.destination.getSurroundingEdgeCoordinates())
            .anyMatch(c -> 
                this.getEdge(c) != null &&
                this.getEdge(c).getBuilding() instanceof Ship &&
                this.getEdge(c).getBuilding().getOwner() == command.actor
            );
        boolean adjancentVertexBuilding = Stream.of(command.destination.getSurroundingVertexCoordinates())
            .anyMatch(c -> 
                this.getVertex(c) != null &&
                this.getVertex(c).getBuilding() != null &&
                this.getVertex(c).getBuilding().getOwner() == command.actor
            );
        if(!adjacentShip && !adjancentVertexBuilding) {
            throw new PlaceShipWithoutConnectionException();
        }
        e.setBuilding(new Ship(command.actor));
    }
}
