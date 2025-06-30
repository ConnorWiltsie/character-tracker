package com.connorwiltsie.fallout.charactertracker.service;

import com.connorwiltsie.fallout.charactertracker.entity.*;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.connorwiltsie.fallout.charactertracker.repository.*;
import com.connorwiltsie.fallout.charactertracker.exception.*;

import java.net.PasswordAuthentication;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService (AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerAccount(Account account) throws InvalidDataException{

        if (account.getPassword().length() < 3) {
            throw new InvalidDataException("Password length must be greater than 3");
        }
        else if (accountRepository.findByUsername(account.getUsername()).isPresent()) {
            throw new InvalidDataException("Username already exists");
        }
        else {
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            accountRepository.save(account);
        }

    }

    public int getUserIdByUsername(String username) throws ResourceNotFoundException {
        if (accountRepository.findByUsername(username).isEmpty()) {
            throw new ResourceNotFoundException("account not found");
        }
        else {
            Account account = accountRepository.findByUsername(username).get();
            return account.getUserId();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found"));

        return User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .roles(account.getRole())
                .build();

    }
}
