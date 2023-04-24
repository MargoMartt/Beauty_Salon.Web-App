package com.example.beauty_salon.enums;

public enum ServiceCode {
    BODY("Body Care"),

    HAIR("Hair Care"),

    FACE("Face Care"),

    MAKEUP("MakeUp"),

    LASHES("Lashes/Brows"),

    NAILS("Nails");

    private String code;

    private ServiceCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
