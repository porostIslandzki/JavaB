package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Vec2[] points = {
                new Vec2(10, 10),
                new Vec2(50, 10),
                new Vec2(50, 50),
                new Vec2(10, 50)
        };

        Style polygonStyle = new Style("black", "red", 2.0f);
        Polygon polygon = new Polygon(points, polygonStyle);

        Style ellipseStyle = new Style("none", "blue", 1.5f);
        Ellipse ellipse = new Ellipse(ellipseStyle, new Vec2(75, 75), 50, 30);

        // Tworzenie dekoratorów z kolorem wypełnienia
        SolidFillShapeDecorator filledPolygon = new SolidFillShapeDecorator(polygon, "yellow");
        SolidFillShapeDecorator filledEllipse = new SolidFillShapeDecorator(ellipse, "green");

        // Generowanie SVG
        System.out.println(filledPolygon.toSvg("stroke=\"black\" stroke-width=\"2\""));
        System.out.println(filledEllipse.toSvg("stroke=\"blue\" stroke-width=\"1.5\""));
    }
}

