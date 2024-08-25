package org.example.oop;

public class SomeClass {
    public static void main(String[] args) {
        SomeClass2 someClass2 = new SomeClass2();
        someClass2.printInfo();

        Car car = new Car();
        car.printInfo();

        Car car2 = new Car("Dodge", 230.0f);
        car2.printInfo();
    }
}
