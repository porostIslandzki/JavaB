package com.example.kolokwium2_termin1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class AdminController {

    private final String admin_password = "i_want_to_kms";
    private ServerSocket serverSocket;
    private boolean adminLogged = false;

    public AdminController(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        System.out.println("Admin server is running on port " + port);
    }

    public void start() throws IOException {

        while (true) {

            if (!adminLogged) {
                Socket socket = serverSocket.accept(); // Akceptowanie połączenia od administratora
                new AdminHandler(socket).start(); // Obsługa klienta w osobnym wątku
            }
        }
    }

    // Klasa obsługująca połączenie administratora
    private class AdminHandler extends Thread {
        private Socket socket;
        private BufferedReader reader;
        private PrintWriter writer;

        public AdminHandler(Socket socket) throws IOException {
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
        }

        @Override
        public void run() {
            try {
                // Oczekiwanie na hasło od administratora
                writer.println("Please enter the admin password:");
                String password = reader.readLine();

                // Weryfikacja hasła
                if (admin_password.equals(password)) {
                    adminLogged = true; // Ustawienie flagi po poprawnym połączeniu
                    writer.println("Access granted. You are now connected as an admin.");
                    handleAdminCommands(); // Obsługa komend od administratora
                } else {
                    writer.println("Access denied. Incorrect password.");
                    closeConnection(); // Rozłączenie po niepoprawnym haśle
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeConnection(); // Rozłączenie po zakończeniu pracy
            }
        }

        // Metoda do obsługi komend od administratora
        private void handleAdminCommands() throws IOException {
            String command;
            while ((command = reader.readLine()) != null) {
                // Tutaj można dodać obsługę komend administracyjnych
                writer.println("Received command: " + command);
            }
        }

        // Metoda do zamykania połączenia
        private void closeConnection() {
            try {
                adminLogged = false;
                socket.close();
                reader.close();
                writer.close();
                System.out.println("Admin disconnected.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

//Wykorzystuje ServerSocket do oczekiwania na połączenia
//Po otrzymaniu połączenia odczyta hasło przesłane przez administratora
//i zweryfikuje je
//jeśli hasło jest poprawne, administrator może kontynuować
//komunikacje