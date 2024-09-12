package org.example.kolos2021_wersjacalosc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class HelloApplication extends Application {

    private MyCanvas canvas;
    private double offsetX = 0;
    private double offsetY = 0;

    // Lista przechowująca wszystkie odcinki
    private final List<LineSegment> lineSegments = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        canvas = new MyCanvas();
        canvas.setWidth(500);
        canvas.setHeight(500);

        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Drawing Canvas");

        // Obsługa przesunięć strzałkami
        scene.setOnKeyPressed(this::handleKeyPress);

        // Połączenie z serwerem
        Socket socket = new Socket("localhost", 3000); // Połącz z serwerem na porcie 3000
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        // Tworzenie obiektu do odbierania wiadomości od serwera
        new Thread(new ClientHandler(socket, reader, writer, this)).start();

        stage.show();
    }

    // Dodaj nowy odcinek do listy
    public void addLineSegment(double x1, double y1, double x2, double y2, Color color) {
        lineSegments.add(new LineSegment(x1, y1, x2, y2, color));
        canvas.draw(lineSegments, offsetX, offsetY); // Odśwież rysowanie
    }

    // Obsługa przesunięć strzałkami
    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP -> offsetY -= 10;
            case DOWN -> offsetY += 10;
            case LEFT -> offsetX -= 10;
            case RIGHT -> offsetX += 10;
        }

        // Po przesunięciu ponownie narysuj odcinki z nowym przesunięciem
        canvas.draw(lineSegments, offsetX, offsetY);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

//Należy jednocześnie uruchomić serwer nasłuchujący
// na wybranym porcie oraz wyświetlić okno służące do
// rysowania grafiki 2D (np. javafx.scene.canvas.Canvas).

