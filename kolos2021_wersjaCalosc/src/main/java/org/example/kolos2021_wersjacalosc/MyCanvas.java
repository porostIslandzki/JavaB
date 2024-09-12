package org.example.kolos2021_wersjacalosc;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class MyCanvas extends Canvas {

    private GraphicsContext gc;
    private List<LineSegment> lineSegments = new ArrayList<>();  // Lista przechowująca odcinki
    private double offsetX = 0;  // Przesunięcie na osi X
    private double offsetY = 0;  // Przesunięcie na osi Y

    public MyCanvas() {
        gc = this.getGraphicsContext2D();
        drawBackground();  // Rysowanie początkowego tła

        // Dodanie obsługi klawiatury dla przesunięcia
        this.setFocusTraversable(true);  // Ustawiamy fokus na kanwie, aby mogła odbierać zdarzenia klawiatury
        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                offsetX -= 10;  // Przesunięcie w lewo
            } else if (event.getCode() == KeyCode.RIGHT) {
                offsetX += 10;  // Przesunięcie w prawo
            } else if (event.getCode() == KeyCode.UP) {
                offsetY -= 10;  // Przesunięcie w górę
            } else if (event.getCode() == KeyCode.DOWN) {
                offsetY += 10;  // Przesunięcie w dół
            }
            redraw();  // Przerysowanie wszystkich odcinków z nowym przesunięciem
        });
    }

    // Metoda do rysowania tła
    private void drawBackground() {
        gc.setFill(Color.WHITE);  // Wypełnienie kanwy białym kolorem
        gc.fillRect(0, 0, getWidth(), getHeight());
    }

    // Dodanie nowego odcinka do listy i przerysowanie
    public void addLine(double x1, double y1, double x2, double y2, Color color) {
        lineSegments.add(new LineSegment(x1, y1, x2, y2, color));  // Dodanie odcinka do listy
        redraw();  // Przerysowanie kanwy
    }

    // Przerysowanie wszystkich odcinków z uwzględnieniem przesunięcia
    private void redraw() {
        drawBackground();  // Wyczyszczenie kanwy przed ponownym rysowaniem
        for (LineSegment line : lineSegments) {
            drawLineWithOffset(line);
        }
        drawOffsetLabel();  // Wyświetlenie aktualnego przesunięcia
    }

    // Rysowanie pojedynczego odcinka z uwzględnieniem przesunięcia
    private void drawLineWithOffset(LineSegment line) {
        gc.setStroke(line.getColor());  // Ustawienie koloru odcinka
        gc.setLineWidth(2);
        gc.strokeLine(line.getX1() + offsetX, line.getY1() + offsetY, line.getX2() + offsetX, line.getY2() + offsetY);  // Rysowanie z przesunięciem
    }

    // Wyświetlanie aktualnego przesunięcia w tytule okna
    private void drawOffsetLabel() {
        gc.setFill(Color.BLACK);
        gc.fillText("Offset: (" + offsetX + ", " + offsetY + ")", 10, 10);  // Rysowanie tekstu na kanwie
    }

    // Klasa pomocnicza reprezentująca odcinek
    private static class LineSegment {
        private final double x1, y1, x2, y2;
        private final Color color;

        public LineSegment(double x1, double y1, double x2, double y2, Color color) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
        }

        public double getX1() {
            return x1;
        }

        public double getY1() {
            return y1;
        }

        public double getX2() {
            return x2;
        }

        public double getY2() {
            return y2;
        }

        public Color getColor() {
            return color;
        }
    }
}
//Klasa odpowiedzialna za grafikę
//GraphicsContext do rysowania na kanwie
//drawLine
//Obsługa przesunięcia układu