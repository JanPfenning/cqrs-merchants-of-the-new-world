package xyz.game.merchants.domain.board;

import java.util.Map;

public interface Board {
    int getTileWidth();
    int getTileHeight();
    int getEdgeWidth();
    int getEdgeHeight();
    int getVertexWidth();
    int getVertexHeight();

    Map<TileCoordinate, Tile> getTiles();
    Map<EdgeCoordinate, Edge> getEdges();
    Map<VertexCoordinate, Vertex> getVertices();

    Tile getTile(TileCoordinate coordinate) throws InvalidTileCoordinateException;
    Edge getEdge(EdgeCoordinate coordinate);
    Vertex getVertex(VertexCoordinate coordinate);

    Tile[] getAdjacentTiles(TileCoordinate coordinate) throws InvalidTileCoordinateException;
    Tile[] getAdjacentTiles(EdgeCoordinate coordinate) throws InvalidTileCoordinateException;
    Tile[] getAdjacentTiles(VertexCoordinate coordinate) throws InvalidTileCoordinateException;

    Edge[] getAdjacentEdges(TileCoordinate coordinate);
    Edge[] getAdjacentEdges(EdgeCoordinate coordinate);
    Edge[] getAdjacentEdges(VertexCoordinate coordinate);
    
    Vertex[] getAdjacentVertices(TileCoordinate coordinate);
    Vertex[] getAdjacentVertices(EdgeCoordinate coordinate);
    Vertex[] getAdjacentVertices(VertexCoordinate coordinate);
}
