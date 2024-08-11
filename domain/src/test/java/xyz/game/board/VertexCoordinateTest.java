package xyz.game.board;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
