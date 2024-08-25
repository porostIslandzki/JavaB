package org.example.bike_challenge;

public class MotorBike extends Bike{
    MotorBike(String brand, String name){
        super(brand, name);
        this.setType("MotorBike");
    }
    public void printInfo(){
        System.out.println("MotorBike: ");
        super.printInfo();
    }
}
