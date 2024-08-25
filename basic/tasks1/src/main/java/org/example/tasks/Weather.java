package org.example.tasks;

import java.io.BufferedReader;

public class Weather {
    public static void main(String[] args) {
        boolean raining = false;
        boolean haveUmbrella = false;
        int temperature = 40;
        if (temperature > 40 || temperature < -20){
            System.out.println("Pozostań w domu");
        } else if (temperature > -10 && temperature <10 && raining && haveUmbrella) {
            {
                System.out.println("Temperatura adekwatna, pada ale można iść");
            }
        } else if (raining == false && temperature > 12){
            System.out.println("Nie pada, temperatura 12 to moze isc");
        } else {
            System.out.println("Zostań w domu");
        }


    }
}
