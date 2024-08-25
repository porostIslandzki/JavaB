package org.example.oop;

public class Plane implements Vehicle{
    @Override
    public void move() {
        System.out.println("Plane is moving");
    }

    @Override
    public void stop() {
        System.out.println("Plane stopped");
    }

    @Override
    public void turn() {
        System.out.println("Plane turned");
    }

    @Override
    public float getTopSpeed() {
        return 800;
    }
}
