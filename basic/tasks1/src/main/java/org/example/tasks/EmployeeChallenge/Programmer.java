package org.example.tasks.EmployeeChallenge;

public class Programmer extends Employee{
    public String languages;
    Programmer(String name, String surname, String languages){
        this.name = name;
        this.surname = surname;
        this.languages = languages;
    }
    public void printInfo(){
        System.out.println("Name: " + name + " Surname: " + surname + " Languages: " + languages);
    }
}