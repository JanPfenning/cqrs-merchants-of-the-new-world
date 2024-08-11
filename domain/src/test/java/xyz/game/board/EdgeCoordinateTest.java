package xyz.game.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.function.Consumer;

import xyz.game.board.EdgeCoordinate.MissingCaseException;

public class EdgeCoordinateTest {

    public static <T> boolean arraysContainSameElements(T[] a, T[] b) {
        // Check if sizes are different
        if (a.length != b.length) {
            return false;
        }

        // Create sets from the arrays
        Set<T> setA = Set.of(a);
        Set<T> setB = Set.of(b);

        // Check if both sets are equal
        return setA.equals(setB);
    }

    @Test
    void shouldBeEqual() {
        EdgeCoordinate a = new EdgeCoordinate(0, 0);
        EdgeCoordinate b = new EdgeCoordinate(0, 0);
        assertEquals(a, b);
    }

    @Test
    void shouldNotBeEqual() {
        EdgeCoordinate a = new EdgeCoordinate(0, 0);
        EdgeCoordinate b = new EdgeCoordinate(1, 0);
        EdgeCoordinate c = new EdgeCoordinate(0, 1);
        EdgeCoordinate d = new EdgeCoordinate(1, 1);
        assertNotEquals(a, b);
        assertNotEquals(a, c);
        assertNotEquals(a, d);
        assertNotEquals(b, c);
        assertNotEquals(b, d);
    }

    @Test
    void shouldGetSurroundingTileCoordinates() {
        Consumer<Void> tc1 = (v) -> {
            EdgeCoordinate c = new EdgeCoordinate(6, 11);
            TileCoordinate[] expectedNeighbours = new TileCoordinate[]{
                new TileCoordinate(2, 2),
                new TileCoordinate(3, 2),
            };
            assertTrue(arraysContainSameElements(expectedNeighbours, c.getSurroundingTileCoordinates()));
        };
    
        Consumer<Void> tc2 = (v) -> {
            EdgeCoordinate c = new EdgeCoordinate(8, 11);
            TileCoordinate[] expectedNeighbours = new TileCoordinate[]{
                new TileCoordinate(3, 2),
                new TileCoordinate(4, 2),
            };
            assertTrue(arraysContainSameElements(expectedNeighbours, c.getSurroundingTileCoordinates()));
        };

        Consumer<Void> tc3 = (v) -> {
            EdgeCoordinate c = new EdgeCoordinate(15, 14);
            TileCoordinate[] expectedNeighbours = new TileCoordinate[]{
                new TileCoordinate(7, 3),
                new TileCoordinate(7, 2),
            };
            assertTrue(arraysContainSameElements(expectedNeighbours, c.getSurroundingTileCoordinates()));
        };

        Consumer<Void> tc4 = (v) -> {
            EdgeCoordinate c = new EdgeCoordinate(16, 15);
            TileCoordinate[] expectedNeighbours = new TileCoordinate[]{
                new TileCoordinate(7, 3),
                new TileCoordinate(8, 3),
            };
            assertTrue(arraysContainSameElements(expectedNeighbours, c.getSurroundingTileCoordinates()));
        };

        Consumer<Void> tc5 = (v) -> {
            EdgeCoordinate c = new EdgeCoordinate(16, 17);
            TileCoordinate[] expectedNeighbours = new TileCoordinate[]{
                new TileCoordinate(7, 3),
                new TileCoordinate(8, 4),
            };
            assertTrue(arraysContainSameElements(expectedNeighbours, c.getSurroundingTileCoordinates()));
        };

        Consumer<Void> tc6 = (v) -> {
            EdgeCoordinate c = new EdgeCoordinate(15, 18);
            TileCoordinate[] expectedNeighbours = new TileCoordinate[]{
                new TileCoordinate(7, 3),
                new TileCoordinate(7, 4),
            };
            assertTrue(arraysContainSameElements(expectedNeighbours, c.getSurroundingTileCoordinates()));
        };

        Consumer<Void> tc7 = (v) -> {
            EdgeCoordinate c = new EdgeCoordinate(14, 17);
            TileCoordinate[] expectedNeighbours = new TileCoordinate[]{
                new TileCoordinate(7, 3),
                new TileCoordinate(6, 4),
            };
            assertTrue(arraysContainSameElements(expectedNeighbours, c.getSurroundingTileCoordinates()));
        };

        Consumer<Void> tc8 = (v) -> {
            EdgeCoordinate c = new EdgeCoordinate(14, 15);
            TileCoordinate[] expectedNeighbours = new TileCoordinate[]{
                new TileCoordinate(7, 3),
                new TileCoordinate(6, 3),
            };
            assertTrue(arraysContainSameElements(expectedNeighbours, c.getSurroundingTileCoordinates()));
        };

        tc1.accept(null);
        tc2.accept(null);
        tc3.accept(null);
        tc4.accept(null);
        tc5.accept(null);
        tc6.accept(null);
        tc7.accept(null);
        tc8.accept(null);
    }

