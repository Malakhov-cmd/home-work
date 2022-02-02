package com.sbrf.reboot.functionalinterface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MyFunctionalInterfaceTest {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class BankAccount {
        private Long accountId;
        private BigDecimal amount;
    }

    @FunctionalInterface
    interface FinderBigger<D, T extends Number> {
        boolean compare(D object, T value);
    }


    static class FindBiggerThanValue<D, T extends Number> {
        public List<D> getClients(List<D> accounts, T value, FinderBigger<D, T> finderBigger) {
            if (accounts.isEmpty())
                throw new IllegalArgumentException("The list is empty");

            return accounts.stream()
                    .filter(account -> finderBigger.compare(account, value))
                    .collect(Collectors.toList());
        }
    }

    @Test
    public void amountFilterTest() {
        FinderBigger<BankAccount, BigDecimal> finderBigger = (object, value) ->
                object.amount.compareTo(value) > 0;

        List<BankAccount> accountList = new ArrayList<>(Arrays.asList(
                new BankAccount(1L, new BigDecimal(1_000_000_000)),
                new BankAccount(2L, new BigDecimal(2_000_000_000)),
                new BankAccount(3L, new BigDecimal(5_000_000_00))
        ));

        FindBiggerThanValue<BankAccount, BigDecimal> findBiggerThanValue = new FindBiggerThanValue<>();
        List<BankAccount> importantClient = findBiggerThanValue.getClients(accountList, new BigDecimal(7_000_000_00), finderBigger);

        Assertions.assertEquals(2,importantClient.size());
        Assertions.assertEquals(1L, (long) importantClient.get(0).accountId);
        Assertions.assertEquals(2L, (long) importantClient.get(1).accountId);
    }
}
