package com.example.bankingaccount.Controller;

import com.example.bankingaccount.Dto.CardDTO;
import com.example.bankingaccount.Dto.CardMapper;
import com.example.bankingaccount.Entities.Account;
import com.example.bankingaccount.Entities.Card;
import com.example.bankingaccount.Entities.User;
import com.example.bankingaccount.Entities.VirtualCard;
import com.example.bankingaccount.Repositories.AccountRepo;
import com.example.bankingaccount.Repositories.CardRepo;
import com.example.bankingaccount.Repositories.UserRepo;
import com.example.bankingaccount.Service.GlobalService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class RestController {
    private final UserRepo userRepo;
    private final AccountRepo accountRepo;
    private final CardRepo cardRepo;
    private final GlobalService globalService;
    private final CardMapper cardMapper;


    @GetMapping("/user/{uuid}")
    private ResponseEntity<User> getUser(@PathVariable UUID uuid){
        return ResponseEntity.ok(userRepo.findById(uuid).get());
    }

    @GetMapping("/account/{uuid}")
    private ResponseEntity<Account> getAccount(@PathVariable UUID uuid){
        return ResponseEntity.ok(this.accountRepo.findAccByUserId(uuid));
    }

    @PostMapping("/cards/enable/{uuid}")
    private boolean enableCard(@PathVariable UUID uuid, @RequestBody Long cardNumber) throws Exception{
        Account account=this.accountRepo.findAccByUserId(uuid);
        Optional<Card> targetedCard=this.globalService.getSpecificVCard(account,cardNumber);
       if(targetedCard.isEmpty()){
            throw new RuntimeException("Virtuel Card not Present in this account");
        }
        else if(targetedCard.get().isEnabled()){
            throw new RuntimeException("Virtuel Card is Already enabled ");
        }
        targetedCard.get().setEnabled(true);
        cardRepo.save(targetedCard.get());
        return targetedCard.get().isEnabled();
    }
    @PostMapping("/cards/disable/{uuid}")
    private boolean disableCard(@PathVariable UUID uuid, @RequestBody Long cardNumber) throws Exception{
        Account account=this.accountRepo.findAccByUserId(uuid);
        Optional<Card> targetedCard=this.globalService.getSpecificVCard(account,cardNumber);
       if(targetedCard.isEmpty()){
            throw new RuntimeException("Virtuel Card not Present in this account");
        }
        else if(!targetedCard.get().isEnabled()){
            throw new RuntimeException("Virtuel Card is Already disabled ");
        }
        targetedCard.get().setEnabled(false);
        cardRepo.save(targetedCard.get());

        return targetedCard.get().isEnabled();
    }

    @PostMapping("/cards/createV/{accNumber}")
    private ResponseEntity<Card> createVCard(@PathVariable Long accNumber, @RequestBody CardDTO cardDto) throws Exception{
        Account account=this.accountRepo.findById(accNumber).get();
        if (account == null) {
            throw new RuntimeException("Account not found");
        }
        cardDto.setEnabled(false);
        cardDto.setCvc(globalService.generateCvc());
        cardDto.setType("virtualCard");
        cardDto.setExpirationDate(globalService.generateExpirationDate());
        cardDto.setCreationDate(globalService.generateCreationDate());

        Card virtualCard = cardMapper.toEntity(cardDto,account);
        virtualCard.setAccount(account);
        account.getVirtualCards().add(virtualCard);
        return ResponseEntity.ok(cardRepo.save(virtualCard));
    }
    @DeleteMapping("/cards/delete/{cardNumber}")
    private ResponseEntity<String> deleteVirtualCard(@PathVariable Long cardNumber) {
        Optional<Card> optionalCard = cardRepo.findById(cardNumber);
        if (optionalCard.isEmpty()) {
            throw new RuntimeException("Virtual Card not found");
        }
        Card card = optionalCard.get();

        if (!(card instanceof VirtualCard)) {
            throw new RuntimeException("Invalid card type. Expected VirtualCard.");
        }

        cardRepo.delete(card);

        return ResponseEntity.ok("Virtual Card deleted successfully");
    }
}
