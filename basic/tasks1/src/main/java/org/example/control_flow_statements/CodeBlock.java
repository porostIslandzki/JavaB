package org.example.control_flow_statements;

public class CodeBlock {
    public static void main(String[] args) {
        int b = 12;
        int num = 100;
        {
            //long num=1000; //powoduje błąd variable num is already defined
            long num2 = 1000;
            int a = 5;
            System.out.println("a: " +  a);
            System.out.println("b: " + b);
            // System.out.println("c: " + c); //powoduje błąd
        }
        int c = 7;
        int a = 99; // nie ma konfliktu nazw z ziemnną a w bloku





    }
}
