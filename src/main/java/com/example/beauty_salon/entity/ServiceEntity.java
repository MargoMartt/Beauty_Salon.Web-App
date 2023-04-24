package com.example.beauty_salon.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "service", schema = "salonbeauty", catalog = "")
@NoArgsConstructor

public class ServiceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "service_id", nullable = false)
    @Getter
    @Setter
    private int serviceId;
    
    @Column(name = "service_name", nullable = true, length = 45)
    @Getter
    @Setter
    private String serviceName;
    
    @Column(name = "service_price", nullable = true, precision = 0)
    @Getter
    @Setter
    private Double servicePrice;

    @Column(name = "service_code", nullable = true, length = 45)
    @Getter
    @Setter
    private String serviceCode;
    
    @Column(name = "master_id", nullable = false)
    @Getter
    @Setter
    private int masterId;


}
