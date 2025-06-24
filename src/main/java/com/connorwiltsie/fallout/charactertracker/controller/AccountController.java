package com.connorwiltsie.fallout.charactertracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.connorwiltsie.fallout.charactertracker.entity.*;
import com.connorwiltsie.fallout.charactertracker.service.*;
import com.connorwiltsie.fallout.charactertracker.exception.*;

@Controller
public class AccountController {

    private final AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping ("/")
    public String login(Model model) {
        return "login";
    }

    @GetMapping ("/register")
    public String register(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "register";
    }

    @PostMapping("/register/save")
    public String registerUser(@ModelAttribute("account") Account account, Model model) {
        try {
            accountService.registerAccount(account);
            return "login";
        }
        catch (InvalidDataException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("errorTitle", "Registration Failed");
            return "error";
        }

    }
}