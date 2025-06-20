package com.connorwiltsie.fallout.charactertracker.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weapon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int apCost;

    private int attackBonus;

    private int damage;

    private String range;

    private String critEffect;

    private String specialProperties;

    private String ammo;

    private String mods;

    private int levelsOfDecay;
}