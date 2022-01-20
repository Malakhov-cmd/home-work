package com.sbrf.reboot.cassete;

import com.sbrf.reboot.banknote.Banknote;

import java.util.ArrayList;
import java.util.List;

public class Cassette<A extends Banknote> {
    private List<A> banknoteCollection;

    public Cassette(ArrayList<A> banknoteCollection) {
        this.banknoteCollection = banknoteCollection;
    }

    public int getCountBanknotes() {
        return banknoteCollection.size();
    }
}
