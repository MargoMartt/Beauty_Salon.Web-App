package com.example.beauty_salon.service;

import com.example.beauty_salon.dao.RecordRepository;
import com.example.beauty_salon.entity.RecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class RecordServiceImpl implements RecordService{
    @Autowired
    RecordRepository recordRepository;

    @Override
    public void saveRecord(RecordEntity record) {
        recordRepository.save(record);
    }

    @Override
    public RecordEntity getRecord(int id) {
        RecordEntity record = null;
        Optional<RecordEntity> optional = recordRepository.findById(id);
        if (optional.isPresent()) {
            record = optional.get();
        }
        return record;
    }

    @Override
    public void deleteRecord(int id) {
        recordRepository.deleteById(id);
    }

    @Override
    public List<RecordEntity> getAllRecord() {
        return recordRepository.findAll();
    }
}
