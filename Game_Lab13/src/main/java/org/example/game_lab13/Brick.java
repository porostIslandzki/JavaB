package org.example.game_lab13;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Brick extends GraphicsItem {

    public enum CrushType { NoCrush, HorizontalCrush, VerticalCrush }

    private static int gridRows;
    private static int gridCols;
    private Color color;  // Dodanie pola color

    public static void setGridSize(int rows, int cols) {
        gridRows = rows;
        gridCols = cols;
    }

    public Brick(int col, int row, Color color) {
        this.width = GameCanvas.getCanvasWidth() / gridCols;
        this.height = GameCanvas.getCanvasHeight() / gridRows;
        this.x = col * width;
        this.y = row * height;
        this.color = color;  // Inicjalizacja koloru cegły
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);  // Użycie koloru cegły
        gc.fillRect(x, y, width, height);
        gc.setFill(Color.BLACK);
        gc.strokeRect(x, y, width, height);
    }

    public CrushType determineCrushType(double ballTop, double ballBottom, double ballLeft, double ballRight) {
        if (ballBottom < y || ballTop > y + height || ballRight < x || ballLeft > x + width) {
            return CrushType.NoCrush;
        }

        boolean verticalOverlap = ballRight > x && ballLeft < x + width;
        boolean horizontalOverlap = ballBottom > y && ballTop < y + height;

        if (verticalOverlap && horizontalOverlap) {
            return (ballBottom < y + height / 2) ? CrushType.HorizontalCrush : CrushType.VerticalCrush;
        }

        return CrushType.NoCrush;
    }
}
