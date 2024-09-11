public class WordClientController {

    @FXML
    private TextField filterField;

    @FXML
    private ListView<String> wordList;

    @FXML
    private Label wordCountLabel;
    
}


//Stwórz klasę kontrolera WordClientController.java,
// która będzie zawierała odniesienia do elementów interfejsu
// użytkownika z pliku FXML (takich jak TextField i ListView),
// a także będzie odpowiedzialna za aktualizowanie interfejsu,
// gdy nadejdzie nowe słowo z serwera.