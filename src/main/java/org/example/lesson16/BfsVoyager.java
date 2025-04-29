package org.example.lesson16;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BfsVoyager extends GraphTraverser {

    @Override
    public int lookupIslands(int[][] inputMap) {
        // Чтобы не портить исходные данные
        int[][] map = cloneMap(inputMap);

        // По сути обходим всю карту
        for (int i = 0; i < inputMap.length; i++) {
            for (int j = 0; j < inputMap[i].length; j++) {
                // Нашли необработанный остров
                if (map[i][j] == Voyager.GROUND) {
                    islandsCount++;
                    markIsland(map, i, j);
                }
            }
        }

        return islandsCount;
    }

    // Помечает все клетки острова как 0
    private void markIsland(int[][] map, int i, int j) {
        Queue<Node> queue = new LinkedList<>();

        queue.offer(new Node(i, j));

        while (!queue.isEmpty()) {
            var node = queue.poll();
            orderOfProcessedNodes.add(node);
            map[node.getI()][node.getJ()] = Voyager.HANDLED_GROUND;
            // Добавляем в стек всех найденных соседей
            List<Node> unhandledNeighbours = getUnhandledNeighbours(map, node);
            for (Node neighbour : unhandledNeighbours) {
                queue.offer(neighbour);
            }
        }
    }
}
