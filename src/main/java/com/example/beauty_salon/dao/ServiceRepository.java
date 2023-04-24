package com.example.beauty_salon.dao;

import com.example.beauty_salon.entity.ServiceEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {
}