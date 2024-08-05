package xyz.game.bord;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import xyz.game.board.Board;
import xyz.game.board.Board.Coord;
import xyz.game.board.Board.Node;
import xyz.game.board.WaterTile;

public class BoardTest {
    @Test
    public void shouldCreateBoardWithGivenSizeOfWaterTiles() throws Exception {
        Board board = new Board(10,5);
        for (Node node : board.getNodes()) {
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
            List<Coord> actualNeighbours = board.getNodeAt(tc.source.x, tc.source.y).getNeighbors().stream()
                .map(n -> new Coord(n.x, n.y))
                .collect(Collectors.toList());
            for (Coord expectedNeighbour : tc.expectedNeighbours) {
                assertTrue(actualNeighbours.contains(expectedNeighbour));
            }
            assertEquals(tc.expectedNeighbours.length, actualNeighbours.size());
        }
    }
}
