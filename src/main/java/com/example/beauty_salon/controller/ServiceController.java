package com.example.beauty_salon.controller;

import com.example.beauty_salon.entity.ServiceEntity;
import com.example.beauty_salon.models.ServiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Component
@Controller
public class ServiceController {

    @Autowired
    ServiceData serviceData;

    @GetMapping("/serviceBody")
    public String serviceBody(Model model) {
        List<ServiceEntity> services = serviceData.specialServices("Body Care");
        model.addAttribute("services", services);
        return "serviceBody";
    }

    @GetMapping("/serviceHair")
    public String serviceHair(Model model) {
        List<ServiceEntity> services = serviceData.specialServices("Hair Care");
        model.addAttribute("services", services);
        return "serviceHair";
    }

    @GetMapping("/serviceMakeUp")
    public String serviceMakeUp(Model model) {
        List<ServiceEntity> services = serviceData.specialServices("MakeUp");
        model.addAttribute("services", services);
        return "serviceMakeUp";
    }

    @GetMapping("/serviceFace")
    public String serviceFace(Model model) {
        List<ServiceEntity> services = serviceData.specialServices("Face Care");
        model.addAttribute("services", services);
        return "serviceFace";
    }

    @GetMapping("/serviceLashes")
    public String serviceLashes(Model model) {
        List<ServiceEntity> services = serviceData.specialServices("Lashes/Brows");
        model.addAttribute("services", services);
        return "serviceLashes";
    }

    @GetMapping("/serviceNails")
    public String serviceNails(Model model) {
        List<ServiceEntity> services = serviceData.specialServices("Nail Care");
        model.addAttribute("services", services);
        return "serviceNails";
    }
}
