package com.example.beauty_salon.controller;

import com.example.beauty_salon.entity.UsersEntity;
import com.example.beauty_salon.entity.UsersHasRoleEntity;
import com.example.beauty_salon.service.RoleHasUsersService;
import com.example.beauty_salon.service.UsersService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Component
@Controller
public class LoginController {
    @Autowired
    UsersService usersService;

    @Autowired
    RoleHasUsersService roleHasUsersService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    @PermitAll
    public String showRegistrationForm(WebRequest request, Model model) {
        return "register";
    }

    @PostMapping("/register")
    @PermitAll
    public String registerForm(
            @RequestParam(name = "username", required = true) String username,
            @RequestParam(name = "password", required = true) String password,
            @RequestParam(name = "name", required = true) String name,
            @RequestParam(name = "surname", required = true) String surname,
            Model model
    ) {
        UsersEntity getUser = usersService.getByLogin(username);

        if (getUser != null)
            return "redirect:/login";

        UsersEntity user = new UsersEntity();
        user.setLogin(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setUserName(name);
        user.setUserSurname(surname);
        user.setBalance(0.0);
        System.out.println(user);
        usersService.saveUser(user);

        UsersEntity returnUser = usersService.getByLogin(username);
        UsersHasRoleEntity role = new UsersHasRoleEntity();
        role.setRoleIdRole(1);
        role.setUsersIdUser(returnUser.getIdUser());
        roleHasUsersService.saveRoleHasUsers(role);

        return "/main";
    }
}
