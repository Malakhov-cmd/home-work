package com.sbrf.reboot.atm.cassettes;

import com.sbrf.reboot.banknote.Banknote;
import com.sbrf.reboot.cassete.Cassette;
import com.sbrf.reboot.investition.Securities;
import com.sbrf.reboot.investition.account.InvestAccounts;
import com.sbrf.reboot.investition.typesInvest.Bonds;
import com.sbrf.reboot.investition.typesInvest.DepositaryReceipts;
import com.sbrf.reboot.investition.typesInvest.Futures;
import com.sbrf.reboot.investition.typesInvest.Stock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class CassetteTest {

    class OneHundred extends Banknote {
    }

    class OneThousand extends Banknote {
    }

    @Test
    void getCountBanknotes() {
        OneHundred oneHundred = new OneHundred();

        Cassette<OneHundred> cassette = new Cassette<>(new ArrayList<OneHundred>() {{
            add(oneHundred);
//            add(new OneThousand()); //it will not compile
//            add(new Banknote()); //it will not compile
        }});

        Assertions.assertEquals(1, cassette.getCountBanknotes());
    }

    @Test
    void getCountBanknotesParentType() {
        OneHundred oneHundred = new OneHundred();

        Cassette<Banknote> cassette = new Cassette<>(new ArrayList<Banknote>() {{
            add(oneHundred);
            add(new OneThousand());
            add(new Banknote());
        }});

        Assertions.assertEquals(3, cassette.getCountBanknotes());
    }

    @Test
    void getAverageRiskValue() {
        Bonds bonds = new Bonds();
        bonds.setPercentOfRisky(10);

        DepositaryReceipts depositaryReceipts = new DepositaryReceipts();
        depositaryReceipts.setPercentOfRisky(10);

        Futures futures = new Futures();
        futures.setPercentOfRisky(20);

        Stock stock = new Stock();
        stock.setPercentOfRisky(60);

        InvestAccounts<Securities> investAccounts = new InvestAccounts<>(1L, new ArrayList<Securities>() {{
            add(bonds);
            add(depositaryReceipts);
            add(futures);
            add(stock);
        }});

        Assertions.assertEquals(25, investAccounts.averageRiskValue().getAsDouble());
    }
}