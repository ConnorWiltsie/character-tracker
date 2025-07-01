package com.connorwiltsie.fallout.charactertracker.controller;

import com.connorwiltsie.fallout.charactertracker.service.AccountService;
import com.connorwiltsie.fallout.charactertracker.service.PlayerCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.connorwiltsie.fallout.charactertracker.entity.*;

@Controller
public class PlayerCharacterController {
    private final PlayerCharacterService playerCharacterService;
    private final AccountService accountService;

    @Autowired
    public PlayerCharacterController(PlayerCharacterService playerCharacterService, AccountService accountService) {
        this.playerCharacterService = playerCharacterService;
        this.accountService = accountService;
    }


    @GetMapping ("/")
    public String characterList(Model model) {

        return "character-list";
    }

    @GetMapping ("/character-create")
    public String characterCreate(Model model, Authentication authentication) {
        PlayerCharacter playerCharacter = new PlayerCharacter();
        model.addAttribute("playerCharacter", playerCharacter);
        return "character-create";
    }

    @PostMapping ("/character-create")
    public String createCharacter(@ModelAttribute("playerCharacter") PlayerCharacter playerCharacter, Model model, Authentication authentication) {

        int userId = accountService.getUserIdByUsername(authentication.getName());
        playerCharacter.setPlayerID(userId);
        System.out.println(playerCharacterService.registerCharacter(playerCharacter));
        return "character-create";
    }

    @GetMapping ("/get-characters")
    public String getCharacters(Authentication authentication, Model model) {
        int playerId = accountService.getUserIdByUsername(authentication.getName());
        model.addAttribute("characters", playerCharacterService.getCharacters(playerId));
        return "character-list";
    }

}
