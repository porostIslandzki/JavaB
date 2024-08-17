package org.example;

public abstract class Shape {
    protected Style style;

    // Domyślny konstruktor
    public Shape() {
        this.style = new Style("none", "black", 1.0f); // lub inny domyślny styl
    }
    public Shape(Style style) {
        this.style = style;
    }
    //to jest abstrakcyjna metoda
    //więc musi być zaimplementowana przez
    public abstract String toSvg();
}
