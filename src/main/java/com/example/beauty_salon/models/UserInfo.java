package com.example.beauty_salon.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserInfo {
    private int idUser;
    private int idBonus;
    private double balance;
    private int certificate;
    private int bonus;
    private String email;
}
