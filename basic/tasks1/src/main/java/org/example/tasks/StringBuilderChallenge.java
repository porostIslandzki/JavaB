package org.example.tasks;

public class StringBuilderChallenge {
    public static void main(String[] args) {
        //Stwórz tablicę kilku imion
        //W pętli połącz wszystkie imiona z pomocą StringBuffer
        //Zaprezentuj wynikowy łańcuch znaków w konsoli
        String strArr[] = {"Ania", "Kasia", "Beata", "Nina"};
        StringBuilder txt = new StringBuilder();
        for(String s : strArr){
            txt.append(s);
        }
        System.out.println(txt.toString());
    }
}
