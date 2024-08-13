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

    Tile getTile(TileCoordinate coordinate);
    Edge getEdge(EdgeCoordinate coordinate);
    Vertex getVertex(VertexCoordinate coordinate);

    Tile[] getAdjacentTiles(TileCoordinate coordinate);
    Tile[] getAdjacentTiles(EdgeCoordinate coordinate);
    Tile[] getAdjacentTiles(VertexCoordinate coordinate);

    Edge[] getAdjacentEdges(TileCoordinate coordinate);
    Edge[] getAdjacentEdges(EdgeCoordinate coordinate);
    Edge[] getAdjacentEdges(VertexCoordinate coordinate);
    
    Vertex[] getAdjacentVertices(TileCoordinate coordinate);
    Vertex[] getAdjacentVertices(EdgeCoordinate coordinate);
    Vertex[] getAdjacentVertices(VertexCoordinate coordinate);
}
