package com.example.beauty_salon.controller;

import com.example.beauty_salon.entity.UsersEntity;
import com.example.beauty_salon.enums.Certificate;
import com.example.beauty_salon.service.UsersService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import com.example.beauty_salon.models.CertificateData;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Controller
public class CertificateController {

    @Autowired
    CertificateData certificateData;

    @Autowired
    UsersService usersService;
    @GetMapping("/certificate")
    public String certificate(Model model) {
        List<Integer> certificates = certificateData.bonuses();
        model.addAttribute("certificates", certificates);
        return "certificate";
    }

    @PostMapping("/certificate")
    public String certificate(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(name = "certificate", required = false) int certificate,
                              Model model) {
        System.out.println(certificate);
        UsersEntity user = usersService.getByLogin(userDetails.getUsername());
        String response = certificateData.result(certificate, user.getIdUser());
        List<Integer> certificates = certificateData.bonuses();
        model.addAttribute("certificates", certificates);
        model.addAttribute("response", response);
        return "certificate";
    }
}
