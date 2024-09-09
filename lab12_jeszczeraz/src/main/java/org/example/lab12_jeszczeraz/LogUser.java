package org.example.lab12_jeszczeraz;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class LogUser implements Runnable {
    private final Socket socket;
    private final BufferedReader reader; // do odczytywania wiadomości wysłanych przez serwer
    private final PrintWriter writer; // do wysyłania wiadomości do serwera
    private final String username;

    public LogUser(String address, int port, String username) throws IOException {
        this.socket = new Socket(address, port);
        this.username = username;

        // Klient musi być zdolny do odbierania wiadomości, które serwer rozsyła do wszystkich połączonych klientów.
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();
        reader = new BufferedReader(new InputStreamReader(input));
        writer = new PrintWriter(output, true);
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String message) {
        writer.println(message);
    }
}

//Zaprogramuj logowanie użytkownika do czatu, korzystając z
// wcześniej napisanej metody logowania klienta.