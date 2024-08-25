package org.example.bike_challenge;

public class Main {
    public static void main(String[] args) {
        Bike bike = new Bike("Romet", "2000");
        bike.printInfo();
        MotorBike motorBike = new MotorBike("Scigacz", "Szybki");
        motorBike.printInfo();
    }
}
