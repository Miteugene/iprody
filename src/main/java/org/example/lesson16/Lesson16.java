package org.example.lesson16;

import java.util.Random;

public class Lesson16 {
    private final static Random random = new Random();
    private final static int PROBABILITY_OF_LAND = 30;
    private final static int ROWS_COUNT = 10;
    private final static int COLUMNS_COUNT = 10;

    public static void main(String[] args) {
        int[][] map = getRandomMap();
        printMap(map);

        DfsVoyager dfsVoyager = new DfsVoyager();
        BfsVoyager bfsVoyager = new BfsVoyager();

        System.out.println();
        System.out.println("DFS count: " + dfsVoyager.lookupIslands(map));
        System.out.println("BFS count: " + bfsVoyager.lookupIslands(map));

        System.out.println();
        System.out.println("DFS Order Of Processed Nodes: " + dfsVoyager.getOrderOfProcessedNodes());
        System.out.println("BFS Order Of Processed Nodes: " + bfsVoyager.getOrderOfProcessedNodes());
    }

    private static int[][] getRandomMap() {
        int[][] map = new int[ROWS_COUNT][COLUMNS_COUNT];

        for (int i = 0; i < ROWS_COUNT; i++) {
            for (int j = 0; j < COLUMNS_COUNT; j++) {
                map[i][j] = random.nextInt(1,100) < PROBABILITY_OF_LAND ? Voyager.GROUND : Voyager.WATER;
            }
        }

        return map;
    }

    private static void printMap(int[][] map) {
        System.out.print("# ");
        for (int j = 0; j < COLUMNS_COUNT; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for (int i = 0; i < ROWS_COUNT; i++) {
            System.out.print(i);
            for (int j = 0; j < COLUMNS_COUNT; j++) {
                if (map[i][j] == Voyager.GROUND) {
                    System.out.print("\uD83C\uDFDD\uFE0F"); // 🏝️
                } else {
                    System.out.print("\uD83C\uDF0A"); // 🌊
                }
            }
            System.out.println();
        }
    }
}
