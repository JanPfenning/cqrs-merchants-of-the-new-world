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
                TileCoordinate c = new TileCoordinate(j, i);
                this.tiles.put(c, new WaterTile(c));
            }
        }

        for(int i = 0; i < (4 * (height + 1) + 2); i++){
            for(int j = 0; j < (2 * (width + 1)); j++){
                EdgeCoordinate c = new EdgeCoordinate(j, i);
                this.edges.put(c, new Edge(c));
            }
        }

        for(int i = 0; i < (2 * height + 2); i++){
            for(int j = 0; j < (2 * width + 1); j++){
                VertexCoordinate c = new VertexCoordinate(j, i);
                this.vertices.put(c, new Vertex(c));
            }
        }
    }

    @Override
    public Tile getTile(TileCoordinate coordinate) {
        return this.tiles.get(coordinate);
    }

    @Override
    public Edge getEdge(EdgeCoordinate coordinate) {
        return this.edges.get(coordinate);
    }

    @Override
    public Vertex getVertex(VertexCoordinate coordinate) {
        return this.vertices.get(coordinate);
    }

    @Override
    public Tile[] getAdjacentTiles(TileCoordinate coordinate) {
        return Arrays.stream(coordinate.getSurroundingTileCoordinates()).map(tc ->  this.getTile(tc)).toArray(Tile[]::new);
    }

    @Override
    public Tile[] getAdjacentTiles(EdgeCoordinate coordinate) {
        return Arrays.stream(coordinate.getSurroundingTileCoordinates()).map(c -> this.getTile(c)).toArray(Tile[]::new);
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
        return Arrays.stream(coordinate.getSurroundingVertexCoordindates()).map(c -> this.getVertex(c)).toArray(Vertex[]::new);    
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