package com.connorwiltsie.fallout.charactertracker.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "player_character")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerCharacter {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Associated Player
    private long playerID;
    
    //General character information
    private String name;
    private String race;
    private String background;
    private String traitsAndPerks;
    private int level;
    private int xp;

    // SPECIAL attributes
    private int strength;
    private int perception;
    private int endurance;
    private int charisma;
    private int intelligence;
    private int agility;
    private int luck;

    // Health and combat stats
    private int armorClass;
    private int damageThreshold;
    private int staminaPoints;
    private int currentStaminaPoints;
    private int hitPoints;
    private int currentHitPoints;
    private int healingRate;
    private int actionPoints;
    private int currentActionPoints;

    // Derived/conditional states
    private String currentConditions;
    private String combatSequence;
    private String passiveSense;
    private String partyNerve;
    private String groupSneak;

    // Fatigue/Survival conditions
    private int hungerLevel;
    private int dehydrationLevel;
    private int exhaustionLevel;
    private int radiationLevel;
    private int radsLevel;

    // Inventory & economy
    private int caps;
    private int carryLoad;
    private String inventory;

    // Skills
    private int barter;
    private int breach;
    private int crafting;
    private int energyWeapons;
    private int explosives;
    private int guns;
    private int intimidation;
    private int medicine;
    private int meleeWeapons;
    private int science;
    private int sneak;
    private int speech;
    private int survival;
    private int unarmed;
    private int pilot;
}