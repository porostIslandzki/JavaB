package org.example.control_flow_statements;

public class InfiniteLoop {
    public static void main(String[] args) {
        while(true){
            int random = (int)Math.round(10 * Math.random());
            System.out.println(random);

            if(random == 10) break; /*bez ifa pętla będzie wykonywać się w nieskońćzinosc*/
        }
        for( ; ;){
            int random = (int)Math.round(10 * Math.random());
            System.out.println(random);
            if (random == 10){
                System.out.println("Wyjście z pętli for");
                break;
            }
        }
    }
}
