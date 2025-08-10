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

    public PlayerCharacter updateCharacter(PlayerCharacter updatedCharacter) {
        // Find the existing character
        PlayerCharacter existingCharacter = findById(updatedCharacter.getId());

        // Update all the fields from the form
        // General Information
        existingCharacter.setName(updatedCharacter.getName());
        existingCharacter.setRace(updatedCharacter.getRace());
        existingCharacter.setBackground(updatedCharacter.getBackground());
        existingCharacter.setLevel(updatedCharacter.getLevel());
        existingCharacter.setXp(updatedCharacter.getXp());

        // S.P.E.C.I.A.L attributes
        existingCharacter.setStrength(updatedCharacter.getStrength());
        existingCharacter.setPerception(updatedCharacter.getPerception());
        existingCharacter.setEndurance(updatedCharacter.getEndurance());
        existingCharacter.setCharisma(updatedCharacter.getCharisma());
        existingCharacter.setIntelligence(updatedCharacter.getIntelligence());
        existingCharacter.setAgility(updatedCharacter.getAgility());
        existingCharacter.setLuck(updatedCharacter.getLuck());

        // Skills
        existingCharacter.setBarter(updatedCharacter.getBarter());
        existingCharacter.setBreach(updatedCharacter.getBreach());
        existingCharacter.setCrafting(updatedCharacter.getCrafting());
        existingCharacter.setEnergyWeapons(updatedCharacter.getEnergyWeapons());
        existingCharacter.setExplosives(updatedCharacter.getExplosives());
        existingCharacter.setGuns(updatedCharacter.getGuns());
        existingCharacter.setIntimidation(updatedCharacter.getIntimidation());
        existingCharacter.setMedicine(updatedCharacter.getMedicine());
        existingCharacter.setMeleeWeapons(updatedCharacter.getMeleeWeapons());
        existingCharacter.setScience(updatedCharacter.getScience());
        existingCharacter.setSneak(updatedCharacter.getSneak());
        existingCharacter.setSpeech(updatedCharacter.getSpeech());
        existingCharacter.setSurvival(updatedCharacter.getSurvival());
        existingCharacter.setUnarmed(updatedCharacter.getUnarmed());
        existingCharacter.setPilot(updatedCharacter.getPilot());

        // Health and combat stats
        existingCharacter.setArmorClass(updatedCharacter.getArmorClass());
        existingCharacter.setDamageThreshold(updatedCharacter.getDamageThreshold());
        existingCharacter.setStaminaPoints(updatedCharacter.getStaminaPoints());
        existingCharacter.setHitPoints(updatedCharacter.getHitPoints());
        existingCharacter.setHealingRate(updatedCharacter.getHealingRate());
        existingCharacter.setActionPoints(updatedCharacter.getActionPoints());

        // Survival conditions
        existingCharacter.setHungerLevel(updatedCharacter.getHungerLevel());
        existingCharacter.setDehydrationLevel(updatedCharacter.getDehydrationLevel());
        existingCharacter.setExhaustionLevel(updatedCharacter.getExhaustionLevel());
        existingCharacter.setRadiationLevel(updatedCharacter.getRadiationLevel());
        existingCharacter.setRadsLevel(updatedCharacter.getRadsLevel());

        // Derived stats
        existingCharacter.setCombatSequence(updatedCharacter.getCombatSequence());
        existingCharacter.setPassiveSense(updatedCharacter.getPassiveSense());
        existingCharacter.setPartyNerve(updatedCharacter.getPartyNerve());
        existingCharacter.setGroupSneak(updatedCharacter.getGroupSneak());

        // Save and return
        return playerCharacterRepository.save(existingCharacter);
    }
}
