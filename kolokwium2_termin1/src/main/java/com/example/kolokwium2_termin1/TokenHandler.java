package com.example.kolokwium2_termin1;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;


@Component
public class TokenHandler {

    HashMap<String, LocalTime> tokenHandlerMap = new HashMap<>();

    //Niech tokeny będą ważne jedynie przez 5 minut od ich
    //wygenerowania lub do włączenia aplikacji
    private static final long TOKEN_VALIDITY_MINUTES = 5;

    //Niech tokeny zarejestrowane podczas działania aplikacji, będą
    //przechowywane w pamięci aplikacji
    public void addToken(String token) {
        tokenHandlerMap.put(token, LocalTime.now());
        System.out.println(token + " został zapisany w pamięci.");
    }

    //Wykorzystaj informację o czasie utworzenia tokena do określenia
    //jego ważności.
    public boolean isTokenValid(String token) {
        LocalTime tokenCreationTime = tokenHandlerMap.get(token);
        if (tokenCreationTime != null) {
            Duration duration = Duration.between(tokenCreationTime, LocalTime.now());
            if (duration.toMinutes() < TOKEN_VALIDITY_MINUTES) {
                return true;
            }
        }
        return false;
    }

    // Usunięcie wygasłych tokenów
    public void removeExpiredTokens() {
        tokenHandlerMap.entrySet().removeIf(entry -> {
            Duration duration = Duration.between(entry.getValue(), LocalDateTime.now());
            return duration.toMinutes() >= TOKEN_VALIDITY_MINUTES;
        });
    }

    // Metoda zwracająca listę tokenów z ich statusem (czy są aktywne)
    //lista ma zawierać token, czas wygenerowania, informację czy token jest
    //aktywny
    public List<Map<String, Object>> getAllTokensWithStatus() {
        List<Map<String, Object>> tokenList = new ArrayList<>();

        for (Map.Entry<String, LocalTime> entry : tokenHandlerMap.entrySet()) {
            Map<String, Object> tokenInfo = new HashMap<>();
            tokenInfo.put("token", entry.getKey());
            tokenInfo.put("createdAt", entry.getValue().toString());
            tokenInfo.put("isActive", isTokenValid(entry.getKey()));
            tokenList.add(tokenInfo);
        }

        return tokenList;
    }
}
