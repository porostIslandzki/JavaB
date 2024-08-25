package org.example.oop;

class Triangle{
    float a; //długość podstawy
    float h; //wysokość trójkąta
    float surfaceArea; //pole powierzchni

    Triangle(float base, float height){
        a = base;
        h = height;
    }
}

class MyMath{
    public void add(int a, int b){
        //ta metoda przyjmuje parametry przez wartosc
        //przekazane do tej metody wartosci beda kopiami
        //nie bedziemy mogli zmienic ich u zrodla
        int result = a+b;
        System.out.println("result: " + result);
    }
}

public class InvokingMethods {
    public static void main(String[] args) {
        MyMath math = new MyMath();
        int a = 10;
        int b = 7;
        math.add(a, b);
        Triangle triangle = new Triangle(10.0f, 5.0f);
    }
}
