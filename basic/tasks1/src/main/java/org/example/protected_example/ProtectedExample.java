package org.example.protected_example;

import org.example.protected_example.package2.Computer;

public class ProtectedExample {
    public static void main(String[] args) {
        Computer computer = new Computer();
        //computer.name = "sdf; //brak dostepu z innego pakietu
        computer.setName("Dell");
        System.out.println(computer.getName());
        }
}
