package org.example.oop.interface_extends_default_static_final;

public interface Eating {
    public void eat();
    public default void searchForFood(){
        System.out.println("Searching for food.");
    }
}
