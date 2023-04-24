package com.example.beauty_salon.service;

import com.example.beauty_salon.dao.RecordRepository;
import com.example.beauty_salon.entity.RecordEntity;
import com.example.beauty_salon.entity.ServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
