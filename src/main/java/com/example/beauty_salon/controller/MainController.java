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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@Controller
public class MainController {

    @RequestMapping("/main")
    public String mainInfo(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        return "main";
    }
}
