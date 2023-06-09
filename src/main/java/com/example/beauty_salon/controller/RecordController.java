package com.example.beauty_salon.controller;

import com.example.beauty_salon.entity.BeautyMastersEntity;
import com.example.beauty_salon.entity.ServiceEntity;
import com.example.beauty_salon.entity.UsersEntity;
import com.example.beauty_salon.enums.RecordTime;
import com.example.beauty_salon.models.MasterData;
import com.example.beauty_salon.models.RecordData;
import com.example.beauty_salon.models.ServiceData;
import com.example.beauty_salon.service.RecordService;
import com.example.beauty_salon.service.ServiceService;
import com.example.beauty_salon.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@Controller
public class RecordController {
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

    @Autowired
    UsersService usersService;


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
    public String record(@AuthenticationPrincipal UserDetails userDetails,
                         @RequestParam(name = "service", required = false) int serviceId,
                         @RequestParam(name = "master", required = false) int masterId,
                         @RequestParam(name = "recordTime", required = false) String recordTime,
                         @RequestParam(name = "date", required = false) String date,
                         Model model
    ) {
        UsersEntity userDB = usersService.getByLogin(userDetails.getUsername());

        boolean isRecord = recordService.isRecord(serviceId, masterId, date, recordTime);

        if (isRecord) {
            System.out.println("1");
            List<ServiceEntity> services = service.getAllServices();
            List<BeautyMastersEntity> masters = masterData.allMasters();
            List<RecordTime> recordTimes = recordData.recordTimes();
            String error = "Record already exists";

            ServiceEntity returnService = service.getService(serviceId);
            model.addAttribute("serviceId", serviceId);
            model.addAttribute("services", services);
            model.addAttribute("masters", masters);
            model.addAttribute("recordTime", recordTimes);
            model.addAttribute("error", error);

            return "record";
        }
        int idUser = userDB.getIdUser();
        String money = recordData.makeRecord(idUser, serviceId, date, recordTime);
        if (money.equals("noMoney")) {
            System.out.println("2");
            List<ServiceEntity> services = service.getAllServices();
            List<BeautyMastersEntity> masters = masterData.allMasters();
            List<RecordTime> recordTimes = recordData.recordTimes();
            String error = "Not enough money";

            ServiceEntity returnService = service.getService(serviceId);
            model.addAttribute("serviceId", serviceId);
            model.addAttribute("services", services);
            model.addAttribute("masters", masters);
            model.addAttribute("recordTime", recordTimes);
            model.addAttribute("error", error);

            return "record";
        }
        if (!money.equals("noMoney")) {
            System.out.println("3");
            List<ServiceEntity> services = service.getAllServices();
            List<BeautyMastersEntity> masters = masterData.allMasters();
            List<RecordTime> recordTimes = recordData.recordTimes();

            ServiceEntity returnService = service.getService(serviceId);
            model.addAttribute("serviceId", serviceId);
            model.addAttribute("services", services);
            model.addAttribute("masters", masters);
            model.addAttribute("recordTime", recordTimes);

            String success = "success";

            System.out.println(success);
            String serviceName = service.getService(serviceId).getServiceName();
            model.addAttribute("success", success);
            model.addAttribute("serviceDone", serviceName);
            model.addAttribute("date", date);
            model.addAttribute("time", recordTime);
            model.addAttribute("money", money);
            recordData.countRecord(idUser);
            return "record";
        }
        System.out.println("4");
        return "record";
    }
}
