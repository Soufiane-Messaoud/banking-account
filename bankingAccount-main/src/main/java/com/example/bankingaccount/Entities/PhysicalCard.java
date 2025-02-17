package com.example.bankingaccount.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;
@Entity
@Data@NoArgsConstructor
@DiscriminatorValue("P")
public class PhysicalCard extends Card{

    @Builder
    public PhysicalCard(Long cardNumber, String expirationDate, String creationDate, int cvc, boolean isEnabled,Account account) {
        super(cardNumber, expirationDate, creationDate, cvc, isEnabled,account);
    }
}
