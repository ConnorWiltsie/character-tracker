package com.connorwiltsie.fallout.charactertracker.repository;


import com.connorwiltsie.fallout.charactertracker.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

}
