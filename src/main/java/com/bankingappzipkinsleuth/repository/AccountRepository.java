package com.bankingappzipkinsleuth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingappzipkinsleuth.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

