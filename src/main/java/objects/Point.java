package objects;

import settings.Settings;

public class Point {
    public int size;
    public int x, y;

    public Point(int x, int y) {
        this.size = Settings.POINT_SIZE;
        this.x = x;
        this.y = y;
    }
}


