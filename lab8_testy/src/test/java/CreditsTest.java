import music.DatabaseConnection;
import music.ListenerAccount;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditsTest {

    private static int testAccountId;

    @BeforeAll
    public static void setUp() throws SQLException {
        // Inicjalizacja bazy danych i tworzenie tabeli
        DatabaseConnection.connect("listener_account.db"); // Podaj odpowiednią ścieżkę do bazy danych
        ListenerAccount.Persistence.init(); // Inicjalizacja tabel

        // Rejestracja użytkownika do testów
        testAccountId = ListenerAccount.Persistence.register("TestUser", "TestPassword");
    }

    @Test
    public void initialCredits_shouldBeZero() throws SQLException {
        //given
        int expectedCredits = 0;

        //when
        int actualCredits = ListenerAccount.Persistence.getCredits(testAccountId);

        //then
        assertEquals(expectedCredits, actualCredits, "The initial credits should be zero.");
    }

    @Test
    public void addCredits_shouldIncreaseCredits() throws SQLException {
        //given
        int creditsToAdd = 50;
        int expectedCredits = creditsToAdd; // Początkowo było 0, więc po dodaniu 50 powinno być 50

        //when
        ListenerAccount.Persistence.addCredits(testAccountId, creditsToAdd);
        int actualCredits = ListenerAccount.Persistence.getCredits(testAccountId);

        //then
        assertEquals(expectedCredits, actualCredits, "Credits should increase by the amount added.");
    }
}
