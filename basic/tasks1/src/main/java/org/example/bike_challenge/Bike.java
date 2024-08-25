package org.example.bike_challenge;

public class Bike {
    public String brand;
    protected String name;
    private String type;
    Bike(){}
    Bike(String brand, String name){
        this.brand = brand;
        this.name = name;
        this.type = "bike";
    }
    public void setType(String type) {
        this.type = type;
    }
    public void printInfo(){
        System.out.println("Brand: " + brand + " Name: " + name + " Brand: " + brand);
    }
}
