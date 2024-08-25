package org.example.string_class;

public class StringBasicExaples {
    public static void main(String[] args) {
        String s1 = "Hello world";
        String s2 = new String("String passed to construktor");
        String s3 = "test".repeat(3);
        System.out.println(s3);
        String s4 = String.join(".","kupa", "sraka", "rzygam tym");
        System.out.println(s4);

        char arr[] = {'0', 'L', '@'};
        String s5 = new String(arr);
    }
}
