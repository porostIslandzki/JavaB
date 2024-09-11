import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application  {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("view.fxml"));
        Parent root = loader.load(); //załaduj plik FXML i ustaw scenę
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        //tu musi być po

        //1. Program po włączeniu powinien wyświetlić okno interfejsu i
        //połączyć się z serwerem.
    }

    public static void main(String[] args) {
        WordBag wordBag = new WordBag();
        wordBag.populate();
        Server server = new Server(5000, wordBag);
        server.start();
        server.startSending();
    }
}

//Program po włączeniu powinien wyświetlić okno interfejsu i
// połączyć się z serwerem. Parametry połączenia mogą być zapisane
// w kodzie.