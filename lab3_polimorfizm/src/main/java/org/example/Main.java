package org.example;

public class Main {
    public static void main(String[] args) {
        Vec2[] points = {
                new Vec2(10, 10),
                new Vec2(50, 10),
                new Vec2(50, 50),
                new Vec2(10, 50)
        };

        Style polygonStyle = new Style("yellow", "black", 2.0f);
        Polygon polygon = new Polygon(points, polygonStyle);

        // Tworzenie dekoratora z translacją i rotacją
        TransformationDecorator transformedPolygon = new TransformationDecorator.Builder()
                .setShape(polygon)
                .setTranslate(new Vec2(20, 30))
                .setRotate(45, new Vec2(25, 25))
                .build();

        System.out.println(transformedPolygon.toSvg(""));
    }
}
