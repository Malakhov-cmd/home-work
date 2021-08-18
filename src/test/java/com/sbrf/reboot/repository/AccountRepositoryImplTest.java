package com.sbrf.reboot.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountRepositoryImplTest {

    AccountRepository accountRepository;

    @Test
    void successGetAllAccountsByClientId() throws IOException {
        accountRepository = new AccountRepositoryImpl("src/main/resources/Accounts.txt");
        Set<String> allAccountsByClientId = accountRepository.getAllAccountsByClientId(1);
        Assertions.assertTrue(allAccountsByClientId.contains("5-ACC1NUM"));
    }

    @Test
    void failGetAllAccountsByClientId() {
        accountRepository = new AccountRepositoryImpl("somePath");
        assertThrows(FileNotFoundException.class, () -> {
            accountRepository.getAllAccountsByClientId(1L);
        });
    }


}