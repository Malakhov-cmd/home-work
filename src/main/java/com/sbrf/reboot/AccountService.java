package com.sbrf.reboot;

import com.sbrf.reboot.repository.AccountRepository;
import lombok.NonNull;

public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean isClientHasContract(
            @NonNull long clientId,
            @NonNull long contractNumber
    ) {
        return accountRepository.getAllAccountsByClientId(clientId)
                .stream().anyMatch((contractNum) -> contractNum == contractNumber);
    }

    public boolean isContractCreated(@NonNull long contractId) {
        return accountRepository.getAccountById(contractId) != null;
    }
}
