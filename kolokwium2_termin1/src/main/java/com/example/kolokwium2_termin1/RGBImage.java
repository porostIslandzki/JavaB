package com.example.kolokwium2_termin1;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class RGBImage {

    private BufferedImage image;
    private final TokenHandler tokenHandler;
    private final CreateDatabase createDatabase;

    public RGBImage(TokenHandler tokenHandler, CreateDatabase createDatabase) {
        this.tokenHandler = tokenHandler;
        this.createDatabase = createDatabase;
        this.image = new BufferedImage(512, 512, BufferedImage.TYPE_INT_RGB);  // Jeden obraz inicjalizowany raz
    }

    // Wyświetlenie obrazu
    @GetMapping("/image")
    public ResponseEntity<byte[]> paintImage() throws IOException {
        // Konwersja obrazu na format PNG i zapisanie do strumienia
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);

        // Przygotowanie odpowiedzi HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "image/png");

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    @PostMapping("/pixel")
    public ResponseEntity<String> colorPixel(@RequestParam("token") String token,
                                             @RequestParam("x") int x, @RequestParam("y") int y,
                                             @RequestParam("color") int hex24Bit) throws IOException {

        // Wartość koloru w formacie RGB jest już przekazywana w postaci liczby całkowitej (hex24Bit)
        int color = hex24Bit;

        // Sprawdzenie, czy token jest aktywny
        if (!tokenHandler.isTokenValid(token)) {
            return new ResponseEntity<>("Invalid or expired token", HttpStatus.FORBIDDEN);
        }

        // Sprawdzenie poprawności współrzędnych x, y
        if (x < 0 || x >= 512 || y < 0 || y >= 512) {
            return new ResponseEntity<>("Invalid coordinates", HttpStatus.BAD_REQUEST);
        }

        // Ustawienie koloru dla danego piksela
        image.setRGB(x, y, color);

        // Zapisanie do bazy danych
        createDatabase.insertEntry(token, x, y, Integer.toHexString(hex24Bit)); // Konwersja koloru na string

        // Zwrócenie odpowiedzi HTTP 200 OK
        return new ResponseEntity<>("Pixel updated and saved to database", HttpStatus.OK);
    }


    // Przeglądanie wszystkich wpisów w bazie danych
    /*@GetMapping("/entries")
    public ResponseEntity<String> getAllEntries() {
        createDatabase.selectEntries();
        return new ResponseEntity<>("Entries displayed in console", HttpStatus.OK);
    }*/

    // Usuwanie wpisu z bazy danych na podstawie tokena
    @DeleteMapping("/pixel")
    public ResponseEntity<String> deletePixel(@RequestParam("token") String token) {
        createDatabase.deleteEntry(token);
        return new ResponseEntity<>("Entry deleted for token: " + token, HttpStatus.OK);
    }
}
