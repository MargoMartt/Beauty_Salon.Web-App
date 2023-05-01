package com.example.beauty_salon.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Setter
@Getter
public class Profit {
    private int idService;
    private String name;
    private int count;
    private double cost;
    private double fullProfit;
    private double finalProfit;
}
