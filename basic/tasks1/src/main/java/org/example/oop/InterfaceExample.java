package org.example.oop;

public class InterfaceExample {
    public static void main(String[] args) {
        CarVehicle carVehicle = new CarVehicle();
        carVehicle.move();
        Vehicle vehicle = new CarVehicle();
        vehicle.turn();
        CarVehicle someCar = (CarVehicle) vehicle;
        System.out.println(someCar.getTopSpeed());
        Plane plane = new Plane();
        Vehicle planeVehicle = plane;
        System.out.println(planeVehicle.getTopSpeed());

        Vehicle vehicles[] = new Vehicle[3];
        vehicles[0] = carVehicle;
        vehicles[1] = someCar;
        vehicles[2] = plane;

        vehicles[2].move();

        if(vehicles[2] instanceof Plane){
            Plane somePlane = (Plane) vehicles[2];
            somePlane.turn();
        }
    }
}
