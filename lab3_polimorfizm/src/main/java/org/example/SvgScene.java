package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class SvgScene {
    private ArrayList<Shape> shapes = new ArrayList<>();
    private static SvgScene instance = null;
    private ArrayList<String> defs = new ArrayList<>();

    public void addDefs(String def){
        defs.add(def);
    }

    //Prywatny konstruktor, aby zapobiec
    //tworzeniu instancji z zewnątrz
    private SvgScene(){}
    public static SvgScene getInstance() {
        if (instance == null) {
            instance = new SvgScene();
        }
        return instance;
    }

    public void addShape(Shape shape){
        shapes.add(shape);
    }
    public void save(String path) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write("<HTML>");
            fileWriter.write("<body>");
            fileWriter.write("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n");  // Dodanie tagu <svg>
            // Zapisanie elementów <defs>
            if (!defs.isEmpty()) {
                fileWriter.write("<defs>\n");
                for (String def : defs) {
                    fileWriter.write("\t" + def + "\n");
                }
                fileWriter.write("</defs>\n");
            }
            for (Shape shape : shapes) {
                fileWriter.write("\t" + shape.toSvg("") + "\n");
            }
            fileWriter.write("</svg>\n");
            fileWriter.write("</body>");
            fileWriter.write("</HTML>");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    //W klasie SvgScene utwórz prywatne, statyczne pole SvgScene
    //instance, początkowo równe null. Napisz akcesor do tego pola.
    // Jeżeli znajduje się tam null, należy je zainicjalizować.
    //Teraz, gdy chcesz uzyskać dostęp do instancji klasy 'SvgScene",
    //możesz to zrobić w ten sposób:
    //SvgScene scene = SvgScene.getInstance();
    //scene.addShape(new Polygon(...));
    //scene.save("output.svg");

}
