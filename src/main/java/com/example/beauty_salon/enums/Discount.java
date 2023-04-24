package com.example.beauty_salon.enums;

public enum Discount {
    TWO(2),
    FIVE(5),
    TEN(10),
    TWENTY(20),
    FIFTY(50);

    private int cost;

    private Discount(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}
