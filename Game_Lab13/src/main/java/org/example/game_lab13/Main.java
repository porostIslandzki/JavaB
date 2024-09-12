package org.example.game_lab13;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Brick Breaker Game");

        // Tworzymy obiekt GameCanvas
        GameCanvas gameCanvas = new GameCanvas();

        // Ustawiamy układ i dodajemy GameCanvas
        BorderPane root = new BorderPane();
        root.setCenter(gameCanvas);

        // Tworzymy scenę i dodajemy ją do primaryStage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);

        // Pokazujemy okno
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
