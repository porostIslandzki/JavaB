package org.example.exeptions_challenge;

public class Main {
    public static void main(String[] args) {
    try{
        int value = 10 / 0;
    } catch (ArithmeticException exception){
        exception.printStackTrace();
    } finally {
        System.out.println("Finally");
    }
    }
}
