package com.example.beauty_salon.enums;

public enum Discount {
    TWO(2),
    THREE(3),
    FIVE(5),
    TEN(10),
    TWENTY(20);

    private int cost;

    private Discount(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}
