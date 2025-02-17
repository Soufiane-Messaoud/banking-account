package com.example.bankingaccount.Repositories;

import com.example.bankingaccount.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {
    @Query(value = "select * from ACCOUNT a where a.user_id=:id ",nativeQuery = true)
    Account findAccByUserId(UUID id);
}
