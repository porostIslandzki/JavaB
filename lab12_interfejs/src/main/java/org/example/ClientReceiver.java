package org.example;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientReceiver implements Runnable {
    private final Socket socket;            // Socket do komunikacji z serwerem
    private final BufferedReader reader;    // Strumień do odbierania wiadomości od serwera
    private final PrintWriter writer;       // Strumień do wysyłania wiadomości do serwera
    private final Chat chat;                // Obiekt klasy Chat do obsługi GUI
    private ArrayList<User> users = new ArrayList<>();  // Lista użytkowników

    public ClientReceiver(Socket socket, BufferedReader reader, PrintWriter writer, Chat chat) {
        this.socket = socket;
        this.reader = reader;
        this.writer = writer;
        this.chat = chat;
    }

    // Dodanie nowego użytkownika do listy
    public void addLoggedUser(String login, PrintWriter writer) {
        User user = new User(login, writer);
        users.add(user);
    }

    @Override
    public void run() {
        try {
            String message;
            // Nasłuchiwanie na wiadomości od serwera
            while ((message = reader.readLine()) != null) {
                if (message.startsWith("USERLIST")) {
                    // Odbieranie listy użytkowników od serwera
                    String[] users = message.split(" ");
                    chat.updateUserList(users);  // Aktualizacja listy użytkowników w GUI
                } else if (message.startsWith("LOGIN")) {
                    // Obsługa logowania nowego użytkownika
                    String login = message.split(" ")[1];
                    chat.receiveMessage(login + " has joined the chat!");
                    chat.updateUserList(users.toArray(new String[0]));
                } else if (message.startsWith("LOGOUT")) {
                    // Obsługa wylogowania użytkownika
                    String login = message.split(" ")[1];
                    chat.receiveMessage(login + " has left the chat.");
                    chat.updateUserList(users.toArray(new String[0]));
                } else {
                    // Odbieranie wiadomości typu broadcast
                    chat.receiveMessage(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Wysyłanie informacji o logowaniu do serwera
    public void loginBroadcast(String login) {
        writer.println("LOGIN " + login);
    }
}
