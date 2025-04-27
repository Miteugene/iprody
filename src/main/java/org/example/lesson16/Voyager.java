package org.example.lesson16;

public interface Voyager {
    public final int HANDLED_GROUND = 0;
    public final int GROUND = 1;
    public final int WATER = 2;

    public int lookupIslands(int[][] map);
}
