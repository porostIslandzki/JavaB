package org.example.lab12_jeszczeraz;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientReceiver implements Runnable {

    private final Socket socket;
    private final BufferedReader reader;
    private final PrintWriter writer;
    private final HelloApplication server;
    private ArrayList<LogUser> users = new ArrayList<>();

    // Konstruktor ClientReceiver
    public ClientReceiver(Socket socket, HelloApplication server) throws IOException {
        this.socket = socket;
        this.server = server;

        // Pobieramy strumienie wejściowy i wyjściowy z gniazda
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        // Tworzymy obiekt BufferedReader do odczytu danych od klienta
        reader = new BufferedReader(new InputStreamReader(input));

        // Tworzymy obiekt PrintWriter do wysyłania danych do klienta
        writer = new PrintWriter(output, true);
    }

    public void whisper(String receiveUserLogin, String message) {
        for (LogUser user : users) {
            if (user.getUsername().equals(receiveUserLogin)) {
                user.send(message);
            }
        }
    }

    public void loginBroadcast(String username) {
        // Ogłoszenie zalogowania się użytkownika
        for (LogUser user : users) {
            user.send("LOGIN BROADCAST: " + username + " has joined the chat!");
        }
    }

    public void logoutBroadcast(String username) {
        // Ogłoszenie wylogowania się użytkownika
        for (LogUser user : users) {
            user.send("LOGOUT BROADCAST: " + username + " has left the chat.");
        }
    }

    public void online() {
        // Wyświetlenie listy aktualnie zalogowanych użytkowników
        StringBuilder onlineUsers = new StringBuilder("ONLINE USERS: ");
        for (LogUser user : users) {
            onlineUsers.append(user.getUsername()).append(", ");
        }
        // Usunięcie ostatniego przecinka i spacji
        if (onlineUsers.length() > 0) {
            onlineUsers.setLength(onlineUsers.length() - 2);
        }
        writer.println(onlineUsers.toString());
    }

    public void file(String fileName) {
        // Obsługa wysyłania plików
        writer.println("FILE TRANSFER: Sending file " + fileName);
        // Tutaj można dodać logikę przesyłania plików
    }

    @Override
    public void run() {
        String received;
        while (true) {
            try {
                if ((received = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (received.startsWith("BROADCAST")) {
                String[] messages = received.split(" ", 2);
                for (LogUser user : users) {
                    user.send(messages[1]);
                }
            } else if (received.startsWith("WHISPER")) {
                String[] messages = received.split(" ", 3);
                whisper(messages[1], messages[2]);
            } else if (received.startsWith("LOGIN BROADCAST")) {
                String[] messages = received.split(" ", 2);
                loginBroadcast(messages[1]);
            } else if (received.startsWith("LOGOUT BROADCAST")) {
                String[] messages = received.split(" ", 2);
                logoutBroadcast(messages[1]);
            } else if (received.startsWith("ONLINE")) {
                online();
            } else if (received.startsWith("FILE")) {
                String[] messages = received.split(" ", 2);
                file(messages[1]);
            }
        }
    }
}

//Stwórz klasę o nazwie ClientReceiver.
// Zdefiniuj w niej funkcje odpowiadające
// wszystkim dotychczas obsługiwanym działaniom
// (login broadcast, logout
// broadcast, online, file). W wątku obsługującym
// połączenie z serwerem, póki co, obsłuż broadcast.
//Wiadomość rozróżnij na podstawie prefiksu.

//broadcast – ta funkcja odnosi się do wysyłania wiadomości
// lub komunikatów do wszystkich użytkowników. W kontekście
// serwera lub aplikacji, gdy administrator lub system wyśle
// wiadomość, zostanie ona wyemitowana (rozgłoszona) do
// wszystkich użytkowników.

//whisper – jest to funkcja służąca do wysyłania
// prywatnych wiadomości pomiędzy dwoma użytkownikami.
// Inni użytkownicy nie widzą tej komunikacji.

//login broadcast – jest to funkcja rozgłaszania informacji o
// logowaniu. Gdy użytkownik loguje się do systemu, jego
// logowanie może być ogłoszone wszystkim innym użytkownikom.

//logout – funkcja wylogowania, która usuwa użytkownika z sesji.
// Może też działać w połączeniu z broadcastem, aby poinformować
// innych, że użytkownik się wylogował.