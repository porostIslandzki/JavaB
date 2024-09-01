
//Napisz test sprawdzający poprawność
//rejestracji nowego konta.

import music.DatabaseConnection;
import music.ListenerAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterTest {

    @BeforeAll
    public static void setUp() {
        // Połączenie z bazą danych przed wykonaniem testów
        DatabaseConnection.connect("listener_account.db"); // Podaj odpowiednią ścieżkę do bazy danych
    }

    @Test
    void register_correctIndex_resultTrue() throws SQLException {
        //given
        String username = "Marek";
        String password = "Skibidi";

        //when
         int id = ListenerAccount.Persistence.register(username, password);

        //then
        assertTrue(id > 0, "The returned ID should be greater than zero.");

    }
}

//      public static int register(String username, String password) throws SQLException {
//            try {
//                int id = Account.Persistence.register(username, password); //rejestruje nowego użytkownika, dodając go do tabeli account
//                String sql = "INSERT INTO listener_account(id_account, credits) VALUES (?, 0)"; //po rejestracji tworzy dla niego wpis w tabeli listener_account z początkową liczbą kredytów o
//                PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
//                statement.setInt(1, id);
//                statement.executeUpdate();
//                return id; //zwraca id
//            } catch (SQLException | RuntimeException e) {
//                throw new RuntimeException(e);
//            }
//        }

//aha i do tego gówna musimy jeszcze zrobić połączenie z bazą bo inaczej nie zadziała