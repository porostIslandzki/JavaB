package org.example.game_lab13;

import javafx.scene.canvas.GraphicsContext;

public abstract class GraphicsItem {
    protected double x, y, width, height;
    protected static double canvasWidth, canvasHeight;

    public static void setCanvasSize(double width, double height) {
        canvasWidth = width;
        canvasHeight = height;
    }

    public abstract void draw(GraphicsContext gc);

    // Akcesory do pól x, y, width i height
    public double getX() { return x; }
    public double getY() { return y; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
}
