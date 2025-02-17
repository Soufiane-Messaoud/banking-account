package com.example.bankingaccount.Repositories;

import com.example.bankingaccount.Entities.Account;
import com.example.bankingaccount.Entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardRepo extends JpaRepository<Card,Long> {

}
