package com.example.lab10_spring;

import com.example.lab10_spring.Rectangle;

import java.util.List;

public class SvgGenerator {

    public String generateSvg(List<Rectangle> rectangles) {
        StringBuilder svgBuilder = new StringBuilder();

        // Dodaj nagłówek SVG
        svgBuilder.append("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n");

        // Dodaj każdy prostokąt do SVG
        for (Rectangle rect : rectangles) {
            svgBuilder.append("<rect x=\"")
                    .append(rect.x())
                    .append("\" y=\"")
                    .append(rect.y())
                    .append("\" width=\"")
                    .append(rect.width())
                    .append("\" height=\"")
                    .append(rect.height())
                    .append("\" fill=\"")
                    .append(rect.color())
                    .append("\" />\n");
        }

        // Zamknij znacznik SVG
        svgBuilder.append("</svg>");

        return svgBuilder.toString();
    }
}

