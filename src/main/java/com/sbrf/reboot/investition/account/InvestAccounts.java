package com.sbrf.reboot.investition.account;

import com.sbrf.reboot.investition.Securities;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.OptionalDouble;

public class InvestAccounts<A extends Securities> {
    @Getter
    private long owner;

    @Getter
    @Setter
    private List<A> accounts;

    public InvestAccounts(long owner, List<A> accounts) {
        this.owner = owner;
        this.accounts = accounts;
    }

    public OptionalDouble averageRiskValue() {
        return accounts.stream().mapToDouble(Securities::getPercentOfRisky).average();
    }
}
