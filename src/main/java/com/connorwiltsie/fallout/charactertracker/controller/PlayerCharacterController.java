package com.connorwiltsie.fallout.charactertracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.connorwiltsie.fallout.charactertracker.entity.*;

@Controller
public class PlayerCharacterController {

        @GetMapping
        public String home(Model model) {
            model.addAttribute("playerCharacter", new PlayerCharacter());
            return "character-form";
        }

}
