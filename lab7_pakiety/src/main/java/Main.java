
import auth.Account;
import auth.AccountManager;
import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        DatabaseConnection dbConnection = new DatabaseConnection();

        // Połączenie z bazą danych
        dbConnection.connect("C:\\SQLite\\userdb.db");

        // Uzyskanie połączenia
        Connection connection = dbConnection.getConnection();

        if (connection != null) {
            try {
                // Tworzenie tabeli users
                Statement statement = connection.createStatement();
                String dropTableSQL = "DROP TABLE IF EXISTS users";
                statement.execute(dropTableSQL);
                String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL," +
                        "password TEXT NOT NULL" +
                        ");";
                statement.execute(createTableSQL);

                /*// Wstawianie danych do tabeli
                String insertSQL = "INSERT INTO users (name, password) VALUES ('Jan Kowalski', 'jan1234');";
                statement.execute(insertSQL);

                // Odczyt danych z tabeli
                String selectSQL = "SELECT * FROM users;";
                ResultSet resultSet = statement.executeQuery(selectSQL);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");

                    System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
                } */
                AccountManager accountManager = new AccountManager(dbConnection);
                // Rejestracja nowego użytkownika
                accountManager.register("jan.kowalski", "password123");

                // Próba logowania
                boolean isAuthenticated = accountManager.authenticate("jan.kowalski", "password123");
                System.out.println("Czy użytkownik uwierzytelniony: " + isAuthenticated);

                // Pobranie danych użytkownika
                Account account = accountManager.getAccount("jan.kowalski");
                if (account != null) {
                    System.out.println("Użytkownik: ID = " + account.ID() + ", Name = " + account.name());
                } else {
                    System.out.println("Nie znaleziono użytkownika o nazwie jan.kowalski");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Rozłączenie z bazą danych
                dbConnection.disconnect();
            }
        }
    }
}

//Utwórz mavenowy projekt o nazwie site zawierający dwa pakiety:
// database oraz auth.
//Poza pakietami napisz klasę Main, w której przetestowane
// zostanie utworzenie, zapis i odczyt danych z tabeli.