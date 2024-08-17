package org.example;

public class Ellipse extends Shape{

    private Point middle;
    private float rx; // Promień w osi X
    private float ry; //Promień w osi Y

    public Ellipse(Style style, Point middle, float rx, float ry) {
        super(style);
        this.middle = middle;
        this.rx = rx;
        this.ry = ry;
    }

    @Override
    public String toSvg() {
        return String.format("<ellipse cx=\"%f\" cy=\"%f\" rx=\"%f\" ry=\"%f\" fill=\"%s\" stroke=\"%s\" stroke-width=\"%f\" /",
                middle.x, middle.y, rx, ry, style.fillColor, style.strokeColor, style.strokeWidth
        );
    }
}
