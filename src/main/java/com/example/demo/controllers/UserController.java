package com.example.demo.controllers;

import com.example.demo.model.Knuffel;
import com.example.demo.model.User;
import com.example.demo.repositories.KnuffelRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private String applicationName = "Knùs";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KnuffelRepository knuffelRepository;

    @GetMapping("/register")
    public String register(Principal principal) {
        if (principal != null) return "redirect:/user/payment";


        return "WebAppLogIn/RegisterPagina";
    }

    @PostMapping("/register")
    public String registerPost(@RequestParam String userName,
                               @RequestParam String email,
                               @RequestParam String adress,
                               @RequestParam Knuffel knuffelId,
                               @RequestParam String postalCode,
                               @RequestParam String city) {
        User newUser = new User();
        newUser.setUsername(userName);
        newUser.setRole("USER");
        newUser.setEmail(email);
        newUser.setAdress(adress);
        newUser.setKnuffel(knuffelId);
        newUser.setPostcode(postalCode);
        newUser.setStadGemeente(city);
        userRepository.save(newUser);
        return "redirect:/user/payment";
    }

    @GetMapping({"/register/{knuffelId}"})
    public String register(@PathVariable int knuffelId, Model model) {
        Optional<Knuffel> optionalKnuffelFromDb = knuffelRepository.findById(knuffelId);
        Knuffel knuffel = optionalKnuffelFromDb.get();
        model.addAttribute("knuffel", knuffel);
        return "WebAppLogIn/RegisterPagina";
    }

    @RequestMapping("/login")
    public String login(Principal principal) {
        if (principal != null) return "redirect:/user/appHome";
        return "WebAppLogIn/InlogPagina";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "WebAppLogIn/LogoutPagina";
    }

    @RequestMapping("/register")
    public String register() {
        return "WebAppLogIn/RegisterPagina";
    }

    @GetMapping("/appHome")
    public String appHome(Principal principal, Model model) {
        Optional<User> optionalUserFromDb = userRepository.findByUsername(principal.getName());
        Iterable<Knuffel> knuffelsFromDb = knuffelRepository.findAll();
        model.addAttribute("knuffels", knuffelsFromDb);
        model.addAttribute("appName", applicationName);
        if (optionalUserFromDb.isEmpty()) {
            model.addAttribute("user", new User[]{});
        } else {
            User user = optionalUserFromDb.get();
            model.addAttribute("user", user);

        }
        return "htmlHome/appHome";
    }

    @RequestMapping("/payment")
    public String payment(Model model) {
        return "WebAppLogIn/Payment";
    }


}