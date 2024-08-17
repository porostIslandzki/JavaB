package org.example;

public class Ellipse implements Shape {
    private Vec2 middle;
    private Style style;
    private float rx; // Promień w osi X
    private float ry; // Promień w osi Y

    public Ellipse(Style style, Vec2 middle, float rx, float ry) {
        this.style = style;
        this.middle = middle;
        this.rx = rx;
        this.ry = ry;
    }

    @Override
    public String toSvg(String tag) {
        return String.format(
                "<ellipse cx=\"%f\" cy=\"%f\" rx=\"%f\" ry=\"%f\" %s />",
                middle.x, middle.y, rx, ry, tag
        );
    }
}
