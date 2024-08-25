package org.example.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Infinite {
    public static void main(String[] args) throws IOException {
        int number = 0;
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(reader);
        for ( ; ;){
           String data = in.readLine();
           if(data.equalsIgnoreCase("quit")) break;
           int num = Integer.parseInt(data);
           number += num;
        }
    }
}
