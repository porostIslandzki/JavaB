package org.example;

import java.io.*;
import java.net.Socket;

public class Klient implements Runnable{
    //zadanie 3a: w klasie Klient musimy wprowadzić obsługę loginu
    //po połączeniu się z serwerem. Klient powinien najpierw wysłać
    //login, a dopiero potem rozpocząć wysyłanie wiadomości

    private final Socket socket;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public Klient(String address, int port) throws IOException {
        socket = new Socket(address, port);

        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();
        reader = new BufferedReader(new InputStreamReader(input));
        writer = new PrintWriter(output, true);
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = reader.readLine()) != null)
                System.out.println(message);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void send(String message){
        writer.println(message);
    }

    public void close() throws IOException {
        socket.close();
    }


}

class Main {
    public static void main(String[] args) throws IOException {

        Klient klient = new Klient("localhost", 3000);

        new Thread(klient).start();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        //zaraz po uruchomieniu klienta proszę użytkownika
        //o podanie loginu, który zostanie przesłany do
        //serwera
        System.out.println("Enter your login: ");
        String login = reader.readLine();
        klient.send(login); //Wyślij login do serwera jako pierwszą wiadomość

        while (true) {
            String message = reader.readLine();
            if (message.equalsIgnoreCase("/quit")) { //dodaję możliwość zamknięcia
                // połączenia komendą quit
                klient.send("Client has left the chat.");
                klient.close();  // Zamknięcie połączenia
                break;
            }
            klient.send(message);
        }

    }
}

//Niech działanie klienta rozpoczyna się zawsze od wpisania
// loginu. Login powinien zostać przesłany serwerowi w
// postaci sformatowanej wiadomości. Zakładamy, że użytkownicy
// podadzą unikalne loginy. W chwili zalogowania pozostali
// użytkownicy czata powinni otrzymać informację o dołączeniu
// użytkownika, a chwili wyłączenia klienta o opuszczeniu
// przez niego czatu.