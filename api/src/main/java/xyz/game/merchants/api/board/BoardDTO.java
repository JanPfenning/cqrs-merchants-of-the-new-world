package xyz.game.merchants.api.board;

import java.util.Map.Entry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.game.merchants.domain.board.Board;
import xyz.game.merchants.domain.board.Edge;
import xyz.game.merchants.domain.board.EdgeCoordinate;
import xyz.game.merchants.domain.board.Vertex;
import xyz.game.merchants.domain.board.VertexCoordinate;
import xyz.game.merchants.domain.board.tiles.Tile;
import xyz.game.merchants.domain.board.tiles.TileCoordinate;

@AllArgsConstructor
public class BoardDTO {
    @Getter
    private final TileDTO[][] tiles;
    @Getter
    private final EdgeDTO[][] edges;
    @Getter
    private final VertexDTO[][] vertices;


    public static BoardDTO from(Board board) {
        TileDTO[][] tiles = new TileDTO[board.getTileHeight()][board.getTileWidth()];
        EdgeDTO[][] edges = new EdgeDTO[board.getEdgeHeight()][board.getEdgeWidth()];
        VertexDTO[][] vertices = new VertexDTO[board.getVertexHeight()][board.getVertexWidth()];
        
        for (Entry<VertexCoordinate, Vertex> entry : board.getVertices().entrySet()) {
            vertices[entry.getKey().getY()][entry.getKey().getX()] = VertexDTO.from(entry.getValue());
        }        
        for (Entry<TileCoordinate, Tile> entry : board.getTiles().entrySet()) {
            tiles[entry.getKey().getY()][entry.getKey().getX()] = TileDTO.from(entry.getValue());
        }        
        for (Entry<EdgeCoordinate, Edge> entry : board.getEdges().entrySet()) {
            edges[entry.getKey().getY()][entry.getKey().getX()] = EdgeDTO.from(entry.getValue());
        }

        return new BoardDTO(tiles, edges, vertices);
    }
}
