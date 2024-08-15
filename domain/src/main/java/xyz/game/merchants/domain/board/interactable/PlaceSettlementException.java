package xyz.game.merchants.domain.board.interactable;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import xyz.game.merchants.domain.Player;
import xyz.game.merchants.domain.board.Edge;
import xyz.game.merchants.domain.board.Tile;
import xyz.game.merchants.domain.board.Vertex;

public abstract class PlaceSettlementException extends PlaceVertexBuildingException {
    PlaceSettlementException(String reason) {
        super(reason);
    }
}

class VertexHasBuildingAlreadyException extends PlaceSettlementException {
    @SuppressWarnings("null")
    VertexHasBuildingAlreadyException(Vertex v) {
        super("Vertex "+v.toString()+" has a Building "+v.getBuilding().toString()+" already");
    }
}

class VertexBuildingNearbyException extends PlaceSettlementException {
    VertexBuildingNearbyException(Vertex v, Vertex[] neighbours) {
        super(buildReason(v, neighbours));
    }

    private static String buildReason(Vertex v, Vertex[] neighbours) {
        if(neighbours.length == 1){
            return buildReason(v, neighbours[0]);
        }
        return Stream.of(neighbours).map(n -> buildReason(v, n)).collect(Collectors.joining(", "));
    }

    @SuppressWarnings("null")
    private static String buildReason(Vertex v, Vertex neighbour) {
        return "Vertex "+neighbour.toString()+" has a Building "+neighbour.getBuilding().toString()+" which is too close to the building site "+v.toString();
    }
}

class VertexBuildingInTheOceanException extends PlaceSettlementException {
    VertexBuildingInTheOceanException(Vertex v, Tile[] adjTiles) {
        super("Vertex "+v.toString()+" is in the ocean - surrounded by the tiles "+Stream.of(adjTiles).filter(x -> x!=null).map(n -> n.toString()).collect(Collectors.joining(", ")));
    }
}

class SettlementWithoutConnectionException extends PlaceSettlementException {
    SettlementWithoutConnectionException(Vertex v, Edge[] adjEdges, Player p) {
        super("Vertex "+v.toString()+" has no connection to any adjacent edge owned by "+p.name+" - the edges are "+Stream.of(adjEdges).filter(x -> x!=null).map(n -> n.toString()).collect(Collectors.joining(", ")));
    }
}
