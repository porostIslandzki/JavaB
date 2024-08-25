package org.example.types;

public class num_conversions {
    public static void main(String[] args) {
        //konwersja jawna - zawężająca
        int i = 5;
        double d = 20.0d/(double) i;
        System.out.println(d);
        //konw niejawna - rozszerzająca
        int a = 4;
        double b = 10.0d * a;
        System.out.println(b);


    }
}
