package com.example.beauty_salon.service;

import com.example.beauty_salon.dao.BonusRepository;
import com.example.beauty_salon.entity.BonusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BonusServiceImpl implements BonusService{
    @Autowired
    BonusRepository bonusRepository;
    @Override
    public void saveBonus(BonusEntity bonus) {
        bonusRepository.save(bonus);
    }

    @Override
    public BonusEntity getBonus(int id) {
        BonusEntity bonus = null;
        Optional<BonusEntity> optional = bonusRepository.findById(id);
        if (optional.isPresent()){
            bonus = optional.get();
        }
        return bonus;
    }

    @Override
    public void deleteBonus(int id) {
        bonusRepository.deleteById(id);
    }

    @Override
    public List<BonusEntity> getAllBonus() {
        return bonusRepository.findAll();
    }

}