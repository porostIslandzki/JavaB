package org.example.lab13_gra;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//Utwórz obiekt GameCanvas dziedziczący po javafx.scene.canvas.Canvas.
// W klasie tej znajdzie się logika i wyświetlanie pisanej gry
public class GameCanvas extends Canvas {

    //W klasie GameCanvas utwórz prywatny obiekt klasy
    // javafx.scene.canvas.GraphicsContex, który
    //odpowiada za rysowanie na kanwie
    private GraphicsContext gc;
    private Paddle paddle;

    //obiekt należy zainicjować w konstruktorze
    public GameCanvas() {
        gc = this.getGraphicsContext2D();
        //pobiera kontekst graficzny z kanwy za pomocą metody
        //getGraphicsContext2D()

        // Inicjalizacja platformy
        paddle = new Paddle(); // Inicjalizacja obiektu Paddle

        // Przechwytywanie zdarzenia przesunięcia myszy
        this.setOnMouseMoved(event -> {
            // Aktualizowanie pozycji platformy na podstawie ruchu myszy
            double mouseX = event.getX();
            paddle.updatePosition(mouseX); // Wywołanie metody w klasie Paddle
            draw(); // Odrysowanie kanwy po przesunięciu myszy
        });
    }

    //utwórz publiczną metodę draw(), która będzie odpowiadała
    //za rysowanie na kanwie. W tej metodzie wypełnij całą
    // powierzchnię kanwy kolorem czarnym przy użyciu obiektu gc.

    public void draw(){
        gc.setFill(Color.BLACK); //wybieramy, że powierzchnia kanwy
        //będzie czarna
        gc.fillRect(0, 0, getWidth(), getHeight());

        // Rysowanie platformy
        paddle.draw(gc);
    }

    //W klasie GameCanvas przechwyć zdarzenie przesunięcia myszy.
    // W klasie Paddle napisz metodę, która przyjmie x-ową składową
    // zdarzenia i ustawi platformę tak, aby jej środek znajdował
    // się pozycji tej składowej.
}
