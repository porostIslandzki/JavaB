package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Chat extends Application {
    private TextArea saveChat;      // Pole do wyświetlania historii wiadomości (czat)
    private TextField typeMessage;  // Pole do wpisywania wiadomości przez użytkownika
    private TextField typeLogin;    // Pole do wpisywania loginu użytkownika
    private ListView<String> activeUsers;  // Lista aktywnych użytkowników (ListView)
    private Button sendMessage;     // Przycisk do wysyłania wiadomości
    private PrintWriter writer;     // Strumień do wysyłania danych do serwera
    private String userLogin = "";  // Przechowuje login aktualnie zalogowanego użytkownika
    private ClientReceiver clientReceiver; // Obiekt odbierający wiadomości od serwera (w osobnym wątku)

    @Override
    public void start(Stage stage) {
        stage.setTitle("Chat Application");

        // Inicjalizacja elementów GUI
        saveChat = new TextArea();              // Pole tekstowe do wyświetlania czatu
        typeMessage = new TextField();          // Pole tekstowe do wpisywania wiadomości
        typeLogin = new TextField();            // Pole do logowania użytkownika
        activeUsers = new ListView<>();         // Lista aktywnych użytkowników
        sendMessage = new Button("Send");       // Przycisk wysyłania wiadomości

        // Ustawienie akcji dla przycisku "Send"
        sendMessage.setOnAction(event -> sendMessageToServer());  // Wysyłanie wiadomości po kliknięciu przycisku

        // Nasłuchiwanie na Enter w polu wiadomości
        typeMessage.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {  // Jeżeli użytkownik naciśnie Enter
                sendMessageToServer();  // Wysyłanie wiadomości po naciśnięciu Enter
            }
        });

        // Nasłuchiwanie na Enter w polu logowania
        typeLogin.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {  // Jeżeli użytkownik naciśnie Enter
                logUser();  // Logowanie użytkownika
            }
        });

        // Ustawienia GUI
        saveChat.setEditable(false);     // Pole czatu nie może być edytowane przez użytkownika
        activeUsers.setPrefWidth(150);   // Szerokość listy aktywnych użytkowników
        saveChat.setPrefHeight(200);     // Wysokość pola czatu

        // Ustawienie układu elementów na siatce (GridPane)
        GridPane pane = new GridPane();
        pane.add(saveChat, 0, 0, 3, 2);  // Pole czatu (zajmuje 3 kolumny i 2 wiersze)
        pane.add(new Label("Type message: "), 0, 2);  // Etykieta dla pola wiadomości
        pane.add(typeMessage, 1, 2, 2, 1);  // Pole do wpisywania wiadomości (zajmuje 2 kolumny)
        pane.add(sendMessage, 2, 3);  // Przycisk "Send"
        pane.add(activeUsers, 3, 0, 1, 3);  // Lista aktywnych użytkowników (zajmuje 3 wiersze)
        pane.add(new Label("Login: "), 0, 3);  // Etykieta dla pola logowania
        pane.add(typeLogin, 1, 3, 1, 1);  // Pole logowania

        // Tworzenie sceny aplikacji
        Scene scene = new Scene(pane, 500, 300);  // Ustawienie rozmiaru okna aplikacji (500x300 pikseli)
        stage.setScene(scene);  // Dodanie sceny do okna aplikacji
        stage.show();  // Wyświetlenie okna

        // Inicjalizacja połączenia z serwerem
        try {
            Socket socket = new Socket("localhost", 3000);  // Połączenie z serwerem na lokalnym hoście (port 3000)
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));  // Strumień do odbierania wiadomości od serwera
            writer = new PrintWriter(socket.getOutputStream(), true);  // Strumień do wysyłania wiadomości do serwera (z auto-flush)

            // Tworzenie obiektu `ClientReceiver` do odbierania wiadomości od serwera (w osobnym wątku)
            clientReceiver = new ClientReceiver(socket, reader, writer, this);
            Thread receiverThread = new Thread(clientReceiver);  // Tworzenie nowego wątku dla ClientReceiver
            receiverThread.start();  // Uruchomienie wątku, który będzie nasłuchiwał wiadomości od serwera

        } catch (Exception e) {
            e.printStackTrace();  // Wyświetlanie błędów, jeżeli połączenie się nie powiedzie
        }
    }

    // Logowanie użytkownika do serwera
    public void logUser() {
        String login = typeLogin.getText().trim();  // Pobieranie i usuwanie białych znaków z loginu
        if (!login.isEmpty()) {  // Jeżeli login nie jest pusty
            userLogin = login;  // Przypisanie loginu do zmiennej `userLogin`
            clientReceiver.loginBroadcast(login);  // Wysyłanie informacji o logowaniu do serwera
            activeUsers.getItems().add(userLogin);  // Dodanie użytkownika do listy aktywnych
            saveChat.appendText("Logged in as: " + userLogin + "\n");  // Wyświetlenie informacji na czacie
            writer.println("REQUEST_USERS"); // Żądanie aktualnej listy użytkowników
            typeLogin.clear();  // Czyszczenie pola logowania
        }
    }

    public void sendMessageToServer() {
        if (writer != null && !typeMessage.getText().isEmpty()) {  // Sprawdź, czy writer nie jest null
            String message = typeMessage.getText();  // Pobieranie wiadomości
            writer.println("BROADCAST " + message);  // Wysyłanie wiadomości do serwera z prefiksem "BROADCAST"
            typeMessage.clear();  // Czyszczenie pola wiadomości po wysłaniu
        } else if (writer == null) {
            saveChat.appendText("Connection to the server is not established.\n");
        }
    }


    // Metoda wyświetlająca wiadomości od serwera w GUI (Chat)
    public void receiveMessage(String message) {
        saveChat.appendText(message + "\n");  // Dodanie wiadomości do pola tekstowego (czatu)
    }

    // Aktualizacja listy użytkowników na podstawie wiadomości od serwera
    public void updateUserList(String[] users) {
        activeUsers.getItems().clear();  // Czyszczenie obecnej listy użytkowników
        for (String user : users) {
            activeUsers.getItems().add(user);  // Dodanie każdego użytkownika z serwera
        }
    }

    public static void main(String[] args) {
        launch(args);  // Uruchomienie aplikacji JavaFX
    }
}
