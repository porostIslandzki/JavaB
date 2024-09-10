package com.example.kolokwium2_termin1;

import java.io.IOException;
import java.sql.*;

public class CreateDatabase {

    private final DatabaseConnection dbConnection;
    private RGBImage image;

    public CreateDatabase(DatabaseConnection dbConnection,
                          RGBImage image) {
        this.dbConnection = dbConnection;
        this.image = image;
    }

    //Metoda do tworzenia tabeli (tylko raz)
    public void initializeDatabase() {
        // Połączenie z bazą danych
        dbConnection.connect("C:\\SQLite\\userdb.db");

        // Uzyskanie połączenia
        Connection connection = dbConnection.getConnection();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();

                // Tworzenie nowej tabeli, jeśli nie istnieje
                String createTableSQL = "CREATE TABLE IF NOT EXISTS entry ("
                        + "token TEXT NOT NULL, " +
                        "x INTEGER NOT NULL, " +
                        "y INTEGER NOT NULL, " +
                        "color TEXT NOT NULL, " +
                        "timestamp TEXT NOT NULL);";
                statement.execute(createTableSQL);

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Rozłączenie z bazą danych
                dbConnection.disconnect();
            }
        }
    }

    // Metoda do wstawiania rekordu do tabeli
    public void insertEntry(String token, int x, int y, String color) {
        // Połączenie z bazą danych
        dbConnection.connect("C:\\SQLite\\userdb.db");

        // Uzyskanie połączenia
        Connection connection = dbConnection.getConnection();

        if (connection != null) {
            try {
                String insertSQL = "INSERT INTO entry (token, x, y, color, timestamp) VALUES(?, ?, ?, ?, ?);";
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.setString(1, token);
                preparedStatement.setInt(2, x);
                preparedStatement.setInt(3, y);
                preparedStatement.setString(4, color);
                preparedStatement.setString(5, String.valueOf(System.currentTimeMillis())); // Zapisanie bieżącego timestampu

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Rozłączenie z bazą danych
                dbConnection.disconnect();
            }
        }
    }

    // Metoda do usuwania wpisu z bazy danych na podstawie tokena
    public void deleteEntry(String token) {
        // Połączenie z bazą danych
        dbConnection.connect("C:\\SQLite\\userdb.db");

        // Uzyskanie połączenia
        Connection connection = dbConnection.getConnection();

        if (connection != null) {
            try {
                String deleteSQL = "DELETE FROM entry WHERE token=?;";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
                preparedStatement.setString(1, token);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Usunięto wpisy dla tokena: " + token);
                } else {
                    System.out.println("Nie znaleziono wpisów dla tokena: " + token);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Rozłączenie z bazą danych
                dbConnection.disconnect();
            }
        }
    }

    // Metoda ma pobierać wpisy z bazy danych i na ich podstawie
// odtwarzać obraz
    public void createImage() {
        // Połączenie z bazą danych
        dbConnection.connect("C:\\SQLite\\userdb.db");

        // Uzyskanie połączenia
        Connection connection = dbConnection.getConnection();

        if (connection != null) {
            try {
                String selectSQL = "SELECT token, x, y, color FROM entry ORDER BY timestamp;";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectSQL);

                // Odtwarzanie obrazu na podstawie wyników
                while (resultSet.next()) {
                    String resultToken = resultSet.getString("token");
                    int resultX = resultSet.getInt("x");
                    int resultY = resultSet.getInt("y");
                    String resultColor = resultSet.getString("color");

                    // Konwersja koloru szesnastkowego na liczbę całkowitą
                    int color = Integer.parseInt(resultColor, 16);

                    // Ustawienie piksela na obrazie za pomocą colorPixel z tokenem
                    image.colorPixel(resultToken, resultX, resultY, color);  // Ustawianie piksela na obrazie z tokenem

                    System.out.println("Token: " + resultToken + ", X: " + resultX + ", Y: " + resultY + ", Color: " + resultColor);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                // Rozłączenie z bazą danych
                dbConnection.disconnect();
            }
        }
    }

    }

