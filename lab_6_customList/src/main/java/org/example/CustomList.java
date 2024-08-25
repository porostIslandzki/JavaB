package org.example;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
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
        if (head == null) {
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
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative: " + index);
        }

        int counter = 0;
        Node<T> countingNode = head;

        while (countingNode != null) {
            if (counter == index) {
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

    public CustomList() {
        this.head = null;
        this.tail = null;
    }

    //void addLast(T value) - dodającą wartość na koniec listy,
    public void addLast(T value) {
        Node<T> node = new Node<>(value); //tworzę nowy węzeł 'Node', który przechowuje
        // wartość value
        //wskaźnik next tego węzła jest domyślnie ustawiony na null,
        //ponieważ nowy węzeł nie wskazuje jeszcze na żaden kolejny węzeł
        if (head == null) {
            head = node;
            tail = node; //jeśli lista jest pusta, zarówno head jak i tail
            //są ustawione na nowo utworzony węzeł
        } else {
            tail.next = node; //obecny ostatni węzeł wskazuje
            //teraz na nowy węzeł
            tail = node;
            //następnie aktualizuję wskaźnik tail
            //aby wskazywał na nowy węzeł
        }
    }

    //T getLast() - zwracającą wartość z końca listy
    public T getLast() {
        if (tail == null) {
            return null;
        } else {
            return tail.data;
        }
    }

    //void addFirst(T value) - dodającą wartość na początek listy
    public void addFirst(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head; //nowy węzeł staje się pierwszym węzłem
            //a jego wskaźnik next wskazuje na poprzedni pierwszy węzeł head
            head = node;
        }
    }

    //T getFirst() - zwracającą wartość z początku listy
    public T getFirst() {
        if (head == null) {
            return null;
        } else {
            return head.data;
        }
    }

    //T removeFirst() - zwracającą i usuwającą element z początku listy
    public T removeFirst() {
        if (head == null) {
            return null;
        } else {
            T headValue = head.data;
            head = head.next;
            return headValue;
        }
    }

    //T removeLast() - zwracającą i usuwającą element z końca listy
    public T removeLast() {
        if (head == null) { //Lista jest pusta
            return null;
        }

        if (head == tail) { //Lista ma tylko jeden element
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

    public Iterator<T> iterator() {
        return new CustomIterator();
    }

    //Klasa wewnętrzna implementująca interfejs Iterator<T>

    private class CustomIterator implements Iterator<T> {

        private Node<T> currentNode;

        public CustomIterator() {
            this.currentNode = head; //Sortujemy od początku listy
        }

        @Override
        public boolean hasNext() {
            return currentNode != null; //oznacza, że można jeszcze
            //pobrać element
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                T data = currentNode.data;
                currentNode = currentNode.next;
                return data;
            }
        }

        //Stream<T> stream() - zwracającą strumień z zawartością listy.
        public Stream<T> stream() {
            return StreamSupport.stream(
                    Spliterators.spliteratorUnknownSize(iterator(), 0), false);
        }

    }

    //Napisz statyczną metodę szablonową, która przyjmie jako
    // parametry Listę obiektów typu szablonowego T oraz obiekt
    // Class. Metoda powinna zwrócić listę obiektów, które należą do wskazanej klasy.
    //Następnie zmodyfikuj metodę tak, aby filtrowała obiekty, które dziedziczą
    // (bezpośrednio lub pośrednio) po wskazanej klasie.

    //metoda szablonowa to metoda w klasie bazowej
    //która definiuje szkic algorytmu
    //pozostawiając szczegóły implementacji niektórych kroków do
    //zdefiniowania w klasach pochodnych

    protected CustomList<T> belongsToClass(Class<?> oClass) {
        CustomList<T> filteredList = new CustomList<>();

        Node<T> currentNode = head; //iteruję przy pomocy currentNode
        //zaczynając od head
        while (currentNode != null) {
            if (oClass.isInstance(currentNode.data)) { //metoda ta sprawdza
                //czy dany obiekt jest instancją zadanej klasy lub jej podklasy
                filteredList.add(currentNode.data);
            }
            currentNode = currentNode.next;
        }

        return filteredList;
    }

    //Napisz predykat, który porówna, czy testowana zmienna znajduje
    // się w otwartym przedziale, zdefiniowanym jego granicami.
    //Korzystając z niego napisz metodę statyczną, która
    // dla listy oraz granic
    // zakresu danych typem szablonowym zwróci liczbę elementów
    // w tej liście, spełniających warunek predykatu.

    public class RangeUtils {

        // Predykat sprawdzający, czy wartość znajduje się w otwartym przedziale (minValue, maxValue)
        public static <T extends Comparable<T>> Predicate<T> isWithinRange(T minValue, T maxValue) {
            return value -> value.compareTo(minValue) > 0 && value.compareTo(maxValue) < 0;
        }

        // Metoda zliczająca elementy w liście, które spełniają warunek predykatu
        public static <T extends Comparable<T>> long countWithinRange(List<T> list, T minValue, T maxValue) {
            Predicate<T> rangePredicate = isWithinRange(minValue, maxValue);
            return list.stream().filter(rangePredicate).count();
        }
    }

    //Napisz komparator, który porówna dwie kolekcje pod względem
    //liczby ich elementów. Następnie zmodyfikuj go tak,
    // aby przyjmował wyłącznie kolekcje liczb i porównywał
    // je pod względem ich sumy.

    public class CollectionsComparator implements Comparator<Collection<? extends Number>> {
        @Override
        public int compare(Collection<? extends Number> c1, Collection<? extends Number> c2) {
            double sum1 = 0;
            double sum2 = 0;
            Iterator<? extends Number> iterator1 = c1.iterator();
            while (iterator1.hasNext()){
                sum1 = sum1 + iterator1.next().doubleValue();
            }
            Iterator<? extends Number> iterator2 = c2.iterator();
            while (iterator2.hasNext()){
                sum2 = sum2 + iterator2.next().doubleValue();
            }
            return Double.compare(sum1, sum2);
        }

    }

    //chat gpt znów się drze że da się zrobić prościej
    //w metodzie compare:
    // for(Number num : c1) {
    // sum1 += num.doubleValue();
    //}
    // for(Number num : c2) {
    // sum2 += num.doubleValue();
    //}
}
