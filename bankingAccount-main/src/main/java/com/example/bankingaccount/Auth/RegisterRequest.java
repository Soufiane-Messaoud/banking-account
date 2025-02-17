package com.example.bankingaccount.Auth;

import com.example.bankingaccount.Entities.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String firstname;
  private String lastname;
  private String phoneNumber;
  private String password;
  private Account account;
}
