package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class User {
    private String login;
    private PrintWriter writer;

    public User(String login, PrintWriter writer) {
        this.login = login;
        this.writer = writer;
    }

    public String getLogin() {
        return login;
    }

    public void sendMessage(String message) {
        writer.println(message);
    }

    public void sendFile(FileInputStream fis) throws IOException {
        int data;
        while ((data = fis.read()) != -1){
            writer.println((char) data);
        }
    }

}

