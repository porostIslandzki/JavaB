package music;

import javax.naming.AuthenticationException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ListenerAccount extends Account {
    public ListenerAccount(int id, String name) {
        super(id, name);
    }

    public Playlist createPlaylist(List<Integer> songIds) throws SQLException, NotEnoughCreditsException {
        Playlist playlist = new Playlist();

        for (int songId : songIds) {
            if (!ListenerAccount.Persistence.hasSong(this.id, songId)) {
                // Jeśli użytkownik nie posiada piosenki, próbujemy ją kupić
                buySong(songId);
            }

            Optional<Song> optionalSong = Song.Persistence.read(songId);
            if (optionalSong.isPresent()) {
                playlist.add(optionalSong.get());
            } else {
                throw new SQLException("Song with ID " + songId + " not found in the database.");
            }
        }

        return playlist;
    }

    public void buySong(int songId) throws NotEnoughCreditsException, SQLException {
        if (Persistence.hasSong(this.id, songId)) {
            // Jeżeli na koncie jest piosenka, nie robi nic
            return;
        }

        int credits = Persistence.getCredits(this.id); // Pobiera liczbę kredytów użytkownika
        int songCost = 1; // Koszt piosenki to 1 kredyt

        if (credits >= songCost) {
            // Jeżeli na koncie nie ma dodanej piosenki dodaje ją i zabiera jeden kredyt
            Persistence.addCredits(this.id, -songCost);
            Persistence.addSong(this.id, songId);
        } else {
            // Jeżeli na koncie nie ma piosenki, ale nie ma także kredytów, rzuca NotEnoughCreditsException
            throw new NotEnoughCreditsException("Not enough credits to buy song with ID: " + songId);
        }
    }

    public static class Persistence {
        public static void init() throws SQLException { //inicjalizuje bazy danych, tworząc tabele, jeśli jeszcze nie istnieją
            Account.Persistence.init();
            {
                String sql = "CREATE TABLE IF NOT EXISTS listener_account( " +
                        "id_account INTEGER NOT NULL PRIMARY KEY," +
                        "credits INTEGER NOT NULL)";
                PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
                statement.executeUpdate(); //listener_account przechowuje konta słuchaczy razem z kredytami
            }
            {
                String sql = "CREATE TABLE IF NOT EXISTS owned_songs( " +
                        "id_account INTEGER NOT NULL," +
                        "id_song INTEGER NOT NULL," +
                        "PRIMARY KEY (id_account, id_song))";
                PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
                statement.executeUpdate(); //owned songs przechowuje powiązania między
                //kontami użytkowników a zakupionymi piosenkami
            }
        }

        public static int register(String username, String password) throws SQLException {
            try {
                int id = Account.Persistence.register(username, password); //rejestruje nowego użytkownika, dodając go do tabeli account
                String sql = "INSERT INTO listener_account(id_account, credits) VALUES (?, 0)"; //po rejestracji tworzy dla niego wpis w tabeli listener_account z początkową liczbą kredytów o
                PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
                statement.setInt(1, id);
                statement.executeUpdate();
                return id; //zwraca id
            } catch (SQLException | RuntimeException e) {
                throw new RuntimeException(e);
            }
        }

        // Metoda do odczytu liczby kredytów
        public static int getCredits(int id) throws SQLException {
            String sql = "SELECT credits FROM listener_account WHERE id_account = ?";
            PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("credits");
            } else {
                throw new SQLException("No credits found for account ID: " + id);
            }
        }

        // Metoda do dodawania kredytów do konta
        public static void addCredits(int id, int amount) throws SQLException {
            int currentCredits = getCredits(id);
            String sql = "UPDATE listener_account SET credits = ? WHERE id_account = ?";
            PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setInt(1, currentCredits + amount);
            statement.setInt(2, id);
            statement.executeUpdate();
        }

        public static void addSong(int accountId, int songId) throws SQLException {
            //dodaje piosenkę do listy zakupionych piosenek owned_songs
            String sql = "INSERT INTO owned_songs VALUES(?, ?)";
            PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setInt(1, accountId);
            statement.setInt(2, songId);
            statement.executeUpdate();
        }

        public static boolean hasSong(int accountId, int songId) throws SQLException {
            //czy istnieje wpis w tabeli owned_songs dla użytkownika i piosenki
            String sql = "SELECT * FROM owned_songs WHERE id_account = ? AND id_song = ?";
            PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setInt(1, accountId);
            statement.setInt(2, songId);
            return statement.executeQuery().next();
        }

        public static ListenerAccount authenticate(String username, String password) throws AuthenticationException {
            //porównuje hasło wprowadzone przez użytkownika z hasłem zapisanym w bazie
            Account account = Account.Persistence.authenticate(username, password);
            return new ListenerAccount(account.getId(), account.getUsername());
        }
    }
}
