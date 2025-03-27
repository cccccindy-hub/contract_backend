package com.nnroad.common.enums;

import lombok.Getter;

@Getter
public enum SysOrgEnum {
    DEV("dev", "HRSH,HRBJ"),
    PROD_SH("prod-sh", "HRSH,HRBJ"),
    PROD_HK("prod-hk", "HRHK"),
    PROD_FDI_CHINA("prod-fdichina", "FDI"),
    PROD_TOP_FDI_HK("prod-topfdi-hk", "Top FDI"),
    PROD_TOP_FDI_SG("prod-topfdi-sg", "Top FDI SG"),
    ;

    private final String system;

    private final String org;

    SysOrgEnum(String system, String org) {
        this.system = system;
        this.org = org;
    }

    public static String getBySystem(String system) {
        for (SysOrgEnum e : SysOrgEnum.values()) {
            if (e.getSystem().equals(system)) {
                return e.getOrg();
            }
        }
        return "";
    }
}
