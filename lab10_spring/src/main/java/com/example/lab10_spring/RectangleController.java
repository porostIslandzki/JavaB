package com.example.lab10_spring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("rectangle")
public class RectangleController {

    private final Rectangle rectangle = new Rectangle(
            10.8f,
            11.2f,
            6.8f,
            8.4f,
            "Blue"
    );

    @GetMapping("/get_rect")
    public Rectangle getRectangle(){
        return rectangle;
    }

    //Stwórz prywatną listę prostokątów.
    private final List<Rectangle> rectangles = new ArrayList<>();

    public RectangleController(){
        rectangles.add(
                new Rectangle(
                        30.5f,
                        40.5f,
                        100.6f,
                        80.7f,
                        "yellow"
                )
        );
        rectangles.add(
                new Rectangle(
                        30.0f,
                        40.2f,
                        100.6f,
                        70.8f,
                        "green"
                )
        );
    }

    //Napisz metodę, która dodaje prostokąt o
    // określonych w kodzie parametrach.
    @PostMapping("/post")
    public ResponseEntity<String> addRect(@RequestBody Rectangle rectangle){
        rectangles.add(rectangle);
        return new ResponseEntity<>("Rectangle added", HttpStatus.CREATED);
    }
    //curl -X POST http://localhost:4444/rectangle/post -H "Content-Type: application/json" -d "{\"x\":10.0,\"y\":20.0,\"width\":30.0,\"height\":40.0,\"color\":\"blue\"}"
    //Rectangle added

    //Napisz metodę, która zwróci listę prostokątów zmapowaną na JSON
    @GetMapping("/rectangles")
    public List<Rectangle> getRectangles(){
        return rectangles;
    }

    private SvgGenerator svgGenerator = new SvgGenerator();

    //Napisz metodę, która wygeneruje napis zawierający
    // kod SVG z prostokątami znajdującymi się na liście.
    @GetMapping("/rectanglesSVG")
    public String getRectanglesSVG(){
        String rectSVG = svgGenerator.
                generateSvg(rectangles);
        return rectSVG;
    }

    //GET z argumentem typu int,
    //zwracającą prostokąt w liście o podanym indeksie
    @GetMapping("{index}")
    public Rectangle getCertainRectangle(@PathVariable int index){
        if(index<0 || index >= rectangles.size()){
            return null;
        } else {
            return rectangles.get(index);
        }
    }

    //PUT z argumentem typu int i argumentem typu Rectangle,
    //modyfikującą istniejący na liście pod tym indeksem
    // prostokąt na prostokąt przekazany argumentem.
    @PutMapping("{index}")
    public ResponseEntity<String> updateRect(
            @PathVariable int index, @RequestBody Rectangle rectangle
    ){
        if(index<0 || index >= rectangles.size()){
            return ResponseEntity.notFound().build();
        } else {
            this.rectangles.set(index, rectangle);
            return ResponseEntity.ok("Rectangle updated successfuly");
        }
    }

    //DELETE  z argumentem typu int,
    // usuwającą prostokąt z listy z miejsca o podanym indeksie
    @DeleteMapping("{index}")
    public ResponseEntity<String> deleteProduct(@PathVariable int index) {
        if (index < 0 || index >= rectangles.size()) {
            return ResponseEntity.notFound().build();
        } else {
            this.rectangles.remove(index);
            return ResponseEntity.ok("Rectangle deleted");
        }
    }


}

//Napisz kontroler REST RectangleController posiadający
// metodę, której wywołanie spowoduje wyświetlenie obiektu
// Rectangle zmapowanego na JSON.