package shop;

import auth.Account;

import java.util.ArrayList;
import java.util.List;

public class ShopClient {
    private final Account account;
    private final List<Cart> carts;

    public ShopClient(Account account) {
        this.account = account;
        this.carts = new ArrayList<>();
    }

    public int getId() {
        return account.ID();
    }

    public String getName() {
        return account.name();
    }

    public List<Cart> getCarts() {
        return carts;
    }
}
