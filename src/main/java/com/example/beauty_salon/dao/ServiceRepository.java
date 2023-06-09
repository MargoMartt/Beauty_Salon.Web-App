package com.example.beauty_salon.dao;

import com.example.beauty_salon.entity.ServiceEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {
    public List<ServiceEntity> getServiceEntitiesByMasterId(int id);

}