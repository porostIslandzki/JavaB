package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Image {

    private BufferedImage image; //pole klasy do przechowywania obrazu
    //BufferedImage przechowuje obraz w postaci macierzy

    public void loadImage(String path) throws IOException {
        //Próba wczytania obrazu z pliku
        File file = new File(path);
        if (file.exists()) {
            image = ImageIO.read(file);
        } else {
            //Próba wczytania obrazu z zasobów aplikacji
            InputStream in = getClass().getResourceAsStream(path);
            if (in != null) {
                image = ImageIO.read(in);
            } else {
                throw new IOException("Nie znaleziono pliku");
            }
        }
    } //metoda loadImage wcztuje obraz z systemu plików lub zasobów aplikacji.
    //jeśli plik nie istnieje, obsługuje wyjątek

    public void saveImage(String path)
            throws IOException {
        if (image == null) {
            throw new IllegalStateException("Brak obrazu do zapisania. Najpierw wczytaj obraz za pomocą loadImage().");
        } else {
            ImageIO.write(image, "jpg", new File(path));
        }
    } //zapisuje obraz z pola image do podanej ścieżki w formacie jpg
    //Sprawdza, czy obraz został wcześniej wczytany, jak nie to rzuca wyjątek

    public BufferedImage getImage() {
        return image;
    }

    //Dodaj metodę, która zwiększy jasność obrazu o podaną stałą
    public void increaseBrightness(int brightness) {
        Instant start = Instant.now();
        int rgb[];
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                rgb = image.getRaster().getPixel(i, j, new int[3]);
                int red = Truncate(rgb[0] + brightness);
                int green = Truncate(rgb[1] + brightness);
                int blue = Truncate(rgb[2] + brightness);
                int arr[] = {red, green, blue};
                image.getRaster().setPixel(i, j, arr);
            }
        }
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));
    }

    //żeby pozycje nie wyszły poza 255
    public static int Truncate(int value) {
        if (value < 0) {
            value = 0;
        } else if (value > 255) {
            value = 255;
        }
        return value;
    }

    //Dodaje metodę, która wykona to samo działanie w
    // oparciu o pulę wątków. Jeden wątek powinien obsłużyć
    // jeden wiersz obrazu. Dodaj czas wykonania tej metody
    // do porównania.

    public void brightnessWithThreadsExec(int brightness) {
        Instant start = Instant.now();
        int numberOfThreads = Runtime.getRuntime().
                availableProcessors();
        ExecutorService executor =
                Executors.newFixedThreadPool(numberOfThreads);

        //każdy wątek zajmuje się pewnym zakresem wierszy
        int rowsPerThread = image.getHeight() / numberOfThreads;

        for (int thread = 0; thread < numberOfThreads; thread++) {
            int startRow = thread * rowsPerThread;
            int endRow;

            // Ustawiamy ostatni wątek, żeby przetworzył ewentualne pozostałe wiersze
            if (thread == numberOfThreads - 1) {
                endRow = image.getHeight();
            } else {
                endRow = startRow + rowsPerThread;
            }

            //Tworzymy zadanie dla wątku
            executor.submit(() -> {
                for (int i = startRow; i < endRow; i++) { // Przetwarzanie każdego wiersza w zakresie
                    for (int j = 0; j < image.getWidth(); j++) { // Przetwarzanie każdego piksela w wierszu
                        int[] rgb = image.getRaster().getPixel(j, i, new int[3]); // Pobieramy RGB piksela
                        int red = Truncate(rgb[0] + brightness); // Zwiększamy jasność koloru czerwonego
                        int green = Truncate(rgb[1] + brightness); // Zwiększamy jasność koloru zielonego
                        int blue = Truncate(rgb[2] + brightness); // Zwiększamy jasność koloru niebieskiego
                        int[] arr = {red, green, blue}; // Nowe wartości RGB
                        image.getRaster().setPixel(j, i, arr); // Ustawiamy nowe wartości piksela
                    }
                }
            });

        }
        // Zamykamy pulę wątków i czekamy, aż wszystkie wątki zakończą pracę
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));
    }

    // Dodaj metodę, która wykona to samo działanie,
    // dzieląc zadanie na określoną liczbę wątków.
    // Liczbę wątków należy powiązać z liczbą dostępnych
    // rdzeni procesora. Porównaj czas wykonania obu metod.

    public void brightnessWithThreads (int brightness) throws InterruptedException{
        Instant start = Instant.now();
        int numberOfThreads = Runtime.getRuntime().
                availableProcessors();

        //każdy wątek zajmuje się pewnym zakresem wierszy
        int rowsPerThread = image.getHeight() / numberOfThreads;

        Thread[] threads = new Thread[numberOfThreads];

        for (int thread = 0; thread < numberOfThreads; thread++) {
            int startRow = thread * rowsPerThread;
            int endRow;

            // Ustawiamy ostatni wątek, żeby przetworzył ewentualne pozostałe wiersze
            if (thread == numberOfThreads - 1) {
                endRow = image.getHeight();
            } else {
                endRow = startRow + rowsPerThread;
            }

            //Tworzymy zadanie dla wątku
            threads[thread] = new Thread(() -> {
                for (int i = startRow; i < endRow; i++) { // Przetwarzanie każdego wiersza w zakresie
                    for (int j = 0; j < image.getWidth(); j++) { // Przetwarzanie każdego piksela w wierszu
                        int[] rgb = image.getRaster().getPixel(j, i, new int[3]); // Pobieramy RGB piksela
                        int red = Truncate(rgb[0] + brightness); // Zwiększamy jasność koloru czerwonego
                        int green = Truncate(rgb[1] + brightness); // Zwiększamy jasność koloru zielonego
                        int blue = Truncate(rgb[2] + brightness); // Zwiększamy jasność koloru niebieskiego
                        int[] arr = {red, green, blue}; // Nowe wartości RGB
                        image.getRaster().setPixel(j, i, arr); // Ustawiamy nowe wartości piksela
                    }
                }
            });
            threads[thread].start();
        }
        // Czekamy na zakończenie pracy wszystkich wątków
        for (Thread thread : threads) {
            thread.join();
        }
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));
    }
}



//Zadanie 1.
//Napisz klasę posiadającą:
//- metodę, która otrzyma ścieżkę i
// wczyta obraz do pola klasy typu BufferedImage,
//- metodę, która zapisze obraz z tego
// pola do podanej ścieżki.