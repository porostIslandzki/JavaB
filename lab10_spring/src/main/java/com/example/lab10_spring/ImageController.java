package com.example.lab10_spring;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

@RestController
@RequestMapping("/image")
public class ImageController {

    @GetMapping("/adjust-brightness")
    public String adjustBrightness( @RequestParam("image") String image64,
                            @RequestParam("brightness") int brightness) throws IOException {

        //dekodujemy obraz z formatu base64 do tablicy bajtów
        byte[] bytesFrom64 = Base64.getDecoder().decode(image64);

        //tablice bajtow przerabiamy na inputStream
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytesFrom64);
        BufferedImage image = ImageIO.read(inputStream);

        //modyfikacja jasności
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));

                int r = Truncate(color.getRed() + brightness);
                int g = Truncate(color.getGreen() + brightness);
                int b = Truncate(color.getBlue() + brightness);

                Color newColor = new Color(r, g, b);
                image.setRGB(x, y, newColor.getRGB());
            }
        }

        //konwersja obrazu z powrotem na base64
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(image, "png", output);
        byte[] outputArr = output.toByteArray();
        String editedImage = Base64.getEncoder().encodeToString(outputArr);
        return editedImage;
        //curl -G "http://localhost:8080/image/adjust-brightness"
        // --data-urlencode "image=<base64Image>" --data-urlencode "brightness=20"
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

    //Napisz kolejną, zbliżoną metodę, w której
    // wyniku znajdzie się niezakodowany obraz.
    @GetMapping("/adjust-brightness-image")
    public ResponseEntity<byte[]> adjustBrightnessImage(@RequestParam("image") String image64,
                                                        @RequestParam("brightness") int brightness) throws IOException {

        // Dekodowanie obrazu z formatu base64 do tablicy bajtów
        byte[] bytesFrom64 = Base64.getDecoder().decode(image64);

        // Tablica bajtów przerabiana na InputStream
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytesFrom64);
        BufferedImage image = ImageIO.read(inputStream);

        // Modyfikacja jasności
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));

                int r = Truncate(color.getRed() + brightness);
                int g = Truncate(color.getGreen() + brightness);
                int b = Truncate(color.getBlue() + brightness);

                Color newColor = new Color(r, g, b);
                image.setRGB(x, y, newColor.getRGB());
            }
        }

        // Konwersja obrazu do tablicy bajtów
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        byte[] imageBytes = outputStream.toByteArray();

        // Ustawienie nagłówków i zwrócenie obrazu
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}

//Napisz kontroler REST ImageController, w którym znajdzie
// się metoda zawracająca obraz ze zmodyfikowaną jasnością.
// Metoda typu GET powinna przyjąć obraz w formacie base64 oraz
// liczbę całkowitą określającą jasność. Metoda powinna
// rozjaśnić obraz o podaną wartość i zwrócić go w formacie base64.