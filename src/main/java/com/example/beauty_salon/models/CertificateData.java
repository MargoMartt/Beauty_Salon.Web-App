package com.example.beauty_salon.models;

import com.example.beauty_salon.entity.BonusEntity;
import com.example.beauty_salon.entity.UsersEntity;
import com.example.beauty_salon.enums.Certificate;
import com.example.beauty_salon.service.BonusService;
import com.example.beauty_salon.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CertificateData {
    @Autowired
    BonusService bonusService;
    @Autowired
    UsersService usersService;

    public List<Integer> bonuses() {
        List<Integer> bonuses = new ArrayList<>();
        bonuses.add(Certificate.TWENTY.getCost());
        bonuses.add(Certificate.FIFTY.getCost());
        bonuses.add(Certificate.ONE_HUNDRED.getCost());
        bonuses.add(Certificate.TWO_HUNDRED.getCost());
        return bonuses;
    }

    public String result(int cost, int id) {
        BonusEntity bonus = bonusService.getBonusByUser(id);
        System.out.println(bonus);
        UsersEntity user = usersService.getUser(id);
        System.out.println("HI");
        if (user.getBalance() < cost)
            return "noMoney";
        if (bonus == null) {
            bonus = new BonusEntity();
            bonus.setIdUser(id);
            bonus.setCertificate(cost);
            bonus.setUsersByIdUser(user);
            bonusService.saveBonus(bonus);
            user.setBalance(user.getBalance() - cost);
            usersService.saveUser(user);
            return "success";
        }
        if (bonus!=null){
            if (bonus.getCertificate() != 0)
                return "alreadyExist";
            bonus.setCertificate(cost);
            bonusService.saveBonus(bonus);
            user.setBalance(user.getBalance() - cost);
            usersService.saveUser(user);
            return "success";
        }

        return "someError";
    }
}
