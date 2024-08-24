package org.example;

import java.util.AbstractList;
import java.util.LinkedList;
//Napisz klasę szablonową CustomList implementującą strukturę
// listy jednokierunkowej ze wskaźnikami na początek i koniec.

class Node<T> {

    T data;
    Node next;
    Node(T d) {
        this.data = d ;
        this.next = null;
    } //przechowuje dane oraz wskaźnik na kolejny element w liście
}

//Lista jednokierunkowa składa się z węzłów("Node"), gdzie każdy węzeł
//zawiera: 'data' - przechowywaną wartość, 'next' - odniesienie do następnego węzła

public class CustomList <T> extends AbstractList<T> {

    @Override
    public boolean add(T t) { // nadpisz metode: ma działać tak samo jak addLast
        //i zwracać prawdę
        Node<T> addNode = new Node<>(t);
        if(head == null){
            head = addNode;
            tail = addNode;
        } else {
            tail.next = addNode;
            tail = addNode;
        }
        return true;
    }

    @Override
    public int size() { //metoda ma zwracać rozmiar tablicy
        int counter = 0;
        Node<T> countingNode = head;
        //tail nie zawsze jest końcem listy, dlatego iterujemy do null
        while (countingNode != null) {
            counter++;
            countingNode = countingNode.next;
        }
        return counter;
    }

    @Override
    public T get(int index) { //zwraca wartość w węźle o podanym indeksie
        //rzucimy wyjątkami, kiedy: index będzie ujemny, index będzie większy od zakresu listy
        if(index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative: " + index);
        }

        int counter = 0;
        Node<T> countingNode = head;

        while (countingNode != null){
            if(counter == index) {
                return countingNode.data;
            }
            counter++;
            countingNode = countingNode.next;
        }

        throw new IndexOutOfBoundsException("Index out of range: " + index);
    }

    private Node<T> head; //wskaźnik na pierwszy węzeł
    private Node<T> tail; //wskaźnik na ostatni węzeł

    public CustomList(Node<T> head, Node<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    //void addLast(T value) - dodającą wartość na koniec listy,
    public void addLast(T value){
        Node<T> node = new Node<>(value); //tworzę nowy węzeł 'Node', który przechowuje
        // wartość value
        //wskaźnik next tego węzła jest domyślnie ustawiony na null,
        //ponieważ nowy węzeł nie wskazuje jeszcze na żaden kolejny węzeł
        if (head == null){
            head = node;
            tail = node; //jeśli lista jest pusta, zarówno head jak i tail
            //są ustawione na nowo utworzony węzeł
        }
        else {
            tail.next = node; //obecny ostatni węzeł wskazuje
            //teraz na nowy węzeł
            tail = node;
            //następnie aktualizuję wskaźnik tail
            //aby wskazywał na nowy węzeł
        }
    }

    //T getLast() - zwracającą wartość z końca listy
    public T getLast(){
        if (tail == null){
            return null;
        } else {
            return tail.data;
        }
    }

    //void addFirst(T value) - dodającą wartość na początek listy
    public void addFirst(T value){
        Node<T> node = new Node<>(value);
        if(head == null){
            head = node;
            tail = node;
        } else {
            node.next = head; //nowy węzeł staje się pierwszym węzłem
            //a jego wskaźnik next wskazuje na poprzedni pierwszy węzeł head
            head = node;
        }
    }

    //T getFirst() - zwracającą wartość z początku listy
    public T getFirst(){
        if(head == null){
            return null;
        } else {
            return head.data;
        }
    }

    //T removeFirst() - zwracającą i usuwającą element z początku listy
    public T removeFirst(){
        if (head == null){
            return null;
        }
        else {
            T headValue = head.data;
            head = head.next;
            return headValue;
        }
    }

    //T removeLast() - zwracającą i usuwającą element z końca listy
    public T removeLast(){
        if (head == null) { //Lista jest pusta
            return null;
        }

        if(head == tail){ //Lista ma tylko jeden element
            T value = head.data;
            head = null;
            tail = null;
            return value;
        }

        //Lista ma więcej niż jeden el
        Node<T> newTail = head;
        while (newTail.next != tail) { //szukam przedostatniego elementu
            newTail = newTail.next;
        }
        T value = tail.data;
        tail = newTail; //Przedostatni element staje się ostatnim
        tail.next = null; //usuwam referencję do poprzedniego ostatniego

        return value;
    }

    //Napisz metodę: Iterator<T> iterator() -
    // zwracającą iterator do listy. Zdefiniuj w niej iterator

    


}
