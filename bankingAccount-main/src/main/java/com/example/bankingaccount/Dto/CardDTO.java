package com.example.bankingaccount.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDTO {
    private Long cardNumber;
    private String expirationDate;
    private String creationDate;
    private int cvc;
    private boolean isEnabled;
    private String type;

    private Long accountId;
}

