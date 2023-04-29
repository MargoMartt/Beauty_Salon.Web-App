package com.example.beauty_salon.controller;

import com.example.beauty_salon.models.ProfileModel;
import com.example.beauty_salon.models.RecordProfile;
import com.example.beauty_salon.models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/futureRecords")
    public String futureRecords(Model model) {
        ArrayList<RecordProfile> futureRecords = profileModel.futureRecords(89);
        model.addAttribute("futureRecords", futureRecords);
        return "futureRecords";
    }

    @GetMapping("/futureRecords/refuse/{id}")
    public String futureRecords(@PathVariable(name = "id") int id, Model model) {

        ArrayList<RecordProfile> futureRecords = profileModel.futureRecords(89);
        model.addAttribute("futureRecords", futureRecords);
        profileModel.refuse(89, id);
        return "redirect:/futureRecords";
    }

    @GetMapping("/activityHistory")
    public String activityHistory(Model model) {
        ArrayList<RecordProfile> activityHistory = profileModel.recordHistory(89);
        model.addAttribute("activityHistory", activityHistory);
        return "activityHistory";
    }

    @GetMapping("/info")
    public String info(Model model) {
        UserInfo user = profileModel.user(89);
        model.addAttribute("user", user);
        return "userInfo";
    }
    @GetMapping("/info/changePassword")
    public String changePassword(Model model) {
        UserInfo user = profileModel.user(89);
        model.addAttribute("user", user);
        model.addAttribute(user.getCertificate());

        return "userInfo";
    }
    @GetMapping("/info/giveCertificate")
    public String giveCertificate(Model model) {
        UserInfo user = profileModel.user(89);
        model.addAttribute("user", user);
        return "userInfo";
    }
    @GetMapping("/info/replenishBalance")
    public String replenishBalance(Model model) {
        UserInfo user = profileModel.user(89);
        model.addAttribute("user", user);
        return "userInfo";
    }
}
