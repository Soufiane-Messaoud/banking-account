package com.example.bankingaccount.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@NoArgsConstructor@AllArgsConstructor@ToString@Builder
public class Account {
    @Id
    @Column(name = "id")
    private Long accountNumber;
    private Long rib;
    private String creationDate;
    private boolean isActive;
    private String agencyAdress;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pcard_number",nullable = false)
    @JsonManagedReference

    private Card physicalCard;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id" , referencedColumnName = "id")
    @JsonManagedReference

    private Set<Card> virtualCards;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getRib() {
        return rib;
    }

    public void setRib(Long rib) {
        this.rib = rib;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getAgencyAdress() {
        return agencyAdress;
    }

    public void setAgencyAdress(String agencyAdress) {
        this.agencyAdress = agencyAdress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPhysicalCard(PhysicalCard physicalCard) {
        if (physicalCard == null) {
            if (this.physicalCard != null) {
                this.physicalCard.setAccount(null);
            }
        } else {
            physicalCard.setAccount(this);
        }
        this.physicalCard = physicalCard;
    }

    public Card getPhysicalCard() {
        return physicalCard;
    }

    public void setPhysicalCard(Card physicalCard) {
        this.physicalCard = physicalCard;
    }

    public Set<Card> getVirtualCards() {
        return virtualCards;
    }

    public void setVirtualCards(Set<Card> virtualCards) {
        this.virtualCards = virtualCards;
        for (Card virtualCard : virtualCards) {
            virtualCard.setAccount(this);
        }

}
}
