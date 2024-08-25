package org.example.tasks;

public class Trojarg {
    public static void main(String[] args) {
        /* Uzyj operatora trojargumentowego aby sprawdzic
        czy liczba jest parzysta czy niepatrzysta.
        Zastosuj operator modulo, ktory jesli zwroci 0 to
        wskaze parzysta liczbę.
        Pokaz info o wyniku w konsoli.
         */
        int a = 5;
        boolean parzysta = (a % 2)==0 ? true : false;
    }
}
