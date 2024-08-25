package org.example.oop;

public class CarVehicle implements Vehicle{
    public void move(){
        System.out.println("car is moving!");
    }

    @Override
    public void stop() {
        System.out.println("Car stopped");
    }

    @Override
    public void turn() {
        System.out.println("Car is turning");
    }

    @Override
    public float getTopSpeed() {
        return 0;
    }
}
