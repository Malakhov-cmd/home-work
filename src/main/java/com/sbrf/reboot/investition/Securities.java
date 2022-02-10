package com.sbrf.reboot.investition;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Securities {
    @Getter @Setter
    private double percentOfRisky;
    @Getter @Setter
    private double recommendSumToStart;
    @Getter @Setter
    private boolean needDegreeInEconomics;
    @Getter @Setter
    private Date recommendedTimeToReward;
}
