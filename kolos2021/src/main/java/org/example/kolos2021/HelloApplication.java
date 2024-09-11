package org.example.kolos2021;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HelloApplication extends Application {
    private ClientReceiver clientReceiver;

    @Override
    public void start(Stage stage) throws Exception {
        MyCanvas canvas = new MyCanvas();
        canvas.setWidth(500);
        canvas.setHeight(500);

        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        stage.setScene(new Scene(root));
        stage.setTitle("Drawing Canvas");
        stage.show();

        // Połączenie z serwerem
        Socket socket = new Socket("localhost", 3007);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        // Tworzenie obiektu do odbierania wiadomości
        clientReceiver = new ClientReceiver(socket, reader, canvas);
        new Thread(clientReceiver).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
