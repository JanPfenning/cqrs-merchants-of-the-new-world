package xyz.game.board;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

public class Board {
    private int width;
    private int height;
    
    @Getter
    private Set<Node> nodes;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.nodes = new HashSet<>();
        buildGraph();
    }

    private void buildGraph() {
        // Create all nodes
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                nodes.add(new Node(x, y));
            }
        }
        
        // Connect nodes
        for (Node node : nodes) {
            connectNeighbors(node);
        }
    }

    private void connectNeighbors(Node node) {
        int[][] directions = {
            {-1, node.x % 2 == 1 ? 0 : -1}, // NW
            {0, -1}, // N
            {1, node.x % 2 == 1 ? 0 : -1}, // NE
            {1, node.x % 2 == 0 ? 0 : 1}, // SE
            {0, 1}, // S
            {-1, node.x % 2 == 0 ? 0 : 1} // SW
        };
        
        for (int[] dir : directions) {
            int nx = node.x + dir[0];
            int ny = node.y + dir[1];
            if (isValidCoord(nx, ny)) {
                Node neighbor = getNodeAt(nx, ny);
                if (neighbor != null) {
                    node.addNeighbor(neighbor);
                    neighbor.addNeighbor(node);
                }
            }
        }
    }

    private boolean isValidCoord(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Node getNodeAt(int x, int y) {
        return nodes.stream()
                .filter(n -> n.x == x && n.y == y)
                .findFirst()
                .orElse(null);
    }

    public static class Node {
        public final int x, y;
        @Getter
        HexTile data;
        @Getter
        Set<Node> neighbors;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.data = new WaterTile();
            this.neighbors = new HashSet<>();
        }

        void addNeighbor(Node neighbor) {
            neighbors.add(neighbor);
        }
    }

    @Data
    @AllArgsConstructor
    public static class Coord {
        public final int x, y;

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }
}