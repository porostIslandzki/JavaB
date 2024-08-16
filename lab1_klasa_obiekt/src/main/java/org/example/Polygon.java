package org.example;

public class Polygon {
    private Point[] points;

    public Polygon(Point[] points) {
        this.points = points;
    }
    // Konstruktor kopiujący (głęboka kopia)
    public Polygon(Polygon other) {
        // Tworzymy nową tablicę punktów o takiej samej długości jak w oryginale
        this.points = new Point[other.points.length];

        // Kopiujemy każdy punkt indywidualnie
        for (int i = 0; i < other.points.length; i++) {
            Point originalPoint = other.points[i];
            this.points[i] = new Point(originalPoint.x, originalPoint.y);
        }
    }

    public String[] toSvg() {
        String[] strings = new String[points.length];
        for (int i = 0; i < points.length; i++) {
            int nextIndex = (i + 1) % points.length; // Ostatni punkt łączy się z pierwszym
            strings[i] = String.format(
                    "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" stroke=\"black\" />",
                    points[i].x, points[i].y, points[nextIndex].x, points[nextIndex].y
            );
        }
        return strings;
    }
}
