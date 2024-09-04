package org.example;

import java.io.*;
import java.net.Socket;

public class Klient implements Runnable{
    private final Socket socket;
    private final BufferedReader reader; //do odczytywania wiadomości wysłanych prez serwer
    private final PrintWriter writer; //do wysyłania wiadomości do serwera

    //1. Klient powinien połączyć się z serwerem na odpowiednim
    //porcie za pomocą obiektu Socket. W tym momencie
    //klient będzie w stanie komunikować się z serwerem
    public Klient(String address, int port) throws IOException {
        socket = new Socket(address, port);

      //2. Klient musi być zdolny do odbierania wiadomości,
        // które serwer rozsyła do wszystkich połączonych klientów.
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();
        reader = new BufferedReader(new InputStreamReader(input));
        writer = new PrintWriter(output, true);
    }

    @Override
    public void run() { //ta metoda jest wywoływana po uruchomieniu klienta
        //w osobnym wątku i odpowiada za ciągłe odbieranie wiadomości
        //od serwera, które następnie są wyświetlane w konsoli klienta
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

}

class Main {
    public static void main(String[] args) throws IOException {

        //klient jest tworzony i połączony z serweram na porcie 3000
        //a następnie uruchamiany w osobnym wątku za pomocą
        //new Thread(klient).start()
        Klient klient = new Klient("localhost", 3000);

        new Thread(klient).start(); //odbieranie wiadomości od serwera powinno
        //być obsługiwane w osobnym wątku
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in)); //klient powinien
        //czytać dane wprowadzone przez użytkownika z klawiatury
        //(standardowe wejście) i wysyłać te dane do serwera

        while (true){
            String message = reader.readLine();
            klient.send(message); //wysyła dane do serwera
        }

    }
}

//Napisz klienta czatu sieciowego współpracującego
// z napisanym serwerem. Klient powinien odczytywać
// standardowe wejście i przesyłać je do serwera oraz
// odbierać wiadomości z serwera i wyświetlać je na
// standardowym wyjściu.