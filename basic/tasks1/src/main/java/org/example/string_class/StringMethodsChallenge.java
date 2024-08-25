package org.example.string_class;

public class StringMethodsChallenge {
    public static void main(String[] args) {
        String txt = "Ola ma kota";
        String s = txt.repeat(3);
        String s1 = s.replaceAll("Ola", "Kasia");
        System.out.println(s1);
        String s2 = s1.replaceFirst("Kasia", "Monika");
        System.out.println(s2);
        int index = s2.indexOf("Kasia");
        System.out.println(index);
        String strPart = s2.substring(index);
        System.out.println(strPart);
    }
}
