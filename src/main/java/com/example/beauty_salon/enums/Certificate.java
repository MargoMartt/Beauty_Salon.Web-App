package com.example.beauty_salon.enums;

public enum Certificate {
    TWENTY(20),
    FIFTY(50),
    ONE_HUNDRED(100),
    TWO_HUNDRED(200);

    private int cost;

    private Certificate(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}
