package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Serwer {
    //klasa Serwer powinna zarządzać klientami oraz
    //rozsyłać wiadomości do wszystkich połączonych użytkowników

    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> handlers = new ArrayList<>();

    public Serwer() throws IOException {
        serverSocket = new ServerSocket(3000);
    }

    public void listen() throws IOException{
        System.out.println("Server started");

        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            ClientHandler handler = new ClientHandler(socket, this);
            Thread thread = new Thread(handler);
            thread.start();
            handlers.add(handler);
        }
    }

    public void removeHandler(ClientHandler handler) {
        handlers.remove(handler);
    }
    //metoda removeHandler usuwa klienta po jego rozłączeniu

    public void broadcast(String message, ClientHandler sender){
        for (ClientHandler handler : handlers) {
            if (handler != sender) {
                handler.send(message); // Wysyłanie wiadomości do innych klientów
            }
        }
        //metoda broadcast została zmodyfikowana, aby wiadomości nie
        //były wysyłane do nadawcy
    }

    //sprawdzamy, czy dany login klienta jest już w użyciu
    boolean isLoginInUse(String login){
        for (ClientHandler clientHandler : handlers){
            if(clientHandler.getLogin().equals(login)){
                return true;
            }
        }
        return false;
    }

    //3b - metoda przechodząca przez listę handlers (czyli po
    //wszystkich zalogowanych ClientHandler) i zwracać listę
    //ich loginów
    public List<String> getOnlineUsers(){
        List<String> onlineUsers = new ArrayList<>();
        for(ClientHandler handler : handlers){
            onlineUsers.add(handler.getLogin()); //Pobieramy login każdego klienta
        }
        return onlineUsers;
    }

    public ClientHandler getClientByLogin(String login) {
        for (ClientHandler handler : handlers) {
            if (handler.getLogin().equals(login)) {
                return handler;
            }
        }
        return null; // Jeśli nie ma takiego klienta
    }

}
