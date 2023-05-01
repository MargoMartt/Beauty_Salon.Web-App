package com.example.beauty_salon.models;

import com.example.beauty_salon.entity.*;
import com.example.beauty_salon.enums.Discount;
import com.example.beauty_salon.enums.ServiceCode;
import com.example.beauty_salon.service.*;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToStdout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdminModel {
    @Autowired
    BeautyMastersService beautyMastersService;
    @Autowired
    ServiceService service;
    @Autowired
    RecordService recordService;
    @Autowired
    UsersService usersService;

    @Autowired
    BonusService bonusService;

    public ArrayList<BeautyMastersEntity> mastersList() {
        ArrayList<BeautyMastersEntity> mastersList = (ArrayList<BeautyMastersEntity>) beautyMastersService.getAllMasters();
        return mastersList;

    }

    public void editMaster(int id, String name, String surname, int experience, String activity) {
        BeautyMastersEntity master = beautyMastersService.getMaster(id);
        master.setMasterName(name);
        master.setMasterSurname(surname);
        master.setWorkExperience(experience);
        master.setActivity(activity);
        beautyMastersService.saveMaster(master);
    }

    public void deleteMaster(int id) {
        BeautyMastersEntity master = beautyMastersService.getMaster(id);
        List<ServiceEntity> serviceEntity = service.getServicesByMasterID(id);
        for (ServiceEntity service : serviceEntity) {
            List<RecordEntity> records = recordService.futureRecordsByService(LocalDate.now(), service.getServiceId());
            for (RecordEntity rec : records) {
                UsersEntity user = usersService.getUser(rec.getIdUser());
                user.setBalance(user.getBalance() + rec.getTotalCost());
                usersService.saveUser(user);
                recordService.deleteRecord(rec.getRecordId());
            }
            List<RecordEntity> pastRec = recordService.pastRecordsByService(LocalDate.now().plusDays(1), service.getServiceId());
            for (RecordEntity rec : pastRec) {
                recordService.deleteRecord(rec.getRecordId());
            }
        }
        for (ServiceEntity serv : serviceEntity) {
            service.deleteService(serv.getServiceId());
        }
        beautyMastersService.deleteMaster(id);
    }

    public void addMaster(String name, String surname, int experience, String activity) {
        BeautyMastersEntity master = new BeautyMastersEntity();
        master.setMasterName(name);
        master.setMasterSurname(surname);
        master.setActivity(activity);
        master.setWorkExperience(experience);
        beautyMastersService.saveMaster(master);
    }

    public ArrayList<ServiceModel> allServices() {
        List<ServiceEntity> serviceEntities = service.getAllServices();
        ArrayList<ServiceModel> allServices = new ArrayList<>();
        for (ServiceEntity s : serviceEntities) {
            ServiceModel serviceModel = new ServiceModel();
            serviceModel.setIdService(s.getServiceId());
            serviceModel.setIdMaster(s.getMasterId());
            serviceModel.setServiceName(s.getServiceName());
            serviceModel.setServiceCode(s.getServiceCode());
            serviceModel.setCost(s.getServicePrice());
            serviceModel.setMasterName(beautyMastersService.getMaster
                    (s.getMasterId()).getMasterName() + " " +
                    beautyMastersService.getMaster(s.getMasterId()).getMasterSurname());
            allServices.add(serviceModel);
        }
        return allServices;
    }

    public ArrayList<ServiceCode> serviceTypes() {
        ArrayList<ServiceCode> serviceCodes = new ArrayList<>();
        serviceCodes.add(ServiceCode.BODY);
        serviceCodes.add(ServiceCode.FACE);
        serviceCodes.add(ServiceCode.HAIR);
        serviceCodes.add(ServiceCode.MAKEUP);
        serviceCodes.add(ServiceCode.LASHES);
        serviceCodes.add(ServiceCode.NAILS);
        return serviceCodes;
    }

    public void addService(int idMaster, String name, String type, double cost) {
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setMasterId(idMaster);
        serviceEntity.setServiceName(name);
        serviceEntity.setServiceCode(type);
        serviceEntity.setServicePrice(cost);
        service.saveService(serviceEntity);
    }

    public void deleteService(int id) {
        ServiceEntity serviceEntity = service.getService(id);
//        BeautyMastersEntity master = beautyMastersService.getMaster(serviceEntity.getMasterId());
        List<RecordEntity> recordEntities = recordService.futureRecordsByService(LocalDate.now(), id);
        for (RecordEntity rec : recordEntities) {
            char[] date = rec.getDate().toCharArray();
            char[] dateNow = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toCharArray();
            boolean isHistory = false;
            for (int i = 0; i < date.length; i++) {
                if (date[i] < dateNow[i])
                    isHistory = true;
            }
            if (isHistory)
                recordService.deleteRecord(rec.getRecordId());
            else {
                UsersEntity user = usersService.getUser(rec.getIdUser());
                user.setBalance(user.getBalance() + rec.getTotalCost());
                recordService.deleteRecord(rec.getRecordId());
            }
        }
        service.deleteService(id);
    }

    public void editService(int id, String name, String type, double cost, int master) {
        ServiceEntity serviceEntity = service.getService(id);
        serviceEntity.setServiceName(name);
        serviceEntity.setMasterId(master);
        serviceEntity.setServicePrice(cost);
        serviceEntity.setServiceCode(type);
        service.saveService(serviceEntity);
    }

    public ArrayList<BonusData> allBonuses() {
        ArrayList<BonusData> allBonuses = new ArrayList<>();
        List<BonusEntity> bonusEntities = bonusService.getAllBonus();
        for (BonusEntity b : bonusEntities) {
            if (b.getDiscount() != 0) {
                BonusData bonusData = new BonusData();
                bonusData.setIdBonus(b.getBonusId());
                bonusData.setIdUser(b.getIdUser());
                bonusData.setBonus(b.getDiscount());
                bonusData.setEmail(b.getUsersByIdUser().getLogin());
                bonusData.setUser(b.getUsersByIdUser().getUserName() + " " + b.getUsersByIdUser().getUserSurname());
                allBonuses.add(bonusData);
            }
        }
        return allBonuses;
    }

    public ArrayList<Discount> discounts() {
        ArrayList<Discount> discounts = new ArrayList<>();
        discounts.add(Discount.TWO);
        discounts.add(Discount.THREE);
        discounts.add(Discount.FIVE);
        discounts.add(Discount.TEN);
        discounts.add(Discount.TWENTY);
        return discounts;
    }

    public void editBonus(int id, int discount) {
        BonusEntity bonus = bonusService.getBonus(id);
        bonus.setDiscount(discount);
        bonusService.saveBonus(bonus);
    }

    public void deleteBonus(int id) {
        BonusEntity bonus = bonusService.getBonus(id);
        if (bonus.getCertificate() == 0)
            bonusService.deleteBonus(id);
        else {
            bonus.setDiscount(0);
            bonusService.saveBonus(bonus);
        }
    }

    public String addBonus(String email, int discount) {
        UsersEntity user = usersService.getByLogin(email);
        if (user == null)
            return "NoUser";
        BonusEntity bonus = bonusService.getBonusByUser(user.getIdUser());
        if (bonus != null) {
            bonus.setDiscount(discount);
            bonusService.saveBonus(bonus);
            return "success";
        }
        bonus.setDiscount(discount);
        bonus.setIdUser(user.getIdUser());
        bonus.setUsersByIdUser(user);
        bonusService.saveBonus(bonus);
        return "success";

    }

    public ArrayList<Profit> profit() {
        ArrayList<Profit> profit = new ArrayList<>();
        List<ServiceEntity> serviceEntities = service.getAllServices();
        for (ServiceEntity serv : serviceEntities) {
            Profit prof = new Profit();
            prof.setIdService(serv.getServiceId());
            prof.setName(serv.getServiceName());
            prof.setCost(serv.getServicePrice());

            double finalProfit = 0;
            double fullProfit = 0;
            int count = 0;
            ArrayList<RecordEntity> records = recordService.recordsByService(serv.getServiceId());

            for (RecordEntity rec : records) {
                finalProfit += rec.getTotalCost();
            }
            prof.setFinalProfit(finalProfit);

            if (!records.isEmpty()) {
                fullProfit = serv.getServicePrice() * records.toArray().length;
                count = records.toArray().length;
            }
            prof.setFullProfit(fullProfit);
            prof.setCount(count);
            profit.add(prof);
        }
        return profit;
    }

    public double resultProfit(ArrayList<Profit> profits) {
        double resultProfit = 0;
        for (Profit pr : profits) {
            resultProfit += pr.getFinalProfit();
        }
        return resultProfit;
    }

    public Report report() {
        Report report = new Report();
        report.setMastersCount(beautyMastersService.getAllMasters().size());
        report.setRecordCount(recordService.getAllRecord().size());
        report.setUsersCount(usersService.getAllUser().size());
        report.setServiceCount(service.getAllServices().size());

        List<BonusEntity> bonusEntities = bonusService.getAllBonus();
        int countCertificate = 0;
        int countDiscount = 0;

        for (BonusEntity b : bonusEntities) {
            if (b.getCertificate() != 0)
                countCertificate++;
            if (b.getDiscount() != 0)
                countDiscount++;
        }
        report.setDiscountCount(countDiscount);
        report.setCertificateCount(countCertificate);
        return report;
    }
}
