package com.example.bankingaccount.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data@AllArgsConstructor@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="card_type",discriminatorType = DiscriminatorType.STRING)@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PhysicalCard.class, name = "physicalCard"),
        @JsonSubTypes.Type(value = VirtualCard.class, name = "virtualCard")
})
public abstract class Card {
    @Id
    @Column(name = "card_id")
    private Long cardNumber;
    private String expirationDate;
    private String creationDate;
    private int cvc;
    private boolean isEnabled;

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = true)
    @JsonBackReference
    private Account account;

}
