package com.example.beauty_salon.service;

import com.example.beauty_salon.entity.RecordEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface RecordService {
    public void saveRecord(RecordEntity record);

    public RecordEntity getRecord(int id);

    public void deleteRecord(int id);

    public List<RecordEntity> getAllRecord();

    public boolean isRecord(int serviceId, int masterId, String date, String time);

    public List<RecordEntity> countRecords(int id);

    public ArrayList<RecordEntity> futureRecords(LocalDate date, int id);

    public ArrayList<RecordEntity> recordHistory(LocalDate date, int id);

    public List<RecordEntity> futureRecordsByService(LocalDate date, int id);

    public List<RecordEntity> pastRecordsByService(LocalDate date, int id);
    public ArrayList<RecordEntity> recordsByService(int id);
}
