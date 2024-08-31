package org.example.loginapp;

public enum Option {
    //do wybrania Admin lub Student z Combobox
    Admin,
    Student;

    private Option() {}

    public String value() {
        return name();
    }

    public static Option fromValue(String v){
        return valueOf(v);
    }
}
