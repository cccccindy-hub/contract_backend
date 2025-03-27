package com.nnroad.lead.enums;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum LeadsCodePrefixEnum {
    SH("MCN0001", "HSH"),
    BJ("MCN0002", "HBJ"),
    FDI_CHINA("MCN0004", "FSH"),
    HK("MCN0011", "HHK"),
    NNROAD("MUS0004", "NUS"),
    TOP_FDI_HK("MHK0004", "THK"),
    TOP_FDI_SGP("MSG0001", "TSG"),
    TOP_FDI("MCN0009", "FSH"),
    ;

    private final String memberCode;

    private final String leadCodePrefix;

    LeadsCodePrefixEnum(String memberCode, String leadCodePrefix) {
        this.memberCode = memberCode;
        this.leadCodePrefix = leadCodePrefix;
    }

    public static String getByMemberCode(String code) {
        if (StrUtil.isBlank(code)) {
            return null;
        }
        return Arrays.stream(values())
                .filter(c -> c.memberCode.equals(code))
                .findFirst()
                .map(LeadsCodePrefixEnum::getLeadCodePrefix)
                .orElse(null);

    }
}
