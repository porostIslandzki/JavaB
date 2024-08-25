package org.example.tasks;

class Roadster {
    float topSpeed;
    float acceleration;
    float crazyTopSpeed;
    float crazyAcceleration;
    boolean crazyMode;

    public Roadster(float topSpeed, float acceleration) {
        this.topSpeed = topSpeed;
        this.acceleration = acceleration;
    }

    public Roadster(float topSpeed, float acceleration,
                    float crazyTopSpeed, float crazyAcceleration) {
        this.topSpeed = topSpeed;
        this.acceleration = acceleration;
        this.crazyTopSpeed = crazyTopSpeed;
        this.crazyAcceleration = crazyAcceleration;
        this.crazyMode = true;
    }

    public float getTopSpeed() {
        return topSpeed;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public float getTopSpeed(boolean crazyMode){
        if(crazyMode){
            return  crazyTopSpeed;
        }
        else {
            return topSpeed;
        }
    }

    public float Acceleration (boolean crazyMode){
        if(crazyMode){
            return crazyAcceleration;
        }
        else {
            return acceleration;
        }
    }
}

public class RoadsterChallenge {
    public static void main(String[] args) {

        Roadster car1 = new Roadster(250.0f, 4.0f);
        System.out.println("Car1 topSpeed: " + car1.getTopSpeed());

        Roadster car2 = new Roadster(250.0f, 4.0f, 300.0f, 1.9f);
        System.out.println("Car2 crazyTopSpeed: " + car2.getTopSpeed(true));

    }
}
