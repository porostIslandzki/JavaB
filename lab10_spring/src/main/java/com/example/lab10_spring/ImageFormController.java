package com.example.lab10_spring;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
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
    public String upload(@RequestParam("Image") MultipartFile file, Model model) throws IOException {
        // Przekonwertowanie przesłanego pliku na Base64
        byte[] bytes = file.getBytes();
        String base64Image = Base64.getEncoder().encodeToString(bytes);

        // Dodanie zakodowanego obrazu do modelu
        model.addAttribute("Image", base64Image);

        // Przekierowanie do pliku image.html
        return "Image";
    } //W kontrolerze metoda z adnotacją
    // @PostMapping("/add_image") odpowiada za obsługę
    // żądań POST pod ścieżką /add_image. Oznacza to, że
    // kiedy użytkownik wciśnie "Upload", przeglądarka
    // wyśle żądanie POST do serwera na adres /add_image,
    // a Spring automatycznie wywoła metodę upload(),
    // ponieważ została zmapowana na tę ścieżkę.
}


//Napisz kontroler ImageFormController. Skorzystaj z
// udostępnionych szablonów w plikach index.html i image.html.