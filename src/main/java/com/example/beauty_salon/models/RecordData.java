package com.example.beauty_salon.models;

import com.example.beauty_salon.entity.BonusEntity;
import com.example.beauty_salon.entity.RecordEntity;
import com.example.beauty_salon.entity.ServiceEntity;
import com.example.beauty_salon.entity.UsersEntity;
import com.example.beauty_salon.enums.Discount;
import com.example.beauty_salon.enums.RecordTime;
import com.example.beauty_salon.enums.ServiceCode;
import com.example.beauty_salon.service.BonusService;
import com.example.beauty_salon.service.RecordService;
import com.example.beauty_salon.service.ServiceService;
import com.example.beauty_salon.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class RecordData {
    @Autowired
    RecordService recordService;

    @Autowired
    UsersService usersService;

    @Autowired
    BonusService bonusService;

    @Autowired
    ServiceService service;


    public List<RecordTime> recordTimes() {
        ArrayList<RecordTime> recordTimes = new ArrayList<>();
        recordTimes.add(RecordTime.NINE);
        recordTimes.add(RecordTime.ELEVEN);
        recordTimes.add(RecordTime.THIRTEEN);
        recordTimes.add(RecordTime.FIFTEEN);
        recordTimes.add(RecordTime.SEVENTEEN);
        return recordTimes;
    }

    public void countRecord(int id) {
        BonusEntity bonus = bonusService.getBonusByUser(id);
        int count = recordService.countRecords(id).toArray().length;
        if (count >= 5 && count < 10) {
            if (bonus != null)
                bonus.setDiscount(Discount.TWO.getCost());
            else {
                bonus.setIdUser(id);
                bonus.setDiscount(Discount.TWO.getCost());
                bonusService.saveBonus(bonus);
            }
        }
        if (count >= 10 && count < 15) {
            if (bonus != null)
                bonus.setDiscount(Discount.THREE.getCost());
            else {
                bonus.setIdUser(id);
                bonus.setDiscount(Discount.THREE.getCost());
                bonusService.saveBonus(bonus);
            }
        }
        if (count >= 15 && count < 25) {
            if (bonus != null)
                bonus.setDiscount(Discount.FIVE.getCost());
            else {
                bonus.setIdUser(id);
                bonus.setDiscount(Discount.FIVE.getCost());
                bonusService.saveBonus(bonus);
            }
        }
        if (count >= 25 && count < 40) {
            if (bonus != null)
                bonus.setDiscount(Discount.TEN.getCost());
            else {
                bonus.setIdUser(id);
                bonus.setDiscount(Discount.TEN.getCost());
                bonusService.saveBonus(bonus);
            }
        }
        if (count >= 40) {
            if (bonus != null)
                bonus.setDiscount(Discount.TWENTY.getCost());
            else {
                bonus.setIdUser(id);
                bonus.setDiscount(Discount.TWENTY.getCost());
                bonusService.saveBonus(bonus);
            }
        }
    }

    public String makeRecord(int userId, int serviceId, String date, String time) {

        RecordEntity record = new RecordEntity();
        BonusEntity bonus = bonusService.getBonusByUser(userId);
        ServiceEntity serviceEntity = service.getService(serviceId);
        UsersEntity user = usersService.getUser(userId);

        System.out.println(user.getBalance());
        double cost = serviceEntity.getServicePrice();
        int discount = 0;
        double totalCost = cost;

        if (bonus != null)
            discount = bonus.getDiscount();

        if (discount != 0)
            totalCost = (cost * (100 - discount) / 100);

        if (totalCost > user.getBalance())
            return "noMoney";

        record.setIdUser(userId);
        record.setServiceId(serviceId);
        record.setDate(Date.valueOf(date));
        record.setTime(time);
        record.setTotalCost(totalCost);
        record.setServiceByServiceId(serviceEntity);
        record.setUsersByIdUser(user);
        recordService.saveRecord(record);

        user.setBalance(user.getBalance() - totalCost);
        usersService.saveUser(user);
        return String.valueOf(totalCost);
    }
}

