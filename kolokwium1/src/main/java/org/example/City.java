package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class City {

    private static final String REGEX = ",";
    private String capital;
    private double summer_timezone;
    private double latitude;
    private double longitude;
    private LocalTime localTime;
    private LocalTime localMeanTime;

    public City(String capital, double summer_timezone, double latitude, double longitude) {
        this.capital = capital;
        this.summer_timezone = summer_timezone;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCapital() {
        return capital;
    }

    public double getSummer_timezone() {
        return summer_timezone;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    // Metoda do konwersji współrzędnych geograficznych z formatu tekstowego na double
    private double parseCoordinate(String coordinate) {
        coordinate = coordinate.trim();
        char direction = coordinate.charAt(coordinate.length() - 1);
        double value = Double.parseDouble(coordinate.substring(0, coordinate.length() - 2).trim());

        // Ustal kierunek (N i E to dodatnie, S i W to ujemne)
        if (direction == 'S' || direction == 'W') {
            value = -value;
        }
        return value;
    }

    // Metoda do parsowania linii tekstowej z pliku CSV na obiekt City
    private City parseLine(String line) {
        String[] tempArr = line.split(REGEX);
        String capital = tempArr.length > 0 ? tempArr[0].trim() : "";
        double summer_timezone = tempArr.length > 1 ? Double.parseDouble(tempArr[1].trim()) : 0;
        double latitude = tempArr.length > 2 ? parseCoordinate(tempArr[2].trim()) : 0;
        double longitude = tempArr.length > 3 ? parseCoordinate(tempArr[3].trim()) : 0;

        return new City(capital, summer_timezone, latitude, longitude);
    }

    // Metoda do wczytywania danych z pliku CSV i tworzenia mapy miast
    public Map<String, City> parseFile(String path) throws IOException {
        HashMap<String, City> map = new HashMap<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine(); // Pomijanie nagłówka
            while ((line = reader.readLine()) != null) {
                City city = this.parseLine(line);
                if (city != null) {
                    map.put(city.getCapital(), city);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) reader.close();
        }
        return map;
    }

    // Metoda obliczająca czas miejscowy (lokalny czas słoneczny)
    public LocalTime localMeanTime(LocalTime localTime) {
        this.localTime = localTime;
        double centralMeridian = this.summer_timezone * 15.0;
        double longitudeDiff = this.longitude - centralMeridian;
        int timeDifferenceMinutes = (int) (longitudeDiff * 4);
        this.localMeanTime = localTime.plusMinutes(timeDifferenceMinutes);

        return localMeanTime;
    }
}