    @Test
    void shouldGetSurroundingVertexCoordinates() {
        EdgeCoordinate c = new EdgeCoordinate(5, 8);
        VertexCoordinate[] expectedNeighbours = new VertexCoordinate[]{
            new VertexCoordinate(2, 4),
            new VertexCoordinate(3, 4),
        };
        assertTrue(arraysContainSameElements(expectedNeighbours, c.getSurroundingVertexCoordinates()));
    
        EdgeCoordinate c2 = new EdgeCoordinate(6, 9);
        VertexCoordinate[] expectedNeighbours2 = new VertexCoordinate[]{
            new VertexCoordinate(3, 4),
            new VertexCoordinate(3, 5),
        };
        assertTrue(arraysContainSameElements(expectedNeighbours2, c2.getSurroundingVertexCoordinates()));

        EdgeCoordinate c3 = new EdgeCoordinate(4, 11);
        VertexCoordinate[] expectedNeighbours3 = new VertexCoordinate[]{
            new VertexCoordinate(2, 5),
            new VertexCoordinate(2, 6),
        };
        assertTrue(arraysContainSameElements(expectedNeighbours3, c3.getSurroundingVertexCoordinates()));
    }

    @Test
    void shouldGetSurroundingEdgeCoordinates() {
        Consumer<Void> tc1 = (v) -> {
            EdgeCoordinate c = new EdgeCoordinate(15, 14);
            EdgeCoordinate[] expectedNeighbours = new EdgeCoordinate[]{
                new EdgeCoordinate(14, 13),
                new EdgeCoordinate(16, 13),
                new EdgeCoordinate(14, 15),
                new EdgeCoordinate(16, 15),
            };
            assertTrue(arraysContainSameElements(expectedNeighbours, c.getSurroundingEdgeCoordinates()));
        };

        Consumer<Void> tc2 = (v) -> {
            EdgeCoordinate c = new EdgeCoordinate(16, 15);
            EdgeCoordinate[] expectedNeighbours = new EdgeCoordinate[]{
                new EdgeCoordinate(15, 14),
                new EdgeCoordinate(16, 13),
                new EdgeCoordinate(17, 16),
                new EdgeCoordinate(16, 17),
            };
            assertTrue(arraysContainSameElements(expectedNeighbours, c.getSurroundingEdgeCoordinates()));
        };

        Consumer<Void> tc3 = (v) -> {
            EdgeCoordinate c = new EdgeCoordinate(16, 13);
            EdgeCoordinate[] expectedNeighbours = new EdgeCoordinate[]{
                new EdgeCoordinate(16, 11),
                new EdgeCoordinate(17, 12),
                new EdgeCoordinate(15, 14),
                new EdgeCoordinate(16, 15),
            };
            assertTrue(arraysContainSameElements(expectedNeighbours, c.getSurroundingEdgeCoordinates()));
        };

        tc1.accept(null);
        tc2.accept(null);
        tc3.accept(null);
    }

    @Test
    void shouldNotThrowMissingCaseException() {
        EdgeCoordinate[] coordinates = new EdgeCoordinate[]{
                                          // x%2 | x%4 
            new EdgeCoordinate(0, 1), // 0|1
            new EdgeCoordinate(2, 1), // 0|1
            new EdgeCoordinate(4, 5), // 0|1
            new EdgeCoordinate(2, 5), // 0|1
            new EdgeCoordinate(4, 9), // 0|1
            new EdgeCoordinate(2, 9), // 0|1
            new EdgeCoordinate(0, 5), // 0|1
            
            new EdgeCoordinate(2, 3), // 0|3
            new EdgeCoordinate(0, 3), // 0|3
            new EdgeCoordinate(2, 7), // 0|3
            new EdgeCoordinate(4, 3), // 0|3
            new EdgeCoordinate(0, 7), // 0|3
            
            new EdgeCoordinate(1, 0), // 1|0
            new EdgeCoordinate(1, 8), // 1|0
            new EdgeCoordinate(1, 4), // 1|0
            
            new EdgeCoordinate(3, 10),// 1|2
            new EdgeCoordinate(3, 2), // 1|2
            new EdgeCoordinate(3, 6), // 1|2
        };
        for (EdgeCoordinate c : coordinates) {
            assertDoesNotThrow(c::getSurroundingTileCoordinates);
        }
    }

    @Test
    void shouldThrowMissingCaseException() {
        EdgeCoordinate c = new EdgeCoordinate(0, 0);
        assertThrows(MissingCaseException.class, c::getSurroundingTileCoordinates);
    }
}
