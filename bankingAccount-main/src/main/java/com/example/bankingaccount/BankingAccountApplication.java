package com.example.bankingaccount;

import com.example.bankingaccount.Entities.*;
import com.example.bankingaccount.Repositories.AccountRepo;
import com.example.bankingaccount.Repositories.CardRepo;
import com.example.bankingaccount.Repositories.UserRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@SpringBootApplication
public class BankingAccountApplication implements CommandLineRunner {
    @Autowired
    private  PasswordEncoder passwordEncoder;


    @Autowired
    private  CardRepo cardRepository;
    @Autowired
    private  UserRepo userRepository;
    public static void main(String[] args) {
        SpringApplication.run(BankingAccountApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
    @PostConstruct
    public void populateData(){

        Account account1 = Account.builder()
                .rib(1234567890L)
                .accountNumber(9876543210L)
                .creationDate("1990-01-01")
                .isActive(true)
                .agencyAdress("123 Main St")
                .build();

        Account account2 = Account.builder()
                .rib(9876543210L)
                .accountNumber(1234567890L)
                .creationDate("1990-01-01")
                .isActive(false)
                .agencyAdress("456 Elm St")
                .build();

        User user1 = User.builder()
                .id(UUID.randomUUID())
                .firstname("John")
                .lastname("Doe")
                .email("john.doe@example.com")
                .phoneNumber("61609506")
                .birthDate("1990-01-01")
                .account(account1)
                .password(passwordEncoder.encode("123456"))
                .build();

        User user2 = User.builder()
                .id(UUID.randomUUID())
                .firstname("Jane")
                .lastname("Smith")
                .email("jane.smith@example.com")
                .phoneNumber("00187854")
                .birthDate("1990-01-01")
                .account(account2)
                .password(passwordEncoder.encode("654321"))
                .build();

        PhysicalCard physicalCard1 = PhysicalCard.builder()
                .cardNumber(1234567890123456L)
                .expirationDate("1990-01-01")
                .creationDate("1990-01-01")
                .cvc(123)
                .isEnabled(false)
                .account(account1)
                .build();

        PhysicalCard physicalCard2 = PhysicalCard.builder()
                .cardNumber(1234567890123457L)
                .expirationDate("1990-01-01")
                .creationDate("1990-01-01")
                .cvc(123)
                .isEnabled(true)
                .account(account2)
                .build();

        VirtualCard virtualCard1 = VirtualCard.builder()
                .cardNumber(9876543210987654L)
                .expirationDate("1990-01-01")
                .creationDate("1990-01-01")
                .cvc(789)
                .isEnabled(false)
                .account(account1)
                .build();

        VirtualCard virtualCard2 = VirtualCard.builder()
                .cardNumber(2345688L)
                .expirationDate("1990-01-01")
                .creationDate("1990-01-01")
                .cvc(789)
                .isEnabled(false)
                .account(account1)
                .build();

        VirtualCard virtualCard22 = VirtualCard.builder()
                .cardNumber(1111111111111111L)
                .expirationDate("1990-01-01")
                .creationDate("1990-01-01")
                .cvc(789)
                .isEnabled(true)
                .account(account2)
                .build();

        VirtualCard virtualCard23 = VirtualCard.builder()
                .cardNumber(2222222222222L)
                .expirationDate("1990-01-01")
                .creationDate("1990-01-01")
                .cvc(789)
                .isEnabled(true)
                .account(account2)
                .build();

        Set<Card> vcard1 = new HashSet<>(Arrays.asList(virtualCard1, virtualCard2));
        Set<Card> vcard2 = new HashSet<>(Arrays.asList(virtualCard22, virtualCard23));

        account1.setVirtualCards(vcard1);
        account1.setPhysicalCard(physicalCard1);
        account2.setVirtualCards(vcard2);
        account2.setPhysicalCard(physicalCard2);
        account1.setUser(user1);
        account2.setUser(user2);

        userRepository.save(user1);
        userRepository.save(user2);

        cardRepository.save(physicalCard1);
        cardRepository.save(physicalCard2);
        cardRepository.save(virtualCard1);
        cardRepository.save(virtualCard2);
        cardRepository.save(virtualCard22);
        cardRepository.save(virtualCard23);
    }



}
