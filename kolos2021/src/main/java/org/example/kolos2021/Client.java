package org.example.kolos2021;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {
    private final Socket socket;
    private final PrintWriter writer; // do wysyłania wiadomości do serwera

    public Client(String address, int port) throws IOException {
        socket = new Socket(address, port);
        OutputStream output = socket.getOutputStream();
        writer = new PrintWriter(output, true);
    }

    @Override
    public void run() {
        //logika wykonywana w tle?
    }

    // Metoda do wysyłania 6-cyfrowego koloru w formacie szesnastkowym
    public void sendHex16(String hexColor) {
        if (hexColor.matches("[0-9A-Fa-f]{6}")) {  // Sprawdza, czy kolor jest w formacie 6-cyfrowym
            writer.println(hexColor);
        } else {
            System.out.println("Błędny format koloru! Wprowadź 6-cyfrową liczbę szesnastkową.");
        }
    }

    // Metoda do wysyłania współrzędnych x1, y1, x2, y2
    public void sendParameters(float x1, float y1, float x2, float y2) {
        String parameters = x1 + " " + y1 + " " + x2 + " " + y2;
        writer.println(parameters);
    }

    public void close() throws IOException {
        writer.close();
        socket.close();
    }
}


//Każdy z klientów może wysłać dwa rodzaje wiadomości:
//pojedynczą, sześciocyfrową liczbę szesnastkową oznaczającą
// kolor (https://en.wikipedia.org/wiki/Web_colors),
//cztery liczby zmiennoprzecinkowe oddzielone spacjami,
// oznaczające współrzędne x, y dwóch punktów ograniczających
// odcinek.