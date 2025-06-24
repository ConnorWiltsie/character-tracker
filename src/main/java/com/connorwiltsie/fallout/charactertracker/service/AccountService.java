package com.connorwiltsie.fallout.charactertracker.service;

import com.connorwiltsie.fallout.charactertracker.entity.*;
import org.springframework.stereotype.Service;
import com.connorwiltsie.fallout.charactertracker.repository.*;
import com.connorwiltsie.fallout.charactertracker.exception.*;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    public AccountService (AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void registerAccount(Account account) throws InvalidDataException{

        if (account.getPassword().length() < 3) {
            throw new InvalidDataException("Password length must be greater than 3");
        }
        else if (accountRepository.findByUsername(account.getUsername()).isPresent()) {
            throw new InvalidDataException("Username already exists");
        }
        else {
            accountRepository.save(account);
        }

    }
}
