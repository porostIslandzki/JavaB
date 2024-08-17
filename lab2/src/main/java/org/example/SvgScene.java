package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SvgScene {
    private ArrayList<Shape> shapes = new ArrayList<>();
    public void addShape(Shape shape){
        shapes.add(shape);
    }
    // Napisz funkcję save(String), która utworzy plik HTML w
    // ścieżce danej argumentem i zapisze do niego reprezentacje
    // wszystkich wielokątów znajdujących się na kanwie.
    public void save(String path) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write("<HTML>");
            fileWriter.write("<body>");
            fileWriter.write("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n");  // Dodanie tagu <svg>
            for (Shape shape : shapes) {
                fileWriter.write("\t" + shape.toSvg() + "\n");
            }
            fileWriter.write("</svg>\n");
            fileWriter.write("</body>");
            fileWriter.write("</HTML>");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
