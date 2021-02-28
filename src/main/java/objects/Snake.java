package objects;

import enums.Direction;
import game.GameField;
import settings.Settings;

import java.util.ArrayList;

import static enums.Direction.*;

public class Snake {
    public ArrayList<Point> snake;
    int stepX = Settings.POINT_SIZE, stepY = 0;
    Direction dir;

    public Snake(int length, int posX, int posY, Direction direction) {
        snake = new ArrayList<Point>();
        int step = 0;
        for (int i = 0; i < length; i++) {
            Point p = new Point(posX-step, posY);
            snake.add(p);
            step+= p.size;
        }
        dir = direction;
    }
    public void moveSnake() {
        for (int i = snake.size() - 1; i > 0; i--) {
                snake.get(i).x = snake.get(i - 1).x;
                snake.get(i).y = snake.get(i - 1).y;
        }
        snake.get(0).x+=stepX;
        snake.get(0).y+=stepY;
    }
    public void eat() {
        Point pp = new Point(snake.get(snake.size()-1).x, snake.get(snake.size()-1).y);
        snake.add(pp);
    }

    public void turnSnake(Direction direction) {
        boolean wait = GameField.isWait();
        switch (direction) {
            case LEFT -> {
                if (dir != RIGHT && !wait) {
                    stepX = -Settings.POINT_SIZE;
                    stepY = 0;
                    dir = LEFT;
                    GameField.setWait(true);
                    break;
                }
            }
            case RIGHT -> {
                if (dir != LEFT && !wait) {
                    stepX = Settings.POINT_SIZE;
                    stepY = 0;
                    dir = RIGHT;
                    GameField.setWait(true);
                    break;
                }
            }
            case UP -> {
                if (dir != DOWN && !wait) {
                    stepX = 0;
                    stepY = -Settings.POINT_SIZE;
                    dir = UP;
                    GameField.setWait(true);
                    break;
                }
            }
            case DOWN -> {
                if (dir != UP && !wait) {
                    stepX = 0;
                    stepY = Settings.POINT_SIZE;
                    dir = DOWN;
                    GameField.setWait(true);
                    break;
                }
            }
        }
    }
}
