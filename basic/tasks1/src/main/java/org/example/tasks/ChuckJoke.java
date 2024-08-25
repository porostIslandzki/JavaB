package org.example.tasks;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class ChuckJoke {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://api.chucknorris.io/jokes/random");
            InputStream is = url.openStream();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(is)
            );
            String line = null;
            while ((line = bufferedReader.readLine())!=null){
                System.out.println(line);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
