package org.example.tasks;

public class Continue {
    public static void main(String[] args) {
        /*Stwóz dowolną pętlę od 0 do 50. W każdej iteracji
         oblicz czy licznik jest podzielny przez 3 (rezta z dzielenia będzie 0),
         w takiej sytuacji przeskocz do kolejnej iteracji. Wyświetl
         wartość licznika po wykonaniu sprawdzenia.*/
        for(int i=0; i<=50; i++){
            if (i%3 ==0){
                continue;
            }
            System.out.println(i);
        }
    }
}
