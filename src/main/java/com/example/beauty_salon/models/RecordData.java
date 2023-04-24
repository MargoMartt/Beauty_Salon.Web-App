package com.example.beauty_salon.models;

import com.example.beauty_salon.entity.RecordEntity;
import com.example.beauty_salon.enums.RecordTime;
import com.example.beauty_salon.enums.ServiceCode;
import com.example.beauty_salon.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecordData {
    @Autowired
    RecordService recordService;
    public List<RecordTime> recordTimes() {
        ArrayList<RecordTime> recordTimes = new ArrayList<>();
        recordTimes.add(RecordTime.NINE);
        recordTimes.add(RecordTime.ELEVEN);
        recordTimes.add(RecordTime.THIRTEEN);
        recordTimes.add(RecordTime.FIFTEEN);
        recordTimes.add(RecordTime.SEVENTEEN);
        return recordTimes;
    }

    public boolean isRecord(int serviceId, int masterId, String time, String date){


        return false;
    };
}
