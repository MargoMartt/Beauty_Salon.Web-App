package com.example.beauty_salon.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@NoArgsConstructor
@Getter
@Setter
public class ServiceModel {
    private int idService;
    private int idMaster;
    private String serviceName;
    private String serviceCode;
    private String masterName;
    private double cost;
}
