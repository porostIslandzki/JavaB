package org.example.loginapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    LoginModelClass loginModelClass = new LoginModelClass();

    @FXML //importuje
    private Label dbstatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<Option> combobox;
    @FXML
    private Button loginbutton;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        //we need to access some fields from fxml (username, password)
        //we access them by id
        //sprawdzamy czy jest połączenie z bazą
        if(this.loginModelClass.isDatabaseConnected()){
            this.dbstatus.setText("Connected");
        } else {
            this.dbstatus.setText("");
        }
    }
}

