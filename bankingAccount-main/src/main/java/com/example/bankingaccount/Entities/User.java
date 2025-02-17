package com.example.bankingaccount.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Entity
@Data@NoArgsConstructor@AllArgsConstructor@Builder@ToString
@Table(name="_user")
public class User implements UserDetails {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonIgnore
    private UUID id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    @JsonIgnore
    private String password;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private Account account;
    private String birthDate;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    @Override
    @JsonIgnore
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
