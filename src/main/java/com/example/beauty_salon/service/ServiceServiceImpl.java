package com.example.beauty_salon.service;

import com.example.beauty_salon.dao.ServiceRepository;
import com.example.beauty_salon.entity.ServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class ServiceServiceImpl implements ServiceService{
    @Autowired
    ServiceRepository repository;

    @Override
    public void saveService(ServiceEntity service) {
        repository.save(service);
    }

    @Override
    public ServiceEntity getService(int id) {
        ServiceEntity service = null;
        Optional<ServiceEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            service = optional.get();
        }
        return service;
    }

    @Override
    public void deleteService(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<ServiceEntity> getAllServices() {
        return repository.findAll();
    }

    @Override
    public List<ServiceEntity> getServicesByMasterID(int id) {
        return repository.getServiceEntitiesByMasterId(id);
    }
}
