package xyz.game.merchants.domain.board;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

public class VertexCoordinateTest {
    @Test
    void testGetSurroundingVertexCoordindates() {
        VertexCoordinate c = new VertexCoordinate(4, 6);
        VertexCoordinate[] expectedNeighbours = new VertexCoordinate[]{
            new VertexCoordinate(4, 5),
            new VertexCoordinate(5, 6),
            new VertexCoordinate(4, 7),
        };
        assertTrue(EdgeCoordinateTest.arraysContainSameElements(expectedNeighbours, c.getSurroundingVertexCoordinates()));
    
        VertexCoordinate c1 = new VertexCoordinate(5, 6);
        VertexCoordinate[] expectedNeighbours1 = new VertexCoordinate[]{
            new VertexCoordinate(4, 6),
            new VertexCoordinate(5, 5),
            new VertexCoordinate(5, 7),
        };
        assertTrue(EdgeCoordinateTest.arraysContainSameElements(expectedNeighbours1, c1.getSurroundingVertexCoordinates()));
    
    
        VertexCoordinate c2 = new VertexCoordinate(5, 7);
        VertexCoordinate[] expectedNeighbours2 = new VertexCoordinate[]{
            new VertexCoordinate(5, 6),
            new VertexCoordinate(6, 7),
            new VertexCoordinate(5, 8),
        };
        assertTrue(EdgeCoordinateTest.arraysContainSameElements(expectedNeighbours2, c2.getSurroundingVertexCoordinates()));
    
    
        VertexCoordinate c3 = new VertexCoordinate(4, 7);
        VertexCoordinate[] expectedNeighbours3 = new VertexCoordinate[]{
            new VertexCoordinate(4, 6),
            new VertexCoordinate(3, 7),
            new VertexCoordinate(4, 8),
        };
        assertTrue(EdgeCoordinateTest.arraysContainSameElements(expectedNeighbours3, c3.getSurroundingVertexCoordinates()));
    }

    @Test
    void shouldGetSurroundingTileCoordinates() {
        BiConsumer<VertexCoordinate, TileCoordinate[]> runTestCase = (inp, expectedOutput) -> {
            TileCoordinate[] actualOutput = inp.getSurroundingTileCoordinates();
            assertTrue(EdgeCoordinateTest.arraysContainSameElements(expectedOutput, actualOutput));
        };

        runTestCase.accept(
            new VertexCoordinate(6, 7),
            new TileCoordinate[]{
                new TileCoordinate(6, 3),
                new TileCoordinate(5, 2),
                new TileCoordinate(5, 3),
            }
        );

        runTestCase.accept(
            new VertexCoordinate(6, 6),
            new TileCoordinate[]{
                new TileCoordinate(6, 3),
                new TileCoordinate(6, 2),
                new TileCoordinate(5, 2),
            }
        );

        runTestCase.accept(
            new VertexCoordinate(7, 6),
            new TileCoordinate[]{
                new TileCoordinate(6, 3),
                new TileCoordinate(7, 2),
                new TileCoordinate(6, 2),
            }
        );

        runTestCase.accept(
            new VertexCoordinate(7, 7),
            new TileCoordinate[]{
                new TileCoordinate(6, 3),
                new TileCoordinate(7, 2),
                new TileCoordinate(7, 3),
            }
        );
    }

    @Test
    void shouldGetSurroundingEdgeCoordinates() {
        Consumer<Void> tc1 = (v) -> {
            VertexCoordinate c = new VertexCoordinate(6, 7);
            EdgeCoordinate[] expectedNeighbours = new EdgeCoordinate[]{
                new EdgeCoordinate(12, 13),
                new EdgeCoordinate(11, 14),
                new EdgeCoordinate(12, 15),
            };
            assertTrue(EdgeCoordinateTest.arraysContainSameElements(expectedNeighbours, c.getSurroundingEdgeCoordinates()));
        };
        Consumer<Void> tc2 = (v) -> {
            VertexCoordinate c = new VertexCoordinate(6, 6);
            EdgeCoordinate[] expectedNeighbours = new EdgeCoordinate[]{
                new EdgeCoordinate(12, 11),
                new EdgeCoordinate(13, 12),
                new EdgeCoordinate(12, 13),
            };
            assertTrue(EdgeCoordinateTest.arraysContainSameElements(expectedNeighbours, c.getSurroundingEdgeCoordinates()));
        };
        Consumer<Void> tc3 = (v) -> {
            VertexCoordinate c = new VertexCoordinate(5, 7);
            EdgeCoordinate[] expectedNeighbours = new EdgeCoordinate[]{
                new EdgeCoordinate(10, 13),
                new EdgeCoordinate(11, 14),
                new EdgeCoordinate(10, 15),
            };
            assertTrue(EdgeCoordinateTest.arraysContainSameElements(expectedNeighbours, c.getSurroundingEdgeCoordinates()));
        };
        Consumer<Void> tc4 = (v) -> {
            VertexCoordinate c = new VertexCoordinate(5, 6);
            EdgeCoordinate[] expectedNeighbours = new EdgeCoordinate[]{
                new EdgeCoordinate(10, 11),
                new EdgeCoordinate(9, 12),
                new EdgeCoordinate(10, 13),
            };
            assertTrue(EdgeCoordinateTest.arraysContainSameElements(expectedNeighbours, c.getSurroundingEdgeCoordinates()));
        };

        tc1.accept(null);
        tc2.accept(null);
        tc3.accept(null);
        tc4.accept(null);
    }
}
