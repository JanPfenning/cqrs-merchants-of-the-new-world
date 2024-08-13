package xyz.game.merchants.domain.board;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import lombok.Getter;

public class GridBoard implements Board {
    @Getter
    private int tileWidth;
    @Getter
    private int tileHeight;
    @Getter
    private int edgeWidth;
    @Getter
    private int edgeHeight;
    @Getter
    private int vertexWidth;
    @Getter
    private int vertexHeight;
    @Getter
    private final Map<TileCoordinate, Tile> tiles = new HashMap<>();
    @Getter
    private final Map<EdgeCoordinate, Edge> edges = new HashMap<>();
    @Getter
    private final Map<VertexCoordinate, Vertex> vertices = new HashMap<>();

    public GridBoard(int width, int height) {
        this.tileWidth = width;
        this.tileHeight = height;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                TileCoordinate c = new TileCoordinate(j, i);
                this.tiles.put(c, new WaterTile(c));
            }
        }

        this.edgeHeight = (4 * (height + 1) + 2);
        this.edgeWidth = (2 * (width + 1));
        for(int i = 0; i < edgeHeight; i++){
            for(int j = 0; j < edgeWidth; j++){
                EdgeCoordinate c = new EdgeCoordinate(j, i);
                this.edges.put(c, new Edge(c));
            }
        }

        this.vertexHeight = (2 * height + 2);
        this.vertexWidth = (2 * width + 1);
        for(int i = 0; i < vertexHeight; i++){
            for(int j = 0; j < vertexWidth; j++){
                VertexCoordinate c = new VertexCoordinate(j, i);
                this.vertices.put(c, new Vertex(c));
            }
        }
    }

    @Override
    public Tile getTile(TileCoordinate coordinate) throws InvalidTileCoordinateException {
        return Optional.ofNullable(this.tiles.get(coordinate)).orElseThrow(InvalidTileCoordinateException::new);
    }

    private Tile getTileOrNull(TileCoordinate coordinate) {
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
    public Tile[] getAdjacentTiles(TileCoordinate coordinate) throws InvalidTileCoordinateException {
        return Arrays.stream(coordinate.getSurroundingTileCoordinates())
            .map(this::getTileOrNull)
            .filter(x -> x!=null)
            .toArray(Tile[]::new);
    }

    @Override
    public Tile[] getAdjacentTiles(EdgeCoordinate coordinate) {
        return Arrays.stream(coordinate.getSurroundingTileCoordinates())
            .map(this::getTileOrNull)
            .filter(x -> x!=null)
            .toArray(Tile[]::new);
    }

    @Override
    public Tile[] getAdjacentTiles(VertexCoordinate coordinate) {
        return Arrays.stream(coordinate.getSurroundingTileCoordinates())
            .map(this::getTileOrNull)
            .filter(x -> x!=null)
            .toArray(Tile[]::new);
    }

    @Override
    public Edge[] getAdjacentEdges(TileCoordinate coordinate) {
        return Arrays.stream(coordinate.getSurroundingEdgeCoordinates()).map(c -> this.getEdge(c)).toArray(Edge[]::new);
    }

    @Override
    public Edge[] getAdjacentEdges(EdgeCoordinate coordinate) {
        return Arrays.stream(coordinate.getSurroundingEdgeCoordinates()).map(c -> this.getEdge(c)).toArray(Edge[]::new);
    }

    @Override
    public Edge[] getAdjacentEdges(VertexCoordinate coordinate) {
        return Arrays.stream(coordinate.getSurroundingEdgeCoordinates()).map(c -> this.getEdge(c)).toArray(Edge[]::new);
    }

    @Override
    public Vertex[] getAdjacentVertices(TileCoordinate coordinate) {
        return Arrays.stream(coordinate.getSurroundingVertexCoordinates()).map(c -> this.getVertex(c)).toArray(Vertex[]::new);    
    }

    @Override
    public Vertex[] getAdjacentVertices(EdgeCoordinate coordinate) {
        return Arrays.stream(coordinate.getSurroundingVertexCoordinates()).map(c -> this.getVertex(c)).toArray(Vertex[]::new);
    }

    @Override
    public Vertex[] getAdjacentVertices(VertexCoordinate coordinate) {
        return Arrays.stream(coordinate.getSurroundingVertexCoordinates()).map(c -> this.getVertex(c)).toArray(Vertex[]::new);
    }
}