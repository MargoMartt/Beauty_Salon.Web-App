package com.example.beauty_salon.service;

import com.example.beauty_salon.dao.BeautyMastersRepository;
import com.example.beauty_salon.entity.BeautyMastersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BeautyMastersServiceImpl implements BeautyMastersService {
    @Autowired
    private BeautyMastersRepository mastersRepository;

    @Override
    public void saveMaster(BeautyMastersEntity master) {
        mastersRepository.save(master);
    }

    @Override
    public BeautyMastersEntity getMaster(int id) {
        BeautyMastersEntity master = null;
        Optional<BeautyMastersEntity> optional = mastersRepository.findById(id);
        if (optional.isPresent()){
            master = optional.get();
        }
        return master;
    }

    @Override
    public void deleteMaster(int id) {
        mastersRepository.deleteById(id);
    }

    @Override
    public List<BeautyMastersEntity> getAllMasters() {
        return mastersRepository.findAll();
    }
}