package com.example.beauty_salon.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
public class Report {
private int serviceCount;
private int mastersCount;
private int certificateCount;
private int discountCount;
private int usersCount;
private int recordCount;
}
