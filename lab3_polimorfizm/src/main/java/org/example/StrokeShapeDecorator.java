package org.example;

public class StrokeShapeDecorator extends ShapeDecorator{
    private String color;
    double width;

    public StrokeShapeDecorator(Shape decoratedShape, String color, double width) {
        super(decoratedShape);
        this.color = color;
        this.width = width;
    }

    @Override
    public String toSvg(String tag) {
        String formattedTag = String.format("stroke=\"%s\" stroke-width=\"%f\" %s", color, width, tag);
        return decoratedShape.toSvg(formattedTag);
    }
}
