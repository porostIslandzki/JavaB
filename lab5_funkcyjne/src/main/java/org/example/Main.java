package org.example;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String csvFilePath = "C:\\Users\\maria\\Desktop\\git\\lab4_pliki_wyjatki\\src\\main\\resources\\family.csv"; // Zmień na właściwą ścieżkę do pliku CSV

        try {
            // Wczytywanie danych z pliku CSV
            List<Person> people = Person.fromCsv(csvFilePath);

            // Wyświetlanie informacji o każdej osobie
            for (Person person : people) {
                System.out.println("Imię: " + person.getName());
                System.out.println("Data urodzenia: " + person.getBirth());
                System.out.println("Data śmierci: " + person.getDeath());

                // Wyświetlanie rodziców
                System.out.println("Rodzice:");
                for (Person parent : person.getParents()) {
                    System.out.println("\tImię: " + parent.getName());
                }

                System.out.println("-------------------------");
            }
        } catch (IOException e) {
            System.err.println("Błąd podczas wczytywania pliku CSV: " + e.getMessage());
        }
    }
}
