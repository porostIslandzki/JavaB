package org.example.oop;

public class Car extends SomeClass2{
    Car(){
        super();
        this.type = "Car";
        this.manufacturer = "Ford";
        this.topSpeed = 200;
    }
    Car(String manufacturer, float topSpeed){
        this();
        this.manufacturer = manufacturer;
        this.topSpeed = topSpeed;
    }
}
