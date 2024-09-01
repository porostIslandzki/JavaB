package org.example.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    //tu będę się łączyć z tabelą school.sqlite
    private static final String SQCONN = "jdbc:sqlite:src/main/java/org/example/school.sqlite";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(SQCONN);
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return null;
    }
}