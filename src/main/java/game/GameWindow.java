package game;

import enums.Direction;
import settings.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameWindow extends JFrame {
    private static final int WINDOW_HEIGHT = Settings.FIELD_SIZE + 36;
    private static final int WINDOW_WIDTH = Settings.FIELD_SIZE+13;
    private static GameField mainPanel = new GameField();
    private static TimeListener myTimerListener = new TimeListener(mainPanel);
    public static Timer timer = new Timer(Settings.TIMER_DELAY, myTimerListener);



    public static void createGameWindow() {
        JFrame frame = new JFrame("Main Gui");
        frame.setTitle("Змейка");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
        frame.addKeyListener(new KeyListener(mainPanel));
        System.out.println(Settings.TIMER_DELAY);

        timer.start();
    }

    public static void main(String[] args) {
        createGameWindow();
    }

}


class TimeListener implements ActionListener {
    private GameField gameField;

    public TimeListener(GameField gameField) {
        this.gameField = gameField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameField.game();
        gameField.isLoose();
        if (gameField.isLoose()) {
            GameWindow.timer.stop();
        }
    }

}

class KeyListener extends KeyAdapter {
    private GameField gameField;

    public KeyListener(GameField gameField) {
        this.gameField = gameField;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT -> gameField.snake.turnSnake(Direction.RIGHT);
            case KeyEvent.VK_LEFT -> gameField.snake.turnSnake(Direction.LEFT);
            case KeyEvent.VK_UP -> gameField.snake.turnSnake(Direction.UP);
            case KeyEvent.VK_DOWN -> gameField.snake.turnSnake(Direction.DOWN);
        }
    }
}






