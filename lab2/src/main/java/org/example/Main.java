package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Style style = new Style("red", "black", 2.0f);
        Point center = new Point(100, 50);
        Ellipse ellipse = new Ellipse(style, center, 50, 30);

        Point[] points = {
                new Point(10, 10),
                new Point(50, 10),
                new Point(50, 50),
                new Point(10, 50)
        };
        Polygon polygon = new Polygon(points, style);

        SvgScene scene = new SvgScene();
        scene.addShape(ellipse);
        scene.addShape(polygon);
        scene.save("scene.html");
    }
}
