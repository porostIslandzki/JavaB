package org.example;
import javafx.application.Application;
import javafx.stage.Stage; //Hauptfenster
import javafx.scene.Scene; //Nächste bauteil solcher javafx anwendung
import javafx.scene.layout.*; //nie jest konieczna
import javafx.scene.control.*; //Button, Label, Tabellen etc.

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        //start Methode - Gibt den Startpunkt einer JavaFx Anwendung an
        //1. Fenster
        Pane root = new Pane(); //Layout Container

        Button button = new Button("OK");
        root.getChildren().add(button);

        Scene scene = new Scene(root, 400, 400);

        //die scene mussen wir stage ubergeben
        primaryStage.setScene(scene);
        primaryStage.show();

        //2. Fenster
        Stage stage = new Stage();

        StackPane root2 = new StackPane();
        Label label = new Label("Wilkommen");

        root2.getChildren().add(label);

        Scene scene2 = new Scene(root2, 200, 200);

        stage.setScene(scene2);
        stage.show(); //zwei Fenster

    }

    public static void main(String[] args) { //ist nicht so wichtig fur javafx
        Application.launch(args);
    }
}