package org.example;

import java.util.ArrayList;
import java.util.List;

class Tuple<E, F> {
    public E color;
    public F offset;

    public Tuple(E color, F offset) {
        this.color = color;
        this.offset = offset;
    }
}

public class GradientFillShapeDecorator extends ShapeDecorator {
    private static int indexCounter = 0;
    private int index;
    private SvgScene svgScene;
    public List<Tuple<String, Float>> gradient = new ArrayList<>();

    public GradientFillShapeDecorator(Shape decoratedShape, SvgScene svgScene, List<Tuple<String, Float>> gradient) {
        super(decoratedShape);
        this.index = ++indexCounter;
        this.svgScene = svgScene;
        this.gradient = gradient;

        String defs = String.format("\t<linearGradient id=\"g%d\">\n", index);
        svgScene.addDefs(defs);

        for (Tuple<String, Float> stop : gradient) {
            svgScene.addDefs(String.format("\t\t<stop offset=\"%f\" style=\"stop-color:%s\" />\n", stop.offset, stop.color));
        }

        svgScene.addDefs("\t</linearGradient>\n");
    }

    @Override
    public String toSvg(String tag) {
        String gradientTag = String.format("fill=\"url(#g%d)\" ", index);
        return super.toSvg(gradientTag + tag);
    }

    public class Builder {
        private String color;
        private Float offset;

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setOffset(Float offset) {
            if (offset < 0 || offset > 1) {
                System.out.println("Zła wartość offsetu. Musi być w przedziale od 0 do 1.");
                return this;
            }
            this.offset = offset;
            return this;
        }

        public GradientFillShapeDecorator build() {
            if (color == null || offset == null) {
                System.out.println("Kolor i offset muszą być ustawione.");
                return null;
            }

            gradient.add(new Tuple<>(color, offset));
            return GradientFillShapeDecorator.this;
        }
    }
}
