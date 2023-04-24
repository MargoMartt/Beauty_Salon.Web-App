package com.example.beauty_salon.models;

import com.example.beauty_salon.entity.ServiceEntity;
import com.example.beauty_salon.enums.ServiceCode;
import com.example.beauty_salon.service.ServiceService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ServiceData {
    @Autowired
    private ServiceService service;

    public List<ServiceEntity> specialServices(String serviceCode) {
        List<ServiceEntity> allServices = service.getAllServices();
        List<ServiceEntity> specialServices = new ArrayList<>();
        for (ServiceEntity services : allServices) {
            if (services.getServiceCode().equals(serviceCode))
                specialServices.add(services);
        }
        return specialServices;
    }

    public ArrayList<ServiceCode> servicesData() {
        servicesData().add(ServiceCode.BODY);
        servicesData().add(ServiceCode.HAIR);
        servicesData().add(ServiceCode.FACE);
        servicesData().add(ServiceCode.MAKEUP);
        servicesData().add(ServiceCode.LASHES);
        servicesData().add(ServiceCode.NAILS);
        return servicesData();
    }
}
