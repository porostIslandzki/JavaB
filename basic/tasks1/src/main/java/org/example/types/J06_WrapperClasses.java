package org.example.types;

public class J06_WrapperClasses {
    public int add(int num1, int num2){
        var result = num1 + num2;
        return result;
    }
    public static void main(String[] args) {
        int a = 123;
        Integer number = Integer.valueOf(a);
        System.out.println(number.toString());
        System.out.println(number.floatValue());
    }
}
