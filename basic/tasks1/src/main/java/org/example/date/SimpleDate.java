package org.example.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDate {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        System.out.println(date.getTime());
        SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm:ss dd.MM.YYYY");
        System.out.println(dataFormat.format(date));
    }
}
