package org.example.oop.polimorfizm;

public class Main {
    public static void main(String[] args) {
        Flat homes[] = new Flat[10];
        int random;
        for (int i=0; i<10; i++){
            random = (int)(Math.random() * 3);
            if(random == 0){
                homes[i] = new Flat("Siedlce", "Lipowa");
            }
            else if(random == 1){
                homes[i] =new House("Warszawa", "Nowy Świat", 40.0f);
            }
            else if (random == 2){
                homes[i] = new Residence("Lublin", "Langiewicza", 30.0f, 10.0f);
            }
        }
        for (int j = 0; j<10; j++){
            if(homes[j] instanceof Residence){
                Residence residence = (Residence) homes[j];
                System.out.println(" Garage Size: " + residence.getGarageSize());
                System.out.println(residence.toString());
            }
            else if (homes[j] instanceof House){
                House house = (House) homes[j];
                System.out.println(" Parcel Size: " + house.getParcelSize());
                System.out.println(house.toString());
            }
            else if (homes[j] instanceof Flat){
                System.out.println(homes[j].toString());
            }
        }
    }
}
