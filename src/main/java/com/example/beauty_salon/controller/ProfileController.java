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

    @GetMapping("/profileRecord")
    public String futureRecords(Model model) {
        ArrayList<RecordProfile> futureRecords = profileModel.futureRecords(89);
        model.addAttribute("futureRecords", futureRecords);
        return "profileRecord";
    }

    @GetMapping("/profileRecord/refuse/{id}")
    public String futureRecords(@PathVariable(name = "id") int id, Model model) {

        ArrayList<RecordProfile> futureRecords = profileModel.futureRecords(89);
        model.addAttribute("futureRecords", futureRecords);
        profileModel.refuse(89, id);
        return "redirect:/profileRecord";
    }

    @GetMapping("/profileHistory")
    public String activityHistory(Model model) {
        ArrayList<RecordProfile> activityHistory = profileModel.recordHistory(89);
        model.addAttribute("activityHistory", activityHistory);
        return "profileHistory";
    }

    @GetMapping("/profileInfo")
    public String info(Model model) {
        UserInfo user = profileModel.user(89);
        model.addAttribute("user", user);
        model.addAttribute("certificate", user.getCertificate());
        return "profileInfo";
    }

    @PostMapping("/info/changePassword")
    public String changePassword(@RequestParam(name = "oldPassword", required = false) String oldPassword,
                                 @RequestParam(name = "newPassword", required = false) String newPassword,
                                 @RequestParam(name = "newPassword2", required = false) String newPassword2,
                                 Model model) {
        UserInfo user = profileModel.user(89);
        model.addAttribute("user", user);
        String answer = profileModel.newPassword(89, oldPassword, newPassword, newPassword2);
        if (answer.equals("success"))
            return "redirect:/profile/info";
        else model.addAttribute("answer", answer);
        return "userInfo";
    }

    @PostMapping("/info/giveCertificate")
    public String giveCertificate(@RequestParam(name = "email", required = false) String email, Model model) {
        UserInfo user = profileModel.user(89);
        model.addAttribute("user", user);
        model.addAttribute("certificate", user.getCertificate());
        String answerCertificate = profileModel.giveCertificate(89, email, user.getCertificate());
        if (answerCertificate.equals("success"))
            return "redirect:/profile/info";
        else
            model.addAttribute("answerCertificate", answerCertificate);
        return "userInfo";
    }

    @PostMapping("/info/replenishBalance")
    public String replenishBalance(@RequestParam(name = "card", required = false) int card,
                                   @RequestParam(name = "CVV", required = false) int CVV,
                                   @RequestParam(name = "sum", required = false) Double sum,
                                   Model model) {
        UserInfo user = profileModel.user(89);
        model.addAttribute("user", user);
        profileModel.replenishBalance(89, sum);
        return "redirect:/profile/info";
    }

    @PostMapping("/info/changeInfo")
    public String changeInfo(@RequestParam(name = "name", required = false) String name,
                             @RequestParam(name = "surname", required = false) String surname,
                             Model model) {
        UserInfo user = profileModel.user(89);
        model.addAttribute("user", user);
        profileModel.changeInfo(89, name, surname);
        return "redirect:/profile/info";
    }
}
