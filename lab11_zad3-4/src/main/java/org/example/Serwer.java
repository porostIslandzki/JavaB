package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Serwer {

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

    public void broadcast(String message){
        handlers.forEach(handler -> handler.send(message));
    }
}
