package com.example.beauty_salon.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Setter
@Getter
public class BonusData {
    private int idUser;
    private int idBonus;
    private String user;
    private int bonus;
    private String email;
}

