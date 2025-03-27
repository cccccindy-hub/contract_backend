package com.nnroad.employee.constants.enums;

import lombok.Getter;

@Getter
public enum EEStatus {
    NOT_ON_BOARD("Not on board"),
    ONGOING("ongoing"),
    OFF_BOARD("Offboard"),
    /**未发送*/
    TOBESENT("To Be Sent"),
    /**已发送*/
    SENT("Sent"),
    /**已登记*/
    REGISTRATION("Registration"),
    /**已转存*/
    TRANSFERRED("Transferred");
    ;

    private final String status;

    EEStatus(String status) {
        this.status = status;
    }
}
