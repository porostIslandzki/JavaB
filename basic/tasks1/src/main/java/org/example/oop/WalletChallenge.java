package org.example.oop;

class Wallet{
    float money;

    public Wallet(float money) {
        this.money = money;
    }

    public void addMoney(float amount){
        money = money + amount;
    }

    public void removeMoney(float amount){
        money = money - amount;
    }

    public void printAmount(){
        System.out.println(money);
    }
}

public class WalletChallenge {
    public static void main(String[] args) {
        Wallet wallet = new Wallet(3000);
        for (int i=0; i<6; i++){
            wallet.addMoney(5000);
            wallet.removeMoney(2000);
            wallet.removeMoney(1000);
        }
        System.out.println("Stan portfela po połowie roku wynosi: ");
        wallet.printAmount();
    }
}

