package xyz.game.merchants.domain.board;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.game.merchants.domain.board.harbours.Harbour;
import xyz.game.merchants.domain.board.tiles.Tile;
import xyz.game.merchants.domain.board.tiles.TileCoordinate;
import xyz.game.merchants.domain.board.tiles.WaterTile;

@AllArgsConstructor
public class GridBoard implements Board {
    @Getter
    final private int tileWidth;
    @Getter
    final private int tileHeight;
    @Getter
    final private int edgeWidth;
    @Getter
    final private int edgeHeight;
    @Getter
    final private int vertexWidth;
    @Getter
    final private int vertexHeight;
    @Getter
    final protected Map<EdgeCoordinate, Harbour> harbours = new HashMap<>();
    @Getter
    final protected Map<TileCoordinate, Tile> tiles = new HashMap<>();
    @Getter
    final protected Map<EdgeCoordinate, Edge> edges = new HashMap<>();
    @Getter
    final protected Map<VertexCoordinate, Vertex> vertices = new HashMap<>();

    public GridBoard(int width, int height, Map<TileCoordinate, Tile> tiles) {
        this(width, height);

        for (Map.Entry<TileCoordinate, Tile> entry : tiles.entrySet()) {
            if(tiles.get(entry.getKey()) == null) throw new RuntimeException("tile is out of bounce with board");
            this.tiles.put(entry.getKey(), entry.getValue());
        }
    }

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
    public @Nullable Tile getTile(TileCoordinate coordinate) {
        return this.tiles.get(coordinate);
    }

    @Override
    public @Nullable Edge getEdge(EdgeCoordinate coordinate) {
        return this.edges.get(coordinate);
    }

    @Override
    public @Nullable Vertex getVertex(VertexCoordinate coordinate) {
        return this.vertices.get(coordinate);
    }

    @Override
    public Tile[] getAdjacentTiles(TileCoordinate coordinate) {
        return Arrays.stream(coordinate.getSurroundingTileCoordinates()).map(c -> this.getTile(c)).toArray(Tile[]::new);
    }

    @Override
    public Tile[] getAdjacentTiles(EdgeCoordinate coordinate) {
        return Arrays.stream(coordinate.getSurroundingTileCoordinates()).map(c -> this.getTile(c)).toArray(Tile[]::new);
    }

    @Override
    public Tile[] getAdjacentTiles(VertexCoordinate coordinate) {
        return Arrays.stream(coordinate.getSurroundingTileCoordinates()).map(c -> this.getTile(c)).toArray(Tile[]::new);
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

    @Override
    public Optional<Harbour> getHarbour(VertexCoordinate coordinate) {
        return Arrays.stream(coordinate.getSurroundingEdgeCoordinates())
            .map(edgeCoordinate -> this.harbours.get(edgeCoordinate))
            .filter(Objects::nonNull)
            .findFirst();
    }
}