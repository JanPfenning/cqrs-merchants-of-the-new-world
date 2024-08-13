package xyz.game.merchants.domain.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;

public class BoardTest {

    public static <T> List<T> findCommonElements(T[]... arrays) {
        if (arrays.length == 0) {
            return new ArrayList<>();
        }
        
        if (arrays.length == 1) {
            return new ArrayList<>(Arrays.asList(arrays[0]));
        }

        HashSet<T> commonElementsSet = new HashSet<>(Arrays.asList(arrays[0]));

        for (int i = 1; i < arrays.length; i++) {
            HashSet<T> currentSet = new HashSet<>(Arrays.asList(arrays[i]));
            commonElementsSet.retainAll(currentSet);
            if (commonElementsSet.isEmpty()) {
                break;
            }
        }

        return new ArrayList<>(commonElementsSet.stream().filter(x -> x != null).toList());
    }

    @Test
    public void shouldInstantiateTheBoard(){
        Board board = new GridBoard(10, 5);
        assertNotNull(board);
    }

    @Test
    public void shouldThrowIfTileCoordinateDoesNotResultInTile () {
        Consumer<TileCoordinate> test = (TileCoordinate c) -> {
            Board board = new GridBoard(2, 3);
            assertThrows(InvalidTileCoordinateException.class, () -> board.getTile(c));
        };
        test.accept(new TileCoordinate(-1, 1));
        test.accept(new TileCoordinate(1, -1));
        test.accept(new TileCoordinate(2, 1));
        test.accept(new TileCoordinate(1, 3));
    }

    @Test
    public void shouldNotThrowIfTileCoordinateResultsInTile () {
        Consumer<TileCoordinate> test = (TileCoordinate c) -> {
            Board board = new GridBoard(2, 3);
            assertDoesNotThrow(() -> board.getTile(c));
        };
        test.accept(new TileCoordinate(0, 0));
        test.accept(new TileCoordinate(1, 0));
        test.accept(new TileCoordinate(0, 1));
        test.accept(new TileCoordinate(1, 2));
        test.accept(new TileCoordinate(1, 1));
    }

    @Test
    public void shouldHaveTheCorrectNeighbours() throws InvalidTileCoordinateException {
        @Data
        @AllArgsConstructor
        final class TestCase {
            TileCoordinate source;
            TileCoordinate[] expectedNeighbours;
        }
        TestCase[] testCases = new TestCase[]{
            new TestCase(
                new TileCoordinate(1,2),
                new TileCoordinate[]{
                    new TileCoordinate(1, 1),
                    new TileCoordinate(2, 2),
                    new TileCoordinate(2, 3),
                    new TileCoordinate(1, 3),
                    new TileCoordinate(0, 3),
                    new TileCoordinate(0, 2),
                }
            ),
            new TestCase(
                new TileCoordinate(2,1),
                new TileCoordinate[]{
                    new TileCoordinate(1, 0),
                    new TileCoordinate(2, 0),
                    new TileCoordinate(3, 0),
                    new TileCoordinate(3, 1),
                    new TileCoordinate(2, 2),
                    new TileCoordinate(1, 1),
                }
            ),
            new TestCase(
                new TileCoordinate(3,1),
                new TileCoordinate[]{
                    new TileCoordinate(3, 0),
                    new TileCoordinate(4, 1),
                    new TileCoordinate(4, 2),
                    new TileCoordinate(3, 2),
                    new TileCoordinate(2, 2),
                    new TileCoordinate(2, 1),
                }
            )
        };

        Board board = new GridBoard(10,5);

        for (TestCase tc : testCases) {
            List<TileCoordinate> actualNeighbours = Arrays.stream(board.getAdjacentTiles(tc.source))
                .map(n -> n.getCoordinate())
                .collect(Collectors.toList());
            for (TileCoordinate expectedNeighbour : tc.expectedNeighbours) {
                assertTrue(actualNeighbours.contains(expectedNeighbour));
            }
            assertEquals(tc.expectedNeighbours.length, actualNeighbours.size());
        }
    }

    @Test
    public void shouldHaveSixEdgesForEveryNode() throws Exception {
        Board board = new GridBoard(10,5);
        Tile[] tiles = board.getTiles().values().stream().toArray(Tile[]::new);
        for (Tile tile : tiles) {
            Edge[] adjacentEdges = board.getAdjacentEdges(tile.getCoordinate());
            for (Edge e : adjacentEdges) {
                assertNotNull(e);
            }
            assertEquals(6, adjacentEdges.length);
        }
    }

