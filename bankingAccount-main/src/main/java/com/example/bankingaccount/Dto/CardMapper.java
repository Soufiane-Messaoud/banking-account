package com.example.bankingaccount.Dto;

import com.example.bankingaccount.Entities.Account;
import com.example.bankingaccount.Entities.Card;
import com.example.bankingaccount.Entities.PhysicalCard;
import com.example.bankingaccount.Entities.VirtualCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CardMapper {

    public CardDTO toDTO(Card card) {
        return CardDTO.builder()
                .cardNumber(card.getCardNumber())
                .expirationDate(card.getExpirationDate())
                .creationDate(card.getCreationDate())
                .cvc(card.getCvc())
                .isEnabled(card.isEnabled())
                .accountId(card.getAccount().getAccountNumber())
                .build();
    }

    public Card toEntity(CardDTO cardDTO, Account account) {
        if ("physicalCard".equalsIgnoreCase(cardDTO.getType())) {
            return PhysicalCard.builder()
                    .cardNumber(cardDTO.getCardNumber())
                    .expirationDate(cardDTO.getExpirationDate())
                    .creationDate(cardDTO.getCreationDate())
                    .cvc(cardDTO.getCvc())
                    .isEnabled(cardDTO.isEnabled())
                    .account(account)
                    .build();
        } else if ("virtualCard".equalsIgnoreCase(cardDTO.getType())) {
            return VirtualCard.builder()
                    .cardNumber(cardDTO.getCardNumber())
                    .expirationDate(cardDTO.getExpirationDate())
                    .creationDate(cardDTO.getCreationDate())
                    .cvc(cardDTO.getCvc())
                    .isEnabled(cardDTO.isEnabled())
                    .account(account)
                    .build();
        } else {
            throw new IllegalArgumentException("Invalid card type: " + cardDTO.getType());
        }
    }
}