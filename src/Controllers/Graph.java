package Controllers;

import java.util.*;
import models.Node;

public class Graph {
    private Set<Node> nodes;

    public Graph() {
        nodes = new HashSet<>();
    }

    public Node addNode(int value) {
        Node newNode = new Node(value);
        nodes.add(newNode);
        return newNode;
    }

    public void addEdge(Node src, Node dest) {
        src.addNeighbor(dest);
        dest.addNeighbor(src); // Grafo no dirigido
    }

    public void addEdgeUni(Node src, Node dest) {
        src.addNeighbor(dest); // Solo una dirección
    }

    public void printGraph() {
        // Ordenar nodos por valor para que el output sea consistente
        List<Node> sortedNodes = new ArrayList<>(nodes);
        sortedNodes.sort(Comparator.comparingInt(Node::getValue));

        for (Node node : sortedNodes) {
            System.out.print("Vertex " + node.getValue() + ": ");
            List<Node> neighbors = new ArrayList<>(node.getNeighbors());
            neighbors.sort(Comparator.comparingInt(Node::getValue));
            for (int i = 0; i < neighbors.size(); i++) {
                System.out.print(neighbors.get(i).getValue());
                if (i < neighbors.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }

    public void getDFS(Node startNode) {
        Set<Node> visited = new HashSet<>();
        System.out.print("DFS: ");
        dfsHelper(startNode, visited);
        System.out.println();
    }

    private void dfsHelper(Node node, Set<Node> visited) {
        if (visited.contains(node)) return;
        visited.add(node);
        System.out.print(node.getValue() + " ");
        for (Node neighbor : node.getNeighbors()) {
            dfsHelper(neighbor, visited);
        }
    }

    public void getBFS(Node startNode) {
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();

        visited.add(startNode);
        queue.add(startNode);

        System.out.print("BFS: ");
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.getValue() + " ");
            for (Node neighbor : current.getNeighbors()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public int[][] getAdjacencyMatrix() {
        List<Node> nodeList = new ArrayList<>(nodes);
        nodeList.sort(Comparator.comparingInt(Node::getValue));
        int size = nodeList.size();
        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            Node node = nodeList.get(i);
            for (Node neighbor : node.getNeighbors()) {
                int j = nodeList.indexOf(neighbor);
                if (j != -1) {
                    matrix[i][j] = 1;
                }
            }
        }
        return matrix;
    }

    public void printAdjacencyMatrix() {
        int[][] matrix = getAdjacencyMatrix();
        System.out.println("Adjacency Matrix:");
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