    @Test
    public void shouldShareOneEdgeWithEveryNonNullNeighbour() throws InvalidTileCoordinateException {
        Board board = new GridBoard(10,5);
        Tile[] tiles = board.getTiles().values().stream().toArray(Tile[]::new);
        for(Tile tile : tiles){
            Edge[] adjacentEdgesToTile = board.getAdjacentEdges(tile.getCoordinate());
            Tile[] neighbourTiles = board.getAdjacentTiles(tile.getCoordinate());
            for(Tile neighbour : neighbourTiles) {
                if (neighbour != null) {
                    Edge[] adjacentEdgesToNeighbour = board.getAdjacentEdges(neighbour.getCoordinate());
                    Edge[] edgesAdjacentToNeighbourAndTile = findCommonElements(adjacentEdgesToNeighbour, adjacentEdgesToTile).toArray(Edge[]::new);
                    assertEquals(1, edgesAdjacentToNeighbourAndTile.length);
                }
            }
        }
    }

    @Test
    public void shouldHaveSixVerticesForEveryNode() {
        Board board = new GridBoard(10,5);
        Tile[] tiles = board.getTiles().values().stream().toArray(Tile[]::new);
        for (Tile tile : tiles) {
            Vertex[] adjacentVertices = board.getAdjacentVertices(tile.getCoordinate());
            for (Vertex v : adjacentVertices) {
                assertNotNull(v);
            }
            assertEquals(6, adjacentVertices.length);
        }
    }

    @Test
    public void shouldShareTwoVerticesWithEveryNonNullNeighbour() throws InvalidTileCoordinateException{
        Board board = new GridBoard(10,5);
        Tile[] tiles = board.getTiles().values().stream().toArray(Tile[]::new);
        for (Tile tile : tiles) {
            Vertex[] adjacentVerticesToTile = board.getAdjacentVertices(tile.getCoordinate());
            Tile[] neighbourTiles = board.getAdjacentTiles(tile.getCoordinate());
            for(Tile neighbour : neighbourTiles) {
                if (neighbour != null) {
                    Vertex[] adjacentVerticesToNeighbour = board.getAdjacentVertices(neighbour.getCoordinate());
                    Vertex[] verticesAdjacentToNeighbourAndTile = findCommonElements(adjacentVerticesToNeighbour, adjacentVerticesToTile).toArray(Vertex[]::new);
                    assertEquals(2, verticesAdjacentToNeighbourAndTile.length);
                }
            }
        }
    }

    // TODO count of edges should match expected count
    // TODO count of vertices should match expected count
    // TODO count of tiles should match expected count

    @Test
    public void shouldHaveCommonVertexIfNodesTrisection() throws InvalidTileCoordinateException {
        record TestCase(
            TileCoordinate nodeA,
            TileCoordinate nodeB,
            TileCoordinate nodeC
        ){};
    
        TestCase[] testCases = new TestCase[]{
            new TestCase(
                new TileCoordinate(1,1),
                new TileCoordinate(2,1),
                new TileCoordinate(2,2)
            ),
            new TestCase(
                new TileCoordinate(4,1),
                new TileCoordinate(3,1),
                new TileCoordinate(4,2)
            ),
            new TestCase(
                new TileCoordinate(4,1),
                new TileCoordinate(3,1),
                new TileCoordinate(3,0)
            ),
            new TestCase(
                new TileCoordinate(4,1),
                new TileCoordinate(4,2),
                new TileCoordinate(3,1)
            ),
            new TestCase(
                new TileCoordinate(2,2),
                new TileCoordinate(3,1),
                new TileCoordinate(3,2)
            ),
        };
        
        GridBoard board = new GridBoard(10,5);

        for (TestCase tc : testCases) {
            Vertex[][] vertexArrays = new Vertex[][]{
                board.getAdjacentVertices(board.getTile(tc.nodeA).getCoordinate()),
                board.getAdjacentVertices(board.getTile(tc.nodeB).getCoordinate()),
                board.getAdjacentVertices(board.getTile(tc.nodeC).getCoordinate()),
            };
            Vertex[] intersectionVertices = findCommonElements(vertexArrays[0], vertexArrays[1], vertexArrays[2]).toArray(Vertex[]::new);
            assertEquals(1, intersectionVertices.length);
            assertNotNull(intersectionVertices[0]);
        }
    }
}
