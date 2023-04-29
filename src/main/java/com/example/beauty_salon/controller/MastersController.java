package com.example.beauty_salon.controller;

import com.example.beauty_salon.entity.BeautyMastersEntity;
import com.example.beauty_salon.models.MasterData;
import com.example.beauty_salon.models.RecordData;
import com.example.beauty_salon.models.ServiceData;
import com.example.beauty_salon.service.RecordService;
import com.example.beauty_salon.service.ServiceService;
import com.example.beauty_salon.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Component
@Controller
public class MastersController {
    @Autowired
    MasterData masterData;


    @GetMapping("/masters")
    public String masters(Model model) {
        List<BeautyMastersEntity> bestMasters = masterData.bestMasters();
        model.addAttribute("masters", bestMasters);
        return "masters";
    }

}
