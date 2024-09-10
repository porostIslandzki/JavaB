package com.example.kolokwium2_termin1;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class AdminSocket {

    private final Socket socket;
    private final PrintWriter writer; //do wysyłania hasła do serwera

    public AdminSocket(String address, int port) throws IOException {
        this.socket = new Socket(address, port);
        OutputStream output = socket.getOutputStream();
        this.writer = new PrintWriter(output, true);
    }

    public void send(String message){
        writer.println(message);
    }
}

//Do serwera dodaj obsługę dodatkowego gniazda(na
// przykład za pomocą ServerSocket)
//do którego może podłączyć się klient, którego nazwiemy
// administratorem.
//Pierwszą czynnością, którą powinien wykonać, jest
// podanie hasła (może
//być zapisane w kodzie serwera). Jeśli hasło jest niepoprawne -
//-należy rozłączyć klienta. Do serwera może się w ten sposób podłączyć
//tylko jeden użytkownik