package org.example.lab13_gra;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//Napisz klasę Paddle, która dziedziczy po GraphicsItem i
// reprezentuje platformę, od której odbija się piłka.
public class Paddle extends GraphicsItem{

    //dziedziczyć po klasie GraphicsItem, co oznacza, że
    // będzie miała dostęp do pól i metod tej klasy, takich
    // jak x, y, width, height oraz draw().


    public Paddle() {
        x = 320; //pozycja pozioma, wyśrodkowana
        y = 550; //pozycja pionowa, blisko dolnej krawędzi, z marginesem
        width = 160; //szerokość platformy
        height = 30; //wysokość platformy
    }

    // Metoda aktualizująca pozycję platformy na podstawie pozycji myszy
    public void updatePosition(double mouseX) {
        // Ustawiamy x-ową składową platformy, tak aby jej środek znajdował się
        // na pozycji przesunięcia myszy
        x = (float) (mouseX - width / 2);

        // Upewnij się, że platforma nie wychodzi poza granice kanwy
        if (x < 0) {
            x = 0; // Nie wyjdzie poza lewą krawędź
        }
        if (x + width > canvasWidth) {
            x = canvasWidth - width; // Nie wyjdzie poza prawą krawędź
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        //w tej metodzie, używając obiektu GraphicsContext,
        //rysuj prostokąt na kanwie w pozycji, która odpowiada
        //współrzędnym platformy (x,y) z odpowiednią szerokością
        //i wysokością
        gc.setFill(Color.BEIGE);
        gc.fillRect(x, y, width, height);
    }
}
