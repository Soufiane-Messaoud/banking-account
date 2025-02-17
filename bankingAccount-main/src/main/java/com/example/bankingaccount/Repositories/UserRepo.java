package com.example.bankingaccount.Repositories;

import com.example.bankingaccount.Entities.Account;
import com.example.bankingaccount.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
    Optional<User> findByPhoneNumber(String phoneNumber);

}
