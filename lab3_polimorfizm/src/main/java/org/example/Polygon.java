package org.example;

public class Polygon implements Shape {
    private Vec2[] points;
    private Style style;

    public Polygon(Vec2[] points, Style style) {
        this.points = points;
        this.style = style != null ? style : new Style("none", "black", 1.0f);
    }

    // Konstruktor kopiujący (głęboka kopia)
    public Polygon(Polygon other) {
        this.points = new Vec2[other.points.length];
        this.style = new Style(other.style.fillColor, other.style.strokeColor, other.style.strokeWidth);
        for (int i = 0; i < other.points.length; i++) {
            Vec2 originalPoint = other.points[i];
            this.points[i] = new Vec2(originalPoint.x, originalPoint.y);
        }
    }

    @Override
    public String toSvg(String tag) {
        StringBuilder pointsString = new StringBuilder();
        for (Vec2 point : points) {
            pointsString.append(point.x).append(",").append(point.y).append(" ");
        }
        return String.format("<polygon points=\"%s\" %s />", pointsString.toString().trim(), tag);
    }
}
