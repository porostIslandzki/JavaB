package org.example.lab13_gra;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {

        // Tworzenie obiektu GameCanvas
        GameCanvas gameCanvas = new GameCanvas();
        gameCanvas.setWidth(800);
        gameCanvas.setHeight(600);

        // Wywołanie metody draw, aby wypełnić kanwę na czarno
        gameCanvas.draw();

        // Tworzenie kontenera i dodanie do niego GameCanvas
        StackPane root = new StackPane(); // Kontener dla canvas
        root.getChildren().add(gameCanvas); // Dodanie canvas do kontenera

        // Ustawienia sceny i wyświetlenie okna
        primaryStage.setTitle("Game");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
