package com.siegetd.game.singletons;

public class Currency {
    private static Currency currencyInstance = null;

    private int currency;

    public Currency(){
        this.currency = 100;
    }
    private void addCurrency(int amount){
        currency += amount;
    }

    public static Currency getInstance(){
        if(currencyInstance == null){
            currencyInstance = new Currency();
        }
        return currencyInstance;
    }

    private void subtractCurrency(int amount){
        currency -= amount;
    }

    public int getCurrency() {
        return currency;
    }
}
