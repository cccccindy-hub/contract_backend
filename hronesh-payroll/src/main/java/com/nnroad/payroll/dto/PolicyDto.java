package com.nnroad.payroll.dto;

import lombok.Data;

@Data
public class PolicyDto {
    private Integer spAccuracy;
    private Integer spRoundMode;
    private Integer pfAccuracy;
    private Integer pfRoundMode;

    public static PolicyDto getDefault() {
        PolicyDto policyDto = new PolicyDto();
        policyDto.spAccuracy = 2;
        policyDto.spRoundMode = 1;
        policyDto.pfAccuracy = 2;
        policyDto.pfRoundMode = 1;

        return policyDto;
    }
}
