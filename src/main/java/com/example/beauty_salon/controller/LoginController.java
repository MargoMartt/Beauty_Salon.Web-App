package com.example.beauty_salon.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@Controller
public class LoginController {
    @GetMapping("/login")
    public String login () {
        return "login";
    }
}
