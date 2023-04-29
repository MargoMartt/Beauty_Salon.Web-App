package com.example.beauty_salon.dao;

import com.example.beauty_salon.entity.BonusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BonusRepository extends JpaRepository<BonusEntity, Integer> {
    public BonusEntity findBonusEntityByIdUser(int id);
}
