package xyz.game.bord;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import xyz.game.board.Board;
import xyz.game.board.Board.Coord;
import xyz.game.board.Board.Edge;
import xyz.game.board.Board.TileNode;
import xyz.game.board.Board.Vertex;
import xyz.game.board.WaterTile;

public class BoardTest {
    @Test
    public void shouldCreateBoardWithGivenSizeOfWaterTiles() throws Exception {
        Board board = new Board(10,5);
        for (TileNode node : board.getNodes()) {
            assertInstanceOf(WaterTile.class, node.getData());
        }
        assertEquals(50, board.getNodes().size());
    }

    @Test
    public void shouldHaveTheCorrectNeighbours() throws Exception {
        @Data
        @AllArgsConstructor
        class TestCase {
            Coord source;
            Coord[] expectedNeighbours;
        }

        Board board = new Board(10,5);

        TestCase[] testCases = new TestCase[]{
            new TestCase(
                new Coord(1,2),
                new Coord[]{
                    new Coord(1, 1),
                    new Coord(2, 2),
                    new Coord(2, 3),
                    new Coord(1, 3),
                    new Coord(0, 3),
                    new Coord(0, 2),
                }
            ),
            new TestCase(
                new Coord(2,1),
                new Coord[]{
                    new Coord(1, 0),
                    new Coord(2, 0),
                    new Coord(3, 0),
                    new Coord(3, 1),
                    new Coord(2, 2),
                    new Coord(1, 1),
                }
            ),
            new TestCase(
                new Coord(3,1),
                new Coord[]{
                    new Coord(3, 0),
                    new Coord(4, 1),
                    new Coord(4, 2),
                    new Coord(3, 2),
                    new Coord(2, 2),
                    new Coord(2, 1),
                }
            )
        };
        for (TestCase tc : testCases) {
            List<Coord> actualNeighbours = Arrays.stream(board.getNodeAt(tc.source.x, tc.source.y).getNeighbours())
                .map(n -> new Coord(n.x, n.y))
                .collect(Collectors.toList());
            for (Coord expectedNeighbour : tc.expectedNeighbours) {
                assertTrue(actualNeighbours.contains(expectedNeighbour));
            }
            assertEquals(tc.expectedNeighbours.length, actualNeighbours.size());
        }
    }

    @Test
    public void shouldHaveSixEdgesForEveryNode() throws Exception {
        Board board = new Board(10,5);
        for (TileNode node : board.getNodes()) {
            for (Edge e : node.getEdges()) {
                assertNotNull(e);
            }
            // Should share one edge with every non-null neighbour
            for(TileNode neighbour : node.getNeighbours()) {
                if (neighbour != null) {
                    long count = Arrays.stream(neighbour.getEdges())
                            .filter(e -> e != null && (e.getTiles()[0] == node || e.getTiles()[1] == node))
                            .count();
                    assertEquals(1, count);
                }
            }
        }
        // TODO count of edges should match expected count
    }

    public static <T> T findCommonElement(T[] array1, T[] array2, T[] array3) {
        Set<T> set1 = new HashSet<>(Arrays.asList(array1));
        Set<T> set2 = new HashSet<>(Arrays.asList(array2));
        
        for (T element : array3) {
            if (set1.contains(element) && set2.contains(element)) {
                return element;
            }
        }
        
        return null; // No common element found
    }

    @Test
    public void shouldHaveCommonVertexIfNodesTrisection() {
        @Data
        @AllArgsConstructor
        class TestCase {
            Coord nodeA;
            Coord nodeB;
            Coord nodeC;
        }

        Board board = new Board(10,5);

        TestCase[] testCases = new TestCase[]{
            new TestCase(
                new Coord(1,1),
                new Coord(2,1),
                new Coord(2,2)
            ),
            // new TestCase(
            //     new Coord(4,1),
            //     new Coord(3,1),
            //     new Coord(4,2)
            // ),
            // new TestCase(
            //     new Coord(4,1),
            //     new Coord(3,1),
            //     new Coord(3,0)
            // ),
            // new TestCase(
            //     new Coord(4,1),
            //     new Coord(4,2),
            //     new Coord(3,2)
            // ),
            // new TestCase(
            //     new Coord(2,2),
            //     new Coord(3,1),
            //     new Coord(3,2)
            // ),
        };

        for (TestCase tc : testCases) {
            Vertex[][] vertexArrays = new Vertex[][]{
                board.getNodeAt(tc.nodeA.x, tc.nodeA.y).getVertices(),
                board.getNodeAt(tc.nodeB.x, tc.nodeB.y).getVertices(),
                board.getNodeAt(tc.nodeC.x, tc.nodeC.y).getVertices(),
            };
            Vertex v = findCommonElement(vertexArrays[0], vertexArrays[1], vertexArrays[2]);
            assertNotNull(v);
        }
    }

    @Test
    public void shouldHaveSixVerticesForEveryNode() {
        Board board = new Board(10,5);
        for (TileNode node : board.getNodes()) {
            // for (Vertex v : node.getVertices()) {
            //     assertNotNull(v);
            // }
            // should share 2 vertices with every non-null neighbour
            for(TileNode neighbour : node.getNeighbours()) {
                if (neighbour != null) {
                    long count = Arrays.stream(neighbour.getVertices())
                            .filter(v -> v != null && (v.getTiles()[0] == node || v.getTiles()[1] == node || v.getTiles()[2] == node))
                            .count();
                    assertEquals(2, count);
                }
            }
        }
    }
}
