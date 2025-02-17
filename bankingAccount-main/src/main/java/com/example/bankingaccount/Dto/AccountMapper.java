package com.example.bankingaccount.Dto;

import com.example.bankingaccount.Entities.Account;
import com.example.bankingaccount.Entities.Card;
import com.example.bankingaccount.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AccountMapper {


    public Account toEntity(AccountDTO accountDTO, User user, Set<Card> virtualCards) {
        return Account.builder()
                .accountNumber(accountDTO.getAccountNumber())
                .rib(accountDTO.getRib())
                .creationDate(accountDTO.getCreationDate())
                .isActive(accountDTO.isActive())
                .agencyAdress(accountDTO.getAgencyAdress())
                .user(user)
                .virtualCards(virtualCards)
                .build();
    }


    public AccountDTO toDTO(Account account) {
        Set<Long> virtualCardIds = account.getVirtualCards()
                .stream()
                .map(Card::getCardNumber)
                .collect(Collectors.toSet());

        return AccountDTO.builder()
                .accountNumber(account.getAccountNumber())
                .rib(account.getRib())
                .creationDate(account.getCreationDate())
                .isActive(account.isActive())
                .agencyAdress(account.getAgencyAdress())
                .userId(account.getUser().getId())
                .virtualCardIds(virtualCardIds)
                .build();
    }
}
