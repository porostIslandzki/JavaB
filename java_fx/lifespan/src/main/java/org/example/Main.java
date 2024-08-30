package org.example;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage; //Hauptfenster
import javafx.scene.Scene; //Nächste bauteil solcher javafx anwendung
import javafx.scene.layout.*; //nie jest konieczna
import javafx.scene.control.*; //Button, Label, Tabellen etc.

//1. init
//2. start
//3. stop

public class Main extends Application{

    //1. init
    @Override
    public void init() throws Exception {
        System.out.println("Init methode");
    }
    //2. start
    @Override
    public void start(Stage primaryStage) {
        try {
            System.out.println("Start methode");
            BorderPane root = new BorderPane();

            Button button = new Button("Beenden");
            root.setCenter(button);
            //Button zum root hinzufugen
            //oot.getChildren().add(button);

            //Event handler
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Platform.exit(); //kiedy klikasz na beenden to sie zamyka
                }
            });

            Scene scene = new Scene(root, 400, 400);
           // scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //3. stop
    public void stop() throws Exception {
        System.out.println("Stop methode");
    }

    public static void main(String[] args) { //ist nicht so wichtig fur javafx
        Application.launch(args);
    }
}