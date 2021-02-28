package objects;

import java.util.ArrayList;
import java.util.Random;

public class PointsArray {
    public ArrayList<Point> points;
    Random random = new Random();

    public PointsArray(int n, int dotsInLine, int size, Snake snake) {
        points = new ArrayList<Point>();
        int x , y;
        boolean isClear = true;
        for (int i = 0; i < n; i++) {
            do {
                x = random.nextInt(dotsInLine-1) * size;
                y = random.nextInt(dotsInLine-1) * size;
                for (Point j : points) {
                    if (x == j.x && y == j.y) isClear = false;
                }
                for (Point j : snake.snake) {
                    if (x == j.x && y == j.y) isClear = false;
                }

            } while (!isClear);
            Point p = new Point(x, y);
            points.add(p);
        }
    }


}
