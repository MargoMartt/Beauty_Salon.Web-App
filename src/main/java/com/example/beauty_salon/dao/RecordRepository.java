package com.example.beauty_salon.dao;

import com.example.beauty_salon.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface RecordRepository extends JpaRepository<RecordEntity, Integer> {
    public List<RecordEntity> getByServiceIdAndTime
            (int serviceId, String time);
    public List<RecordEntity> getByTime(String time);
    public List<RecordEntity> getRecordEntitiesByIdUser(int id);

    public ArrayList<RecordEntity> getRecordEntitiesByDateAfterAndIdUser(LocalDate date, int id);
    public ArrayList<RecordEntity> getRecordEntitiesByIdUserAndDateBefore(int id, LocalDate date);
}
