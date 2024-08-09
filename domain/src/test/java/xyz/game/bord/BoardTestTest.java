package xyz.game.bord;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import xyz.game.board.GridBoard;
import xyz.game.board.Vertex;

public class BoardTestTest {
    
    @Test
    public void shouldDoIt(){

        GridBoard board = new GridBoard(10,5);

        Integer[][][] tcCoords = new Integer[][][]{
            new Integer[][]{
                new Integer[]{1,1},
                new Integer[]{2,1},
                new Integer[]{2,2}
            },
            new Integer[][]{
                new Integer[]{4,1},
                new Integer[]{3,1},
                new Integer[]{4,2}
            }
        };

        for (Integer[][] tc : tcCoords) {
            Vertex[][] vertexArrays = new Vertex[][]{
                board.getNodeAt(tc[0][0], tc[0][1]).getVertices(),
                board.getNodeAt(tc[1][0], tc[1][1]).getVertices(),
                board.getNodeAt(tc[2][0], tc[2][1]).getVertices(),
            };
            Vertex v = findCommonElement(vertexArrays[0], vertexArrays[1], vertexArrays[2]);
            assertNotNull(v);
        }
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
    
    
    // @Test
    // public void shouldHaveCommonVertexIfNodesTrisection() {
        
    //     Board board = new Board(10,5);

    //     record TestCaseForTrisectionVertex(
    //         Coord nodeA,
    //         Coord nodeB,
    //         Coord nodeC
    //     ){};
    
    //     TestCaseForTrisectionVertex[] testCases = new TestCaseForTrisectionVertex[]{
    //         new TestCaseForTrisectionVertex(
    //             new Coord(1,1),
    //             new Coord(2,1),
    //             new Coord(2,2)
    //         ),
    //         // new TestCaseForTrisectionVertex(
    //         //     new Coord(4,1),
    //         //     new Coord(3,1),
    //         //     new Coord(4,2)
    //         // ),
    //         // new TestCaseForTrisectionVertex(
    //         //     new Coord(4,1),
    //         //     new Coord(3,1),
    //         //     new Coord(3,0)
    //         // ),
    //         // new TestCaseForTrisectionVertex(
    //         //     new Coord(4,1),
    //         //     new Coord(4,2),
    //         //     new Coord(3,2)
    //         // ),
    //         // new TestCaseForTrisectionVertex(
    //         //     new Coord(2,2),
    //         //     new Coord(3,1),
    //         //     new Coord(3,2)
    //         // ),
    //     };

    //     for (TestCaseForTrisectionVertex tc : testCases) {
    //         Vertex[][] vertexArrays = new Vertex[][]{
    //             board.getNodeAt(tc.nodeA.x, tc.nodeA.y).getVertices(),
    //             board.getNodeAt(tc.nodeB.x, tc.nodeB.y).getVertices(),
    //             board.getNodeAt(tc.nodeC.x, tc.nodeC.y).getVertices(),
    //         };
    //         Vertex v = findCommonElement(vertexArrays[0], vertexArrays[1], vertexArrays[2]);
    //         assertNotNull(v);
    //     }
    // }
}
