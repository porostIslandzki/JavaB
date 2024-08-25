package org.example.tasks.EmployeeChallenge;

public class Administrator extends Employee{
    public String certificates;
    Administrator(String name, String surname, String certificates){
        this.name = name;
        this.surname = surname;
        this.certificates = certificates;
    }
    public void printInfo(){
        System.out.println("Name: " + name + " Surname: " + surname + " Certificates: " + certificates);
    }
}
