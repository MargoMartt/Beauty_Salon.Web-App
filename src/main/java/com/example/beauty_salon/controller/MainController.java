package com.example.beauty_salon.controller;

import com.example.beauty_salon.entity.BeautyMastersEntity;
import com.example.beauty_salon.entity.ServiceEntity;
import com.example.beauty_salon.enums.RecordTime;
import com.example.beauty_salon.enums.ServiceCode;
import com.example.beauty_salon.models.MasterData;
import com.example.beauty_salon.models.RecordData;
import com.example.beauty_salon.models.ServiceData;
import com.example.beauty_salon.service.RecordService;
import com.example.beauty_salon.service.ServiceService;
import com.example.beauty_salon.service.ServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Component
@Controller
public class MainController {
    @Autowired
    ServiceService service;
    @Autowired
    RecordService recordService;

    @Autowired
    ServiceData serviceData;
    @Autowired
    MasterData masterData;
    @Autowired
    RecordData recordData;

    @RequestMapping("/main")
    public String mainInfo(Model model) {
        return "main";
    }

    @GetMapping("/serviceBody")
    public String serviceBody(Model model) {
        List<ServiceEntity> services = serviceData.specialServices("Body Care");
        model.addAttribute("services", services);
        return "serviceBody";
    }

    @GetMapping("/record")
    public String record
            (@RequestParam(name = "service", required = false, defaultValue = "0") int id, Model model) {
        List<ServiceEntity> services = service.getAllServices();
        List<BeautyMastersEntity> masters = masterData.allMasters();
        List<RecordTime> recordTimes = recordData.recordTimes();

        if (id > 0) {
            ServiceEntity returnService = service.getService(id);
            model.addAttribute("serviceId", id);
        }

        model.addAttribute("services", services);
        model.addAttribute("masters", masters);
        model.addAttribute("recordTime", recordTimes);

        return "record";
    }

    @PostMapping("/record")
    public String record
            (@RequestParam(name = "service", required = false) int serviceId,
             @RequestParam(name = "master", required = false) int masterId,
             @RequestParam(name = "recordTime", required = false) String recordTime,
             @RequestParam(name = "date", required = false) String date, Model model) {

        boolean isRecord = recordService.isRecord(serviceId, masterId, date, recordTime);
        if (isRecord == true) {
            List<ServiceEntity> services = service.getAllServices();
            List<BeautyMastersEntity> masters = masterData.allMasters();
            List<RecordTime> recordTimes = recordData.recordTimes();
            String error = "WTF";

            ServiceEntity returnService = service.getService(serviceId);
            model.addAttribute("serviceId", serviceId);
            model.addAttribute("services", services);
            model.addAttribute("masters", masters);
            model.addAttribute("recordTime", recordTimes);
            model.addAttribute("error", error);

//        System.out.println(serviceId);
//        System.out.println(masterId);
//        System.out.println(recordTime);
//        System.out.println(date);
            return "record";
        }
        return "redirect:/serviceBody";
    }
}
