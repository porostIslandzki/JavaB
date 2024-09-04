package com.example.lab10_spring;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageFormController {
    //Napisz metodę, która wyświetli plik index.html.
    @GetMapping()
    public String get(){
        return "Index";
    }

    // Metoda upload, która zostanie wywołana po naciśnięciu przycisku Upload
    @PostMapping("/add_image")
    public String upload(@RequestParam("Image") MultipartFile file,
                         @RequestParam("brightness") int brightness,
                         Model model) throws IOException {

        // Przekonwertowanie przesłanego pliku na Base64
        byte[] bytes = file.getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        BufferedImage image = ImageIO.read(inputStream);

        //Modyfikacja jasności
        BufferedImage brightenedImage = adjustBrightness(image, brightness);

        // Konwersja obrazu z powrotem na Base64
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(brightenedImage, "png", outputStream);
        String base64Image = Base64.getEncoder().encodeToString(outputStream.toByteArray());

        // Dodanie zakodowanego obrazu do modelu
        model.addAttribute("Image", base64Image);

        // Przekierowanie do pliku image.html
        return "Image"; //plik image.html w katalogu templates
    }

    // Metoda do modyfikacji jasności obrazu
    private BufferedImage adjustBrightness(BufferedImage image, int brightness) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));

                int r = truncate(color.getRed() + brightness);
                int g = truncate(color.getGreen() + brightness);
                int b = truncate(color.getBlue() + brightness);

                Color newColor = new Color(r, g, b);
                image.setRGB(x, y, newColor.getRGB());
            }
        }
        return image;
    }
    
    // Metoda pomocnicza do ograniczenia wartości RGB do zakresu [0, 255]
    private int truncate(int value) {
        return Math.max(0, Math.min(255, value));
    }

    //W kontrolerze metoda z adnotacją
    // @PostMapping("/add_image") odpowiada za obsługę
    // żądań POST pod ścieżką /add_image. Oznacza to, że
    // kiedy użytkownik wciśnie "Upload", przeglądarka
    // wyśle żądanie POST do serwera na adres /add_image,
    // a Spring automatycznie wywoła metodę upload(),
    // ponieważ została zmapowana na tę ścieżkę.

    //Zmodyfikuj formatkę w pliku index.html i dodaj do niej pole,
    // w którym można ustawić zmianę jasności. Niech naciśnięcie
    // przycisku Upload spowoduje wyświetlenie załadowanego
    // obrazu rozjaśnionego o daną wartość.
}


//Napisz kontroler ImageFormController. Skorzystaj z
// udostępnionych szablonów w plikach index.html i image.html.