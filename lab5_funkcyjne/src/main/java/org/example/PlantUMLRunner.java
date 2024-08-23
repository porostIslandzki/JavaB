package org.example;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PlantUMLRunner {
    //Napisz klasę PlantUMLRunner, posiadającą
    // publiczne statyczne metody:
    //- ustawienie ścieżki do uruchamialnego pliku jar.
    //- wygenerowanie schematu po przekazaniu:
    // napisu z danymi, ścieżki do katalogu wynikowego i nazwy pliku wynikowego.

    public static String jarFilePath; //będzie przechowywać ścieżkę do pliku JAR PlantUML

    //metoda do ustawiania ścieżki, inaczej setter:

    public static void setJarFilePath(String jarFilePath) {
        PlantUMLRunner.jarFilePath = jarFilePath;
    }

    //metoda do wygenerowania schematu po przekazaniu:
    //outputDirectory: ścieżka do katalogu, w którym ma być zapisany
    //wynikowy plik
    //data: napis zawierający dane w formacie PlantUML
    //outputFileName: nazwa pliku wynikowego

    public static void generateDiagram(String data, String outputDirectory,
                                       String outputFileName) throws IOException{
        //Stworzenie pliku tymczasowego
        File tempFile = File.createTempFile("uml", ".puml");

        try (FileWriter fw = new FileWriter(tempFile)) {
            fw.write(data);

            // Przygotowanie komendy do uruchomienia PlantUML
            String command = String.format("java -jar %s -tpng %s -o %s", jarFilePath, tempFile.getAbsolutePath(), outputDirectory);

            // Uruchomienie procesu
            Process process = Runtime.getRuntime().exec(command);

            //oczekiwanie na zakończenie procesu
            process.waitFor();

            // Sprawdzenie czy plik został utworzony
            File outputFile = new File(outputDirectory, outputFileName + ".png");
            if (!outputFile.exists()) {
                throw new IOException("Diagram nie został wygenerowany");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            throw new IOException("Proces generowania diagramu został przerwany", e);
        } finally {
            // Usunięcie pliku tymczasowego
            tempFile.delete();
        }
    }
}
