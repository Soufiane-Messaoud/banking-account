package com.example.bankingaccount.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {
    private Long accountNumber;
    private Long rib;
    private String creationDate;
    private boolean isActive;
    private String agencyAdress;
    private Set<Long> virtualCardIds; // Instead of Set<CardDTO>, we use the cardIds
    private UUID userId;
}
