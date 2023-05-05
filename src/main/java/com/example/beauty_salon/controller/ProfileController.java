package com.example.beauty_salon.controller;

import com.example.beauty_salon.entity.UsersEntity;
import com.example.beauty_salon.models.ProfileModel;
import com.example.beauty_salon.models.RecordProfile;
import com.example.beauty_salon.models.UserInfo;
import com.example.beauty_salon.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Component
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileModel profileModel;
    @Autowired
    UsersService usersService;

    @GetMapping("/profileRecord")
    public String futureRecords(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails.getUsername().equals("admin@mail.ru"))
            return "redirect:/admin/mastersAction";
        UsersEntity user = usersService.getByLogin(userDetails.getUsername());
        ArrayList<RecordProfile> futureRecords = profileModel.futureRecords(user.getIdUser());
        model.addAttribute("futureRecords", futureRecords);
        return "profileRecord";
    }

    @GetMapping("/profileRecord/refuse/{id}")
    public String futureRecords(@AuthenticationPrincipal UserDetails userDetails, @PathVariable(name = "id") int id, Model model) {
        UsersEntity user = usersService.getByLogin(userDetails.getUsername());
        ArrayList<RecordProfile> futureRecords = profileModel.futureRecords(user.getIdUser());
        model.addAttribute("futureRecords", futureRecords);
        profileModel.refuse(user.getIdUser(), id);
        return "redirect:/profile/profileRecord";
    }

    @GetMapping("/profileHistory")
    public String activityHistory(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        UsersEntity user = usersService.getByLogin(userDetails.getUsername());
        ArrayList<RecordProfile> activityHistory = profileModel.recordHistory(user.getIdUser());
        model.addAttribute("activityHistory", activityHistory);
        return "profileHistory";
    }

    @GetMapping("/profileInfo")
    public String info(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        UsersEntity userDB = usersService.getByLogin(userDetails.getUsername());
        UserInfo user = profileModel.user(userDB.getIdUser());
        model.addAttribute("user", user);
        model.addAttribute("certificate", user.getCertificate());
        return "profileInfo";
    }

    @GetMapping("/info/changePassword")
    public String changePassword(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        UsersEntity userDB = usersService.getByLogin(userDetails.getUsername());
        UserInfo user = profileModel.user(userDB.getIdUser());
        String password = "password";
        model.addAttribute("password", password);
        model.addAttribute("user", user);
        model.addAttribute("certificate", user.getCertificate());
        return "profileInfo";
    }

    @PostMapping("/info/changePassword")
    public String changePassword(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(name = "oldPassword", required = false) String oldPassword,
                                 @RequestParam(name = "newPassword", required = false) String newPassword,
                                 @RequestParam(name = "newPassword2", required = false) String newPassword2,
                                 Model model) {
        UsersEntity userDB = usersService.getByLogin(userDetails.getUsername());
        UserInfo user = profileModel.user(userDB.getIdUser());
        model.addAttribute("user", user);
        String answer = profileModel.newPassword(userDB.getIdUser(), oldPassword, newPassword, newPassword2);
        if (answer.equals("success"))
            return "redirect:/profile/profileInfo";
        else model.addAttribute("answer", answer);
        return "redirect:/profile/info/changePassword";
    }

    @GetMapping("/info/giveCertificate")
    public String giveCertificate(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        UsersEntity userDB = usersService.getByLogin(userDetails.getUsername());
        UserInfo user = profileModel.user(userDB.getIdUser());
        model.addAttribute("user", user);
        model.addAttribute("certificate", user.getCertificate());
        String giveCertificate = "giveCertificate";
        model.addAttribute("giveCertificate", giveCertificate);
        return "profileInfo";
    }

    @PostMapping("/info/giveCertificate")
    public String giveCertificate(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(name = "email", required = false) String email, Model model) {
        UsersEntity userDB = usersService.getByLogin(userDetails.getUsername());
        UserInfo user = profileModel.user(userDB.getIdUser());
        model.addAttribute("user", user);
        model.addAttribute("certificate", user.getCertificate());
        String answerCertificate = profileModel.giveCertificate(userDB.getIdUser(), email, user.getCertificate());
        if (answerCertificate.equals("success"))
            return "redirect:/profile/profileInfo";
        else
            model.addAttribute("answerCertificate", answerCertificate);
        return "redirect:/profile/info/giveCertificate";
    }

    @GetMapping("/info/replenishBalance")
    public String replenishBalance(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        UsersEntity userDB = usersService.getByLogin(userDetails.getUsername());
        UserInfo user = profileModel.user(userDB.getIdUser());
        model.addAttribute("user", user);
        model.addAttribute("certificate", user.getCertificate());
        String balance = "balance";
        model.addAttribute("balance", balance);
        return "profileInfo";
    }

    @PostMapping("/info/replenishBalance")
    public String replenishBalance(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(name = "card", required = false) int card,
            @RequestParam(name = "CVV", required = false) int CVV,
            @RequestParam(name = "sum", required = false) Double sum,
            Model model) {
        UsersEntity userDB = usersService.getByLogin(userDetails.getUsername());
        UserInfo user = profileModel.user(userDB.getIdUser());
        model.addAttribute("user", user);
        profileModel.replenishBalance(userDB.getIdUser(), sum);
        return "redirect:/profile/profileInfo";
    }

    @GetMapping("/info/changeInfo")
    public String changeInfo(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        UsersEntity userDB = usersService.getByLogin(userDetails.getUsername());
        UserInfo user = profileModel.user(userDB.getIdUser());
        model.addAttribute("user", user);
        model.addAttribute("certificate", user.getCertificate());
        String info = "info";
        model.addAttribute("info", info);
        return "profileInfo";

    }

    @PostMapping("/info/changeInfo")
    public String changeInfo(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(name = "name", required = false) String name,
                             @RequestParam(name = "surname", required = false) String surname,
                             Model model) {
        UsersEntity userDB = usersService.getByLogin(userDetails.getUsername());
        UserInfo user = profileModel.user(userDB.getIdUser());
        model.addAttribute("user", user);
        profileModel.changeInfo(userDB.getIdUser(), name, surname);
        return "redirect:/profile/profileInfo";
    }
}
