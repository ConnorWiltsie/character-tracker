package com.connorwiltsie.fallout.charactertracker.service;

import com.connorwiltsie.fallout.charactertracker.entity.PlayerCharacter;
import com.connorwiltsie.fallout.charactertracker.repository.AccountRepository;
import com.connorwiltsie.fallout.charactertracker.repository.PlayerCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerCharacterService {
    private final PlayerCharacterRepository playerCharacterRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public PlayerCharacterService(PlayerCharacterRepository playerCharacterRepository, AccountRepository accountRepository) {
        this.playerCharacterRepository = playerCharacterRepository;
        this.accountRepository = accountRepository;
    }

    public PlayerCharacter registerCharacter(PlayerCharacter playerCharacter) {
        return playerCharacterRepository.save(playerCharacter);
    }

}
