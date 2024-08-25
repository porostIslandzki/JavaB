package org.example.oop.interface_extends_default_static_final;

public class Parrot implements Bird{
    @Override
    public int getNumLegs() {
        return 2;
    }

    @Override
    public String getName() {
        return "Parrot";
    }

    @Override
    public void eat() {
        System.out.println("Eating.");
    }

    @Override
    public void fly() {
        System.out.println("Parrot is flying");
    }
}
