package auth;

import database.DatabaseConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountManager {
    private final DatabaseConnection databaseConnection;

    public AccountManager(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    //Metoda rejestrująca nowego użytkownika w bazie danych
    //Metoda hashuje hasło za pomocą BCrypt.hashpw() i zapisuje
    //je w bazie danych wraz z nazwą użytkownika
    public void register(String username, String password) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        String sql = "INSERT INTO users (name, password) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, username);
            statement.setString(2, hashedPassword);
            statement.executeUpdate();
            System.out.println("Zarejestrowano nowego użytkownika: " + username);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Metoda uwierzytelniająca użytkownika
    //Metoda, która porównuje zaszyfrowane hasło zapisane
    //w bazie danych z hasłem podanym przez użytkownika przy logowaniu
    //za pomocą BCrypt.checkpw()
    public boolean authenticate(String username, String password) throws SQLException{
        Connection connection = databaseConnection.getConnection();
        String sql = "SELECT password FROM users WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                String storedHashedPassword = resultSet.getString("password");
                return BCrypt.checkpw(password, storedHashedPassword);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } return false;
    }

    //Metoda zwracająca obiekt Account dla nazwy użytkownika
    public Account getAccount(String username) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String sql = "SELECT id, name FROM users WHERE name = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                return new Account(id, name);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //Metoda zwracająca obiekt Account dla id
    public Account getAccount(int id) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String sql = "SELECT id, name FROM users WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                String name = resultSet.getString("name");
                return new Account(id, name);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
//posiadającą metody publiczne:
//- register, dodającą nowego użytkownika do bazy danych,
//- authenticate, zwracająca wartość logiczną mówiącą,
// czy nazwa użytkownika i hasło zgadzają się,
//- getAccount, zwracającą obiekt Account
// dla zadanej nazwy użytkownika lub id.
//Hasło w bazie należy zaszyfrować z użyciem
// biblioteki bcrypt. W klasie Main przetestuj napisane metody.