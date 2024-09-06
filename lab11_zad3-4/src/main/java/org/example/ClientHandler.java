package org.example;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final BufferedReader reader;
    private final PrintWriter writer;
    private final Serwer server;
    private String login; //dodajemy pole, które będzie przechowywało
    //login użytkownika

    public ClientHandler(Socket socket, Serwer server) throws IOException {
        this.socket = socket;
        this.server = server;

        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        reader = new BufferedReader(new InputStreamReader(input));

        writer = new PrintWriter(output, true);
    }

    public void send(String message){
        writer.println(message);
    }

    private void close() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error closing socket: " + e.getMessage());
        }
        server.removeHandler(this);
    }

    //dadajemy getter do loginu, żeby móc napisać
    // metodę w klasie serwer sprawdzającą unikalność loginu
    public String getLogin() {
        return login;
    }

    //metoda pobiera listę aktualnie zalogowanych użytkowników od
    //serwera i wysyłać ją do klienta, który zażądał tej informacji
    private void sendOnlineUsers(){
        List<String> onlineUsers = server.getOnlineUsers();//metoda przechodząca przez
        writer.println("Currently online users:");
        for(String user : onlineUsers){
            writer.println("- " + user);
        }
        //listę handlers w serwerze
    }

    private void handlePrivateMessage(String message) {
        // Rozdziel wiadomość na komendy: "/w recipient message"
        String[] messageParts = message.split(" ", 3);
        if (messageParts.length < 3) {
            writer.println("Invalid private message format. Use /w recipient message");
            return;
        }

        String recipientLogin = messageParts[1]; // login odbiorcy
        String privateMessage = messageParts[2]; // treść wiadomości

        // Znajdź odbiorcę po loginie
        ClientHandler recipient = server.getClientByLogin(recipientLogin);

        if (recipient != null) {
            // Jeśli odbiorca jest zalogowany, wyślij wiadomość
            recipient.send("[Private] " + login + ": " + privateMessage);
            writer.println("[Private to " + recipientLogin + "] " + privateMessage);
        } else {
            // Jeśli odbiorca nie jest zalogowany, poinformuj nadawcę
            writer.println("User " + recipientLogin + " is not online.");
        }
    }


    //na rzecz 3a modyfikujemy metodę run(), aby najpierw odbierała
    //login użytkownika, a następnie zaczynała przesyłać wiadomość
    @Override
    public void run() {
        try {
            //Odbieramy login jako pierwszą wiadomość:
            writer.println("Please enter your login: ");
            login = reader.readLine(); //Odczytaj login od klienta

            //Sprawdzenie unikalności loginu
            if(server.isLoginInUse(login)){
                writer.println("Login is already in use");
                close();
                return;
            }

            //Powiadomienie o dołączeniu nowego użytkownika
            server.broadcast(login + " has joined the chat!", this);

            String command;
            while((command = reader.readLine()) != null) {
                //Obsługa komendy /online
                if(command.equalsIgnoreCase("/online")){
                    sendOnlineUsers(); //metoda w ClientHandler wysyłająca listę użytkownikow
                } else {
                    server.broadcast(login + ": " + command, this); //Rozsyłanie
                    //wiadomości do innych użytkowników
                }
            }
            // Odbieranie wiadomości od tego klienta i ich rozsyłanie do innych
            String message;
            while ((message = reader.readLine()) != null) {
                if (message.startsWith("/w ")) {
                    // Obsługa wiadomości prywatnej
                    handlePrivateMessage(message);
                } else {
                    // Obsługa normalnej wiadomości
                    server.broadcast(login + ": " + message, this);
                }
            }

        } catch (IOException e){
            throw  new RuntimeException(e);
        } finally {
            server.broadcast(login + " has left the chat.", this);
            close();
            }
        }
}

