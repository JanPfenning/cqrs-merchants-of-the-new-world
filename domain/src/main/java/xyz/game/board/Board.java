package xyz.game.board;

import java.util.*;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

public class Board {
    private int width;
    private int height;
    
    @Getter
    private final Set<TileNode> nodes = new HashSet<>();
    @Getter
    private final Set<Edge> edges = new HashSet<>();
    @Getter
    private final Set<Vertex> vertices = new HashSet<>();

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        buildGraph();
    }

    private void buildGraph() {
        // Create all nodes
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                nodes.add(new TileNode(x, y));
            }
        }
        
        // Connect nodes
        for (TileNode node : nodes) {
            connectNeighbors(node);
        }

        for (TileNode node : nodes) {
            for (TileNode neighbour : node.getNeighbours()) {
                if (neighbour != null) {
                    boolean sharesEdge = false;
                    for (Edge edge : node.getEdges()) {
                        if (edge != null && (edge.getTiles()[0] == neighbour || edge.getTiles()[1] == neighbour)) {
                            sharesEdge = true;
                            break;
                        }
                    }
                    if (!sharesEdge) {
                        Edge e = new Edge(node, neighbour);
                         IntStream.range(0, node.getEdges().length)
                                .filter(i -> node.getEdges()[i] == null)
                                .findFirst()
                                .ifPresent(i -> node.getEdges()[i] = e);
                        IntStream.range(0, neighbour.getEdges().length)
                                .filter(i -> neighbour.getEdges()[i] == null)
                                .findFirst()
                                .ifPresent(i -> neighbour.getEdges()[i] = e);
                        edges.add(e);
                    }
                } else {
                    Edge e = new Edge(node, null);
                    IntStream.range(0, node.getEdges().length)
                        .filter(i -> node.getEdges()[i] == null)
                        .findFirst()
                        .ifPresent(i -> node.getEdges()[i] = e); 
                    edges.add(e);
                }
            }
        }
    }

    private void connectNeighbors(TileNode node) {
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
                TileNode neighbor = getNodeAt(nx, ny);
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

    public TileNode getNodeAt(int x, int y) {
        return nodes.stream()
                .filter(n -> n.x == x && n.y == y)
                .findFirst()
                .orElse(null);
    }

    public static class TileNode {
        public final int x, y;
        @Getter
        private HexTile data;
        @Getter
        private final TileNode[] neighbours = new TileNode[6];
        @Getter
        private final Edge[] edges = new Edge[6];
        @Getter
        private final Vertex[] vertices = new Vertex[6];

        TileNode(int x, int y) {
            this.x = x;
            this.y = y;
            this.data = new WaterTile();
        }

        private void addNeighbor(TileNode neighbour) {
            for (int i = 0; i < neighbours.length; i++) {
                if(neighbours[i] == neighbour) return;
                if (neighbours[i] == null) {
                    neighbours[i] = neighbour;
                    return;
                }
            }
            throw new RuntimeException("No null element found in neighbours array");
        }
    }

    public static class Edge {
        @Getter
        private final TileNode[] tiles = new TileNode[2];
        // private final Vertex[] vertices = new Vertex[2];
        // private Data data;

        Edge(TileNode a, TileNode b) {
            this.tiles[0] = a;
            this.tiles[1] = b;
        }
    }

    public static class Vertex {
        private final Edge[] edges = new Edge[3];
        private final TileNode[] tiles = new TileNode[3];
        // private Data data;
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