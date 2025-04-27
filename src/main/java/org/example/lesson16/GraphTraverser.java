package org.example.lesson16;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public abstract class GraphTraverser implements Voyager {
    protected int islandsCount = 0;
    protected LinkedHashSet<Node> orderOfProcessedNodes = new LinkedHashSet<>();

    public LinkedHashSet<Node> getOrderOfProcessedNodes() {
        return orderOfProcessedNodes;
    }

    protected int[][] cloneMap(int[][] inputMap) {
        int[][] map = new int[inputMap.length][];
        for (int i = 0; i < inputMap.length; i++) {
            map[i] = inputMap[i].clone();
        }
        return map;
    }

    // Находим всех необработанных соседей
    protected List<Node> getUnhandledNeighbours(int[][] map, Node node) {
        List<Node> neighbours = new ArrayList<>();

        int[][] directions = {
            {-1, 0}, // вверх
            {1, 0},  // вниз
            {0, -1}, // влево
            {0, 1}   // вправо
        };

        for (int[] dir : directions) {
            int newI = node.getI() + dir[0];
            int newJ = node.getJ() + dir[1];

            if (newI >= 0 && newI < map.length && newJ >= 0 && newJ < map[newI].length) {
                if (map[newI][newJ] == Voyager.GROUND) {
                    Node newNode = new Node(newI, newJ);
                    if (!orderOfProcessedNodes.contains(newNode)) {
                        neighbours.add(newNode);
                    }
                }
            }
        }

        return neighbours;
    }
}
