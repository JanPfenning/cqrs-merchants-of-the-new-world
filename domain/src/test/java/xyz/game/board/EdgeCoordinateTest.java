package xyz.game.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Set;

import xyz.game.board.EdgeCoordinate.MissingCaseException;

public class EdgeCoordinateTest {

    public <T> boolean arraysContainSameElements(T[] a, T[] b) {
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
        EdgeCoordinate c = new EdgeCoordinate(6, 11);
        TileCoordinate[] expectedNeighbours = new TileCoordinate[]{
            new TileCoordinate(2, 2),
            new TileCoordinate(3, 2),
        };
        assertTrue(arraysContainSameElements(expectedNeighbours, c.getSurroundingTileCoordinates()));
    
        EdgeCoordinate c2 = new EdgeCoordinate(8, 11);
        TileCoordinate[] expectedNeighbours2 = new TileCoordinate[]{
            new TileCoordinate(3, 2),
            new TileCoordinate(4, 2),
        };
        assertTrue(arraysContainSameElements(expectedNeighbours2, c2.getSurroundingTileCoordinates()));
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
