package org.example.tasks;

import java.util.Scanner;

public class CompoundIntrest {
    //procent składany
    //1000(kapitaL) * (1+0.05(oprocentowanie)^3(liczba lat)
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double startCapital = 0;
        double yearlyInterest = 0;
        int numYears = 0;

        double finalCapital; //rezultat oszczędzania
        System.out.println("Wpisz kapitał początkowy: ");
        startCapital = Double.valueOf(in.nextLine()).doubleValue();

        System.out.println("Wpisz roczne oprocentowanie np 5 to 5%: ");
        startCapital = Double.valueOf(in.nextLine()).doubleValue();

        System.out.println("Wpisz ilość lat oszczędzania: ");
        numYears = in.nextInt();

        finalCapital = startCapital * Math.pow(1 + (yearlyInterest / 100.0d), numYears);
        System.out.println("Efekt oszczędzania z procentem składania " + finalCapital);
    }


    }
