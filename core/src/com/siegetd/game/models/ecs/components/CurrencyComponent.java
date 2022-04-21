package com.siegetd.game.models.ecs.components;

import com.badlogic.ashley.core.Component;

public class CurrencyComponent implements Component {
    public int currency = 0;

    public CurrencyComponent(int currency) {
        this.currency = currency;
    }
}
