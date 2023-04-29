package com.example.beauty_salon.models;

import com.example.beauty_salon.entity.BeautyMastersEntity;
import com.example.beauty_salon.entity.ServiceEntity;
import com.example.beauty_salon.service.BeautyMastersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MasterData {
    @Autowired
    BeautyMastersService mastersService;

    @Autowired
    ServiceData serviceData;

    public List<BeautyMastersEntity> allMasters() {
        List<BeautyMastersEntity> allMasters = mastersService.getAllMasters();
        return allMasters;
    }

    public List<BeautyMastersEntity> bestMasters() {
        List<BeautyMastersEntity> bestMasters = new ArrayList<>();
        List<ServiceEntity> bodyCare = serviceData.specialServices("Body Care");
        List<ServiceEntity> hairCare = serviceData.specialServices("Hair Care");
        List<ServiceEntity> makeUp = serviceData.specialServices("MakeUp");
        List<ServiceEntity> faceCare = serviceData.specialServices("Face Care");
        List<ServiceEntity> lashes = serviceData.specialServices("Lashes/Brows");
        List<ServiceEntity> nailCare = serviceData.specialServices("Nail Care");

        bestMasters.add(bestMaster(bodyCare));
        bestMasters.add(bestMaster(hairCare));
        bestMasters.add(bestMaster(makeUp));
        bestMasters.add(bestMaster(faceCare));
        bestMasters.add(bestMaster(lashes));
        bestMasters.add(bestMaster(nailCare));

        return bestMasters;
    }

    public BeautyMastersEntity bestMaster(List<ServiceEntity> service) {
        Set<Integer> id = new HashSet<>();
        List<Integer> idMasters= new ArrayList<>();
        List<BeautyMastersEntity> masters = new ArrayList<>();
        int experience = 0;
        int idMaster = 0;

        for (ServiceEntity s : service) {
            id.add(s.getMasterId());
            System.out.println(id);
        }
        idMasters.addAll(id);
        for (Integer m: idMasters) {
            masters.add(mastersService.getMaster(m));
        }
//        for (int i = 0; i < idMasters.size(); i++) {
//            masters.add(mastersService.getMaster(i));
//            System.out.println(masters);
//        }
        for (BeautyMastersEntity m : masters) {
            if (m.getWorkExperience() > experience) {
                idMaster = m.getMasterId();
                experience = m.getWorkExperience();
            }
        }
        return mastersService.getMaster(idMaster);
    }
}
