package com.example.bankingaccount.Dto;

import com.example.bankingaccount.Entities.Account;
import com.example.bankingaccount.Entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .birthDate(user.getBirthDate())
                .accountId(user.getAccount().getAccountNumber()) // Assuming getAccount() is not null.
                .build();
    }

    public User toEntity(UserDTO userDTO, Account account) {
        return User.builder()
                .id(userDTO.getId())
                .firstname(userDTO.getFirstname())
                .lastname(userDTO.getLastname())
                .email(userDTO.getEmail())
                .phoneNumber(userDTO.getPhoneNumber())
                .birthDate(userDTO.getBirthDate())
                .account(account)
                .build();
    }
}
