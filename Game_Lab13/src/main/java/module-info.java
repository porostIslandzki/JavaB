module org.example.game_lab13 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.game_lab13 to javafx.fxml;
    exports org.example.game_lab13;
}