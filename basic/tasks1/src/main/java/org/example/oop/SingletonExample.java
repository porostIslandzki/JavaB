package org.example.oop;

class DBConnection {
    private static DBConnection instance;
    private DBConnection(){}
    public static DBConnection getInstance(){
        if(instance == null){
            instance = new DBConnection();
        }
        return instance;
    }
}

public class SingletonExample {
    public static void main(String[] args) {
        DBConnection connection = DBConnection.getInstance();
    }
}
