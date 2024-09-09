package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {

    //Zdefiniuj pola potrzebne do komunikacji:
    //Socket - umożliwia połączenie klienta z serwerem
    private Socket socket;
    private BufferedReader reader; //Odbiór wiadomości od serwera
    private PrintWriter writer; //wysyłanie wiadomości do serwera
    private BufferedReader consoleReader; //Odbiór danych od gracza z konsoli

    public GameClient(String address, int port) {
        try {
            // Nawiązywanie połączenia z serwerem
            socket = new Socket(address, port);
            System.out.println("Połączono z serwerem gry na adresie " + address + " i porcie " + port);

            // Inicjalizacja strumieni
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));  // Odbiór z serwera
            writer = new PrintWriter(socket.getOutputStream(), true);  // Wysyłanie do serwera
            consoleReader = new BufferedReader(new InputStreamReader(System.in));  // Czytanie z konsoli

            // Start komunikacji z serwerem
            startCommunication();

        } catch (IOException e) {
            System.out.println("Błąd przy połączeniu z serwerem: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void startCommunication() throws IOException {

        String serverMessage;
        String clientMessage;

        while (true){
            if((serverMessage = reader.readLine())!=null){
                System.out.println("Message from serwer: " + serverMessage );
            } if(serverMessage.equalsIgnoreCase("koniec gry")){
                System.out.println("Gra zakończona");
                break;
            }

            //Wprowadź ruch gracza i wyślij do serwera
            System.out.println("Wprowadź wiadomość: ");
            clientMessage = consoleReader.readLine();
            writer.println(clientMessage); //wyślij ruch do serwera

            // Jeśli gracz wpisze "koniec", zakończ grę
            if (clientMessage.equalsIgnoreCase("koniec")) {
                System.out.println("Zakończono grę na życzenie gracza.");
                break;
            }
        }
    }

    private void closeConnection(){
        try{
            if(socket != null){
                socket.close();
                System.out.println("Połączenie z serwerem zamknięte. ");
            }
        }catch(IOException e){
            System.out.println("Błąd przy zamykaniu połączenia: "
            + e.getMessage());
        }
    }


}

//Polecenie: Napisz klasę klienta, który będzie łączył się
//zaprogramowanym z serwerem gry. Po zakończonej rozgrywce klient
//zostanie zamknęity