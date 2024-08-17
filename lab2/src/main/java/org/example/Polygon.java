package org.example;

public class Polygon extends Shape{
    private Point[] points;


    public Polygon(Point[] points, Style style) {
        super(style); //musimy wywołać konstruktor klasy shape
        this.points = points;
    }
    // Konstruktor kopiujący (głęboka kopia)
    public Polygon(Polygon other) {
        super(other.style);
        // Tworzymy nową tablicę punktów o takiej samej długości jak w oryginale
        this.points = new Point[other.points.length];

        // Kopiujemy każdy punkt indywidualnie
        for (int i = 0; i < other.points.length; i++) {
            Point originalPoint = other.points[i];
            this.points[i] = new Point(originalPoint.x, originalPoint.y);
        }
    }

    public String toSvg() {
        StringBuilder svgBuilder = new StringBuilder();
        svgBuilder.append("<polygon points=\"");
        for (Point point : points) {
            svgBuilder.append(point.x).append(",").append(point.y).append(" ");
        }
        svgBuilder.append("\" ").append(style.toSvg()).append(" />");
        return svgBuilder.toString();
    }

    public Polygon square(Segment segment1, Style style){
        float newX = (segment1.getPoint1().x + segment1.getPoint2().x)/2;
        float newY = (segment1.getPoint1().y + segment1.getPoint2().y)/2;
        Point middle = new Point(newX, newY);
        float length = segment1.segmentSize()/2;
        Segment[] segment2 = Segment.perpendicular(middle, segment1, length);
        Point point2 = segment2[0].getPoint2();
        Point point4 = segment2[1].getPoint2();
        Point point1 = segment1.getPoint1();
        Point point3 = segment1.getPoint2();
        Point[] squarePoints = {point1, point2, point3, point4};
        return  new Polygon(squarePoints, style);
    }

}
