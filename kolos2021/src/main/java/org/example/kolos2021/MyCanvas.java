package org.example.kolos2021;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyCanvas extends Canvas {

    private GraphicsContext gc;

    public MyCanvas() {
        gc = this.getGraphicsContext2D();
        //pobiera kontekst graficzny z kanwy za pomocą metody
        //getGraphicsContext2D()
        draw(); //Wywołanie metody rysującej tło
    }

    public void draw(){
        gc.setFill(Color.WHITE); //wypełnienie tła kolorem białym
        gc.fillRect(0, 0, getWidth(), getHeight());
    }

    // Metoda do rysowania linii o określonym kolorze
    public void drawLine(float x1, float y1, float x2, float y2, Color color) {
        gc.setStroke(color); // Ustawienie koloru linii
        gc.setLineWidth(2); // Ustawienie grubości linii
        gc.strokeLine(x1, y1, x2, y2); // Rysowanie linii
    }
}
