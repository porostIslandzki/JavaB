package org.example.kolos2021;

import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class ClientReceiver implements Runnable {
    private final Socket socket;
    private final BufferedReader reader;
    private final MyCanvas canvas;

    public ClientReceiver(Socket socket, BufferedReader reader, MyCanvas canvas) {
        this.socket = socket;
        this.reader = reader;
        this.canvas = canvas;
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = reader.readLine()) != null) {
                // Przetwarzanie wiadomości
                processMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processMessage(String message) {
        try {
            // Oczekujemy wiadomości w formacie: kolor lub współrzędne
            if (message.matches("[0-9A-Fa-f]{6}")) {
                // Przykład koloru w formacie szesnastkowym
                Color color = Color.web("#" + message);
                canvas.drawLine(100, 100, 200, 200, color); // Rysowanie linii o kolorze
            } else {
                String[] parts = message.split(" ");
                if (parts.length == 4) {
                    // Współrzędne x1, y1, x2, y2
                    float x1 = Float.parseFloat(parts[0]);
                    float y1 = Float.parseFloat(parts[1]);
                    float x2 = Float.parseFloat(parts[2]);
                    float y2 = Float.parseFloat(parts[3]);

                    // Ustaw domyślny kolor linii
                    canvas.drawLine(x1, y1, x2, y2, Color.BLACK);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
