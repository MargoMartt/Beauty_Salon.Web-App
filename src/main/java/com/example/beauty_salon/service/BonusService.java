package com.example.beauty_salon.service;

import com.example.beauty_salon.entity.BonusEntity;
import com.example.beauty_salon.entity.BonusEntity;

import java.util.List;

public interface BonusService {
    public void saveBonus(BonusEntity  bonus);
    public BonusEntity getBonus(int id);

    public void deleteBonus(int id);
    public List<BonusEntity> getAllBonus();
}
