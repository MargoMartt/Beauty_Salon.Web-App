package com.example.beauty_salon.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecordProfile {
    private int idRecord;
    private int idService;
    private int idMaster;
    private String masterName;
    private String serviceName;
    private String date;
    private Double cost;

}
