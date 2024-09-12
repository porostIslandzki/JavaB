package org.example.game_lab13;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Paddle extends GraphicsItem {

    public Paddle() {
        // Inicjalizuj położenie platformy
        this.width = 100;
        this.height = 20;
        this.x = GameCanvas.getCanvasWidth() / 2 - width / 2;
        this.y = GameCanvas.getCanvasHeight() - height - 10;
    }

    public void moveTo(double xPosition) {
        this.x = xPosition - width / 2;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GRAY);
        gc.fillRect(x, y, width, height);
    }
}
