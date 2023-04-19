package com.example.beauty_salon.service;

import com.example.beauty_salon.entity.ServiceEntity;

import java.util.List;

public interface ServiceService {
    public void saveService(ServiceEntity service);
    public ServiceEntity getService(int id);

    public void deleteService (int id);
    public List<ServiceEntity> getAllServices();
}
