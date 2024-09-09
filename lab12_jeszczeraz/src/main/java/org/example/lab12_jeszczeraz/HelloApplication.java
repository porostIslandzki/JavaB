package org.example.lab12_jeszczeraz;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private TextArea textArea; //wieloliniowe pole
    // tekstowe z zapisem czatu
    private TextField textField; //pole tekstowe do
    // wprowadzania nowej wiadomości
    private ListView listView; //lista aktywnych
    // uczestników czatu
    private Button button; //do wysyłania wiadomości.

    private void send(){
        //jego naciśnięcie powodowało dopisanie wiadomości z
        //obiektu klasy TextField do TextArea i wyczyszczenie
        // zawartości obiektu klasy TextField
        String text = textField.getText();
        textArea.appendText(text);
        textField.clear();
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Komunikator");
        button = new Button("Send");
        button.setOnAction(event -> send()); //przycisk do wysyłania wiadomości

        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            // Analogicznie powinno działać naciśnięcie entera w polu wiadomości.
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER){
                    String text = textField.getText();
                    textArea.appendText(text);
                    textField.clear();
                }
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}

