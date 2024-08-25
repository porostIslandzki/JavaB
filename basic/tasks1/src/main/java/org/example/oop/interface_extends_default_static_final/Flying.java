package org.example.oop.interface_extends_default_static_final;

public interface Flying {
    public void fly();

    public default void flyHighter(){
        System.out.println("Flying higher");
    }
}
