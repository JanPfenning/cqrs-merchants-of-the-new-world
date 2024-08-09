package xyz.game.board;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public class GridBoard implements Board {
    @Getter
    private int width;
    @Getter
    private int height;
    @Getter
    private final Map<TileCoordinate, Tile> tiles = new HashMap<>();
    @Getter
    private final Map<EdgeCoordinate, Edge> edges = new HashMap<>();
    @Getter
    private final Map<VertexCoordinate, Vertex> vertices = new HashMap<>();

    public GridBoard(int width, int height) {
        this.width = width;
        this.height = height;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                TileCoordinate c = new TileCoordinate(i, j);
                this.tiles.put(c, new WaterTile(c));
            }
        }

        for(int i = 0; i < (4 * (width + 1) + 2); i++){
            for(int j = 0; j < (2 * (height + 1)); j++){
                EdgeCoordinate c = new EdgeCoordinate(i, j);
                this.edges.put(c, new Edge(c));
            }
        }

        for(int i = 0; i < (2 * width + 1); i++){
            for(int j = 0; j < (2 * height + 1); j++){
                VertexCoordinate c = new VertexCoordinate(i,j);
                this.vertices.put(c, new Vertex(c));
            }
        }
    }

    @Override
    public Tile getTile(TileCoordinate coordinate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTile'");
    }

    @Override
    public Edge getEdge(EdgeCoordinate coordinate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEdge'");
    }

    @Override
    public Vertex getVertex(VertexCoordinate coordinate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVertex'");
    }

    @Override
    public Tile[] getAdjacentTiles(TileCoordinate coordinate) {
        return coordinate.getSurroundingTileCoordinates().values().stream().map(tc ->  this.getTile(tc)).toArray(Tile[]::new);
    }

    @Override
    public Tile[] getAdjacentTiles(EdgeCoordinate coordinate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAdjacentTiles'");
    }

    @Override
    public Tile[] getAdjacentTiles(VertexCoordinate coordinate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAdjacentTiles'");
    }

    @Override
    public Edge[] getAdjacentEdges(TileCoordinate coordinate) {
        return Arrays.stream(coordinate.getSurroundingEdgeCoordindates()).map(c -> this.getEdge(c)).toArray(Edge[]::new);
    }

    @Override
    public Edge[] getAdjacentEdges(EdgeCoordinate coordinate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAdjacentEdges'");
    }

    @Override
    public Edge[] getAdjacentEdges(VertexCoordinate coordinate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAdjacentEdges'");
    }

    @Override
    public Vertex[] getAdjacentVertices(TileCoordinate coordinate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAdjacentVertices'");
    }

    @Override
    public Vertex[] getAdjacentVertices(EdgeCoordinate coordinate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAdjacentVertices'");
    }

    @Override
    public Vertex[] getAdjacentVertices(VertexCoordinate coordinate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAdjacentVertices'");
    }
}