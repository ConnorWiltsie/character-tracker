package com.connorwiltsie.fallout.charactertracker.controller;

import com.connorwiltsie.fallout.charactertracker.exception.InvalidDataException;
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
    public String characterList(Model model, Authentication authentication) {
        System.out.println("Authenticated user: " + authentication.getName());
        long playerId = accountService.getUserIdByUsername(authentication.getName());
        model.addAttribute("characters", playerCharacterService.getCharacters(playerId));
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

        long userId = accountService.getUserIdByUsername(authentication.getName());
        playerCharacter.setPlayerID(userId);
        System.out.println(playerCharacterService.registerCharacter(playerCharacter));
        return "character-create";
    }

    @GetMapping ("/get-characters")
    public String getCharacters(Authentication authentication, Model model) {
        long playerId = accountService.getUserIdByUsername(authentication.getName());
        model.addAttribute("characters", playerCharacterService.getCharacters(playerId));
        return "character-list";
    }

    @GetMapping ("/character-view/{id}")
    public String viewCharacter(@PathVariable long id, Authentication authentication, Model model) {
        PlayerCharacter playerCharacter = playerCharacterService.getCharacterByCharacterID(id);
        String username = authentication.getName();

        if(!(accountService.getUserIdByUsername(username) == playerCharacter.getPlayerID())) {
            throw new InvalidDataException("Access Denied");
        }
        else {
            model.addAttribute("character", playerCharacter);
            return "character-view";
        }
    }

    @PostMapping ("/character-view/{id}")
    public String updateCharacterView(@ModelAttribute("character") PlayerCharacter playerCharacter, @PathVariable long id, Authentication authentication, Model model) {

        PlayerCharacter existingCharacter = playerCharacterService.getCharacterByCharacterID(id);
        existingCharacter.setCurrentHitPoints(playerCharacter.getCurrentHitPoints());
        existingCharacter.setCurrentStaminaPoints(playerCharacter.getCurrentStaminaPoints());
        existingCharacter.setCurrentActionPoints(playerCharacter.getCurrentActionPoints());
        existingCharacter.setHungerLevel(playerCharacter.getHungerLevel());
        existingCharacter.setExhaustionLevel(playerCharacter.getExhaustionLevel());
        existingCharacter.setRadiationLevel(playerCharacter.getRadiationLevel());
        existingCharacter.setInventory(playerCharacter.getInventory());
        existingCharacter.setTraitsAndPerks(playerCharacter.getTraitsAndPerks());
        existingCharacter.setDehydrationLevel(playerCharacter.getDehydrationLevel());
        playerCharacterService.save(existingCharacter);

        model.addAttribute("character", existingCharacter);
        return "character-view";
    }

    @DeleteMapping("/characters/{id}")
    public String deleteCharacter(@PathVariable Long id) {
        playerCharacterService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/character-edit/{id}")
    public String getEditableCharacter(@PathVariable Long id, Model model, Authentication authentication) {
        PlayerCharacter playerCharacter = playerCharacterService.findById(id);
        model.addAttribute("playerCharacter", playerCharacter);
        return "character-edit";
    }

    @PostMapping("/character-edit/{id}")
    public String postEditedCharacter(@PathVariable Long id, @ModelAttribute PlayerCharacter playerCharacter, Authentication authentication) {
        // Set the ID to ensure we're updating the existing character
        playerCharacter.setId(id);

        // Update the character using your service method
        playerCharacterService.updateCharacter(playerCharacter);

        // Redirect to character view page instead
        return "redirect:/character-view/" + id;
    }

}
