import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class SnakeGame extends JFrame implements KeyListener {

    private int width = 400;
    private int height = 400;
    private int[] snakeX = new int[100];
    private int[] snakeY = new int[100];
    private int snakeLength = 3;
    private int appleX;
    private int appleY;
    private boolean gameOver = false;

    public SnakeGame() {
        super("Snake Game");
        setSize(width, height);
        setLocationRelativeTo(null);
        addKeyListener(this);
        setVisible(true);

        snakeX[0] = width / 2;
        snakeY[0] = height / 2;
        for (int i = 1; i < snakeLength; i++) {
            snakeX[i] = snakeX[i - 1] - 10;
        }

        appleX = (int) (Math.random() * width);
        appleY = (int) (Math.random() * height);

        Thread gameLoop = new Thread(() -> {
            while (!gameOver) {
                repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gameLoop.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int i = 0; i < snakeLength; i++) {
            g.setColor(Color.GREEN);
            g.fillRect(snakeX[i], snakeY[i], 10, 10);
        }

        g.setColor(Color.RED);
        g.fillRect(appleX, appleY, 10, 10);

        if (gameOver) {
            g.setColor(Color.RED);
            g.drawString("Game Over", width / 2, height / 2);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (snakeX[0] > 0) {
                    for (int i = snakeLength - 1; i >= 0; i--) {
                        snakeY[i + 1] = snakeY[i];
                    }
                    snakeX[0] -= 10;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (snakeX[0] < width - 10) {
                    for (int i = snakeLength - 1; i >= 0; i--) {
                        snakeY[i + 1] = snakeY[i];
                    }
                    snakeX[0] += 10;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (snakeY[0] > 0) {
                    for (int i = snakeLength - 1; i >= 0; i--) {
                        snakeX[i + 1] = snakeX[i];
                    }
                    snakeY[0] -= 10;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (snakeY[0] < height - 10) {
                    for (int i = snakeLength - 1; i >= 0; i--) {
                        snakeX[i + 1] = snakeX[i];
                    }
                    snakeY[0] += 10;
                }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}