package org.example.tasks.inheritance;

public class Shop {
    public static void main(String[] args) {
        Product product = new Product();
        Mouse mouse = new Mouse();
        VerticalMouse verticalMouse = new VerticalMouse();
        Monitor monitor = new Monitor();
        Keyboard keyboard = new Keyboard();
        Computer computer = new Computer();
        System.out.println(computer.monitor.resolution);
    }
}
