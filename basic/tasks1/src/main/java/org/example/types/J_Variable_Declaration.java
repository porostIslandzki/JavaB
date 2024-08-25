package org.example.types;

public class J_Variable_Declaration {
    public static void main(String args[]){
        System.out.println("Hello World!");
        int number = 24;
        number = 98;
        System.out.println("Wartość zmiennej number:" + number);
        int data = -123;
        System.out.println("data:" + data);
        //1 bajt czyli 8 bitow, wartość z zakresu -128 do 127
        byte smallNum = 12;
        //2 bajty = 16 bitów, wartość -32768 do 32767
        short num2b = 32000;
        //4 bajty = 32 bity, najczęściej stosowany
        int number2 = -234234;
        //8 bajtów = 64 bity
        long bigNum = 998293L;
    }
}
