package com.example.bankingaccount.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Data@NoArgsConstructor
@DiscriminatorValue("V")
public class VirtualCard extends Card {
    @Builder
    public VirtualCard(Long cardNumber, String expirationDate, String creationDate, int cvc, boolean isEnabled,Account account) {
        super(cardNumber, expirationDate, creationDate, cvc, isEnabled,account);

    }
}
