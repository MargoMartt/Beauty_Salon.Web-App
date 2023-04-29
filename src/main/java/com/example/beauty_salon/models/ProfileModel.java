package com.example.beauty_salon.models;

import com.example.beauty_salon.entity.*;
import com.example.beauty_salon.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ProfileModel {
    @Autowired
    UsersService usersService;

    @Autowired
    RecordService recordService;
    @Autowired
    BeautyMastersService mastersService;

    @Autowired
    ServiceService service;

    @Autowired
    BonusService bonusService;

    public ArrayList<RecordProfile> futureRecords(int id) {


        ArrayList<RecordEntity> userRecord = recordService.futureRecords(LocalDate.now(), id);
        ArrayList<RecordProfile> futureRecord = addRecords(userRecord);

        return futureRecord;
    }

    public void refuse(int idUser, int idRecord) {
        UsersEntity user = usersService.getUser(idUser);
        user.setBalance(user.getBalance() + recordService.getRecord(idRecord).getTotalCost());
        usersService.saveUser(user);
        recordService.deleteRecord(idRecord);
    }

    public ArrayList<RecordProfile> recordHistory(int id) {

        ArrayList<RecordEntity> userRecord = recordService.recordHistory(LocalDate.now().plusDays(1), id);
        ArrayList<RecordProfile> recordHistory = addRecords(userRecord);
        System.out.println(userRecord);
        return recordHistory;
    }

    private ArrayList<RecordProfile> addRecords(ArrayList<RecordEntity> recordEntity) {
        ArrayList<RecordProfile> record = new ArrayList<>();
        for (RecordEntity rec : recordEntity) {
            RecordProfile recordProfile = new RecordProfile();
            ServiceEntity serviceEntity = service.getService(rec.getServiceId());
            BeautyMastersEntity master = mastersService.getMaster(serviceEntity.getMasterId());

            recordProfile.setIdRecord(rec.getRecordId());
            recordProfile.setIdService(serviceEntity.getServiceId());
            recordProfile.setServiceName(serviceEntity.getServiceName());
            recordProfile.setIdMaster(master.getMasterId());
            recordProfile.setMasterName(master.getMasterName() + " " + master.getMasterSurname());
            recordProfile.setDate(rec.getDate() + " " + rec.getTime());
            recordProfile.setCost(rec.getTotalCost());

            record.add(recordProfile);
        }
        return record;
    }

    public UserInfo user(int id) {
        UsersEntity userEntity = usersService.getUser(id);
        BonusEntity bonus = bonusService.getBonusByUser(id);

        UserInfo user = new UserInfo();

        user.setIdUser(id);
        user.setBalance(userEntity.getBalance());
        user.setEmail(userEntity.getLogin());

        if (bonus!=null) {
            user.setIdBonus(bonus.getBonusId());
            user.setCertificate(bonus.getCertificate());
            user.setBonus(bonus.getDiscount());
        }
        else {
            user.setCertificate(0);
            user.setBonus(0);
        }
        return user;
    }
}
