package org.example.tasks.EmployeeChallenge;

public class Employee {
    String name;
    String surname;
    Employee(){}
    Employee(String name, String surname){
        this.name = name;
        this.surname = surname;
    }
    public void printInfo(){
        System.out.println("Name: " + name + " Surname: " + surname);
    }
}
