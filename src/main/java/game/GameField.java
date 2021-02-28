package game;

import enums.Direction;
import objects.Point;
import objects.PointsArray;
import objects.Snake;
import settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameField extends JPanel {

    Random random = new Random();
    private static final int snakePosX = Settings.START_POS_X * Settings.POINT_SIZE;
    private static final int snakePosY = Settings.START_POS_Y * Settings.POINT_SIZE;
    private static boolean wait = false;

    public static boolean isWait() {
        return wait;
    }
    public static void setWait(boolean wait) {
        GameField.wait = wait;
    }

    Snake snake = new Snake(3,snakePosX,snakePosY, Direction.RIGHT);
    PointsArray points = new PointsArray(Settings.N_POINTS,Settings.DOTS_IN_LINE,Settings.POINT_SIZE, snake);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSnake(g, snake);
        drawPoints(g, points);
    }

    public void game() {
        snake.moveSnake();
        for (objects.Point pp : points.points) {
            if (snake.snake.get(0).x == pp.x && snake.snake.get(0).y == pp.y) {
                snake.eat();
                randPos(pp);
            }
        }
        wait = false;
        repaint();
    }

    public void drawPoint (Graphics g, objects.Point p) {
        g.drawRect(p.x,p.y,p.size,p.size);
    }
    public void drawPoints (Graphics g, PointsArray pArr) {
        for (objects.Point p : pArr.points) {
        drawPoint(g, p);
        }
    }
    public void drawSnake (Graphics g, Snake snake) {
        for (objects.Point p : snake.snake) {
            drawPoint(g, p);
        }
    }
    public void randPos(Point pp) {
        pp.x = random.nextInt(Settings.DOTS_IN_LINE-1) * Settings.POINT_SIZE;
        pp.y = random.nextInt(Settings.DOTS_IN_LINE-1) * Settings.POINT_SIZE;
    }

    public boolean isLoose() {
        if (snake.snake.size() > 1) {
            for (int i = 2; i < snake.snake.size(); i++) {
                if (snake.snake.get(0).x == snake.snake.get(i).x && snake.snake.get(0).y == snake.snake.get(i).y) {
                    return true;
                }
            }
            if (snake.snake.get(0).x < 0 || snake.snake.get(0).x > Settings.FIELD_SIZE - Settings.POINT_SIZE
                    || snake.snake.get(0).y < 0 || snake.snake.get(0).y > Settings.FIELD_SIZE-Settings.POINT_SIZE) {
                return true;
            }
        }
        return false;
    }
}