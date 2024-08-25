package org.example.date.date_challenge;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.YYYY");
        System.out.println(dateFormat.format(date));
    }
}
