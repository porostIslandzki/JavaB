package org.example;

public class Style {
    final public String fillColor;
    final public String strokeColor;
    final public Float strokeWidth;

    public Style(String fillColor, String strokeColor, Float strokeWidth) {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }
    public String toSvg() {
        return String.format("fill=\"%s\" stroke=\"%s\" stroke-width=\"%f\"", fillColor, strokeColor, strokeWidth);
    }
}
