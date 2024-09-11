package org.example.lab13_gra;

import javafx.scene.canvas.GraphicsContext;

public abstract class GraphicsItem {

    protected static float canvasWidth;
    protected static float canvasHeight;

    public static void setCanvasWidth(float canvasWidth) {
        GraphicsItem.canvasWidth = canvasWidth;
    }

    public static void setCanvasHeight(float canvasHeight) {
        GraphicsItem.canvasHeight = canvasHeight;
    }

    protected float x;
    protected float y;
    protected float width;
    protected float height;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public abstract void draw(GraphicsContext gc);
}
