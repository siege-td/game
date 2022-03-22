package com.siegetd.game.singletons;

public class Currency {
    private static Currency currencyInstance = null;

    private int currency;

    private Currency(){
        this.currency = 100;
    }

    public static Currency getInstance(){
        if(currencyInstance == null){
            currencyInstance = new Currency();
        }
        return currencyInstance;
    }
    private void addCurrency(int amount){
        currency += amount;
    }

    private void subtractCurrency(int amount){
        currency -= amount;
    }

    public int getCurrency() {
        return currency;
    }
}
