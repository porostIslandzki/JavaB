package org.example;

import java.util.Date;

public class TimestampChallenge {
    public static void main(String[] args) {
        Date date = new Date();
        long timestamp = date.getTime();
        System.out.println(timestamp);
        long oneHour = 60 * 1000 * 60; // 1 h w milisekundach
        timestamp += oneHour * 24 * 3; //+ 3 dni
        timestamp += oneHour * 10; // + 10h
        timestamp += 1000 * 60 * 25; // + 25 minut

        Date futureDate = new Date(timestamp);
        System.out.println(futureDate);
    }
}
