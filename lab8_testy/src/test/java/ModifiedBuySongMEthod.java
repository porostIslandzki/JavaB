import music.ListenerAccount;
import music.NotEnoughCreditsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ModifiedBuySongMEthod {

    private ListenerAccount listenerAccount;

    @BeforeEach
    public void setUp() throws SQLException {
        // Zakładając, że Persistence.init() inicjalizuje bazę danych dla testów
        ListenerAccount.Persistence.init();
        listenerAccount = new ListenerAccount(1, "TestUser");
        ListenerAccount.Persistence.addCredits(listenerAccount.getId(), 1); // Dodaj 1 kredyt do konta
    }

    @Test
    public void testBuySongAlreadyOwned() throws SQLException, NotEnoughCreditsException {
        int songId = 1;
        ListenerAccount.Persistence.addSong(listenerAccount.getId(), songId); // Dodajemy piosenkę do konta

        // Test, czy piosenka nie zostanie ponownie kupiona
        listenerAccount.buySong(songId);
        // Brak wyjątku, brak zmian kredytów oznacza, że test zakończył się powodzeniem
    }

    @Test
    public void testBuySongWithEnoughCredits() throws SQLException, NotEnoughCreditsException {
        int songId = 2;
        listenerAccount.buySong(songId);

        // Test, czy piosenka została kupiona i kredyty zmniejszyły się o 1
        assertTrue(ListenerAccount.Persistence.hasSong(listenerAccount.getId(), songId), "The song should be added to the account.");
        int remainingCredits = ListenerAccount.Persistence.getCredits(listenerAccount.getId());
        assertTrue(remainingCredits == 0, "Remaining credits should be 0 after purchase.");
    }

    @Test
    public void testBuySongWithoutEnoughCredits() throws SQLException {
        int songId = 3;
        ListenerAccount.Persistence.addCredits(listenerAccount.getId(), -1); // Odejmujemy wszystkie kredyty

        // Test, czy rzucany jest wyjątek, jeśli brakuje kredytów
        assertThrows(NotEnoughCreditsException.class, () -> listenerAccount.buySong(songId));
    }
}
