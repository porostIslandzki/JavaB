package programs;

import java.util.Scanner;

public class BasicScanner {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Podaj swoje imię");
        String name = in.nextLine();
        System.out.println("Twoje imię to: " + name);
    }
}
