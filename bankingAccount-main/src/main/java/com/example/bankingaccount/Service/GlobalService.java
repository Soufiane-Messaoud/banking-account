package com.example.bankingaccount.Service;

import com.example.bankingaccount.Entities.Account;
import com.example.bankingaccount.Entities.Card;
import com.example.bankingaccount.Entities.VirtualCard;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class GlobalService {

    public Optional<Card> getSpecificVCard(Account account, Long cardNumber) throws Exception {
        Optional<Card> optionalCard = Stream.concat(account.getVirtualCards().stream(), Stream.of(account.getPhysicalCard()))
                .filter(card -> card instanceof VirtualCard && card.getCardNumber().equals(cardNumber))
                .findFirst();
        return optionalCard;
    }
    private String generateCardNumber(int length) {
        StringBuilder cardNumber = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            cardNumber.append(digit);
        }

        return cardNumber.toString();
    }
    public int generateCvc() {
        Random random = new Random();
        int cvc = random.nextInt(1000);
        return cvc;
    }
    public String generateCreationDate() {
        LocalDate creationDate = LocalDate.now();
        return creationDate.toString();
    }
    public String generateExpirationDate() {
        LocalDate expirationDate = LocalDate.now().plusYears(5);
        return expirationDate.toString();
    }
}
