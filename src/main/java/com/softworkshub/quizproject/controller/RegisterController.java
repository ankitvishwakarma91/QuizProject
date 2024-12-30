package com.softworkshub.quizproject.controller;

import com.softworkshub.quizproject.entity.User;
import com.softworkshub.quizproject.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (userServices.existsByUsername(username)) {
            redirectAttributes.addFlashAttribute("error", "Username already exists!");
            return "redirect:/register";
        }

        if (userServices.existsByEmail(email)) {
            redirectAttributes.addFlashAttribute("error", "Email already exists!");
            return "redirect:/register";
        }


        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        userServices.save(user);

        redirectAttributes.addFlashAttribute("success", "Registration successful! Please login.");
        return "redirect:/login";
    }
}
