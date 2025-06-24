package com.connorwiltsie.fallout.charactertracker.controller;

import com.connorwiltsie.fallout.charactertracker.service.AccountService;
import com.connorwiltsie.fallout.charactertracker.service.PlayerCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.connorwiltsie.fallout.charactertracker.entity.*;

@Controller
public class PlayerCharacterController {
    private final PlayerCharacterService playerCharacterService;

    @Autowired
    public PlayerCharacterController(PlayerCharacterService playerCharacterService) {
        this.playerCharacterService = playerCharacterService;
    }


    @GetMapping ("/home")
    public String characterList(Model model) {
        return "character-list";
    }


}
