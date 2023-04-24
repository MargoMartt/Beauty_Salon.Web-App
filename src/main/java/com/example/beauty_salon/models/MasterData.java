package com.example.beauty_salon.models;

import com.example.beauty_salon.entity.BeautyMastersEntity;
import com.example.beauty_salon.service.BeautyMastersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class MasterData {
    @Autowired
    BeautyMastersService mastersService;

    public List<BeautyMastersEntity> allMasters() {
        List<BeautyMastersEntity> allMasters = mastersService.getAllMasters();
        return allMasters;
    }
}
