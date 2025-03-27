package com.nnroad.payroll.dto;

import java.math.RoundingMode;

public class CalculationRules {
    private Integer pfAccuracy;
    private Integer spAccuracy;
    private RoundingMode pfRoundMode;
    private RoundingMode spRoundMode;

    public CalculationRules() {
    }

    public int getPfAccuracy() {
        return pfAccuracy;
    }

    public void setPfAccuracy(int pfAccuracy) {
        this.pfAccuracy = pfAccuracy;
    }

    public int getSpAccuracy() {
        return spAccuracy;
    }

    public void setSpAccuracy(int spAccuracy) {
        this.spAccuracy = spAccuracy;
    }

    public RoundingMode getPfRoundMode() {
        return pfRoundMode;
    }

    public void setPfRoundMode(RoundingMode pfRoundMode) {
        this.pfRoundMode = pfRoundMode;
    }

    public RoundingMode getSpRoundMode() {
        return spRoundMode;
    }

    public void setSpRoundMode(RoundingMode spRoundMode) {
        this.spRoundMode = spRoundMode;
    }

    @Override
    public String toString() {
        return "CalculationRules{" +
                "pfAccuracy=" + pfAccuracy +
                ", spAccuracy=" + spAccuracy +
                ", pfRoundMode=" + pfRoundMode +
                ", spRoundMode=" + spRoundMode +
                '}';
    }
}
