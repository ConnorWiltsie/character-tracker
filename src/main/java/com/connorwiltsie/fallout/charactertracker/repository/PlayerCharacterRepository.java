package com.connorwiltsie.fallout.charactertracker.repository;

import com.connorwiltsie.fallout.charactertracker.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerCharacterRepository extends JpaRepository<PlayerCharacter, Long>{
    List<PlayerCharacter> findAllByPlayerID(long playerId);
}
