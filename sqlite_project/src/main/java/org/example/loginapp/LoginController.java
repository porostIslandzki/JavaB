package org.example.loginapp;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.admin.AdminController;
import org.example.students.StudentsController;

import javafx.event.ActionEvent;
import java.io.IOException;
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
    private ComboBox<Option> combobox; //setting options from our enum
    @FXML
    private Button loginbutton;
    @FXML
    private Label loginStatus;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        //we need to access some fields from fxml (username, password)
        //we access them by id
        //sprawdzamy czy jest połączenie z bazą
        System.out.println("Initialize method called");
        if(this.loginModelClass.isDatabaseConnected()){
            this.dbstatus.setText("Connected");
        } else {
            this.dbstatus.setText("Not Connected");
        }
        this.combobox.setItems(FXCollections.observableArrayList(Option.values()));
    }

    //piszemy metodę żeby Login miał on action event
    //ogólnie w tym miejscu przechodzimy do czynności zalogowania się
    @FXML
    public void login(ActionEvent event){
        //sprawdzamy czy udało się zalogować
        try {
            System.out.println("Username: " + this.username.getText());
            System.out.println("Password: " + this.password.getText());
            System.out.println("Option: " + this.combobox.getValue());
            //to sprawdzamy tą metodę boolean z loginmodelclass ze
            //string password and option
            if(this.loginModelClass.isLogin(this.username.getText(), this.password.getText(), ((Option)this.combobox.getValue()).toString())){
               // Stage stage = (Stage) this.loginbutton.getScene().getWindow();
                //stage.close();
                switch(((Option)this.combobox.getValue()).toString()) {
                    case "Admin":
                        adminLogin(); //metoda do zalogowania się admina
                        break;
                    case "Student":
                        studentLogin();
                        break;
                }
                } else {
                    loginStatus.setText("Wrong Creditials");
                }
        } catch (Exception localExeption){

        }
    }

    public void studentLogin() {
        System.out.println("Opening Student Dashboard");
        try {
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/students/studentFXML.fxml").openStream());

            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Student Dashboard");
            userStage.setResizable(false);
            userStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
 //musimy stworzyć pakiet dla każdego z nich

    public void adminLogin() {
        System.out.println("Opening Admin Dashboard");
        try {
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminroot = (Pane) adminLoader.load(getClass().getResource("/admin/Admin.fxml").openStream());

            Scene scene = new Scene(adminroot);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.setResizable(false);
            adminStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //sprawdza czy username is for student czy for admin


}

