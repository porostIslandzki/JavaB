package org.example.tasks.inheritance;

public class Computer extends Product {
    public Mouse mouse;
    public Keyboard keyboard;
    public Monitor monitor;
    public Computer() {
        mouse = new Mouse();
        keyboard = new Keyboard();
        monitor = new Monitor();
    }
}
