package org.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;

public class Main extends Application {

    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
    @Override
    public void start(Stage primaryStage) {
        try {
            //Węzeł root - layout sceny
            HBox root = new HBox(); //elementy ustawione poziomo
            //Chcę zmniejszyć scenę tak, żeby zostały same przyciski
            root.setPrefSize(340.0d, 70.0d);
            root.setSpacing(10.0d);
            root.setAlignment(Pos.CENTER); //wszystkie przyciski położone w centrum

            //Wprowadzenie elementów typu przyciski
            Button button1 = new Button();
            Button button2 = new Button();
            Button button3 = new Button();
            Button button4 = new Button();
            Button button5 = new Button();

            //Dodanie klas CSS do elementów
            root.getStyleClass().add("root");
            button1.getStyleClass().add("button");
            button2.getStyleClass().add("button");
            button3.getStyleClass().add("button");
            button4.getStyleClass().add("button");
            button5.getStyleClass().add("button");


            //Wciśnięcie elementów do węzła root
            root.getChildren().add(button1);
            root.getChildren().add(button2);
            root.getChildren().add(button3);
            root.getChildren().add(button4);
            root.getChildren().add(button5);

            //Chcę dać każdemu z przycisków określoną wielkość
            button1.setPrefSize(64.0d, 64.0d);
            button2.setPrefSize(64.0d, 64.0d);
            button3.setPrefSize(64.0d, 64.0d);
            button4.setPrefSize(64.0d, 64.0d);
            button5.setPrefSize(64.0d, 64.0d);

            //Dodanie obrazków na przyciski
            // Ustawienie obrazków na przyciski
            Image image1 = new Image(getClass().getResourceAsStream("/images/facebook.png"));
            Image image2 = new Image(getClass().getResourceAsStream("/images/youtube.png"));
            Image image3 = new Image(getClass().getResourceAsStream("/images/twitter.png"));
            Image image4 = new Image(getClass().getResourceAsStream("/images/amazon.png"));
            Image image5 = new Image(getClass().getResourceAsStream("/images/exit.png"));

            button1.setGraphic(new ImageView(image1));
            button2.setGraphic(new ImageView(image2));
            button3.setGraphic(new ImageView(image3));
            button4.setGraphic(new ImageView(image4));
            button5.setGraphic(new ImageView(image5));

            //Eventhandling żeby dało się klikać elementy
            button1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    getHostServices().showDocument("https://www.facebook.com/");
                }
            });
            button2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    getHostServices().showDocument("https://www.youtube.com/");
                }
            });
            button3.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    getHostServices().showDocument("https://www.x.com/");
                }
            });
            button4.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    getHostServices().showDocument("https://www.amazon.com/");
                }
            });
            button5.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    System.exit(0);
                }
            });

            //Config sceny
            Scene scene = new Scene(root, 400, 200);
            scene.setFill(Color.TRANSPARENT);
            scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setX(screenSize.getWidth() / 2 - 280); //Pozycja X
            primaryStage.setY(screenSize.getHeight() / 2 - 280); //Pozycja Y
            primaryStage.initStyle(StageStyle.TRANSPARENT); //żeby można było ją przenosić
            primaryStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
