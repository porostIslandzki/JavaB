package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Image {

    private BufferedImage image; //pole klasy do przechowywania obrazu

    public void loadImage(String path) throws IOException {
        //Próba wczytania obrazu z pliku
        File file  = new File(path);
        if(file.exists()){
            image = ImageIO.read(file);
        } else {
            //Próba wczytania obrazu z zasobów aplikacji
            InputStream in = getClass().getResourceAsStream(path);
            if(in != null){
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

    public BufferedImage getImage(){
        return image;
    }

    //Dodaj metodę, która zwiększy jasność obrazu o podaną stałą
    public void increaseBrightness(float percentage){
        Graphics g = image.getGraphics();
        int brightness = (int)(256 - 256 * percentage);
        g.setColor(new Color(0, 0, 0, brightness));
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
    }
}

//Zadanie 1.
//Napisz klasę posiadającą:
//- metodę, która otrzyma ścieżkę i
// wczyta obraz do pola klasy typu BufferedImage,
//- metodę, która zapisze obraz z tego
// pola do podanej ścieżki.