package org.example.loginapp;

import org.example.DBUtil.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModelClass {
    Connection connection;

    public LoginModelClass() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        if (this.connection == null){
            System.exit(1);
        }
    }

    public boolean isDatabaseConnected() {
        return this.connection != null;
    }

    public boolean isLogin(String user, String pass, String opt) throws Exception {

        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM login where username = ? and password = ? and division = ?"; //wybierasz username coś z tabeli login

        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, user); //sprawdzamy czy username z database jest takie samo jak user przekazane do metody
            pr.setString(2, pass);
            pr.setString(3, opt); //division

            rs = pr.executeQuery();

            boolean boll1;

            if(rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            return false;
        }
        //you need to close the connection after you create the connection

        finally {
            pr.close();
            rs.close();
        }
    }

}
//Obiekt odpowiadający za połączenie z bazą? Skoro to login
//model class to jakoś musimy się połączyć cn
//Tutaj chcemy wybrać czy logujemy się studentem czy adminem

//Konstruktor - sprawdzamy try catch czy udało się połączyć
//z bazą SQLite

//Sprawdzamy czy udało się połączyć z bazą? Czy connection nie jest null

//Potem booleanem sprawdzamy to samo i guess

//Sprawdzamy czy jest logged in czy nie

//Tej model class możemy użyć w controllclass żeby dostać się do danych