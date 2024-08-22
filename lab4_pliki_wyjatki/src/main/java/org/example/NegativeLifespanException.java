package org.example;

//Napisz klasę NegativeLifespanException. Rzuć jej obiekt
// jako wyjątek,
// jeżeli data śmierci osoby jest wcześniejsza niż data urodzin.
// Wywołanie metody getMessage() powinno wyświetlić stosowną
// informację zawierającą przyczyny rzucenia wyjątku.
public class NegativeLifespanException extends Exception{
    public NegativeLifespanException (String message) {
        super(message);
    }

}
