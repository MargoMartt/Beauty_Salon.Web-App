package com.example.beauty_salon.enums;

public enum Roles {
    VISITOR ("Посетитель", 1) ,
    ADMIN("Администратор", 2);
    private String value;
    private int idRole;
     private Roles(String value, int id){
        this.value = value;
        this.idRole = id;
    }

    public String getValue() {
        return value;
    }

    public int getId() {
        return idRole;
    }
}
