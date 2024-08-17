package org.example;

public class SolidFilledPolygon extends Polygon{
    private String color;

    public SolidFilledPolygon(Vec2[] vec2s, Style style, String color) {
        super(vec2s, style);
        this.color = color;
    }

    @Override
    public String toSvg(String tag) {
        String formattedTag = String.format("fill=\"%s\" %s", color, tag);
        return super.toSvg(formattedTag);
    }
    //metoda toSvg w Polygon buduje listę punktów i używa parametru 'tag'
    //do sformatowania tagu SVG
    //tutaj metoda toSvg jest nadpisywana, formatując tag svg z kolorem
    //wypełnienia i dodatkowym parametrem tag
}
