package com.example.demo.controllers;

import com.example.demo.model.Game;
import com.example.demo.model.Knuffel;
import com.example.demo.model.Video;
import com.example.demo.model.*;
import com.example.demo.repositories.GameRepository;
import com.example.demo.repositories.KnuffelRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.VideoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    private String applicationName = "Knùs";

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private KnuffelRepository knuffelRepository;

    @Autowired
    private GameRepository gameRepository;


    @GetMapping({"/VideoPage/{knuffelId}"})
    public String VideoPagina(@PathVariable int knuffelId, Model model) {
        Optional<Knuffel> optionalKnuffelFromDb = knuffelRepository.findById(knuffelId);

        if (optionalKnuffelFromDb.isEmpty()) {
            model.addAttribute("video", new Video[]{});
        } else {
            Knuffel knuffel = optionalKnuffelFromDb.get();
            model.addAttribute("knuffel", knuffel);
            model.addAttribute("video", videoRepository.findVideosByKnuffel(knuffel));

        }
        return "htmlVideoGames/VideoPage";
    }

    @GetMapping({"/GamePage/{knuffelId}"})
    public String GamePagina(@PathVariable int knuffelId, Model model) {
        Optional<Knuffel> optionalKnuffelFromDb = knuffelRepository.findById(knuffelId);
        if (optionalKnuffelFromDb.isEmpty()) {
            model.addAttribute("game", new Game[]{});
        } else {
            Knuffel knuffel = optionalKnuffelFromDb.get();
            model.addAttribute("knuffel", knuffel);
            model.addAttribute("game", gameRepository.findGameByKnuffel(knuffel));

        }
        return "htmlVideoGames/GamePage";
    }

    @GetMapping("/")
    public String index(Model model) {
        Iterable<Knuffel> knuffelsFromDb = knuffelRepository.findAll();
        model.addAttribute("knuffels", knuffelsFromDb);
        model.addAttribute("appName", applicationName);
        return "htmlWebshop/index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("appName", applicationName);
        return "htmlWebshop/about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("appName", applicationName);
        return "htmlWebshop/contact";
    }
}