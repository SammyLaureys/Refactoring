package com.example.demo.controllers;

import com.example.demo.model.Game;
import com.example.demo.model.Knuffel;
import com.example.demo.model.User;
import com.example.demo.model.Video;
import com.example.demo.repositories.GameRepository;
import com.example.demo.repositories.KnuffelRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.VideoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;


@Controller
@RequestMapping("/admins")
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    private String applicationName = "Knùs";

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private KnuffelRepository knuffelRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping({"/newVideo"})
    public String newVideo(Model model) {
        model.addAttribute("video", videoRepository.findAll());
        model.addAttribute("knuffel", knuffelRepository.findAll());
        return "admins/newVideo";
    }

    @GetMapping({"/newKnuffel"})
    public String newKnuffel(Model model) {
        model.addAttribute("knuffel", knuffelRepository.findAll());
        return "admins/newKnuffel";
    }

    @GetMapping({"/newGame"})
    public String newGame(Model model) {
        model.addAttribute("game", gameRepository.findAll());
        model.addAttribute("knuffel", knuffelRepository.findAll());
        return "admins/newGame";
    }

    @GetMapping({"/editKnuffel", "/editKnuffel/{id}"})
    public String editKnuffel(@PathVariable(required = false) int id, Model model) {
        Optional<Knuffel> optionalKnuffelFromDb = knuffelRepository.findById(id);
        Knuffel knuffel = (optionalKnuffelFromDb.isPresent()) ? optionalKnuffelFromDb.get() : null;
        model.addAttribute("knuffel", knuffel);
        model.addAttribute("knuffel", knuffelRepository.findAll());
        model.addAttribute("appName", applicationName);
        return "admins/editKnuffel";
    }

    @GetMapping({"/overviewUsers"})
    public String overviewUsers(Model model) {
        Iterable<User> userFromDb = userRepository.findAllUsersByPasswordNull();
        model.addAttribute("users", userFromDb);
        return "admins/Users";
    }
    @GetMapping({"/editVideo", "/editVideo/{id}"})
    public String editVideo(@PathVariable(required = false) int id, Model model) {
        Optional<Video> optionalVideoFromDb = videoRepository.findById(id);
        Video video = (optionalVideoFromDb.isPresent()) ? optionalVideoFromDb.get() : null;
        model.addAttribute("video", video);
        model.addAttribute("video", videoRepository.findAll());

        return "admins/editVideo";
    }

    @GetMapping({"/editGame", "/editGame/{id}"})
    public String editGame(@PathVariable(required = false) int id, Model model) {
        Optional<Game> optionalGameFromDb = gameRepository.findById(id);
        Game game = (optionalGameFromDb.isPresent()) ? optionalGameFromDb.get() : null;
        model.addAttribute("game", game);
        model.addAttribute("game", gameRepository.findAll());

        return "admins/editGame";
    }



    @GetMapping({"/overzichtKnuffels"})
    public String overzichtKnuffels(Model model) {
        Iterable<Knuffel> knuffelsFromDb = knuffelRepository.findAll();
        model.addAttribute("knuffels", knuffelsFromDb);
        model.addAttribute("appName", applicationName);
        return "admins/overzichtKnuffels";
    }

    @GetMapping({"/overviewVideo"})
    public String overviewVideos(Model model) {
        Iterable<Video> videosFromDb = videoRepository.findAll();
        model.addAttribute("videos", videosFromDb);
        return "admins/overviewVideo";
    }


    @GetMapping({"/overviewGame"})
    public String overviewGames(Model model) {
        Iterable<Game> gamesFromDb = gameRepository.findAll();
        model.addAttribute("games", gamesFromDb);
        return "admins/overviewGame";
    }


    @GetMapping("/")
    public String index(Model model) {
        Iterable<Knuffel> knuffelsFromDb = knuffelRepository.findAll();
        model.addAttribute("knuffels", knuffelsFromDb);
        model.addAttribute("appName", applicationName);
        return "htmlWebshop/index";
    }


    @PostMapping({"/newKnuffel"})
    public String newKnuffelPost(@RequestParam String NameKnuffel,
                                 @RequestParam String PriceKnuffel,
                                 @RequestParam String PicKnuffel,
                                 @RequestParam String KnuffelDescription,
                                 Model model) {
        logger.info(String.format("newKnuffelPost ANIMAL_NAME=%s, ANIMAL_PRICE=%s, ANIMAL_PIC=%s, ANIMAL_DESCRIPTION=%s\n", NameKnuffel, PriceKnuffel, PicKnuffel, KnuffelDescription));


        Knuffel knuffel = new Knuffel();
        knuffel.setAnimalName(NameKnuffel);
        knuffel.setAnimalPrice(PriceKnuffel);
        knuffel.setAnimalPic(PicKnuffel);
        knuffel.setAnimalDescription(KnuffelDescription);


        knuffelRepository.save(knuffel);
        return "redirect:/admins/newKnuffel";
    }


    @PostMapping({"/editKnuffel", "/editKnuffel/{knuffelId}"})
    public String editKnuffelPost(@PathVariable(required = false) int knuffelId,
                                  @RequestParam String NameKnuffel,
                                  @RequestParam String PriceKnuffel,
                                  @RequestParam String PicKnuffel,
                                  @RequestParam String KnuffelDescription,
                                  Model model) {
        logger.info(String.format("editKnuffelPost %d -- ANIMAL_NAME=%s, ANIMAL_PRICE=%s, ANIMAL_PIC=%s, ANIMAL_DESCRIPTION=%s\n", knuffelId, NameKnuffel, PriceKnuffel, PicKnuffel, KnuffelDescription));

        Optional<Knuffel> knuffelFromDb = knuffelRepository.findById(knuffelId);

        if (knuffelFromDb.isPresent()) {
            Knuffel knuffel = knuffelFromDb.get();
            knuffel.setAnimalName(NameKnuffel);
            knuffel.setAnimalPrice(PriceKnuffel);
            knuffel.setAnimalPic(PicKnuffel);
            knuffel.setAnimalDescription(KnuffelDescription);
            knuffelRepository.save(knuffel);
        }
        return "redirect:/admins/editKnuffel/" + knuffelId;
    }

    @PostMapping({"/newVideo"})
    public String newVideoPost(@RequestParam String videoTitle,
                               @RequestParam String videoUrl,
                               @RequestParam Knuffel knuffelID,
                               Model model) {
        logger.info(String.format("newVideoPost TITEL=%s, VIDEO_FILE_NAME=%s, KNUFFEL_ID=%s\n", videoTitle, videoUrl, knuffelID));
        Video video = new Video();
        video.setTitel(videoTitle);
        video.setVideoFileName(videoUrl);
        video.setKnuffel(knuffelID);

        videoRepository.save(video);
        return "redirect:/admins/newVideo";

    }
    @PostMapping({"/editVideo", "/editVideo/{videoId}"})
    public String editVideoPost(@PathVariable(required = false) int videoId,
                                @RequestParam String videoTitleEdit,
                                @RequestParam String videoUrlEdit,
                                @RequestParam Knuffel knuffelIDEdit,
                                Model model) {
        logger.info(String.format("editVideoPost %d -- TITEL=%s, VIDEO_FILE_NAME=%s, KNUFFEL_ID=%s\n", videoId, videoTitleEdit, videoUrlEdit, knuffelIDEdit));
        Optional<Video> videoFromDb = videoRepository.findById(videoId);

        if (videoFromDb.isPresent()) {
            Video video = videoFromDb.get();
            video.setTitel(videoTitleEdit);
            video.setVideoFileName(videoUrlEdit);
            video.setKnuffel(knuffelIDEdit);

            videoRepository.save(video);
        }
        return "redirect:/admins/editVideo/" + videoId;
    }




    @PostMapping({"/editGame", "/editGame/{gameId}"})
    public String editGamePost(@PathVariable(required = false) int gameId,
                               @RequestParam String gameTitleEdit,
                               @RequestParam String gamePictureEdit,
                               @RequestParam Knuffel knuffelIDgameEdit,
                               Model model) {
        logger.info(String.format("editGamePost %d -- TITEL_GAME=%s, PICTURE_GAME=%s, KNUFFEL_ID=%s\n", gameId, gameTitleEdit, gamePictureEdit, knuffelIDgameEdit));
        Optional<Game> gameFromDb = gameRepository.findById(gameId);

        if (gameFromDb.isPresent()) {
            Game game = gameFromDb.get();
            game.setTitelGame(gameTitleEdit);
            game.setPictureGame(gamePictureEdit);
            game.setKnuffel(knuffelIDgameEdit);

            gameRepository.save(game);
        }
        return "redirect:/admins/editGame/" + gameId;
    }

    @PostMapping({"/newGame"})
    public String newGamePost(@RequestParam String gamePicture,
                              @RequestParam String gameTitle,
                              @RequestParam Knuffel knuffelIDgame,
                              Model model) {
        logger.info(String.format("newGamePost PICTURE_GAME=%s, TITEL_GAME=%s, KNUFFEL_ID=%s\n", gamePicture, gameTitle, knuffelIDgame));
        Game game = new Game();
        game.setPictureGame(gamePicture);
        game.setTitelGame(gameTitle);
        game.setKnuffel(knuffelIDgame);

        gameRepository.save(game);
        return "redirect:/admins/newGame";

    }

    @GetMapping({"/addUser", "/addUser/{username}"})
    public String editUser(@PathVariable(required = false) String username, Model model) {
        Optional<User> optionalUserFromDb = userRepository.findByUsername(username);
        User user = (optionalUserFromDb.isPresent()) ? optionalUserFromDb.get() : null;
        model.addAttribute("user", user);
        model.addAttribute("user", userRepository.findAll());
        return "admins/adminAddUser";
    }


    @PostMapping({"/addUser", "/addUser/{username}"})
    public String addUserPost(@PathVariable(required = false) String username,
                              @RequestParam String password,
                              Model model) {

        Optional<User> userFromDb = userRepository.findByUsername(username);

        if (userFromDb.isPresent()) {
            User user = userFromDb.get();
            String encode = passwordEncoder.encode(password);
            logger.info(String.format("password %s\n", encode));
            user.setPassword(encode);
            userRepository.save(user);
        }
        return "redirect:/admins/overviewUsers";
    }
}