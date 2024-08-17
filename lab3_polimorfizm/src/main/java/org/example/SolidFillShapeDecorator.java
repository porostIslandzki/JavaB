package org.example;

public class SolidFillShapeDecorator extends ShapeDecorator{
    private String color;

    public SolidFillShapeDecorator(Shape decoratedShape, String color) {
        super(decoratedShape);
        this.color = color;
    }

    @Override
    public String toSvg(String tag) {
        String formattedTag = String.format("fill=\"%s\" %s", color, tag);
        return decoratedShape.toSvg(formattedTag);
    }
}
