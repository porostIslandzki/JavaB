package shop;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    // Klasa pomocnicza do przechowywania par ID produktu i liczebności
    private static class Pair {
        private final int productId;
        private final int quantity;

        public Pair(int productId, int quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }

        public int productId() {
            return productId;
        }

        public int quantity() {
            return quantity;
        }
    }
    int ID;
    int acc_id;
    List<Pair> items;

    public Cart(int ID, int acc_id) {
        this.ID = ID;
        this.acc_id = acc_id;
        this.items = new ArrayList<>();
        System.out.println("Koszyk utworzony: ID= " + ID + ", ID Klienta = " + acc_id);
    }

    //Metoda dodająca produkt w określonej liczbie do koszyka
    public void add(int productId, int quantity){
        items.add(new Pair(productId, quantity));
        System.out.println("Dodano produkt: ID = " + productId + ", Ilość= " + quantity);
    }

    // Metoda zwracająca wartość koszyka
    public double price(List<Product> productList) {
        double totalPrice = 0.0;
        for (Pair item : items) {
            Product product = productList.stream()
                    .filter(p -> p.ID() == item.productId())
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Produkt nie znaleziony: ID = " + item.productId()));
            totalPrice += product.price() * item.quantity();
        }
        return totalPrice;
    }
}
//klasę Cart (id, konto kupującego,
// lista par: id produktu, liczebność). W klasie
// Cart zdefiniuj publiczne metody:
//- konstruktor, generujący informację
// o koszyku (id i id klienta),
//- add - dodającą produkt w określonej
// liczbie (mnożnik) do koszyka,
//- price - zwracającą wartość koszyka.