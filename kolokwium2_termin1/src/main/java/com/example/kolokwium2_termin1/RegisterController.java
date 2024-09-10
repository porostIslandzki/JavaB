package com.example.kolokwium2_termin1;

import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class RegisterController {

    private final TokenHandler tokenHandler;
    private RGBImage rgbImage;

    @Autowired
    public RegisterController(TokenHandler tokenHandler, RGBImage rgbImage) {
        this.tokenHandler = tokenHandler;
        this.rgbImage = rgbImage;
    }

    //zwraca obiekt JSON składający się z tokena oraz
    //chwili jego utworzenia
    @PostMapping("/register")
    public HashMap<String, String> returnToken() {
        HashMap<String, String> tokenInfo = new HashMap<>();
        String token = UUID.randomUUID().toString();  // Generowanie unikalnego tokena

        if (tokenHandler.isTokenValid(token)) {  // Sprawdzenie, czy token jest unikalny
            String time = LocalTime.now().toString();
            tokenInfo.put("token", token);  // Przechowywanie tokena
            tokenInfo.put("time", time);  // Przechowywanie czasu utworzenia tokena
        } else {
            tokenInfo.put("error", "Token już istnieje.");
        }

        return tokenInfo;
    }

    @GetMapping("/tokens")
    public List<Map<String, Object>> getAllTokens() {
        // Usuwamy wygasłe tokeny przed zwróceniem listy
        tokenHandler.removeExpiredTokens();

        // Zwracamy listę wszystkich tokenów wraz z ich statusem
        return tokenHandler.getAllTokensWithStatus();
    }
}
