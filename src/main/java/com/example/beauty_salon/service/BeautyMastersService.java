package com.example.beauty_salon.service;

import com.example.beauty_salon.entity.BeautyMastersEntity;

import java.util.List;

public interface BeautyMastersService {
    public void saveMaster(BeautyMastersEntity master);
    public BeautyMastersEntity getMaster(int id);

    public void deleteMaster(int id);
    public List<BeautyMastersEntity> getAllMasters();
}
