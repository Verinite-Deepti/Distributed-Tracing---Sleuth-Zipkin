package com.bankingappzipkinsleuth.service;


import com.bankingappzipkinsleuth.entity.Account;
import com.bankingappzipkinsleuth.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankingService {

    @Autowired
    private AccountRepository accountRepository;

    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Transactional
    public void transfer(Long fromAccountId, Long toAccountId, double amount) {
        Account fromAccount = getAccount(fromAccountId);
        Account toAccount = getAccount(toAccountId);

        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Sufficient funds required");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }



	 public Account createAccount(Account account) {
	        
	        if (account.getName() == null || account.getName().isEmpty()) {
	            throw new IllegalArgumentException("Account name cannot be null or empty");
	        }
	        if (account.getBalance() < 0) {
	            throw new IllegalArgumentException("Initial balance cannot be negative");
	        }
	        return accountRepository.save(account);
	    }
	
}

