package com.connorwiltsie.fallout.charactertracker.service;

import com.connorwiltsie.fallout.charactertracker.entity.PlayerCharacter;
import com.connorwiltsie.fallout.charactertracker.exception.ResourceNotFoundException;
import com.connorwiltsie.fallout.charactertracker.repository.AccountRepository;
import com.connorwiltsie.fallout.charactertracker.repository.PlayerCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerCharacterService {
    private final PlayerCharacterRepository playerCharacterRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public PlayerCharacterService(PlayerCharacterRepository playerCharacterRepository, AccountRepository accountRepository) {
        this.playerCharacterRepository = playerCharacterRepository;
        this.accountRepository = accountRepository;
    }

    public PlayerCharacter findById(long id) {
        Optional<PlayerCharacter> playerCharacter = playerCharacterRepository.findById(id);
        if (playerCharacter.isEmpty()) {
            throw new ResourceNotFoundException("Character not found");
        }
        else {
            return playerCharacter.get();
        }
    }

    public PlayerCharacter registerCharacter(PlayerCharacter playerCharacter) {
        playerCharacter.setCurrentHitPoints(playerCharacter.getHitPoints());
        playerCharacter.setCurrentActionPoints(playerCharacter.getActionPoints());
        playerCharacter.setCurrentStaminaPoints(playerCharacter.getStaminaPoints());
        return playerCharacterRepository.save(playerCharacter);
    }

    public PlayerCharacter save(PlayerCharacter playerCharacter) {
        return playerCharacterRepository.save(playerCharacter);
    }

    public List<PlayerCharacter> getCharacters(long playerId) {
        return playerCharacterRepository.findAllByPlayerID(playerId);
    }

    public PlayerCharacter getCharacterByCharacterID(long id) {
        Optional<PlayerCharacter> optionalCharacter = playerCharacterRepository.findById(id);
        if(optionalCharacter.isEmpty()) {
            throw new ResourceNotFoundException("Character not found");
        }
        else {
            return optionalCharacter.get();
        }

    }

    public void delete(Long id) {
        playerCharacterRepository.deleteById(id);
    }
}
