package com.bankingappzipkinsleuth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bankingappzipkinsleuth.entity.Account;
import com.bankingappzipkinsleuth.dto.AccountCreationRequest;
import com.bankingappzipkinsleuth.service.BankingService;

@RestController
@RequestMapping("/api/banking")
public class BankingController {

    private static final Logger logger = LoggerFactory.getLogger(BankingController.class);

    @Autowired
    private BankingService bankingService;

    @PostMapping("/account/details")
    public ResponseEntity<Account> createAccount(@RequestBody AccountCreationRequest request) {
        logger.debug("Received request to create account with name: {} and balance: {}", request.getName(), request.getBalance());

        Account account = new Account();
        account.setName(request.getName());
        account.setBalance(request.getBalance());

        Account createdAccount = bankingService.createAccount(account);
        logger.info("Created account with ID: {}", createdAccount.getId());

        return ResponseEntity.ok(createdAccount);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        logger.debug("Received request to get account with ID: {}", id);

        try {
            Account account = bankingService.getAccount(id);
            if (account != null) {
                logger.info("Retrieved account with ID: {}", id);
                return ResponseEntity.ok(account);
            } else {
                logger.warn("Account with ID: {} not found", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error occurred while retrieving account with ID: {}", id, e);
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam Long fromAccountId, @RequestParam Long toAccountId, @RequestParam double amount) {
        logger.debug("Received request to transfer amount: {} from account ID: {} to account ID: {}", amount, fromAccountId, toAccountId);

        try {
            bankingService.transfer(fromAccountId, toAccountId, amount);
            logger.info("Successfully transferred amount: {} from account ID: {} to account ID: {}", amount, fromAccountId, toAccountId);
            return ResponseEntity.ok("Transferred Successfully");
        } catch (Exception e) {
            logger.error("Error occurred while transferring amount: {} from account ID: {} to account ID: {}", amount, fromAccountId, toAccountId, e);
            return ResponseEntity.status(500).body("Transfer Failed");
        }
    }
}
