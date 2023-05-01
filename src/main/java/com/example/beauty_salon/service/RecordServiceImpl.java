package com.example.beauty_salon.service;

import com.example.beauty_salon.dao.RecordRepository;
import com.example.beauty_salon.entity.RecordEntity;
import com.example.beauty_salon.entity.ServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecordServiceImpl implements RecordService {
    @Autowired
    RecordRepository recordRepository;

    @Autowired
    ServiceService serviceService;

    @Autowired
    BeautyMastersService mastersService;

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

    @Override
    public boolean isRecord(int serviceId, int masterId, String date, String time) {
        boolean isRecord = true;
        List<RecordEntity> recordEntities = recordRepository.getByServiceIdAndTime(serviceId, time);
        if (recordEntities.isEmpty() == true) {
            isRecord = false;
        } else {
            for (RecordEntity record : recordEntities) {
                if (record.getDate().equals(date))
                    return true;
            }
            if (isRecord == false) {
                List<RecordEntity> allRecords = recordRepository.getByTime(time);
                for (RecordEntity record : allRecords) {
                    ServiceEntity service = (serviceService.getService(record.getServiceId()));
                    if (mastersService.getMaster(service.getMasterId()).getMasterId() == masterId && record.getDate() == date)
                        return true;
                }
            }
        }
        return isRecord;
    }

    @Override
    public List<RecordEntity> countRecords(int id) {
        return recordRepository.getRecordEntitiesByIdUser(id);
    }

    @Override
    public ArrayList<RecordEntity> futureRecords(LocalDate date, int id) {
        return recordRepository.getRecordEntitiesByDateAfterAndIdUser(date, id);
    }

    @Override
    public ArrayList<RecordEntity> recordHistory(LocalDate date, int id) {
        return recordRepository.getRecordEntitiesByIdUserAndDateBefore(id, date);
    }

    @Override
    public List<RecordEntity> futureRecordsByService(LocalDate date, int id) {
        return recordRepository.getRecordEntitiesByDateAfterAndServiceId(date, id);
    }

    @Override
    public List<RecordEntity> pastRecordsByService(LocalDate date, int id) {
        return recordRepository.getRecordEntitiesByDateBeforeAndServiceId(date, id);
    }

    @Override
    public ArrayList<RecordEntity> recordsByService(int id) {
        return recordRepository.getRecordEntitiesByServiceId(id);
    }
}
