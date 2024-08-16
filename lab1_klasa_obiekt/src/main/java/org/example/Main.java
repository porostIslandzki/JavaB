package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Point point1 = new Point(10.0f, 5.23f);
        Point point2 = new Point(12.56f, -8.5f);
        Segment segment = new Segment(point1, point2);
        Point point3 = new Point(5.6f, 4.0f);
        Segment[] segments = Segment.perpendicular(point3, segment);
        System.out.println(segments[0].toSvg());
        System.out.println(segments[1].toSvg());
        Point[] points = {
                new Point(10, 10),
                new Point(50, 10),
                new Point(50, 50),
                new Point(10, 50)
        };

        Polygon polygon = new Polygon(points);
        String[] svgLines = polygon.toSvg();

        for (String line : svgLines) {
            System.out.println(line);
        }

    }
}