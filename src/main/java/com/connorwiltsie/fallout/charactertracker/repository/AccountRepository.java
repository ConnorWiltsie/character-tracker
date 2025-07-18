package com.connorwiltsie.fallout.charactertracker.repository;

import com.connorwiltsie.fallout.charactertracker.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>{
    Optional<Account> findByUsername(String username);

}
