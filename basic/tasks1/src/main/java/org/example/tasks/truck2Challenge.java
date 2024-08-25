package org.example.tasks;

class Truck {
    String producent;
    String model;
    int wheel_num;
    String color;
    final int TOP_SPEED = 100;
    final float ACCELERATION;

    Truck(){
        producent = "Freightliner";
        model = "9664";
        wheel_num = 6;
        ACCELERATION = 60;
    }
    Truck(String color){
        this();
        this.color = color;
    }
    public void printInfo(){
        System.out.println("Model: " + model + " Producent: " + producent + " Color: " + color);
    }
}
public class truck2Challenge {
    public static void main(String[] args) {
        Truck truck = new Truck("blue");
        truck.printInfo();
    }
}
