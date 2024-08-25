package org.example.oop;

class PointWithConstans{
    int x, y;
    final int width = 12;
    final int height;

    PointWithConstans(){
        height = 20;
    }

    PointWithConstans(int posX, int posY){
        this();
        x = posX;
        y = posY;
    }

    public void changePosition( int newX,
                              final int newY){
        newX = 30; //zmiana wartości argumentu
        x = newX;
        y = newY;
    }
}

public class FinalConstansExample {
    public static void main(String[] args) {
        PointWithConstans point = new PointWithConstans(33, 44);
        point.changePosition(55, 66);
        System.out.println("x: " + point.x + "y: " + point.y);
    }
}
