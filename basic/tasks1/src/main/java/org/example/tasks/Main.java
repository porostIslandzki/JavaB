package org.example.tasks;

enum Model {
    toyota,
    bmw,
    mustang,
    opel;
}

enum Brand {
    germany,
    usa,
    spain,
    china
}

class  Car{
    Model model;
    Brand brand;
    String color;

    public Car(Model model, Brand brand, String color) {
        this.model = model;
        this.brand = brand;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void printInfo(){
        System.out.println("Kolor: " + color);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
    }
}
class CarFactoryChallenge {
        public Car buildCar(Model model, Brand brand){
            String colors[] = {"czerwony", "biały", "czarny"};
            int index = (int) Math.floor(Math.random() * colors.length);
            String color = colors[index];
            return new Car(model, brand, color);
        }
}
public class Main {
    public static void main(String[] args) {
        CarFactoryChallenge carFactoryChallenge = new CarFactoryChallenge();
        Car car1 = carFactoryChallenge.buildCar(Model.bmw, Brand.germany);
        car1.printInfo();
        System.out.println("Car color: " + car1.getColor());
    }

}
