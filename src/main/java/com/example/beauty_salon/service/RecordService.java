package com.example.beauty_salon.service;

import com.example.beauty_salon.entity.RecordEntity;

import java.util.List;

public interface RecordService {
    public void saveRecord(RecordEntity record);
    public RecordEntity getRecord(int id);

    public void deleteRecord (int id);
    public List<RecordEntity> getAllRecord();
    public boolean isRecord(int serviceId, int masterId, String date, String time);
}
