import music.Account;
import music.DatabaseConnection;
import music.ListenerAccount;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.naming.AuthenticationException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    @BeforeAll
    public static void setUp() throws SQLException {
        // Inicjalizacja bazy danych i tworzenie tabeli
        DatabaseConnection.connect("listener_account.db"); // Podaj odpowiednią ścieżkę do bazy danych
        ListenerAccount.Persistence.init(); // Inicjalizacja tabel

        // Rejestracja użytkownika do testów
        ListenerAccount.Persistence.register("Marek", "Skibidi");
    }

    @Test
    public void authenticate_correctPassword_equalsTrue() throws AuthenticationException {

        //given
        String username = "Marek";
        String password = "Skibidi";

        //when
        ListenerAccount listenerAccount = ListenerAccount.Persistence.authenticate(username, password);

        //then
        assertEquals(username, listenerAccount.getUsername(), "The username should match.");
        // Możesz również sprawdzić inne właściwości, jak ID:
        // assertEquals(expectedId, listenerAccount.getId(), "The ID should match.");
    }
}
