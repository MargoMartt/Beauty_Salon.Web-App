package com.example.beauty_salon.dao;
import com.example.beauty_salon.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<RecordEntity, Integer> {
}
