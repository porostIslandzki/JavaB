package org.example.tasks.EmployeeChallenge;

public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee("Adam", "Kowalski");
        Administrator administrator = new Administrator("Nikodem", "Kiselov", "4");
        Programmer programmer = new Programmer("Sigma", "Kwiątek", "java");
        employee.printInfo();
        administrator.printInfo();
        programmer.printInfo();
    }
}
