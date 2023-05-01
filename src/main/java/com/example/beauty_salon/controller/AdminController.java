package com.example.beauty_salon.controller;

import com.example.beauty_salon.entity.BeautyMastersEntity;
import com.example.beauty_salon.entity.BonusEntity;
import com.example.beauty_salon.entity.ServiceEntity;
import com.example.beauty_salon.entity.UsersEntity;
import com.example.beauty_salon.enums.Discount;
import com.example.beauty_salon.enums.ServiceCode;
import com.example.beauty_salon.models.*;
import com.example.beauty_salon.service.BeautyMastersService;
import com.example.beauty_salon.service.BonusService;
import com.example.beauty_salon.service.ServiceService;
import com.example.beauty_salon.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    BeautyMastersService mastersService;

    @Autowired
    AdminModel adminModel;
    @Autowired
    ServiceService service;
    @Autowired
    BonusService bonusService;
    @Autowired
    UsersService usersService;

    @GetMapping("/mastersAction")
    public String allMasters(Model model) {
        ArrayList<BeautyMastersEntity> mastersList = adminModel.mastersList();
        model.addAttribute("masters", mastersList);
        return "mastersAction";
    }

    @GetMapping("/mastersAction/edit/{id}")
    public String editMaster(@PathVariable(name = "id") int id, Model model) {
        BeautyMastersEntity master = mastersService.getMaster(id);
        model.addAttribute("master", master);
        return "editMaster";
    }

    @PostMapping("/mastersAction/edit/{id}")
    public String editMaster(@PathVariable(name = "id") int id,
                             @RequestParam(name = "name", required = false) String name,
                             @RequestParam(name = "surname", required = false) String surname,
                             @RequestParam(name = "experience", required = false) int experience,
                             @RequestParam(name = "activity", required = false) String activity) {
        adminModel.editMaster(id, name, surname, experience, activity);
        return "redirect:/admin/mastersAction";
    }

    @GetMapping("/mastersAction/delete/{id}")
    public String deleteMaster(@PathVariable(name = "id") int id, Model model) {
        adminModel.deleteMaster(id);
        return "redirect:/admin/mastersAction";
    }

    @GetMapping("/mastersAction/add")
    public String addMaster(Model model) {
        return "addMaster";
    }

    @PostMapping("/mastersAction/add")
    public String addMaster(@RequestParam(name = "name", required = false) String name,
                            @RequestParam(name = "surname", required = false) String surname,
                            @RequestParam(name = "experience", required = false) int experience,
                            @RequestParam(name = "activity", required = false) String activity) {
        adminModel.addMaster(name, surname, experience, activity);
        return "redirect:/admin/mastersAction";
    }


    @GetMapping("/serviceAction")
    public String allServices(Model model) {
        List<ServiceModel> services = adminModel.allServices();
        model.addAttribute("services", services);
        return "serviceAction";
    }

    @GetMapping("/serviceAction/edit/{id}")
    public String editService(@PathVariable(name = "id") int id, Model model) {
        ServiceEntity serviceEntity = service.getService(id);
        model.addAttribute("service", serviceEntity);
        ArrayList<BeautyMastersEntity> masters = adminModel.mastersList();
        model.addAttribute("masters", masters);

        ArrayList<ServiceCode> types = adminModel.serviceTypes();
        model.addAttribute("types", types);
        return "editService";
    }

    @PostMapping("/serviceAction/edit/{id}")
    public String editService(@PathVariable(name = "id") int id,
                              @RequestParam(name = "name", required = false) String name,
                              @RequestParam(name = "type", required = false) String type,
                              @RequestParam(name = "cost", required = false) double cost,
                              @RequestParam(name = "master", required = false) int master) {
        adminModel.editService(id, name, type, cost, master);
        return "redirect:/admin/serviceAction";
    }

    @GetMapping("/serviceAction/delete/{id}")
    public String deleteService(@PathVariable(name = "id") int id, Model model) {
        adminModel.deleteService(id);
        return "redirect:/admin/serviceAction";
    }

    @GetMapping("/serviceAction/add")
    public String addService(Model model) {
        ArrayList<BeautyMastersEntity> masters = adminModel.mastersList();
        model.addAttribute("masters", masters);

        ArrayList<ServiceCode> types = adminModel.serviceTypes();
        model.addAttribute("types", types);

        return "addService";
    }

    @PostMapping("/serviceAction/add")
    public String addService(@RequestParam(name = "name", required = false) String name,
                             @RequestParam(name = "type", required = false) String type,
                             @RequestParam(name = "cost", required = false) double cost,
                             @RequestParam(name = "master", required = false) int master) {
        adminModel.addService(master, name, type, cost);
        return "redirect:/admin/serviceAction";
    }


    @GetMapping("/bonusAction")
    public String allBonuses(Model model) {
        List<BonusData> bonuses = adminModel.allBonuses();
        model.addAttribute("bonuses", bonuses);
        return "bonusAction";
    }

    @GetMapping("/bonusAction/edit/{id}")
    public String editBonus(@PathVariable(name = "id") int id, Model model) {
        BonusEntity bonus = bonusService.getBonus(id);
        model.addAttribute("bonus", bonus);

        ArrayList<Discount> discounts = adminModel.discounts();
        model.addAttribute("discounts", discounts);

        UsersEntity user = usersService.getUser(bonus.getIdUser());
        model.addAttribute("user", user);
        return "editBonus";
    }

    @PostMapping("/bonusAction/edit/{id}")
    public String editBonus(@PathVariable(name = "id") int id,
                            @RequestParam(name = "email", required = false) String email,
                            @RequestParam(name = "discount", required = false) int discount) {
        adminModel.editBonus(id, discount);
        return "redirect:/admin/bonusAction";
    }

    @GetMapping("/bonusAction/delete/{id}")
    public String deleteBonus(@PathVariable(name = "id") int id, Model model) {
        adminModel.deleteBonus(id);
        return "redirect:/admin/bonusAction";
    }

    @GetMapping("/bonusAction/add")
    public String addBonus(Model model) {
        ArrayList<Discount> discounts = adminModel.discounts();
        model.addAttribute("discounts", discounts);
        return "addBonus";
    }

    @PostMapping("/bonusAction/add")
    public String addBonus(@RequestParam(name = "email", required = false) String email,
                           @RequestParam(name = "discount", required = false) int discount) {
        String answer = adminModel.addBonus(email, discount);
        if (answer.equals("success"))
            return "redirect:/admin/bonusAction";
        else
            return "redirect:/admin/bonusAction/add";
    }

    @GetMapping("/profit")
    public String profit(Model model) {
        ArrayList<Profit> profits = adminModel.profit();
        double resultProfit = adminModel.resultProfit(profits);

        model.addAttribute("profits", profits);
        model.addAttribute("resultProfit", resultProfit);
        return "profit";
    }

    @GetMapping("/report")
    public String report(Model model) {
        Report report = adminModel.report();
        model.addAttribute("report", report);
        return "report";
    }

}
