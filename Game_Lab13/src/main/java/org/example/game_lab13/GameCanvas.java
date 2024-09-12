package org.example.game_lab13;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class GameCanvas extends Canvas {

    private GraphicsContext gc;
    private Paddle paddle;
    private Ball ball;
    private boolean isGameRunning;
    private long lastUpdateTime;
    private List<Brick> bricks = new ArrayList<>();

    // Statyczne zmienne do przechowywania rozmiarów
    private static double canvasWidth;
    private static double canvasHeight;

    public GameCanvas() {
        gc = this.getGraphicsContext2D();
        canvasWidth = 800;  // Możesz ustawić na właściwe wartości
        canvasHeight = 600;
        setWidth(canvasWidth);
        setHeight(canvasHeight);
        GraphicsItem.setCanvasSize(canvasWidth, canvasHeight);

        Paddle.setCanvasSize(canvasWidth, canvasHeight);
        Brick.setGridSize(20, 10);

        paddle = new Paddle();
        ball = new Ball();

        ball.setPosition(new Point2D(paddle.getX() + paddle.getWidth() / 2, paddle.getY() - ball.getHeight()));

        this.setOnMouseMoved(event -> {
            paddle.moveTo(event.getX());

            if (!isGameRunning) {
                ball.setPosition(new Point2D(paddle.getX() + paddle.getWidth() / 2, paddle.getY() - ball.getHeight()));
            }
        });

        this.setOnMouseClicked(event -> {
            if (!isGameRunning) {
                isGameRunning = true;
                lastUpdateTime = System.nanoTime();
                System.out.println("Game started"); // Debug
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double deltaTime = (now - lastUpdateTime) / 1_000_000_000.0;
                lastUpdateTime = now;
                updateAndDraw(deltaTime);
            }
        };

        timer.start();
        loadLevel();  // Załaduj poziom na start
    }

    private void updateAndDraw(double deltaTime) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, getWidth(), getHeight());

        paddle.draw(gc);
        System.out.println("Drawing paddle"); // Debug

        if (isGameRunning) {
            if (shouldBallBounceHorizontally()) {
                ball.bounceHorizontally();
                System.out.println("Ball bounced horizontally"); // Debug
            }

            if (shouldBallBounceVertically()) {
                ball.bounceVertically();
                System.out.println("Ball bounced vertically"); // Debug
            }

            if (shouldBallBounceFromPaddle()) {
                double ballCenterX = ball.getLeft() + ball.width / 2;
                double paddleCenterX = paddle.getX() + paddle.getWidth() / 2;
                double relativeHitPosition = (ballCenterX - paddleCenterX) / (paddle.getWidth() / 2);
                ball.bounceFromPaddle(relativeHitPosition);
                System.out.println("Ball bounced from paddle"); // Debug
            }

            for (Brick brick : bricks) {
                Brick.CrushType crushType = brick.determineCrushType(ball.getTop(), ball.getBottom(), ball.getLeft(), ball.getRight());

                if (crushType != Brick.CrushType.NoCrush) {
                    bricks.remove(brick);  // Usuwanie cegły po rozbiciu
                    if (crushType == Brick.CrushType.HorizontalCrush) {
                        ball.bounceHorizontally();
                        System.out.println("Ball bounced horizontally after brick crush"); // Debug
                    } else if (crushType == Brick.CrushType.VerticalCrush) {
                        ball.bounceVertically();
                        System.out.println("Ball bounced vertically after brick crush"); // Debug
                    }
                    break;  // Sprawdź inne cegły tylko po odbiciu
                }
            }

            ball.updatePosition(deltaTime);
            System.out.println("Updated ball position"); // Debug
        }

        ball.draw(gc);
        System.out.println("Drawing ball"); // Debug

        for (Brick brick : bricks) {
            brick.draw(gc);
        }
        System.out.println("Drawing bricks"); // Debug
    }

    private boolean shouldBallBounceHorizontally() {
        return ball.getLeft() <= 0 || ball.getRight() >= getWidth();
    }

    private boolean shouldBallBounceVertically() {
        return ball.getTop() <= 0 || ball.getBottom() >= getHeight();
    }

    private boolean shouldBallBounceFromPaddle() {
        return ball.getBottom() >= paddle.getY() &&
                ball.getRight() >= paddle.getX() &&
                ball.getLeft() <= paddle.getX() + paddle.getWidth();
    }

    private void loadLevel() {
        int rows = 20;
        int cols = 10;
        double brickWidth = GameCanvas.getCanvasWidth() / cols;
        double brickHeight = GameCanvas.getCanvasHeight() / rows;

        Color[] rowColors = {
                Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PURPLE
        };

        for (int row = 2; row <= 7; row++) {
            Color color = rowColors[row - 2];
            for (int col = 0; col < cols; col++) {
                Brick brick = new Brick(col, row, color);
                bricks.add(brick);
            }
        }

        System.out.println("Level loaded with " + bricks.size() + " bricks"); // Debug
    }


    public static double getCanvasWidth() {
        return canvasWidth;
    }

    public static double getCanvasHeight() {
        return canvasHeight;
    }
}
