package org.example.oop;

class CarObj {
    private String modelName;
    private String manufacturer;
    private int year;

    public static class CarObjBuilder {
        private CarObj car = new CarObj();
        public CarObjBuilder setModelName(String modelName){
            car.modelName = modelName;
            return this;
        }
        public CarObj getCar(){
            return this.car;
        }
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

public class PublicStaticInnerClass {
    public static void main(String[] args) {
        CarObj car = new CarObj.CarObjBuilder().setModelName("T").getCar();

    }
}
