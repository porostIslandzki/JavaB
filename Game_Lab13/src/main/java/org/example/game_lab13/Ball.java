package org.example.game_lab13;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends GraphicsItem {

    private Point2D moveVector;
    private double velocity;

    public Ball() {
        this.velocity = 600.0;  // Prędkość piłki
        this.moveVector = new Point2D(1, 1).normalize();  // Wektor ruchu pod kątem 45 stopni
        this.width = 20;  // Średnica piłki
        this.height = 20;
    }

    public void setPosition(Point2D position) {
        this.x = position.getX() - width / 2;
        this.y = position.getY() - height / 2;
    }

    public void updatePosition(double deltaTime) {
        this.x += moveVector.getX() * velocity * deltaTime;
        this.y += moveVector.getY() * velocity * deltaTime;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillOval(x, y, width, height);
    }

    public void bounceHorizontally() {
        moveVector = new Point2D(-moveVector.getX(), moveVector.getY());
    }

    public void bounceVertically() {
        moveVector = new Point2D(moveVector.getX(), -moveVector.getY());
    }

    public void bounceFromPaddle(double relativeHitPosition) {
        double angle = Math.toRadians(45 * relativeHitPosition);
        moveVector = new Point2D(Math.cos(angle), -Math.sin(angle)).normalize();
    }

    public double getTop() {
        return y;
    }

    public double getBottom() {
        return y + height;
    }

    public double getLeft() {
        return x;
    }

    public double getRight() {
        return x + width;
    }
}
