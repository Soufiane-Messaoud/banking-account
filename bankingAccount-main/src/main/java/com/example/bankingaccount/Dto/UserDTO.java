package com.example.bankingaccount.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private UUID id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String birthDate;
    private Long accountId;;
}
